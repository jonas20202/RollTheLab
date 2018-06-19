package game;

import general.DrawingArc;
import general.DrawingLine;
import general.DrawingObjektGroup;
import general.Vektor;

public class LabGroup extends DrawingObjektGroup{
	
	public LabGroup(String strFile)
	{
		//drawingObjekts.add(new DrawingArc(new Vektor(0, 500), 300, 0, 90));
		drawingObjekts.add(new DrawingLine(new Vektor(0,400), new Vektor(400, 700)));
		drawingObjekts.add(new DrawingLine(new Vektor(0, 650), new Vektor(1000, 500)));
		//drawingObjekts.add(new DrawingLine(new Vektor(500, 750), new Vektor(1500, 550)));
		drawingObjekts.add(new DrawingLine(new Vektor(75,365), new Vektor(800, 860)));
	}
}
