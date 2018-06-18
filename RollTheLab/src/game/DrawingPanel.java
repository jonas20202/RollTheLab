package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel{	
	LabGroup drawingObjects;
	Ball ball;
	//Constructor
	public DrawingPanel() {
		drawingObjects = new LabGroup("test");
		ball = new Ball(drawingObjects);
//		drawingObjects.add(new DrawingArc(100, 100, 100, 0, 360));
	}
	
	//@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
//		for(int i = 0; i < 1000; i++)
//		{
//			g.drawArc(i, i, i+50, i+50, 0, 360);
//		}
		//DrawingArc st = new DrawingArc(200, 200, 100, 0, 360);
		//st.draw(g);
		drawingObjects.draw(g);
		ball.draw(g);
		try {
			Thread.sleep(1);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		this.repaint();
	}
	
	
}
