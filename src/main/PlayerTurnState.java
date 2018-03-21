package main;

public class PlayerTurnState implements GameState {

	private Game game;
	
	public PlayerTurnState(Game game) {
		this.game = game;
	}
	
	@Override
	public void startGame() {
		return;
	}

	@Override
	public void endPlayerTurn() {
		game.flipDealerCardsFaceUp();
		game.setState(game.getDealerTurnState());
	}

	@Override
	public void endRound() {
		game.setState(game.getRoundEndState());
	}

	@Override
	public void resetGame() {
		return;
	}

}
