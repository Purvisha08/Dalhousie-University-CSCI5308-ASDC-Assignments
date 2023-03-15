import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CalculateArea areaCalculator = new CalculateArea();
        PrintShape printShape = new PrintShape();

        Circle circle = new Circle();
        circle.setRadius(10);

        Rectangle Rectangle = new Rectangle();
        Rectangle.setLength(20);

        Cube cube = new Cube();
        cube.setLength(30);

        double total = areaCalculator.sum(Arrays.asList(circle, Rectangle, cube));

        System.out.println(printShape.printSum(total));
    }
}