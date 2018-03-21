package main;

public interface GameState {
	public void startGame();
	public void endPlayerTurn();
	public void endRound();
	public void resetGame();
}

