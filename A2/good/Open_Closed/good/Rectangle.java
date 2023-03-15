public class Rectangle implements Shape {
    private int length;
    private final String SHAPE_NAME = "Rectangle";

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public double area() {
        return Math.pow(length, 2);
    }
    @Override
    public String shapeName() {
        return SHAPE_NAME;
    }

    @Override
    public double volume() {
        return 0;
    }
}
