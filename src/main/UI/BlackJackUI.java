package main.UI;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import main.Game;

public class BlackJackUI extends JFrame implements Observer {
	private Game game;
	private JPanel rootPanel;
	private TablePanel tablePanel;
	private GameLogPanel gameLogPanel;
	private JPanel actionBarPanel;
	

	public BlackJackUI(Game game) {
		this.game = game;
		
		rootPanel = new JPanel(new BorderLayout());
		tablePanel = new TablePanel(this.game);
		gameLogPanel = new GameLogPanel();
		actionBarPanel = new ActionBarPanel(this.game, gameLogPanel, rootPanel);
		
		rootPanel.add(tablePanel, BorderLayout.CENTER);
		rootPanel.add(actionBarPanel, BorderLayout.PAGE_END);
		rootPanel.add(gameLogPanel, BorderLayout.LINE_END);
		
		this.setTitle("Blackjack");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setContentPane(rootPanel);
		this.setVisible(true);
	}
	
	public JPanel getRootPanel() {
		return this.rootPanel;
	}
	
	public GameLogPanel getGameLogPanel() {
		return this.gameLogPanel;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg == "repaint")
			this.rootPanel.repaint();
		else if(arg == "reset game log")
			this.gameLogPanel.reset();
		else {
			this.gameLogPanel.log(arg);
		}
	}
}
