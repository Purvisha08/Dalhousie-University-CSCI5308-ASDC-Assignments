package bad;

public class Circle implements Shape {
    private int radius;
    private final String SHAPE_NAME = "Circle";

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
    @Override
    public String shapeName() {
        return SHAPE_NAME;
    }

     /* Violating the Interface Segregation Principle as in this class we cannot calculate
     the volume as it a 2-D figure. */

    @Override
    public double volume() {
        return 0;
    }

}
