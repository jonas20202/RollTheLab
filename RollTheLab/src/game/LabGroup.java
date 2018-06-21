package game;

import general.*;

import java.awt.*;

public class LabGroup extends DrawingObjektGroup{
	private double rotate;
	private Vektor midPoint;
	boolean turnRigtht = false;

	public LabGroup(String strFile)
	{
		midPoint = new Vektor(600, 450);
        drawingObjekts.add(new DrawingLine(new Vektor(0,100), new Vektor(150, 110)));
        //drawingObjekts.add(new DrawingLine(new Vektor(150, 106), new Vektor(150, 151)));
		//drawingObjekts.add(new DrawingArc(new Vektor(0, 300), 300, 0, 90));
        //drawingObjekts.add(new DrawingLine(new Vektor(0,300), new Vektor(400, 301)));
        //drawingObjekts.add(new DrawingLine(new Vektor(0,350), new Vektor(1000, 350)));
		drawingObjekts.add(new DrawingLine(new Vektor(500, 400), new Vektor(1000, 395)));
		drawingObjekts.add(new DrawingLine(new Vektor(00, 300), new Vektor(1500, 150)));
		drawingObjekts.add(new DrawingLine(new Vektor(0,265), new Vektor(400, 400)));
	}

	@Override
	public void draw(Graphics g) {
		if(GameKeyListener.isWPressed())
			startTurnRight();
		else
		super.draw(g);

	}

	public void startTurnRight(){
	    rotate = 0.001;

		rotate(rotate, midPoint);
	}

	public void stopTurnRight(){

	}
}
