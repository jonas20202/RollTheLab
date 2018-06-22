package general;

import java.awt.Graphics;

public abstract class DrawingObjekt {
	protected Vektor moveOnLine = new Vektor(0,0);

	public Vektor getMoveOnLineVec() {return moveOnLine;}

	//Redraws this Objekt and the complete DrawingPanel;
	public abstract void draw(Graphics g);
	
	//Returns the Type of this Objekt
	abstract DrawingType GetDrawingType();
	
	public abstract void move(Vektor move);
	public abstract void rotate(double angle, Vektor midPoint);
	public abstract boolean checkCollision(DrawingObjekt check, boolean onlyCrossing);
	public abstract Vektor getMoveVek(Vektor reverencePoint);
}
