package drawingObjects.general;

import general.math.geometry.GeoPoint;
import general.math.calculation.Vektor;

import java.awt.Graphics;

public abstract class DrawingObject {

	//Redraws this Object and the complete DrawingPanel;
	public abstract void draw(Graphics g);

	//Returns true if the Object collidate with the checkObj
	public abstract boolean collidate( DrawingObject checkObj);

	//Returns true if the Object is crossing with the checkObj
	public abstract boolean crossing( DrawingObject checkObj);

	//Rotate the Object around the mid Point right-oriented
	public abstract void rotate( double rotateAngle, GeoPoint midPoint);

	//Moves the Object with the given Vektor
	public abstract void move( Vektor move);
}
