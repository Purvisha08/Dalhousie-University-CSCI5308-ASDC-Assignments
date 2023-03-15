package bad;

import java.util.List;

public class CalculateArea {
    public double sum(List<Shape> shapes) {
        double total = 0;
        for (int i = 0; i < shapes.size(); i++ ) {
            total = total + shapes.get(i).area();
            System.out.println("Area of "+shapes.get(i).shapeName()+": "+shapes.get(i).area());
        }
        return total;
    }
}

