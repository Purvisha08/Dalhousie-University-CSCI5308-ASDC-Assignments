public interface Shape {
    double area();
    String shapeName();
    double volume();
}
/* A new interface with the name Shape has been made to stop from
violating the open close principle, this interface is extended whenever required. */