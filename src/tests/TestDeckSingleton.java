package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Card;
import main.DeckSingleton;

class TestDeckSingleton {

	@Test
	void testGetInstance() {
		DeckSingleton deck = DeckSingleton.getInstance();
		DeckSingleton anotherDeck = DeckSingleton.getInstance();
		assertEquals(deck, anotherDeck);
	}
	
	@Test
	void testShuffle() {
		DeckSingleton deck = DeckSingleton.getInstance();
		deck.shuffle(); // reset the deck iterator to zero
		
		Card cardPreShuffle = deck.next();
		deck.shuffle();
		Card cardPostShuffle = deck.next();
		
		assertNotSame(cardPreShuffle, cardPostShuffle);
	}
	
	@Test
	void testDeckIterator() {
		DeckSingleton deck = DeckSingleton.getInstance();
		ArrayList<Card> cards = new ArrayList<Card>();
		
		while(deck.hasNext()) {
			cards.add(deck.next());
		}
		
		assertEquals(cards.size(), 52);
	}

}
