package baseDatos;
import java.util.Scanner;

public class Herramienta extends NoPerecedero implements Enviable{
	private final double VALOR_IVA=0.04;
	private final String TIPO="herramienta";
	public Herramienta() {}
	public Herramienta(int cod, String nom, double pre, int cant, double pes) {
		super(cod, nom, pre, cant, pes);
		setIva(VALOR_IVA);
		// TODO Auto-generated constructor stub
	}
	public Herramienta(Scanner x) {
		super(x);
		super.setIva(VALOR_IVA);
	}	
	@Override
	public boolean envioFragil() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public double tarifaEnvio() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		System.out.printf("-Herramienta:");
		super.imprimir();
		
	}
	@Override
	public void imprimirEnvio() {
		String fragil="";
		if (envioFragil()) {
			fragil="Frágil";
		}
		Double precioFinal=(precio+(precio*iva)+tarifaEnvio());
		super.imprimirEnvio();
		System.out.printf("Tarifa de envio: %f  %s PRECIO-TOTAL %f", tarifaEnvio(), fragil,precioFinal);
	}
	@Override
	public String volcar() {
		// TODO Auto-generated method stub
		return super.volcar()+ " " +TIPO;
	}
}
