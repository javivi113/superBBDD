package baseDatos;
import java.util.Scanner;

public abstract class Perecedero extends Producto implements Enviable{
	String fechacad;
	public Perecedero() {}
	public Perecedero(int cod, String nom, double pre, int cant, double pes,String fechacad) {
		super(cod, nom, pre, cant, pes);
		this.fechacad=fechacad;
		// TODO Auto-generated constructor stub
	}

	public Perecedero(Scanner x) {
		
		super(x);
		System.out.printf("Introduce la fecha de caducida del producto: \n");
		while(true) {
			try {				
				this.fechacad= x.next();
				break;
			}catch (Exception e) {
				System.out.printf("Error!\n");
				x.next();
			}			
		}
		
	}

	public String getFechacad() {
		return fechacad;
	}
	public void setFechacad(String fechacad) {
		this.fechacad = fechacad;
	}
	@Override
	public boolean envioFragil() {
		return true;
	};
	@Override
	public double tarifaEnvio() {
		return 1.17;
	};
	@Override
	public void imprimirEnvio() {
		String fragil="";
		if (this.envioFragil()==true) {
			fragil="Frágil";
		}
		Double precioFinal=(precio+(precio*iva)+tarifaEnvio());
		super.imprimirEnvio();
		System.out.printf("Tarifa de envio: %f  %s PRECIO-TOTAL %f", tarifaEnvio(), fragil,precioFinal);
	}
}
