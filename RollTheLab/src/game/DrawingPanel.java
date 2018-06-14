package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel{	
	//Constructor
	public DrawingPanel() {
//		drawingObjects.add(new DrawingArc(100, 100, 100, 0, 360));
	}
	
	//@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		
//		int length = drawingObjects.size();
		int length = 0;
		for(int i = 0; i < length; i++)
		{
//			DrawingObjekt curObj = drawingObjects.get(i);
//			curObj.draw(g);
		}
		
		this.repaint();
	}
	
	
}
