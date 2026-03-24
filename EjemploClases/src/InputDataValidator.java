import java.util.Scanner;

public class InputDataValidator {

    public int getValidInt(Scanner scanner, String msg){
        int value;
        while (true) {
            System.out.println(msg);
            if(scanner.hasNextInt()){
                value=scanner.nextInt();
                if(value>0){
                    return value;
                }
                System.out.println("El dato esta fuera de rango (1-n)");
            }else{
                System.out.println("El dato no es numerico");
                scanner.next();
            }
        }
    }
}