package game;

import general.DrawingArc;
import general.DrawingLine;
import general.DrawingObjektGroup;
import general.Vektor;

public class LabGroup extends DrawingObjektGroup{
	
	public LabGroup(String strFile)
	{
		//drawingObjekts.add(new DrawingArc(new Vektor(0, 500), 300, 0, 90));
		drawingObjekts.add(new DrawingLine(new Vektor(0,400), new Vektor(400, 450)));
		drawingObjekts.add(new DrawingLine(new Vektor(0, 550), new Vektor(700, 550)));
		//drawingObjekts.add(new DrawingLine(new Vektor(65,370), new Vektor(400, 430)));
	}
}
