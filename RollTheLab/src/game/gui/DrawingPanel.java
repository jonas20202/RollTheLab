package game.gui;

import drawingObjects.Ball;
import drawingObjects.labyrinth.LabGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingPanel extends JPanel{
	public static double drawingAngle = 0;
	LabGroup drawingObjects;
	Ball ball;
	//Constructor
	public DrawingPanel() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent ke) {
				synchronized (GameKeyListener.class) {
					switch (ke.getID()) {
						case KeyEvent.KEY_PRESSED:
							if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
								GameKeyListener.isTurnRightPressed = true;
							}else
							if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
								GameKeyListener.isTurnLeftPressed = true;
							}
							break;

						case KeyEvent.KEY_RELEASED:
							if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
								GameKeyListener.isTurnRightPressed = false;
							}else
							if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
								GameKeyListener.isTurnLeftPressed = false;
							}

							break;

					}
					return false;
				}
			}
		});
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
