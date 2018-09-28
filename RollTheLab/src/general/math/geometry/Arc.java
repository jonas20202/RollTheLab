package general.math.geometry;

import general.math.calculation.Vektor;

public class Arc implements GeometryObject{
    private Point midPoint;
    private double radius;

    private double startAngl;
    private double endAngl;

    //Konstruktoren
    public Arc(Point midPoint, double radius) {
        this(midPoint, radius, 0.0, 2*Math.PI);
    }

    public Arc(Point midPoint, double radius, double startAngl, double endAngl) {
        this.midPoint = midPoint;
        this.radius = radius;
        this.startAngl = startAngl;
        this.endAngl = endAngl;
    }

    //Getter and Setter
    public Point getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(Point midPoint) {
        this.midPoint = midPoint;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getStartAngl() {
        return startAngl;
    }

    public void setStartAngl(double startAngl) {
        this.startAngl = startAngl;
    }

    public double getEndAngl() {
        return endAngl;
    }

    public void setEndAngl(double endAngl) {
        this.endAngl = endAngl;
    }

    @Override
    public void move(Vektor move) {
        midPoint.move(move);
    }

    @Override
    public void rotate(double angle, Vektor midPoint) {
        //TODO: Rotate the Curve
    }
}
