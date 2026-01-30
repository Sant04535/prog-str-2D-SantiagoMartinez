import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Menú de opciones");
        System.out.println("1) Sumar");
        System.out.println("2) Restar");
        System.out.println("3) Multiplicar");
        System.out.println("4) Dividir");

        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();

        System.out.print("Ingresa el valor de a: ");
        double a = sc.nextDouble();

        System.out.print("Ingresa el valor de b: ");
        double b = sc.nextDouble();

        switch (opcion) {

            case 1:
                System.out.println("Operacion elegida: Suma");
                System.out.println("Valores ingresados: a = " + a + ", b = " + b);
                System.out.println("Resultado: " + (a + b));
                break;

            case 2:
                System.out.println("Operación elegida: Resta");
                System.out.println("Valores ingresados: a = " + a + ", b = " + b);
                System.out.println("Resultado: " + (a - b));
                break;

            case 3:
                System.out.println("Operación elegida: Multiplicación");
                System.out.println("Valores ingresados: a = " + a + ", b = " + b);
                System.out.println("Resultado: " + (a * b));
                break;

            case 4:
                System.out.println("Operación elegida: División");
                System.out.println("Valores ingresados: a = " + a + ", b = " + b);

                if (b == 0) {
                    System.out.println("No se puede dividir entre cero");
                } else {
                    System.out.println("Resultado: " + (a / b));
                }
                break;

            default:
                System.out.println("Opción inválida");
                break;
        }

        sc.close();
    }
}
