import java.util.Scanner;

public class SistemaPersonas {

    static Persona[] personas = new Persona[20];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1) Alta");
            System.out.println("2) Buscar por ID (solo activas)");
            System.out.println("3) Baja lógica por ID");
            System.out.println("4) Listar activas");
            System.out.println("5) Actualizar nombre por ID (solo activas)");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción inválida. Debe ser número.");
                sc.next();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    alta();
                    break;
                case 2:
                    buscarActiva();
                    break;
                case 3:
                    bajaLogica();
                    break;
                case 4:
                    listarActivas();
                    break;
                case 5:
                    actualizarNombre();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }

        } while (opcion != 0);
    }

    public static void alta() {
        System.out.print("ID (>0): ");
        int id = sc.nextInt();
        sc.nextLine();

        if (id <= 0) {
            System.out.println("El ID debe ser mayor a 0.");
            return;
        }

        if (buscarPorId(id) != -1) {
            System.out.println("No se permite ID repetido.");
            return;
        }

        System.out.print("Nombre (no vacío): ");
        String nombre = sc.nextLine();

        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        for (int i = 0; i < personas.length; i++) {
            if (personas[i] == null) {
                personas[i] = new Persona(id, nombre);
                System.out.println("Persona dada de alta.");
                return;
            }
        }

        System.out.println("Arreglo lleno, no se pueden agregar más personas.");
    }

    public static void buscarActiva() {
        System.out.print("ID a buscar: ");
        int id = sc.nextInt();

        int pos = buscarPorId(id);

        if (pos != -1 && personas[pos].activa) {
            System.out.println("Encontrada: " + personas[pos].nombre);
        } else {
            System.out.println("Persona no encontrada o inactiva.");
        }
    }

    public static void bajaLogica() {
        System.out.print("ID para baja lógica: ");
        int id = sc.nextInt();

        int pos = buscarPorId(id);

        if (pos != -1 && personas[pos].activa) {
            personas[pos].activa = false;
            System.out.println("Persona dada de baja lógica.");
        } else {
            System.out.println("Persona no encontrada o ya inactiva.");
        }
    }

    public static void listarActivas() {
        System.out.println("\n--- PERSONAS ACTIVAS ---");
        boolean hayActivas = false;

        for (Persona p : personas) {
            if (p != null && p.activa) {
                System.out.println("ID: " + p.id + " | Nombre: " + p.nombre);
                hayActivas = true;
            }
        }

        if (!hayActivas) {
            System.out.println("No hay personas activas.");
        }
    }

    public static void actualizarNombre() {
        System.out.print("ID a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        int pos = buscarPorId(id);

        if (pos != -1 && personas[pos].activa) {

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = sc.nextLine();

            if (nuevoNombre.trim().isEmpty()) {
                System.out.println("Nombre inválido.");
                return;
            }

            personas[pos].nombre = nuevoNombre;
            System.out.println("Nombre actualizado.");

        } else {
            System.out.println("Persona no encontrada o inactiva.");
        }
    }

    public static int buscarPorId(int id) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] != null && personas[i].id == id) {
                return i;
            }
        }
        return -1;
    }
}
