package bad;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CalculateArea areaCalculator = new CalculateArea();
        PrintShape printShape = new PrintShape();

        Circle circle = new Circle();
        circle.setRadius(10);

        Rectangle rectangle = new Rectangle();
        rectangle.setLength(20);

        Cube cube = new Cube();
        cube.setLength(30);

        double total = areaCalculator.sum(Arrays.asList(circle, rectangle, cube));

        System.out.println(printShape.printSum(total));
    }
}