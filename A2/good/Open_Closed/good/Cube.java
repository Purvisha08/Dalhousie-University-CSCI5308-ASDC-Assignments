public class Cube implements Shape{
    private int length;
    private final String SHAPE_NAME = "Cube";

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public double area() {
        return 6*Math.pow(length, 2);
    }
    @Override
    public String shapeName() {
        return SHAPE_NAME;
    }

    @Override
    public double volume() {
        return Math.pow(length, 3);
    }
}
