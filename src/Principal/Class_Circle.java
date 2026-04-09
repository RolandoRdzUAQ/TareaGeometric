package Principal;

public class Class_Circle extends GeometryObject {
    private double radius;

    public Class_Circle() {}

    public Class_Circle(double radius) {
        this.radius = radius;
    }

    public Class_Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    public double getArea() { return Math.PI * radius * radius; }
    public double getPerimeter() { return 2 * Math.PI * radius; }
    public double getDiameter() { return 2 * radius; }

    public void printCircle() {
        System.out.println("El círculo fue creado el " + getDateCreated() + " y su radio es " + radius);
    }
}
