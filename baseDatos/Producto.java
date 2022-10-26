package baseDatos;

import java.io.Serializable;
import java.util.Scanner;

public class Producto implements Serializable{
	double iva;
	int codigo;
	String nombre;
	double precio;
	int cantidad;
	double pesos;

	public Producto(int cod, String nom, double pre, int cant, double pes) {
		this.codigo=cod;
		this.nombre=nom;
		this.precio=pre;
		this.cantidad=cant;
		this.pesos=pes;
	}
	public Producto(Scanner x) {


		System.out.println("Introduce un nombre:");

		while (true){

			try{
				nombre = x.next();
				break;
			}catch (Exception e){
				System.out.println("nombre incorrecto, vuelva a introducirlo");
				x.next();
			}

		}
	
		setCodigo();
				
		System.out.println("Introduce un precio:");

		while (true){

			try{
				precio = x.nextDouble();
				break;
			}catch (Exception e){
				System.out.println("Precio incorrecto, vuelva a introducirlo. Debe ser un numero");
				x.nextLine();
			}

		}

		System.out.println("Introduce una cantidad:");

		while (true){

			try{
				cantidad = x.nextInt();
				break;
			}catch (Exception e){
				System.out.println("Cantidad incorrecta, vuelva a introducirlo. Debe ser un numero entero.");
				x.nextLine();
			}

		}

		System.out.println("Introduce un peso:");

		while (true){

			try{
				pesos = x.nextDouble();
				break;
			}catch (Exception e){
				System.out.println("Peso incorrecto, vuelva a introducirlo. Debe ser un numero.");
				x.nextLine();
			}

		}

	}
	public Producto() {

	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCodigo() {
		this.codigo=Inventario.tamaño()+1;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCodigo() {
		return codigo;
	}
	public double getPrecio() {
		return precio;
	}
	public double getPeso() {
		return pesos;
	}
	public double calcularPercioIva() {
		return precio+((iva*precio)/100);
	}
	public void imprimir() {
		System.out.printf("Producto- %s - %d - %f - %f - cantidad: %d", nombre, codigo, iva, precio, cantidad);
	}
	public void imprimirEnvio() {
		System.out.printf("Id: %d Nombre: %s Peso: %f IVA(%f),",codigo, nombre, pesos, iva);
	}
	public String volcar() {
		return codigo +" "+nombre+" "+precio+" " +cantidad+" "+pesos;
	}


}
