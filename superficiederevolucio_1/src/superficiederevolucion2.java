import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.Scanner;

public class superficiederevolucion2 {

    // Método para calcular la derivada numérica de una función en un punto
    public static double derivada(String funcion, double x) {
        double h = 1e-5;
        Expression expr1 = new ExpressionBuilder(funcion).variable("x").build().setVariable("x", x + h);
        Expression expr2 = new ExpressionBuilder(funcion).variable("x").build().setVariable("x", x - h);
        return (expr1.evaluate() - expr2.evaluate()) / (2 * h);
    }

    // Método para calcular el área de revolución utilizando el método del trapecio
    public static double areaRevolucion(String funcion, double a, double b, int n) {
        double h = (b - a) / n;
        double suma = 0.0;

        for (int i = 0; i <= n; i++) {
            double x = a + i * h;
            Expression expr = new ExpressionBuilder(funcion).variable("x").build().setVariable("x", x);
            double fx = expr.evaluate();
            double deriv = derivada(funcion, x);
            double valor = 2 * Math.PI * fx * Math.sqrt(1 + Math.pow(deriv, 2));

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
        System.out.print("Ingrese la función f(x) en términos de x (por ejemplo, x^2 + 3*x + 1): ");
        String funcion = sc.nextLine();

        System.out.print("Ingrese el límite inferior (a): ");
        double a = sc.nextDouble();

        System.out.print("Ingrese el límite superior (b): ");
        double b = sc.nextDouble();

        System.out.print("Ingrese el número de divisiones (n): ");
        int n = sc.nextInt();

        double area = areaRevolucion(funcion, a, b, n);
        System.out.printf("El área aproximada de la superficie de revolución es: %.6f unidades²\n", area);
        sc.close();
    }
}
