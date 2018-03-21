package main;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
	private GameState startState;
	private GameState playerTurnState;
	private GameState dealerTurnState;
	private GameState roundEndState;
	private GameState currentState;
	
	public Player player;
	public Dealer dealer;
	private DeckSingleton deck;
	
	
	public Game() {
		startState = new StartState(this);
		playerTurnState = new PlayerTurnState(this);
		dealerTurnState = new DealerTurnState(this);
		roundEndState = new RoundEndState(this);
		currentState = startState;
		
		player = new Player();
		dealer = new Dealer();
		deck = DeckSingleton.getInstance();
	}
	
	public void setState(GameState newState) {
		currentState = newState;
	}
	
	public GameState getCurrentState() {
		return currentState;
	}
	
	public GameState getStartState() {
		return this.startState;
	}
	
	public GameState getPlayerTurnState() {
		return this.playerTurnState;
	}
	
	public GameState getDealerTurnState() {
		return this.dealerTurnState;
	}
	
	public GameState getRoundEndState() {
		return this.roundEndState;
	}
	
	
	public void sendGameLogUpdate(String logMessage) {
		setChanged();
		notifyObservers(logMessage);
	}
	
	public void sendUIRepaintUpdate() {
		setChanged();
		notifyObservers("repaint");
	}
	
	public void sendGameLogResetUpdate() {
		setChanged();
		notifyObservers("reset game log");
	}
	
	public void run() {
		boolean running = true;
		while(running) {
			
			if(currentState.equals(playerTurnState)) {
				if(player.hasBustedHand()) {
					sendGameLogUpdate("Player Busted!\n"+getWinner());
					currentState.endRound();
				}
				
				if(player.hasBlackJack()) {
					sendGameLogUpdate("Player has 21!\n"+getWinner());
					currentState.endRound();
				}
			}
			
			if(currentState.equals(dealerTurnState)) {
				if(dealerShouldEndTurn()) {
					if(dealer.hasBlackJack()) {
						sendGameLogUpdate("Dealer has Blackjack!\n"+getWinner());
					} else if(dealer.hasBustedHand()) {
						sendGameLogUpdate("Dealer Busted!\n"+getWinner());
					} else {
						sendGameLogUpdate("Dealer Stays!\n"+getWinner());
					}
					currentState.endRound();
					
				}
				
				if(!dealerShouldEndTurn()) {
					sendGameLogUpdate("Dealer Hits!");
					drawCardAndGiveToDealer(false);
					if(dealer.hasBlackJack()) {
						sendGameLogUpdate("Dealer has 21!\n"+getWinner());
						currentState.endRound();
					}
					if(dealer.hasBustedHand()) {
						sendGameLogUpdate("Dealer Busts!\n"+getWinner());
						currentState.endRound();
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sendUIRepaintUpdate();
		}
	}
	
	public void reset() {
		shuffleDeck();
		player.removeCardsFromHand();
		dealer.flipCardsFaceUp(); // In case the player busts before the dealers cards are flipped.
		dealer.removeCardsFromHand();
	}
	
	public void dealCards() {
		for(int i = 0; i < 2; i++) {
			drawCardAndGiveToPlayer();
			if(i == 0) {
				drawCardAndGiveToDealer(true);
			} else {
				drawCardAndGiveToDealer(false);
			}
		}
	}
	
	public void drawCardAndGiveToDealer(boolean faceDown) {
		if(deck.hasNext()) {
			Card c = deck.next();
			if(faceDown)
				c.flipCardFaceDown();
			dealer.addCardToHand(c);
		}
	}
	
	public void drawCardAndGiveToPlayer() {
		if(deck.hasNext()) {
			Card c = deck.next();
			player.addCardToHand(c);
		}
	}
	
	public void shuffleDeck() {
		this.deck.shuffle();
	}
	
	public ArrayList<Card> getPlayersCards() {
		return player.getCards();
	}
	
	public ArrayList<Card> getDealersCards() {
		return dealer.getCards();
	}
	
	public boolean dealerShouldEndTurn() {
		if(dealer.hasBlackJack() || dealer.hasBustedHand() || ((dealer.getHandValue() >= 17) && (dealer.getHandValue() >= player.getHandValue()))){
			return true;
		}
		return false;
	}

	public void flipDealerCardsFaceUp() {
		dealer.flipCardsFaceUp();
	}
	
	public String getWinner() {
		if(player.hasBustedHand() && !dealer.hasBustedHand()) {
			return "Dealer Wins!\nPress reset to play again!";
		}
		else if(!player.hasBustedHand() && dealer.hasBustedHand()) {
			return "Player Wins!\nPress reset to play again!";
		}
		else if(player.getHandValue() > dealer.getHandValue()) {
			return "Player Wins!\nPress reset to play again!";
		}
		else if(player.getHandValue() < dealer.getHandValue()) {
			return "Dealer Wins!\nPress reset to play again!";
		} else {
			return "It's a tie!\nPress reset to play again!";
		}
	}
}
