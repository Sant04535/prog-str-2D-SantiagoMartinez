public class Perro extends Animal{

    public Perro(String name){
        super(name);
    }

    @Override
    public void hacerSonido() {
        System.out.println(name+" hace guau guau! ");
    }

    public void marcarTerritorio(){
        System.out.println(name+" esta alazando su patita trasera...");
    }
}