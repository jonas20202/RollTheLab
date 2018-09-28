package general.math.calculation;

public class LineFunction {
    private double m = 0;
    private double t = 0;
    private boolean goesDown = false;

    public LineFunction(Vektor vec, Vektor point){
        if(vec.x == 0) {
            goesDown = true;
            t = point.x;
        }
        else {
            m = vec.y / vec.x;
            t = point.y - m * point.x;
        }
    }

    public Vektor getCrossingPoint(LineFunction crossF){
        double x = t;
        double y = crossF.m * x + crossF.t;
        if(!goesDown ) {
            if (!crossF.goesDown) {
                x = (t - crossF.t) / (crossF.m - m);
                y = m * x + t;
            } else {
                x = crossF.t;
                y = m * x + t;
            }
        }
        return new Vektor(x, y);
    }
}
