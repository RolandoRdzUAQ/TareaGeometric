package Principal;

public class Clase_principal {
    public static void main(String[] args) {
        Class_Circle miCirculo = new Class_Circle(5.5, "Azul", true);
        
        Class_Rectangle miRectangulo = new Class_Rectangle();
        miRectangulo.setWidth(10);
        miRectangulo.setHeight(4);
        miRectangulo.setColor("Rojo");
        miRectangulo.setFilled(false);

        System.out.println("=== DATOS DEL CÍRCULO ===");
        miCirculo.printCircle(); 
        System.out.println("Área: " + miCirculo.getArea());
        System.out.println("Diámetro: " + miCirculo.getDiameter());
        
        System.out.println("\n=== DATOS DEL RECTÁNGULO ===");
        System.out.println("Color: " + miRectangulo.getColor());
        System.out.println("Área: " + miRectangulo.getArea());
        System.out.println("Perímetro: " + miRectangulo.getPerimeter());
    }
}
