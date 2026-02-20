public class Gato extends Animal {

    public Gato(String name){
        super(name);
    }

    @Override


    public void hacerSonido(){
        System.out.println(name+" hace Miau o Rawrl");
    }
}