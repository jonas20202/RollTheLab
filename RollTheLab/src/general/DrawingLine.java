package general;

import javax.swing.plaf.metal.MetalTheme;
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
	public void rotate(double angle, Vektor midPoint) {
		endPoint.rotate(angle);
	}

	@Override
	public boolean checkCollision(DrawingObjekt check, boolean onlyCrossing) {

		if(check.GetDrawingType() == DrawingType.DRAWING_TYPE_ARC)
		{
			//Convert to Arc
			DrawingArc checkArc = (DrawingArc) check;
			if(checkArc.getMidPoint().minusVec(startPoint).getLen() <= checkArc.getRadius() || checkArc.getMidPoint().minusVec(endPoint).getLen() <= checkArc.getRadius())
				return true;
			LineFunction lineThis = new LineFunction(endPoint.minusVec(startPoint), startPoint);
			LineFunction thisOrtho = new LineFunction(endPoint.minusVec(startPoint).getOrtho(), checkArc.getMidPoint());

			Vektor pointOnLine = lineThis.getCrossingPoint(thisOrtho);

			Vektor midToPoint = pointOnLine.minusVec(checkArc.getMidPoint());
			Vektor startToEnd = endPoint.minusVec(startPoint);
			Vektor endToPoint = pointOnLine.minusVec(endPoint);
			Vektor startToPoint = pointOnLine.minusVec(startPoint);
			int lenMidToPoint = (int) midToPoint.getLen();
			int lenStartToEnd = (int)startToEnd.getLen() + checkArc.getRadius();
			int collisionDifValue = lenMidToPoint - checkArc.getRadius();
			midToPoint.setLen(collisionDifValue+1);
			midToPoint.x = -midToPoint.x;
			midToPoint.y = -midToPoint.y;
			moveOnLine = midToPoint;
			if( lenStartToEnd  >= endToPoint.getLen() && lenStartToEnd >= startToPoint.getLen() && collisionDifValue <= 0) {
				if(onlyCrossing)
					if(collisionDifValue < 0)
						return true;
					else
						return false;
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
