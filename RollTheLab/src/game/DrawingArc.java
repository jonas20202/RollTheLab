package game;

import java.awt.Color;
import java.awt.Graphics;

public class DrawingArc extends DrawingObjekt{
	private int x, y, radius;
	private double startAngle, arcAngle;
	
	public DrawingArc(int midX, int midY, int radius) {
		//this.DrawingArc(midX, midY, radius, 0.0, 360.0);
	}
	
	public DrawingArc(int midX, int midY, int radius, double startAngle, double arcAngle) {
		this.x = midX;
		this.y = midY;
		this.radius = radius;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawArc(x - radius, y - radius, radius, radius, (int)startAngle, (int)arcAngle);
	}

	@Override
	public DrawingType GetDrawingType() { return DrawingType.DRAWING_TYPE_ARC; }
}
