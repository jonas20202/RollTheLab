package game;

import general.DrawingArc;
import general.DrawingLine;
import general.DrawingObjektGroup;
import general.Vektor;

public class LabGroup extends DrawingObjektGroup{
	
	public LabGroup(String strFile)
	{
		//drawingObjekts.add(new DrawingArc(new Vektor(0, 500), 300, 0, 90));
		drawingObjekts.add(new DrawingLine(new Vektor(0,400), new Vektor(500, 450)));
	}
}
