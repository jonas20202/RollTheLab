package game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import general.BallPhysic;
import general.DrawingArc;
import general.DrawingObjektGroup;
import general.Vektor;

public class Ball extends DrawingObjektGroup{
	BallPhysic physik = new BallPhysic();
	LabGroup lab;
	DrawingArc ball;
	public Ball(LabGroup lab) {
		ball = new DrawingArc(new Vektor(0,100), 30, 0, 360);
		this.lab = lab; 
		drawingObjekts.add(ball);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		changeDirVec();
		ball.move(physik.getDir());
	}

	//Changes the dirVektor of the Ball
	private void changeDirVec(){
		DrawingObjektGroup collidateObjekts = lab.getCollidateObjekts(ball);
		physik.RecalkPhysic(collidateObjekts);
	}
}
