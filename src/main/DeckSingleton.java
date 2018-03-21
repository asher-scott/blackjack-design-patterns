package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Singleton class for a deck of cards in blackjack.
 * Uses the Iterator Pattern to iterate through the cards in the deck.
 */
public class DeckSingleton implements Iterator<Card> {
	
	final static String[] cardCodes = {
			"AC", "AD", "AH", "AS",
			"2C", "2D", "2H", "2S",
			"3C", "3D", "3H", "3S",
			"4C", "4D", "4H", "4S",
			"5C", "5D", "5H", "5S",
			"6C", "6D", "6H", "6S",
			"7C", "7D", "7H", "7S",
			"8C", "8D", "8H", "8S",
			"9C", "9D", "9H", "9S",
			"0C", "0D", "0H", "0S",
			"JC", "JD", "JH", "JS",
			"QC", "QD", "QH", "QS",
			"KC", "KD", "KH", "KS",
	};
	
	private final int totalCards = 52;
	private int iteratorPosition = 0;
	private ArrayList<Card> cards;
	
	private static DeckSingleton uniqueInstance;
	
	private DeckSingleton() {
		cards = new ArrayList<Card>();
		
		
		for(String c : cardCodes) {
			cards.add(new Card(c));
		}

		this.shuffle();
		
	}
	
	public static DeckSingleton getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new DeckSingleton();
		}
		
		return uniqueInstance;
	}
	
	
	public void shuffle() {
		Collections.shuffle(cards);
		iteratorPosition = 0;
	}

	@Override
	public boolean hasNext() {
		if(iteratorPosition < totalCards)
			return true;
		else
			return false;
	}

	@Override
	public Card next() {
		Card c = cards.get(iteratorPosition);
		iteratorPosition++;
		
		return c;
	}
}
