package baseDatos;
import java.util.Scanner;

public class NoPerecedero extends Producto {
	public NoPerecedero(int cod, String nom, double pre, int cant, double pes) {
		super(cod, nom, pre, cant, pes);
	}
	public NoPerecedero() {}
	public NoPerecedero(Scanner x) {			
		super(x);
	}
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		super.imprimir();
		
	}
}
