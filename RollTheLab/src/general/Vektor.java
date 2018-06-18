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

	public double getAngle(){
		return Math.sin(x/getLen());
	}

	public void rotate(double angle){
		angle += getAngle();
		double hypo = getLen();
		y = Math.sinh(angle)*hypo;
		x = Math.cosh(angle)*hypo;
	}

	public boolean isRightOr() {if(x > 0) return true; return false;}
	public boolean isLeftOr() {if(x < 0) return true; return false;}
	public boolean goesDown() {if(y > 0) return true; return false;}
}
