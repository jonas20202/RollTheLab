package game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import general.DrawingArc;
import general.DrawingObjektGroup;
import general.Vektor;

public class Ball extends DrawingObjektGroup{
	LabGroup lab;
	DrawingArc ball;
	public Ball(LabGroup lab) {
		ball = new DrawingArc(new Vektor(100,1), 30, 0, 360);
		this.lab = lab; 
		drawingObjekts.add(ball);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		//drawingObjekts.get(0).move(10, 0);
		if(lab.getCollidateObjekts(ball).drawingObjekts.size() == 0)
			ball.move(new Vektor(0, 1));	
	}
}
