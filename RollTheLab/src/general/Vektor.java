package general;

public class Vektor {
	public double x;
	public double y;
	
	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;		
	}
	
	public void Add(Vektor move)
	{
		x += move.x;
		y += move.y;
	}
	
	public double getLen() {
		return Math.sqrt(x * x + y * y);
	}
	
	public Vektor getNormVek() {
		double dLen = getLen();
		return new Vektor(x / dLen, y / dLen );
	}
	public Vektor minusVec(Vektor vec)
	{	
		return new Vektor(x -vec.x, y - vec.y);
	}
}
