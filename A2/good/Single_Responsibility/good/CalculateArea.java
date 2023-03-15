import java.util.List;

public class CalculateArea {
    public double sum(List<Object> shapes) {
        double total = 0;
        for (int i = 0; i < shapes.size(); i++ ) {
            Object shape = shapes.get(i);
            if (shape instanceof Rectangle) {
                total = total + Math.pow(((Rectangle) shape).getLength(), 2);
                System.out.println("Area Of "+((Rectangle) shape).getSHAPE_NAME()+" is "+Math.pow(((Rectangle) shape).getLength(), 2));
            }
            if (shape instanceof Circle) {
                total = total + Math.PI * Math.pow(((Circle) shape).getRadius(), 2);
                System.out.println("Area Of "+((Circle) shape).getSHAPE_NAME()+" is "+Math.PI * Math.pow(((Circle) shape).getRadius(), 2));
            }
        }
        return total; // printSum method is removed, and has been converted to a new class
    }
}

