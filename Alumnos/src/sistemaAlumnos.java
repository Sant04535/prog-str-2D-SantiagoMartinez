import java.util.Scanner;

public class sistemaAlumnos {

    static Alumno[] alumnos = new Alumno[25];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Alta alumno");
            System.out.println("2) Buscar por ID (solo activos)");
            System.out.println("3) Actualizar promedio por ID (solo activos)");
            System.out.println("4) Baja lógica por ID");
            System.out.println("5) Listar activos");
            System.out.println("6) Reportes");
            System.out.println("0) Salir");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    altaAlumno();
                    break;
                case 2:
                    buscarAlumno();
                    break;
                case 3:
                    actualizarPromedio();
                    break;
                case 4:
                    bajaLogica();
                    break;
                case 5:
                    listarActivos();
                    break;
                case 6:
                    reportes();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    public static void altaAlumno() {
        System.out.println("\n--- Alta Alumno ---");

        System.out.print("ID (>0): ");
        int id = sc.nextInt();

        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        if (existeId(id)) {
            System.out.println("ID repetido.");
            return;
        }

        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        if (nombre.isEmpty()) {
            System.out.println("Nombre no puede estar vacío.");
            return;
        }

        System.out.print("Promedio (0 a 10): ");
        double promedio = sc.nextDouble();

        if (promedio < 0 || promedio > 10) {
            System.out.println("Promedio fuera de rango.");
            return;
        }

        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] == null) {
                alumnos[i] = new Alumno(id, nombre, promedio, true);
                System.out.println("Alumno registrado.");
                return;
            }
        }

        System.out.println("Arreglo lleno.");
    }

    public static boolean existeId(int id) {
        for (Alumno a : alumnos) {
            if (a != null && a.id == id) {
                return true;
            }
        }
        return false;
    }

    public static void buscarAlumno() {
        System.out.print("ID a buscar: ");
        int id = sc.nextInt();

        for (Alumno a : alumnos) {
            if (a != null && a.id == id && a.activo) {
                System.out.println("Encontrado:");
                System.out.println("ID: " + a.id);
                System.out.println("Nombre: " + a.nombre);
                System.out.println("Promedio: " + a.promedio);
                return;
            }
        }

        System.out.println("Alumno no encontrado o inactivo.");
    }

    public static void actualizarPromedio() {
        System.out.print("ID del alumno: ");
        int id = sc.nextInt();

        for (Alumno a : alumnos) {
            if (a != null && a.id == id && a.activo) {
                System.out.print("Nuevo promedio (0 a 10): ");
                double nuevo = sc.nextDouble();

                if (nuevo < 0 || nuevo > 10) {
                    System.out.println("Promedio inválido.");
                    return;
                }

                a.promedio = nuevo;
                System.out.println("Promedio actualizado.");
                return;
            }
        }

        System.out.println("Alumno no encontrado o inactivo.");
    }

    public static void bajaLogica() {
        System.out.print("ID del alumno: ");
        int id = sc.nextInt();

        for (Alumno a : alumnos) {
            if (a != null && a.id == id && a.activo) {
                a.activo = false;
                System.out.println("Alumno dado de baja.");
                return;
            }
        }

        System.out.println("Alumno no encontrado.");
    }

    public static void listarActivos() {
        System.out.println("\n--- Alumnos activos ---");

        for (Alumno a : alumnos) {
            if (a != null && a.activo) {
                System.out.println(a.id + " - " + a.nombre + " - " + a.promedio);
            }
        }
    }

    public static void reportes() {

        double suma = 0;
        int contador = 0;

        Alumno mayor = null;
        Alumno menor = null;
        int excelentes = 0;

        for (Alumno a : alumnos) {
            if (a != null && a.activo) {

                suma += a.promedio;
                contador++;

                if (mayor == null || a.promedio > mayor.promedio) {
                    mayor = a;
                }

                if (menor == null || a.promedio < menor.promedio) {
                    menor = a;
                }

                if (a.promedio >= 8.0) {
                    excelentes++;
                }
            }
        }

        if (contador == 0) {
            System.out.println("No hay alumnos activos.");
            return;
        }

        double promedioGeneral = suma / contador;

        System.out.println("\n--- REPORTES ---");
        System.out.println("Promedio general activos: " + promedioGeneral);

        System.out.println("\nMayor promedio:");
        System.out.println(mayor.id + " - " + mayor.nombre + " - " + mayor.promedio);

        System.out.println("\nMenor promedio:");
        System.out.println(menor.id + " - " + menor.nombre + " - " + menor.promedio);

        System.out.println("\nAlumnos con promedio >= 8: " + excelentes);
    }
}
