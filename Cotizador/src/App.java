import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ShippingCalculator calculator = new ShippingCalculator();

        double pesoKg = leerDoubleEnRango(sc, "Ingrese peso (kg) [0.1 - 50.0]: ", 0.1, 50.0);
        int distanciaKm = leerIntEnRango(sc, "Ingrese distancia (km) [1 - 2000]: ", 1, 2000);
        int tipoServicio = leerIntEnRango(sc, "Tipo de servicio (1=Estándar, 2=Express): ", 1, 2);
        boolean zonaRemota = leerBoolean(sc, "¿Zona remota? (true/false): ");

        double subtotal = calculator.calcularSubtotal(pesoKg, distanciaKm, tipoServicio, zonaRemota);
        double iva = calculator.calcularIVA(subtotal);
        double total = calculator.calcularTotal(subtotal, iva);

        imprimirTicket(tipoServicio, pesoKg, distanciaKm, zonaRemota, subtotal, iva, total);

        sc.close();
    }


    public static double leerDoubleEnRango(Scanner sc, String msg, double min, double max) {
        double valor;
        do {
            System.out.print(msg);
            valor = sc.nextDouble();
        } while (valor < min || valor > max);
        return valor;
    }

    public static int leerIntEnRango(Scanner sc, String msg, int min, int max) {
        int valor;
        do {
            System.out.print(msg);
            valor = sc.nextInt();
        } while (valor < min || valor > max);
        return valor;
    }

    public static boolean leerBoolean(Scanner sc, String msg) {
        boolean valor;
        System.out.print(msg);
        valor = sc.nextBoolean();
        return valor;
    }


    public static void imprimirTicket(int tipoServicio, double pesoKg, int distanciaKm,
                                      boolean zonaRemota, double subtotal, double iva, double total) {

        String servicio = (tipoServicio == 1) ? "Estándar" : "Express";

        System.out.println("\n========== TICKET DE ENVÍO ==========");
        System.out.println("Servicio: " + servicio);
        System.out.println("Peso (kg): " + pesoKg);
        System.out.println("Distancia (km): " + distanciaKm);
        System.out.println("Zona remota: " + zonaRemota);
        System.out.println("-------------------------------------");
        System.out.println("Subtotal (antes de IVA): $" + subtotal);
        System.out.println("IVA (16%): $" + iva);
        System.out.println("TOTAL FINAL: $" + total);
        System.out.println("=====================================");
    }
}
