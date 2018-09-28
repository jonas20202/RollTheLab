package general.math.geometry;

import general.math.calculation.Vektor;

public interface GeometryObject {
    public void move(Vektor move);
    public void rotate(double rotateAngle, GeoPoint midPoint);
}
