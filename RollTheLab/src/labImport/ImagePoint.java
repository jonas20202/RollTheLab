package labImport;

public class ImagePoint {
	
	&
	public ImagePoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int x;
	public int y;
	
	public boolean IsNextToMe(ImagePoint checkingPoint)
	{
		if(this.x - checkingPoint.x < 2 && this.x - checkingPoint.x >= -1)
			if(this.y - checkingPoint.y < 2 && this.y - checkingPoint.y >= -1)
				return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		return "Punkt: x: " + x + " y: " + y;
		
	}
}
