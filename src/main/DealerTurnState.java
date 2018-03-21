package main;

public class DealerTurnState implements GameState {

	private Game game;
	
	public DealerTurnState(Game game) {
		this.game = game;
	}
	
	@Override
	public void startGame() {
		return;
		
	}

	@Override
	public void endPlayerTurn() {
		return;
		
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
