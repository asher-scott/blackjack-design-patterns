package main.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Game;

public class ActionBarPanel extends JPanel {
	private Game game;
	private GameLogPanel gameLogPanel;
	private JPanel rootPanel;
	
	private JButton dealButton;
	private JButton hitButton;
	private JButton stayButton;
	private JButton resetButton;
	
	public ActionBarPanel(Game game, GameLogPanel gameLogPanel, JPanel rootPanel) {
		this.game = game;
		this.gameLogPanel = gameLogPanel;
		this.rootPanel = rootPanel;
		
		dealButton = new JButton("Deal");
		hitButton = new JButton("Hit");
		stayButton = new JButton("Stay");
		resetButton = new JButton("Reset");
		
		dealButton.addActionListener(new DealActionListener());
		hitButton.addActionListener(new HitActionListener());
		stayButton.addActionListener(new StayActionListener());
		resetButton.addActionListener(new ResetActionListener());
		
		this.add(dealButton);
		this.add(hitButton);
		this.add(stayButton);
		this.add(resetButton);
	}
	
	private class DealActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.getCurrentState().startGame();
			if(game.player.hasBlackJack()) {
				gameLogPanel.log("Player has Blackjack!");
				gameLogPanel.log("Dealer's Turn");
				game.getCurrentState().endPlayerTurn();
			} else {
				gameLogPanel.log("Player's turn!");
			}
			rootPanel.repaint();
		}
	}

	private class HitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(game.getCurrentState().equals(game.getPlayerTurnState())) {
				gameLogPanel.log("Player Hits!");
				game.drawCardAndGiveToPlayer();
				rootPanel.repaint();
			}
		}
	}
	
	private class StayActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(game.getCurrentState().equals(game.getPlayerTurnState())) {
				gameLogPanel.log("Player Stays!");
				gameLogPanel.log("Dealer's Turn");
				game.getCurrentState().endPlayerTurn();
				rootPanel.repaint();
			}	
		}	
	}
	
	private class ResetActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.getCurrentState().resetGame();
			rootPanel.repaint();
		}
	}
}
