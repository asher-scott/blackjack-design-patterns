package main;


public class Card {	
	private String cardCode;
	private boolean faceDown;
	
	public Card(String cardCode) {
		this.cardCode = cardCode;
	}
	
	public String getCardCode() {
		return cardCode;
	}
	
	public String getImagePath() {
		String baseImagePath = "assets/img/";
		if(faceDown) {
			return baseImagePath + "CardBack.png";
		} else {
			return baseImagePath + cardCode + ".png";
		}
	}
	
	public boolean isFaceDown() {
		return faceDown;
	}
	
	public void flipCardFaceDown() {
		faceDown = true;
	}
	
	public void flipCardFaceUp() {
		faceDown = false;
	}
	
	@Override
	public String toString() {
		return cardCode + " Face down: " + faceDown;
	}
}
