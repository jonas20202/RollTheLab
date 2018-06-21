package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class GameFrame extends JFrame{
	public int width = 1200;
	public int height = 900;
	
	private JPanel drawingPanel;
	//Constructor for the Frame
	public GameFrame() {
		setResizable(false);
		drawingPanel = new DrawingPanel();
		drawingPanel.setPreferredSize(new Dimension(width, height));
		
		Container container = getContentPane();
		container.add(drawingPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("RollTheLab");
		setVisible(true);
	}
}
