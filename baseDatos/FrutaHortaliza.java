package baseDatos;
import java.util.Scanner;

public class FrutaHortaliza extends Perecedero {
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	private String origen;
	private final double VALOR_IVA=0.16;
	private final String TIPO="frutahortaliza";
	public FrutaHortaliza() {}
	public FrutaHortaliza(int cod, String nom, double pre, int cant, double pes, String fechacad, String origen) {
		super(cod, nom, pre, cant, pes, fechacad);
		this.origen=origen;
		setIva(VALOR_IVA);
		// TODO Auto-generated constructor stub
	}
	
	public FrutaHortaliza(Scanner x) {
		super(x);
		System.out.printf("Introduce el origen del producto: \n");
		while(true) {
			try {				
				this.origen= x.next();
				break;
			}catch (Exception e) {
				System.out.printf("Error!\n");
				x.next();
			}			
		}
		setIva(VALOR_IVA);
	}
	public void imprimir() {
		System.out.printf("-fruta/hortaliza: ");
		super.imprimir();
		System.out.printf(", origen: "+origen);
	}
	@Override
	public boolean envioFragil() {
		// TODO Auto-generated method stub
		return false;
	}
	public void calcularPrecioEnvio(){
		
	}
	@Override
	public String volcar() {
		// TODO Auto-generated method stub
		return super.volcar()+" "+origen+" "+TIPO;
	}



}
