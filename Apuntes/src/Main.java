public class Main {
    public static void main(String[] args) {

//Herencia
        Perro dogo= new Perro("Dogo");
        Gato rawrl = new Gato("rawrl");

        dogo.comer();
        dogo.hacerSonido();
        dogo.marcarTerritorio();

        rawrl.comer();
        rawrl.hacerSonido();
        System.out.println("-------------");
//Polimorfismo
        Animal[] animales = new Animal[3];
        animales[0] = new Perro("Ramonchis");
        animales[1] = new Gato("Salem");
        animales[2] = new Perro("Firulais");
        for(Animal animal : animales){
            animal.hacerSonido();
        }
        System.out.println("___________________-");

        Animal miercoles = new Perro("miercoles");

        if(miercoles instanceof Perro){
            Perro miercoles1= (Perro) miercoles;
            miercoles1.marcarTerritorio();
        }


    }
}
