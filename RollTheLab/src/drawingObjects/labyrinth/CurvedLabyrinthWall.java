package drawingObjects.labyrinth;

import drawingObjects.general.DrawingObject;
import general.DrawingType;
import general.math.calculation.Vektor;

import java.awt.Color;
import java.awt.Graphics;

public class CurvedLabyrinthWall extends LabyrinthObject {
	private Vektor midPoint;
	private int radius;
	private double startAngle, endAngle;

	public CurvedLabyrinthWall(Vektor mid, int radius, double startAngle, double arcAngle) {
		midPoint = mid;
		this.radius = radius;
		this.startAngle = startAngle;
		this.endAngle = arcAngle;
	}

	public CurvedLabyrinthWall(CurvedLabyrinthWall copyArc) {
		midPoint = new Vektor(copyArc.getMidPoint());
		radius = copyArc.getRadius();
		startAngle = copyArc.startAngle;
		endAngle = copyArc.endAngle;
	}
	
	public Vektor getMidPoint() {
		return midPoint;
	}
	
	public int getRadius() { return radius;}
	

	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		int startAngle = (int)this.startAngle;
		g.drawArc((int)midPoint.x - radius, (int)midPoint.y - radius, 2*radius, 2*radius, (int)startAngle, (int)(endAngle - this.startAngle));
	}

	@Override
	public DrawingType GetDrawingType() { return DrawingType.DRAWING_TYPE_ARC; }


	@Override
	public void move(Vektor move) {
		midPoint.Add(move);
	}

	@Override
	public void rotate(double angle, Vektor midPoint) {
		this.midPoint.rotateAroundMid(angle, midPoint);
	}


	@Override
	public boolean checkCollision(DrawingObject check, boolean onlyCrossing) {
		//Wenn das zu berprfende Objekt ein Bogen ist
		if(check.GetDrawingType() == DrawingType.DRAWING_TYPE_ARC)
		{
			CurvedLabyrinthWall checkArc = (CurvedLabyrinthWall) check;
			Vektor diffVec = new Vektor(checkArc.midPoint.x - midPoint.x, checkArc.midPoint.y - midPoint.y);
			double diffLen = Math.abs(diffVec.getLen());
			diffVec.setLen(diffLen - (radius + checkArc.radius)-1);
			moveOnLine = diffVec;
			if(diffLen <= (radius + checkArc.radius)) {
				if (onlyCrossing) {
					if (diffLen < ((radius + checkArc.radius)))
						return true;
					else
						return false;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public Vektor getMoveVek(Vektor reverencePoint) {
		return new Vektor(0,0);
//		Vektor reverencePointOnNull = reverencePoint.minusVec(midPoint);
//		reverencePointOnNull.setLen(radius);
//		Vektor newMovePoint = new Vektor(reverencePointOnNull);
//		newMovePoint.rotate(((2*Math.PI)/(360*5)));
//		return newMovePoint.minusVec(reverencePointOnNull);
	}
}
