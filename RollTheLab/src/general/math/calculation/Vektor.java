package general.math.calculation;

public class Vektor {
	public double x;
	public double y;

	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;		
	}

	public Vektor(Vektor copyVec){
		x = copyVec.x;
		y = copyVec.y;
	}

	//returns the length of the vector
	public double getLen() {
		return Math.sqrt(x * x + y * y);
	}

	public Vektor getOrtho(){
		Vektor newVec = new Vektor(this);
		newVec.rotate(Math.PI/2);
		newVec.setLen(1);
		return newVec;
	}

	//returns the this vector normalist to length 1
	public Vektor getNormVek() {
		double dLen = getLen();
		return new Vektor(x / dLen, y / dLen );
	}

	//return the angle with the crossing vector(crossVec)
	//if crossVec = null the x axis will be taken
	public double getCrossingAngle(Vektor crossVec){
		if(crossVec == null || crossVec.y == 0)
			crossVec = new Vektor(1,0);
		if(y == 0)
			return 0;
		double skalarProduct = x * crossVec.x + y * crossVec.y;
		double cos = Math.acos(skalarProduct/(getLen() * crossVec.getLen()));
		if(goesDown())
			return cos;
		else
			return 2*Math.PI - cos;
	}

	//sets the length of the vector
	public void setLen(double len){
		len = Math.abs(len);
		if(getLen() == 0)
			return;
		Vektor normVec = getNormVek();
		x = normVec.x * len;
		y = normVec.y * len;
	}

	//direction functions of the vector
	public boolean isRightOr() 	{if(x > 0) return true; return false;}
	public boolean isLeftOr() 	{if(x < 0) return true; return false;}
	public boolean goesDown() 	{if(y > 0) return true; return false;}

	//Add a vector to this vector
	public void Add(Vektor move){
		x += move.x;
		y += move.y;
	}

	public Vektor minusVec(Vektor vec){
		return new Vektor(x -vec.x, y - vec.y);
	}

	public void rotate(double angle){
		if(angle > 2*Math.PI)
			angle -= 2*Math.PI;
		if(angle < 0)
			angle = 2*Math.PI + angle;
		angle += getCrossingAngle(null);
		double hypo = getLen();
		y = Math.sin(angle)*hypo;
		x = Math.cos(angle)*hypo;
	}

	public void rotateAroundMid(double angle, Vektor midPoint){
		Vektor rotateVec = minusVec(midPoint);
		rotateVec.rotate(angle);
		rotateVec.Add(midPoint);
		this.x = rotateVec.x;
		this.y = rotateVec.y;
	}

}
