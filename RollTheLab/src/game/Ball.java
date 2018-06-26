package game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import general.BallPhysic;
import general.DrawingArc;
import general.DrawingObjektGroup;
import general.Vektor;

public class Ball extends DrawingObjektGroup{
	BallPhysic physik = new BallPhysic();
	public LabGroup lab;
	public DrawingArc ball;
	public Ball(LabGroup lab) {
		ball = new DrawingArc(new Vektor(450,400), 15, 0, 360);
		this.lab = lab;
		drawingObjekts.add(ball);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		changeDirVec();
		Vektor testdirection = new Vektor(physik);
		testdirection.setLen(100);
		testdirection.Add(ball.getMidPoint());

		//g.drawLine((int)ball.getMidPoint().x, (int)ball.getMidPoint().y, (int)testdirection.x, (int)testdirection.y);
		ball.move(physik);
	}

	//Changes the dirVektor of the Ball
	private void changeDirVec(){
		physik.RecalkPhysic(this);
	}
}
