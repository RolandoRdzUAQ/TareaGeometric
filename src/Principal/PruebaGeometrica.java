package Principal;

public class PruebaGeometrica {
    public static void main(String[] args) {
        System.out.println("Iniciando batería de pruebas unitarias (Geometric Object)...");

        Class_Circle circulo = new Class_Circle(5.0, "Verde", true);
        Class_Rectangle rectangulo = new Class_Rectangle(4.0, 5.0, "Azul", false);

        assert circulo instanceof GeometryObject : "Error: El círculo no hereda de GeometryObject";
        assert rectangulo instanceof GeometryObject : "Error: El rectángulo no hereda de GeometryObject";

        assert circulo.getDiameter() == 10.0 : "Error: Cálculo de diámetro incorrecto en Círculo";
        assert Math.abs(circulo.getArea() - (Math.PI * 25)) < 0.0001 : "Error: Cálculo de área incorrecto en Círculo";

        assert rectangulo.getArea() == 20.0 : "Error: Cálculo de área incorrecto en Rectángulo";
        assert rectangulo.getPerimeter() == 18.0 : "Error: Cálculo de perímetro incorrecto en Rectángulo";

        assert circulo.getColor().equals("Verde") : "Error: El color no se asignó correctamente al Círculo";
        assert !rectangulo.isFilled() : "Error: El estado 'filled' no se asignó correctamente al Rectángulo";

        System.out.println("¡Todas las pruebas pasaron exitosamente!");
    }
}
