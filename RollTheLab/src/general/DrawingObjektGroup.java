package general;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawingObjektGroup {
	//List with all drawing objects of the group
	public ArrayList<DrawingObjekt> drawingObjekts = new ArrayList<>();
	
	//draws all drawing objects in the list
	public void draw(Graphics g) {
		int length = drawingObjekts.size();
		for(int i = 0; i < length; i++)
		{
			DrawingObjekt curObj = drawingObjekts.get(i);
			if (!curObj.isBounceActivator())
				curObj.draw(g);
		}
	}
	
	public DrawingObjektGroup getCollidateObjekts(DrawingObjekt checkObj, boolean onlyCrossing, boolean withBounceChange)
	{
		int length = drawingObjekts.size();
		DrawingObjektGroup collidateGroup = new DrawingObjektGroup();
		for(int i = 0; i < length; i++)
		{
			DrawingObjekt curObj = drawingObjekts.get(i);
			if(curObj.checkCollision(checkObj, onlyCrossing)) {
				if(curObj.isBounceActivator()) {
					if (withBounceChange)
						collidateGroup.drawingObjekts.add(curObj);
				}else
					collidateGroup.drawingObjekts.add(curObj);
			}
		}
		return collidateGroup;
	}

	public void rotate(double angle, Vektor midPoint){
		int length = drawingObjekts.size();
		for(int i = 0; i < length; i++)
			drawingObjekts.get(i).rotate(angle, midPoint);
	}

}
