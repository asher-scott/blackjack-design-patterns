package main.UI;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameLogPanel extends JScrollPane {
	private JTextArea gameLog;
	
	public GameLogPanel() {
		gameLog = new JTextArea("Welcome to Blackjack!\nPress the deal button to play!\n");
		gameLog.setColumns(20);
		gameLog.setEditable(false);
		this.setViewportView(gameLog);
		
	}
	
	public void log(Object arg) {
		gameLog.append(arg+"\n");
	}
	
	public void reset() {
		gameLog.setText("Welcome to Blackjack!\nPress the deal button to play!\n");
	}
}
