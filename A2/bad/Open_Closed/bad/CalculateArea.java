package bad;

import java.util.List;

public class CalculateArea {
    public int sum(List<Object> shapes) {
        int sum = 0;
        for (int i = 0; i < shapes.size(); i++ ) {
            Object shape = shapes.get(i);
            if (shape instanceof Rectangle) {
                sum += Math.pow(((Rectangle) shape).getLength(), 2);
                System.out.println("Area Of "+((Rectangle) shape).getSHAPE_NAME()+" is "+Math.pow(((Rectangle) shape).getLength(), 2));
            }
            if (shape instanceof Circle) {
                sum += Math.PI * Math.pow(((Circle) shape).getRadius(), 2);
                System.out.println("Area Of "+((Circle) shape).getSHAPE_NAME()+" is "+Math.PI * Math.pow(((Circle) shape).getRadius(), 2));
            }
        } /* This class violates open close principle as it is open for the modification that means whenever
         we want to add a new shape we have to write a new if statement that means we are rewritting the existing
         code. */
        return sum;
    }
}

