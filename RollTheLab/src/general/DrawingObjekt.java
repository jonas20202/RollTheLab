package general;

import java.awt.Graphics;

public abstract class DrawingObjekt {
	boolean isAboveOfObject = false;

	public boolean isAboveOfObject(){ return isAboveOfObject;}
	//Redraws this Objekt and the complete DrawingPanel;
	public abstract void draw(Graphics g);
	
	//Returns the Type of this Objekt
	abstract DrawingType GetDrawingType();
	
	public abstract void move(Vektor move);
	public abstract boolean checkCollision(DrawingObjekt check, boolean onlyCrossing);
	public abstract Vektor getMoveVek();
}
