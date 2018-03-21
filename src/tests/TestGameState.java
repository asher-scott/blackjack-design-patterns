package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Game;

class TestGameState {
	
	Game game;
	
	TestGameState() {
		game = new Game();
	}

	@Test
	void testInititalStateCorrect() {
		assertEquals(game.getCurrentState(), game.getStartState());
	}
	
	@Test
	void testSetState() {
		game.setState(game.getPlayerTurnState());
		assertEquals(game.getCurrentState(), game.getPlayerTurnState());
	}
	
	@Test 
	void testStartState() {
		game.setState(game.getStartState());
		
		game.getCurrentState().endPlayerTurn();
		assertEquals(game.getCurrentState(), game.getStartState());
		
		game.getCurrentState().resetGame();
		assertEquals(game.getCurrentState(), game.getStartState());
		
		game.getCurrentState().endRound();
		assertEquals(game.getCurrentState(), game.getStartState());
		
		game.getCurrentState().startGame();
		assertEquals(game.getCurrentState(), game.getPlayerTurnState());		
	}
	
	@Test 
	void testPlayerTurnState() {
		game.setState(game.getPlayerTurnState());
		
		game.getCurrentState().startGame();
		assertEquals(game.getCurrentState(), game.getPlayerTurnState());
		
		game.getCurrentState().resetGame();
		assertEquals(game.getCurrentState(), game.getPlayerTurnState());
		
		game.getCurrentState().endPlayerTurn();
		assertEquals(game.getCurrentState(), game.getDealerTurnState());
		
		game.setState(game.getPlayerTurnState());
		
		game.getCurrentState().endRound();
		assertEquals(game.getCurrentState(), game.getRoundEndState());
		
				
	}
	
	@Test 
	void testDealerTurnState() {
		game.setState(game.getDealerTurnState());
		
		game.getCurrentState().endPlayerTurn();
		assertEquals(game.getCurrentState(), game.getDealerTurnState());
		
		game.getCurrentState().resetGame();
		assertEquals(game.getCurrentState(), game.getDealerTurnState());
		
		game.getCurrentState().startGame();
		assertEquals(game.getCurrentState(), game.getDealerTurnState());
		
		game.getCurrentState().endRound();
		assertEquals(game.getCurrentState(), game.getRoundEndState());	
	}
	
	@Test 
	void testRoundEndState() {
		game.setState(game.getRoundEndState());
		
		game.getCurrentState().endPlayerTurn();
		assertEquals(game.getCurrentState(), game.getRoundEndState());
		
		game.getCurrentState().endRound();
		assertEquals(game.getCurrentState(), game.getRoundEndState());
		
		game.getCurrentState().startGame();
		assertEquals(game.getCurrentState(), game.getRoundEndState());
		
		game.getCurrentState().resetGame();
		assertEquals(game.getCurrentState(), game.getStartState());
	}

}
