package general;

import java.awt.*;

public class DrawingLine extends DrawingObjekt {
	private Vektor startPoint;
	private Vektor endPoint;

	
	public DrawingLine(Vektor startPoint, Vektor endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawLine((int)startPoint.x, (int)startPoint.y, (int)endPoint.x, (int)endPoint.y);
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
	public boolean checkCollision(DrawingObjekt check) {
		if(check.GetDrawingType() == DrawingType.DRAWING_TYPE_ARC)
		{
			//Convert to Arc
			DrawingArc checkArc = (DrawingArc) check;
			if(checkArc.getMidPoint().minusVec(startPoint).getLen() <= checkArc.getRadius() || checkArc.getMidPoint().minusVec(endPoint).getLen() <= checkArc.getRadius())
				return true;

			double m1 = 0;
			double m2 = 0;
			double x = 0;
			Vektor pointOnLine = null;
			if(endPoint.y - startPoint.y != 0)
			{
				if(endPoint.x - startPoint.x != 0)

				{
					m1 = (endPoint.y - startPoint.y) / (endPoint.x - startPoint.x);
					m2 = -1 / m1;
					double t1 = startPoint.y - m1 * startPoint.x;
					double t2 = checkArc.getMidPoint().y - m2* checkArc.getMidPoint().x;
					x = (t2 - t1) / (m1 - m2);
					pointOnLine = new Vektor(x, m1 * x + startPoint.y - m1 * startPoint.x);
				}else {
					pointOnLine = new Vektor(startPoint.x, checkArc.getMidPoint().y);
				}
			}else {
				x = checkArc.getMidPoint().x;
				pointOnLine = new Vektor(x, m1 * x + startPoint.y - m1 * startPoint.x);
			}

			Vektor midToPoLine = pointOnLine.minusVec(checkArc.getMidPoint());
			Vektor sToEnd = endPoint.minusVec(startPoint);
			Vektor eToPoint = pointOnLine.minusVec(endPoint);
			Vektor sToPoint = pointOnLine.minusVec(startPoint);
			int dLen = (int) midToPoLine.getLen();
			if( sToEnd.getLen() >= eToPoint.getLen() && sToEnd.getLen() >= sToPoint.getLen() && dLen <= checkArc.getRadius()) {
				if(midToPoLine.goesDown())
					isAboveOfObject = false;
				else
					isAboveOfObject = true;
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
		return endPoint.minusVec(startPoint);
	}

}
