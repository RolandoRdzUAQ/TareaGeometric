package Principal;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Motor3D {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private static final double ASPECT_RATIO = 2.0;

    static class Vec3 {
        double x, y, z;
        Vec3(double x, double y, double z) {
            this.x = x; this.y = y; this.z = z;
        }
    }

    static class Edge {
        int a, b;
        Edge(int a, int b) {
            this.a = a; this.b = b;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecciona la figura 3D:");
        System.out.println("1. Caja (Rectángulo 3D)");
        System.out.println("2. Cilindro (Círculo 3D)");
        int choice = scanner.nextInt();

        List<Vec3> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        if (choice == 1) {
            System.out.print("Ancho: "); double w = scanner.nextDouble();
            System.out.print("Alto: "); double h = scanner.nextDouble();
            System.out.print("Profundidad: "); double d = scanner.nextDouble();
            generarCaja(vertices, edges, w, h, d);
        } else {
            System.out.print("Radio: "); double r = scanner.nextDouble();
            System.out.print("Altura: "); double h = scanner.nextDouble();
            generarCilindro(vertices, edges, r, h, 16);
        }

        System.out.print("\033[?25l");
        
        double angleZ = 0;
        double angleX = 0.5; 
        double angleY = 0.3; 

        char[][] buffer = new char[HEIGHT][WIDTH];

        while (true) {
            limpiarBuffer(buffer);

            for (Edge edge : edges) {
                Vec3 v1 = rotar(vertices.get(edge.a), angleX, angleY, angleZ);
                Vec3 v2 = rotar(vertices.get(edge.b), angleX, angleY, angleZ);

                int x1 = (int) (v1.x * ASPECT_RATIO + WIDTH / 2);
                int y1 = (int) (-v1.y + HEIGHT / 2);
                int x2 = (int) (v2.x * ASPECT_RATIO + WIDTH / 2);
                int y2 = (int) (-v2.y + HEIGHT / 2);

                dibujarLinea(buffer, x1, y1, x2, y2);
            }

            imprimirBuffer(buffer);
            angleZ += 0.05;
            Thread.sleep(50);
        }
    }

    private static Vec3 rotar(Vec3 v, double ax, double ay, double az) {
        double y1 = v.y * Math.cos(ax) - v.z * Math.sin(ax);
        double z1 = v.y * Math.sin(ax) + v.z * Math.cos(ax);

        double x2 = v.x * Math.cos(ay) + z1 * Math.sin(ay);
        double z2 = -v.x * Math.sin(ay) + z1 * Math.cos(ay);

        double x3 = x2 * Math.cos(az) - y1 * Math.sin(az);
        double y3 = x2 * Math.sin(az) + y1 * Math.cos(az);

        return new Vec3(x3, y3, z2);
    }

    private static void dibujarLinea(char[][] buffer, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
        int err = dx + dy, e2;

        while (true) {
            if (x0 >= 0 && x0 < WIDTH && y0 >= 0 && y0 < HEIGHT) {
                buffer[y0][x0] = '#';
            }
            if (x0 == x1 && y0 == y1) break;
            e2 = 2 * err;
            if (e2 >= dy) { err += dy; x0 += sx; }
            if (e2 <= dx) { err += dx; y0 += sy; }
        }
    }

    private static void generarCaja(List<Vec3> v, List<Edge> e, double w, double h, double d) {
        w /= 2; h /= 2; d /= 2;
        v.add(new Vec3(-w, -h, -d)); v.add(new Vec3(w, -h, -d));
        v.add(new Vec3(w, h, -d));   v.add(new Vec3(-w, h, -d));
        v.add(new Vec3(-w, -h, d));  v.add(new Vec3(w, -h, d));
        v.add(new Vec3(w, h, d));    v.add(new Vec3(-w, h, d));

        for (int i = 0; i < 4; i++) {
            e.add(new Edge(i, (i + 1) % 4));
            e.add(new Edge(i + 4, (i + 1) % 4 + 4));
            e.add(new Edge(i, i + 4));
        }
    }

    private static void generarCilindro(List<Vec3> v, List<Edge> e, double r, double h, int seg) {
        h /= 2;
        for (int i = 0; i < seg; i++) {
            double theta = 2.0 * Math.PI * i / seg;
            double x = r * Math.cos(theta);
            double z = r * Math.sin(theta);
            v.add(new Vec3(x, h, z));
            v.add(new Vec3(x, -h, z));
        }
        for (int i = 0; i < seg; i++) {
            int next = (i + 1) % seg;
            e.add(new Edge(i * 2, next * 2));
            e.add(new Edge(i * 2 + 1, next * 2 + 1));
            e.add(new Edge(i * 2, i * 2 + 1));
        }
    }

    private static void limpiarBuffer(char[][] buffer) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) buffer[i][j] = ' ';
        }
    }

    private static void imprimirBuffer(char[][] buffer) {
        System.out.print("\033[H");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < HEIGHT; i++) {
            sb.append(buffer[i]).append('\n');
        }
        System.out.print(sb.toString());
        System.out.flush();
    }
}
