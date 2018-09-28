package labImport;

import general.List;
import general.List.ListEl;
import java.lang.Math;
import java.util.ArrayList;

public class ImageLine {
	private ImagePoint startPoint;
	private ImagePoint endPoint;
	private ArrayList<ImagePoint> pointList = null;
	
	public ImageLine(ArrayList<ImagePoint> pointList)
	{
		this(pointList.get(0), pointList.get(pointList).getLast().element);
		this.pointList = pointList;	
	}
	
	public ImageLine(ImagePoint startPoint, ImagePoint endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public ImagePoint getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(ImagePoint startPoint) {
		this.startPoint = startPoint;
	}

	public ImagePoint getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(ImagePoint endPoint) {
		this.endPoint = endPoint;
	}

	public ImagePoint getVektor() {
		return new ImagePoint(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
	}
	
	public ImagePoint getVektorLen1() {
		ImagePoint vektor = getVektor();
		return vektor;
	}
	
	//Checks the Line if correct
	public static boolean isLineCor(List<ImagePoint> pointList) {
		double x0 = pointList.getFirst().element.x;
		double y0 = pointList.getFirst().element.y;
		double x1 = pointList.getLast().element.x;
		double y1 = pointList.getLast().element.y;
		
		double dx =  Math.abs(x1-x0), sx = x0<x1 ? 1 : -1;
		double dy = -Math.abs(y1-y0), sy = y0<y1 ? 1 : -1;
		double err = dx+dy, e2; /* error value e_xy */

		ListEl curEl = pointList.getFirst();
		while(curEl != null){
			ImagePoint curPoint = (ImagePoint)curEl.element;
			if(curPoint.x != x0 || curPoint.y != y0)
				return false;
			if (x0==x1 && y0==y1) break;
			e2 = 2*err;
		    if (e2 > dy) { err += dy; x0 += sx; } /* e_xy+e_x > 0 */
		    if (e2 < dx) { err += dx; y0 += sy; } /* e_xy+e_y < 0 */
		    curEl = curEl.getNext();
			  }
			return true;
		}
	
	//combines 2 Lines wich are each other and have the same vektor
	public static List<ImageLine> CombineSameLines(List<ImageLine> lineList)
	{
		ListEl curLineEl = lineList.getFirst(); 
		while(curLineEl != null)
		{
			for(ListEl compareLineEl = lineList.getFirst(); compareLineEl != null; compareLineEl = compareLineEl.getNext())
			{
				if(curLineEl != compareLineEl)
				{
					ImageLine curLine = (ImageLine)curLineEl.element;
					ImageLine compareLine = (ImageLine)compareLineEl.element;
					if(curLine.getVektorLen1() == compareLine.getVektorLen1())
					{ 
						if(curLine.startPoint.IsNextToMe(compareLine.endPoint))
						{
							lineList.removeEl(curLineEl);
							lineList.removeEl(compareLineEl);
							ImageLine newLine = new ImageLine(compareLine.startPoint, curLine.endPoint); 
							lineList.Add(newLine);
							curLineEl = lineList.getFirst();
							break;
						}
						if(curLine.endPoint.IsNextToMe(compareLine.startPoint))
						{
							lineList.removeEl(curLineEl);
							lineList.removeEl(compareLineEl);
							ImageLine newLine = new ImageLine(curLine.startPoint, compareLine.endPoint);
							lineList.Add(newLine);
							curLineEl = lineList.getFirst();
							break;
						}
						
					}
					
				}
			}
			curLineEl = curLineEl.getNext();
			
		}
		return lineList;
	}
	
	@Override
	public String toString() {
		return new String("Line:\nstartP: x: " +  getStartPoint().x + " y: " + getStartPoint().y 
		 + "\nendP: x: " + getEndPoint().x + " y: " + getEndPoint().y);
	}		
}
