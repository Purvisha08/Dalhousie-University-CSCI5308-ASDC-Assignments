package bad;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CalculateArea calculateArea = new CalculateArea();

        Circle circle = new Circle();
        circle.setRadius(12);

        Rectangle square = new Rectangle();
        square.setLength(24);

        double total = calculateArea.sum(Arrays.asList(circle, square));

        System.out.println(calculateArea.printSum(total));
    }
}