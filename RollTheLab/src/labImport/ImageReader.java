package labImport;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import general.*;
import general.List.ListEl;

public class ImageReader {
	 BufferedImage img = null;
	 File file = null;
	 List<ImagePoint> pointList = new List();
	 List<ImageLine> lineList = new List();
	 
	 public ImageReader(String file) {
		 try
		 {
     		this.file = new File(file);
	     	img = ImageIO.read(this.file);
	     	fillPointList();
	     	fillLineList();
		 }catch(IOException e){
	    	System.out.println(e);
		 }
		 
	 }
	 
	 private void fillPointList() {
		 int width = img.getWidth();
		 int height = img.getHeight();
		 for(int x = 0; x < width; x++)
		 {
			 for(int y = 0; y < height; y++)
			 {
				 int p = img.getRGB(x,y);
				 if(p <= -1600000)
				 {
					ImagePoint point =new ImagePoint(x, y); 
					pointList.Add(point);
					System.out.println(point.toString());
				 }
			 }
		 }
		 
	 }
	 
	 private void fillLineList() {
		 
		 ListEl curEl = pointList.getFirst();
		 while(curEl != null)
		 {
			 List<ImagePoint> pointSearchList = pointList;
			 List<ImagePoint> curLine = new List();
			 ImagePoint curPoint = (ImagePoint)curEl.element;
			 
			 ListEl curFoundEl = pointSearchList.getFirst(); 
			 while(curFoundEl != null)
			 {
				 ImagePoint curFoundPoint = (ImagePoint)curFoundEl.element;
				 
				 //If the Point is next my Point 
				 if(curPoint.IsNextToMe(curFoundPoint))
				 {
					 curLine.Add(curFoundPoint);
					 
					 //Check if there is already a line
					 if(ImageLine.isLineCor(curLine))
					 {
						 pointSearchList.removeEl(curFoundEl);
						 curFoundEl = pointSearchList.getFirst(); 
						 curPoint = curFoundPoint;
						 continue;
					 }
					 else
					 {
						 curLine.removeEl(curLine.getLast());
					 }
				 }
				 curFoundEl = curFoundEl.getNext();
			 }
			 if(curLine.getCount() > 1)
			 {
				 ImageLine newLine = new ImageLine(curLine); 
				 lineList.Add(newLine);
				 System.out.println(newLine.toString());
				 curEl = pointList.getFirst();
				 pointList = pointSearchList;
			 }else
			 {
				 curEl = curEl.getNext();
			 }
		 }
		 lineList = ImageLine.CombineSameLines(lineList);
	 }
}
