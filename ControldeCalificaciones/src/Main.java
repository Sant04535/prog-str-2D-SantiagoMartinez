import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GradeService service = new GradeService();

        String nombre = leerTextoNoVacio(sc, "Ingrese el nombre del alumno: ");

        double p1 = leerDoubleEnRango(sc, "Ingrese calificación parcial 1 (0-100): ", 0, 100);
        double p2 = leerDoubleEnRango(sc, "Ingrese calificación parcial 2 (0-100): ", 0, 100);
        double p3 = leerDoubleEnRango(sc, "Ingrese calificación parcial 3 (0-100): ", 0, 100);

        int asistencia = leerIntEnRango(sc, "Ingrese porcentaje de asistencia (0-100): ", 0, 100);

        boolean entregaProyecto = leerBoolean(sc, "¿Entregó proyecto? (true/false): ");

        double promedio = service.calcularPromedio(p1, p2, p3);
        double finalCalificacion = service.calcularFinal(promedio, asistencia);
        String estado = service.determinarEstado(finalCalificacion, asistencia, entregaProyecto);

        imprimirReporte(nombre, p1, p2, p3, promedio, asistencia, entregaProyecto, finalCalificacion, estado);

        sc.close();
    }


    public static String leerTextoNoVacio(Scanner sc, String msg) {
        String texto;
        do {
            System.out.print(msg);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Error: el texto no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    public static double leerDoubleEnRango(Scanner sc, String msg, double min, double max) {
        double valor;
        while (true) {
            System.out.print(msg);
            if (sc.hasNextDouble()) {
                valor = sc.nextDouble();
                sc.nextLine();
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("Error: el valor debe estar entre " + min + " y " + max);
                }
            } else {
                System.out.println("Error: ingrese un número válido.");
                sc.nextLine();
            }
        }
    }

    public static int leerIntEnRango(Scanner sc, String msg, int min, int max) {
        int valor;
        while (true) {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                sc.nextLine();
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("Error: el valor debe estar entre " + min + " y " + max);
                }
            } else {
                System.out.println("Error: ingrese un número entero válido.");
                sc.nextLine();
            }
        }
    }

    public static boolean leerBoolean(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("Error: solo se permite true o false.");
            }
        }
    }


    public static void imprimirReporte(String nombre, double p1, double p2, double p3,
                                       double promedio, int asistencia,
                                       boolean entregaProyecto,
                                       double finalCalificacion, String estado) {

        System.out.println("\n===== REPORTE FINAL =====");
        System.out.println("Alumno: " + nombre);
        System.out.println("Parciales: " + p1 + ", " + p2 + ", " + p3);
        System.out.println("Promedio parciales: " + promedio);
        System.out.println("Asistencia: " + asistencia + "%");
        System.out.println("Entregó proyecto: " + entregaProyecto);
        System.out.println("Calificación final: " + finalCalificacion);
        System.out.println("Estado: " + estado);
    }
}
