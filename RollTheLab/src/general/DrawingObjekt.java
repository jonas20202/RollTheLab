package general;

import java.awt.Graphics;

public abstract class DrawingObjekt {
	//Redraws this Objekt and the complete DrawingPanel;
	public abstract void draw(Graphics g);
	
	//Returns the Type of this Objekt
	abstract DrawingType GetDrawingType();
	
	public abstract void move(Vektor move);
	public abstract boolean collidate(DrawingObjekt check);
	public abstract Vektor getMoveVek();
}
