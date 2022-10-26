package baseDatos;

import java.util.Scanner;


public class Bebida extends Perecedero {
	public String getGraduacion() {
		return graduacion;
	}
	public void setGraduacion(String graduacion) {
		this.graduacion = graduacion;
	}
	private String graduacion;
	private final double VALOR_IVA=0.16;
	private final String TIPO="bebida";
	public Bebida() {}
	public Bebida(int cod, String nom, double pre, int cant, double pes, String fechacad, String graduacion) {
		super(cod, nom, pre, cant, pes, fechacad);
		this.graduacion=graduacion;
		super.setIva(VALOR_IVA);
		// TODO Auto-generated constructor stub
	}
	
	public Bebida(Scanner x) {
		super(x);
		System.out.printf("Introduce la graduacion del producto: \n");
		while(true) {
			try {				
				this.graduacion= x.next();
				break;
			}catch (Exception e) {
				System.out.printf("Error!\n");
				x.next();
			}			
		}
		super.setIva(VALOR_IVA);
	}
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		System.out.printf("-Bebida:");
		super.imprimir();
		System.out.printf(", graduacion: "+graduacion);
	}
	@Override
	public boolean envioFragil() {
		// TODO Auto-generated method stub
		return super.envioFragil();
	}
	public void calcularPrecioEnvio(){
		
	}
	@Override
	public String volcar() {
		// TODO Auto-generated method stub
		return super.volcar()+" "+graduacion +" "+TIPO;
	}
	
}
