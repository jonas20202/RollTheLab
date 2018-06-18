package general;

import java.awt.Graphics;

public class DrawingLine extends DrawingObjekt {
	private Vektor startPoint;
	private Vektor endPoint;
	private Vektor testLine;
	private Vektor testLine2;
	
	public DrawingLine(Vektor startPoint, Vektor endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		testLine = new Vektor(0,0);
		testLine2 = new Vektor(0,0);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawLine((int)startPoint.x, (int)startPoint.y, (int)endPoint.x, (int)endPoint.y);
		g.drawLine((int)testLine.x, (int)testLine.y, (int)testLine2.x, (int)testLine2.y);
	}

	@Override
	DrawingType GetDrawingType() {
		return DrawingType.DRAWING_TYPE_LINE;
	}

	@Override
	public void move(Vektor move) {
		startPoint.Add(move);
		endPoint.Add(move);
	}

	@Override
	public boolean collidate(DrawingObjekt check) {
		//Wenn das zu überprüfende Objekt ein Bogen ist
		if(check.GetDrawingType() == DrawingType.DRAWING_TYPE_ARC)
		{
			DrawingArc checkArc = (DrawingArc) check;
			double m1 = 0;
			double m2 = 1;
			if(endPoint.y - startPoint.y != 0)
			{
				m1 = endPoint.y - startPoint.y / endPoint.x - startPoint.x;
				m2 = -1 / m1;
			}
			double t1 = startPoint.y - m1 * startPoint.x;
			double t2 = checkArc.getMidPoint().y - m2* checkArc.getMidPoint().x;
			double x = (t1 - t2) / (m1 - m2);
			Vektor pointOnLine = new Vektor(x, m1 * x + startPoint.y - m1 * startPoint.x);
			Vektor midToPoLine = pointOnLine.minusVec(checkArc.getMidPoint());
			Vektor sToEnd = endPoint.minusVec(startPoint);
			Vektor eToPoint = pointOnLine.minusVec(endPoint);
			Vektor sToPoint = pointOnLine.minusVec(startPoint);
			int dLen = (int) midToPoLine.getLen();
			testLine = checkArc.getMidPoint();
			testLine2 = pointOnLine;
			if( sToEnd.getLen() >= eToPoint.getLen() && sToEnd.getLen() >= sToPoint.getLen() && dLen <= checkArc.getRadius())
			{
				
				return true;
			}
		}
		if(check.GetDrawingType() == DrawingType.DRAWING_TYPE_LINE)
		{
			//DrawingLine checkLine = (DrawingLine)check;
			
		}
		return false;
	}

	@Override
	public Vektor getMoveVek() {
		if(endPoint.x - startPoint.x > 0)
			return new Vektor(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
		else if(startPoint.x - endPoint.x > 0)
			return new Vektor(startPoint.x - endPoint.x, startPoint.y - endPoint.y);
		return new Vektor(0,0);
	}

}
