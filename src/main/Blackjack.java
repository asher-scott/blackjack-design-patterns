package main;

import main.UI.BlackJackUI;

public class Blackjack {
	
	public static void main(String[] args) {
		Game game = new Game();
		BlackJackUI blackJackUI = new BlackJackUI(game);
		game.addObserver(blackJackUI);
		game.run();
	}
}
