package main.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;


import main.Game;

public class TablePanel extends JPanel {
	private Game game;
	private CardPanel dealerCards;
	private CardPanel playerCards;
	
	public TablePanel(Game game) {
		this.game = game;
	
		this.setLayout(new GridLayout(2,1));
		dealerCards = new CardPanel(this.game.getDealersCards());
		playerCards = new CardPanel(this.game.getPlayersCards());
		
		dealerCards.setBackground(new Color(7, 121, 7));
		playerCards.setBackground(new Color(7, 121, 7));

		
		this.add(dealerCards);
		this.add(playerCards);	
	}
}
