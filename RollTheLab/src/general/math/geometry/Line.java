package general.math.geometry;

import general.math.calculation.Vektor;

import java.lang.reflect.GenericArrayType;

public class Line implements GeometryObject {
    private GeoPoint startPoint;
    private GeoPoint endPoint;

    public Line(GeoPoint startPoint, GeoPoint endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public GeoPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(GeoPoint startPoint) {
        this.startPoint = startPoint;
    }

    public GeoPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(GeoPoint endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void move(Vektor move) {
        startPoint.move(move);
        endPoint.move(move);
    }

    @Override
    public void rotate(double rotateAngle, GeoPoint midPoint) {
        startPoint.rotate(rotateAngle, midPoint);
        endPoint.rotate(rotateAngle, midPoint);
    }
}
