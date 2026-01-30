import java.util.Random;
import java.util.Scanner;

public class AdivinaNumero {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int numeroSecreto = rand.nextInt(100) + 1;
        int intentos = 0;
        int maxIntentos = 7;

        boolean gano = false;

        int fueraDeRango = 0;
        int noNumerico = 0;

        System.out.println("Juego: Adivina el número (1 al 100)");
        System.out.println("Tienes máximo 7 intentos\n");

        while (intentos < maxIntentos && !gano) {

            System.out.print("Intento " + (intentos + 1) + ": Ingresa un número -> ");

            if (!sc.hasNextInt()) {
                System.out.println("Error: No ingresaste un número.");
                noNumerico++;
                sc.next();
                continue;
            }

            int numeroUsuario = sc.nextInt();

            if (numeroUsuario < 1 || numeroUsuario > 100) {
                System.out.println("El número debe estar entre 1 y 100.");
                fueraDeRango++;
                continue;
            }

            intentos++;

            if (numeroUsuario == numeroSecreto) {
                System.out.println("¡Ganaste! Adivinaste el número.");
                gano = true;
            } else if (numeroUsuario < numeroSecreto) {
                System.out.println("El número secreto es MAYOR.");
            } else {
                System.out.println("El número secreto es MENOR.");
            }
        }

        System.out.println("\n Resultados:");
        System.out.println("Entradas fuera de rango: " + fueraDeRango);
        System.out.println("Entradas no numéricas: " + noNumerico);

        if (!gano) {
            System.out.println("Perdiste. El número secreto era: " + numeroSecreto);
        }

        sc.close();
    }
}
