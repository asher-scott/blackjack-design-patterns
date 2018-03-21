package main;

import java.util.ArrayList;

public class Player {
	protected ArrayList<Card> cardsInHand;
	
	public Player() {
		cardsInHand = new ArrayList<Card>();
	}
	
	public void addCardToHand(Card c) {
		cardsInHand.add(c);
	}
	
	public void removeCardsFromHand() {
		cardsInHand.clear();
	}
	
	public int getHandValue() {
		int numberOfAces = 0;
		
		int handValue = 0;
		
		for(Card c : cardsInHand) {
			if(c.getCardCode().contains("A")) numberOfAces++;
			else if(c.getCardCode().contains("2")) handValue += 2;
			else if(c.getCardCode().contains("3")) handValue += 3;
			else if(c.getCardCode().contains("4")) handValue += 4;
			else if(c.getCardCode().contains("5")) handValue += 5;
			else if(c.getCardCode().contains("6")) handValue += 6;
			else if(c.getCardCode().contains("7")) handValue += 7;
			else if(c.getCardCode().contains("8")) handValue += 8;
			else if(c.getCardCode().contains("9")) handValue += 9;
			else if(c.getCardCode().contains("0")) handValue += 10;
			else if(c.getCardCode().contains("J")) handValue += 10;
			else if(c.getCardCode().contains("Q")) handValue += 10;
			else if(c.getCardCode().contains("K")) handValue += 10;
		}
		
		// add the aces in hand to total value after adding all others.
		for(int i = 0; i < numberOfAces; i++) {
			if(handValue + 11 > 21) {
				handValue += 1;
			} else {
				handValue += 11;
			}
		}
		
		return handValue;
	}
	
	public boolean hasBustedHand() {
		if(getHandValue() > 21)
			return true;
		else
			return false;
	}
	
	public boolean hasBlackJack() {
		if(getHandValue() == 21) 
			return true;
		else return false;
	}
	
	public ArrayList<Card> getCards() {
		return cardsInHand;
	}
	
	
	public void displayCardsInHand() {
		for(Card c : cardsInHand) {
			System.out.print(c + ", ");
		}
		System.out.println("");
	}
}
