package drawingObjects.general;

import general.math.calculation.Vektor;


import java.awt.Graphics;
import java.util.ArrayList;

public class DrawingObjectGroup extends ArrayList<DrawingObject>{
	
	//calls the draw function of all drawing objects in the list
	public void draw(Graphics g) {
		int length = size();
		for(int i = 0; i < length; i++)
		{
			DrawingObject curObj = get(i);
			curObj.draw(g);
		}
	}
	
	public DrawingObjectGroup getCollissionObjects(DrawingObject checkObj)
	{
		int length = size();
		DrawingObjectGroup collidateGroup = new DrawingObjectGroup();
		for(int i = 0; i < length; i++)
		{
			DrawingObject curObj = get(i);
			if(curObj.collidate(checkObj)) {
					collidateGroup.add(curObj);
			}
		}
		return collidateGroup;
	}

	public DrawingObjectGroup getCrossingObjects(DrawingObject checkObj)
	{
		int length = size();
		DrawingObjectGroup crossingGroup = new DrawingObjectGroup();
		for(int i = 0; i < length; i++)
		{
			DrawingObject curObj = get(i);
			if(curObj.crossing(checkObj)) {
				crossingGroup.add(curObj);
			}
		}
		return crossingGroup;
	}

	public void rotate(double angle, Point midPoint){
		int length = size();
		for(int i = 0; i < length; i++)
			get(i).rotate(angle, midPoint);
	}

	public void move(Vektor move){
		int length = size();
		for(int i = 0; i < length; i++)
			get(i).move(move);
	}

}
