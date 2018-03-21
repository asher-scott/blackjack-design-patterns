package main;

public class StartState implements GameState {

	private Game game;
	
	public StartState(Game game) {
		this.game = game;
	}
	
	@Override
	public void startGame() {
		game.dealCards();
		game.setState(game.getPlayerTurnState());
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
		return;
	}

}
