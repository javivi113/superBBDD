package baseDatos;
import java.util.Scanner;

public class Lacteo extends Perecedero{
	
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	private String lote;
	private final double VALOR_IVA=0.04;
	private final String TIPO="Lacteo";
	public Lacteo() {}
	public Lacteo(int cod, String nom, double pre, int cant, double pes, String fechacad,String lote) {
		super(cod, nom, pre, cant, pes, fechacad);
		this.lote=lote;
		setIva(VALOR_IVA);
		// TODO Auto-generated constructor stub
	}
	public Lacteo(Scanner x) {
		super(x);
		boolean noValido=true;	
		System.out.printf("Introduce el lote del producto: \n");
		while(noValido) {
			try {				
				this.lote= x.next();
				noValido=false;
			}catch (Exception e) {
				System.out.printf("Error!\n");
				x.next();
			}			
		}
		setIva(VALOR_IVA);
	}
	public void imprimir() {
		System.out.printf("-"+TIPO+" ");
		super.imprimir();
		System.out.printf(", lote: "+lote);
	}
	@Override
	public boolean envioFragil() {
		// TODO Auto-generated method stub
		return false;
	}
	public void calcularPrecioEnvio(){
		System.out.println("El precio es de "+(precio+(precio*(2*iva))));
	}
	@Override
	public String volcar() {
		// TODO Auto-generated method stub
		return super.volcar()+" "+lote +" "+TIPO;
	}

}
