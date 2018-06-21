package general;

import java.awt.Color;
import java.awt.Graphics;

public class DrawingArc extends DrawingObjekt{
	private Vektor midPoint;
	private int radius;
	private double startAngle, arcAngle;

	public DrawingArc(Vektor mid, int radius, double startAngle, double arcAngle) {
		midPoint = mid;
		this.radius = radius;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}

	public DrawingArc(DrawingArc copyArc) {
		midPoint = new Vektor(copyArc.getMidPoint());
		radius = copyArc.getRadius();
		startAngle = copyArc.startAngle;
		arcAngle = copyArc.arcAngle;
	}
	
	public Vektor getMidPoint() {
		return midPoint;
	}
	
	public int getRadius() { return radius;}
	

	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawArc((int)midPoint.x - radius, (int)midPoint.y - radius, 2*radius, 2*radius, (int)startAngle, (int)arcAngle);
	}

	@Override
	public DrawingType GetDrawingType() { return DrawingType.DRAWING_TYPE_ARC; }


	@Override
	public void move(Vektor move) {
		midPoint.Add(move);
	}

	@Override
	public void rotate(double angle, Vektor midPoint) {

	}


	@Override
	public boolean checkCollision(DrawingObjekt check, boolean onlyCrossing) {
		//Wenn das zu berprfende Objekt ein Bogen ist
		if(check.GetDrawingType() == DrawingType.DRAWING_TYPE_ARC)
		{
			DrawingArc checkArc = (DrawingArc) check;
			Vektor diffVec = new Vektor(checkArc.midPoint.x - midPoint.x, checkArc.midPoint.y - midPoint.y);
			double diffLen = Math.abs(diffVec.getLen());
			if(diffLen < (radius + checkArc.radius))
				return true;
		}
		return false;
	}

	@Override
	public Vektor getMoveVek() {
		
		return null;
	}
}
