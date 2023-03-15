package good;

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
}
