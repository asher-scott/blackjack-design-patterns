package main;

public class RoundEndState implements GameState {

	private Game game;
	
	public RoundEndState(Game game) {
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
		return;
	}

	@Override
	public void resetGame() {
		game.reset();
		game.sendGameLogResetUpdate();
		game.setState(game.getStartState());
	}


}
