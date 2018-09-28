package drawingObjects.labyrinth;

import drawingObjects.general.DrawingObject;
import general.math.geometry.*;
import general.math.calculation.*;

import java.awt.*;

public class StraightLabyrinthWall extends LabyrinthObject {
	private Line line;

	public StraightLabyrinthWall(Line line) {
		this.line = line;
	}

	public StraightLabyrinthWall(GeoPoint startPoint, GeoPoint endPoint) {
		this(new Line(startPoint, endPoint));
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine((int) line.getStartPoint().getX(), (int) line.getStartPoint().getY(), (int) line.getEndPoint().getX(), (int) line.getEndPoint().getY());
	}

	@Override
	public void move(Vektor move) {
		line.move(move);
	}

	@Override
	public void rotate(double rotateAngle, GeoPoint midPoint) {
		line.rotate(rotateAngle, midPoint);
	}

	@Override
	public boolean collidate(DrawingObject check) {
//
//		if (check.GetDrawingType() == DrawingType.DRAWING_TYPE_ARC) {
//			//Convert to Arc
//			DrawingArc checkArc = (DrawingArc) check;
//			LineFunction lineThis = new LineFunction(endPoint.minusVec(startPoint), startPoint);
//			LineFunction thisOrtho = new LineFunction(endPoint.minusVec(startPoint).getOrtho(), checkArc.getMidPoint());
//
//			Vektor pointOnLine = lineThis.getCrossingPoint(thisOrtho);
//
//			Vektor midToPoint = pointOnLine.minusVec(checkArc.getMidPoint());
//			Vektor startToEnd = endPoint.minusVec(startPoint);
//			Vektor endToPoint = pointOnLine.minusVec(endPoint);
//			Vektor startToPoint = pointOnLine.minusVec(startPoint);
//			double lenMidToPoint = midToPoint.getLen();
//			double lenStartToEnd = startToEnd.getLen() + checkArc.getRadius();
//			int collisionDifValue = (int) Math.round(lenMidToPoint - checkArc.getRadius());
////			if(collisionDifValue > 0)
////				collisionDifValue++;
//			midToPoint.setLen(collisionDifValue);
//			midToPoint.x = -midToPoint.x;
//			midToPoint.y = -midToPoint.y;
//			moveOnLine = midToPoint;
//			if (lenStartToEnd >= endToPoint.getLen() && lenStartToEnd >= startToPoint.getLen() && collisionDifValue <= 0.00) {
//				if (onlyCrossing)
//					if (collisionDifValue < 0.0) {
//						return true;
//					} else
//						return false;
//				return true;
//			}
//		}
//		if (check.GetDrawingType() == DrawingType.DRAWING_TYPE_LINE) {
//			//DrawingLine checkLine = (DrawingLine)check;
//
//		}
		return false;
	}
}