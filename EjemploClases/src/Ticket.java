public class Ticket {

    public Ticket(){} //Constructor

    private final double IVA=0.16;
    private final double PRECIO_PRODUCTO=10;
    public double subtotal;
    public double total;
//private final double DESCUENTO=0.10;

    public void process(int cantidad){
        calcularSubtotal(cantidad);
        calcularTotal();
    }

    private void calcularSubtotal(int cantidad){
        this.subtotal = cantidad*PRECIO_PRODUCTO;
    }

    private double calcularIva(){
        return this.subtotal*IVA;
    }

    private void calcularTotal(){
        this.total= subtotal + calcularIva();
    }

    public void imprimirTicket(int cantidad){

        System.out.println("Cantidad: "+cantidad);
        System.out.println("Subtotal: "+this.subtotal);
        System.out.println("Iva: "+calcularIva());
        System.out.println("Total: "+this.total);

    }