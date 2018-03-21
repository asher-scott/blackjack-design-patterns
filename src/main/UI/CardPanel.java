package main.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.Card;


public class CardPanel extends JPanel {
	private int imageWidth = 113;
	private int imageHeight = 157;
	private ArrayList<Card> cards;
	
	public CardPanel(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			for(int i = 0; i < cards.size(); i++) {
				BufferedImage cardImage =
						ImageIO.read(new File(cards.get(i).getImagePath()));
				g.drawImage(cardImage, ((imageWidth+3)*i), 50, imageWidth, imageHeight, this);
			}
		} catch(IOException ex) {
			System.out.println(ex);
		}
	}
}
