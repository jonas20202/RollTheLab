package game;

import general.*;

import java.awt.*;

public class LabGroup extends DrawingObjektGroup{
	private double rotate;
	boolean turnRigtht = false;

	public LabGroup(String strFile)
	{
		Vektor midPoint = GameFrame.midPoint;
        //drawingObjekts.add(new DrawingLine(new Vektor(0,100), new Vektor(150, 110)));
        //drawingObjekts.add(new DrawingLine(new Vektor(150, 106), new Vektor(150, 151)));
		//drawingObjekts.add(new DrawingArc(new Vektor(0, 390), 300, 0, 90));
        //drawingObjekts.add(new DrawingLine(new Vektor(0,300), new Vektor(400, 301)));
        //drawingObjekts.add(new DrawingLine(new Vektor(0,350), new Vektor(1000, 350)));
		//drawingObjekts.add(new DrawingLine(new Vektor(500, 400), new Vektor(1000, 395)));
		//drawingObjekts.add(new DrawingLine(new Vektor(00, 300), new Vektor(1500, 250)));
		//drawingObjekts.add(new DrawingLine(new Vektor(0,265), new Vektor(400, 400)));
		//LabLevel 1
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x + 250,midPoint.y + 250), new Vektor(midPoint.x + 250, midPoint.y - 250)));
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x + 250,midPoint.y + 250), new Vektor(midPoint.x -200, midPoint.y + 250)));
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x -200, midPoint.y + 250), new Vektor(midPoint.x -200, midPoint.y + 300)));
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x -200, midPoint.y + 300), new Vektor(midPoint.x -250, midPoint.y + 300)));
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x -250, midPoint.y - 250), new Vektor(midPoint.x -250, midPoint.y + 300)));
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x -200, midPoint.y + 300), new Vektor(midPoint.x -250, midPoint.y + 300)));
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x -200, midPoint.y + 300), new Vektor(midPoint.x -250, midPoint.y + 300)));
		drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x -200, midPoint.y + 300), new Vektor(midPoint.x -250, midPoint.y + 300)));
		//drawingObjekts.add(new DrawingLine(new Vektor(midPoint.x + 300,midPoint.y + 300), new Vektor(midPoint.x + 300, midPoint.y - 300)));

	}

	@Override
	public void draw(Graphics g) {
		if(GameKeyListener.isTurnRightPressed())
			startTurnRight();
		if(GameKeyListener.isTurnLeftPressed())
			startTurnLeft();
		super.draw(g);

	}

	public void startTurnRight(){
	    rotate = +0.001;
		rotate(rotate, GameFrame.midPoint);
	}

	public void startTurnLeft(){
		rotate = -0.001;
		rotate(rotate, GameFrame.midPoint);
	}
}
