package bad;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CalculateArea calculateArea = new CalculateArea();
        PrintShape printShape = new PrintShape();

        Circle circle = new Circle();
        circle.setRadius(12);

        Rectangle Rectangle = new Rectangle();
        Rectangle.setLength(24);

        int total = calculateArea.sum(Arrays.asList(circle, Rectangle));

        System.out.println(printShape.printSum(total));
    }
}