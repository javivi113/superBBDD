package baseDatos;
public interface Enviable {
	
	public double peso = 0;
	public boolean fragil = false;
	public double tarifa = 0;
	
	public double getPeso();
	public boolean envioFragil();
	public double tarifaEnvio();
}
