import java.util.Scanner;

public class superficierevolucion {

    public static double f(double x, int opcion) {
        if (opcion == 1) {
            return x;       
        } else {
            return Math.pow(x, 2); 
        }
    }

    public static double derivada(double x, int opcion) {
        double dx = 1e-5;
        return (f(x + dx, opcion) - f(x - dx, opcion)) / (2 * dx);
    }

    public static double areaRevolucion(double a, double b, int n, int opcion) {
        double h = (b - a) / n;
        double suma = 0.0;

        for (int i = 0; i <= n; i++) {
            double x = a + i * h;
            double fx = f(x, opcion);
            double valor = 2 * Math.PI * fx * Math.sqrt(1 + Math.pow(derivada(x, opcion), 2));

            if (i == 0 || i == n) {
                suma += valor;
            } else {
                suma += 2 * valor;
            }
        }
        return (h / 2) * suma;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Cálculo del área de una superficie de revolución ===");
        System.out.println("Elija la función:");
        System.out.println("1. f(x) = x");
        System.out.println("2. f(x) = x^2");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();

        System.out.print("Ingrese el límite inferior (a): ");
        double a = sc.nextDouble();

        System.out.print("Ingrese el límite superior (b): ");
        double b = sc.nextDouble();

        System.out.print("Ingrese el número de divisiones (mayor = más precisión): ");
        int n = sc.nextInt();

        double area = areaRevolucion(a, b, n, opcion);
        System.out.printf("El área aproximada de la superficie de revolución es: %.6f unidades²\n", area);
        sc.close();
    }
}
