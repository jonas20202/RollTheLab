package general.math.geometry;

import general.math.calculation.Vektor;

public class GeoPoint implements GeometryObject{
    private Vektor point;

    public GeoPoint(GeoPoint newPoint){
        this(newPoint.getX(), newPoint.getY());
    }

    public GeoPoint(double x, double y){
        point.x = x;
        point.y = y;
    }

    public double getX() { return point.x; }
    public double getY(){ return point.y; }

    public void setX( double x) { point.x = x; }
    public void setY( double y) { point.y = y; }

    @Override
    public void move(Vektor move) {
        point.Add(move);
    }

    @Override
    public void rotate(double angle, GeoPoint midPoint) {
        point.rotateAroundMid(angle, midPoint);
    }
}
