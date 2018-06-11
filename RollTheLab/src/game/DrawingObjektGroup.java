package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawingObjektGroup {
	//List with all drawing objects of the group
	protected ArrayList<DrawingObjekt> drawingObjekts = new ArrayList<>();
	
	//draws all drawing objects in the list
	public void draw(Graphics g) {
		int length = drawingObjekts.size();
		for(int i = 0; i < length; i++)
		{
			DrawingObjekt curObj = drawingObjekts.get(i);
			curObj.draw(g);
		}
	}
}
