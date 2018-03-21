package main;


public class Dealer extends Player {

	public Dealer() {
		super();
	}
	
	public void flipCardsFaceUp() {
		for(Card c : cardsInHand) {
			c.flipCardFaceUp();
		}
	}
}
