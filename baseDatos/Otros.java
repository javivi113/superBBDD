package baseDatos;
import java.util.Scanner;

public class Otros extends NoPerecedero{
	public String categoria;
	private final double VALOR_IVA=0.16;
	private final String TIPO="otros";
	
	public Otros() {}
	public Otros(int cod, String nom, double pre, int cant, double pes, String categoria) {
		super(cod, nom, pre, cant, pes);
		// TODO Auto-generated constructor stub
		this.categoria=categoria;
	}
	public Otros(Scanner x) {
		super(x);
		System.out.printf("Introduce el lote del producto: \n");
		while(true) {
			try {				
				this.categoria= x.next();
				break;
			}catch (Exception e) {
				System.out.printf("Error!\n");
				x.next();
			}			
		}
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String volcar() {
		// TODO Auto-generated method stub
		return super.volcar()+" "+categoria+" "+TIPO;
	}
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		System.out.printf("-Otros:");
		super.imprimir();
		System.out.printf(", categoria: "+categoria);
	}

}
