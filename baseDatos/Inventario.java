package baseDatos;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.*;

public class Inventario{
	private static Connection c = null;
	private final static String user="root";
	private final static String pass="a";
	private static  Inventario single;	
	private Inventario(){
		c= bbdd();
	}
	public static Inventario getInstance(){
		if(single == null)
			single = new Inventario();
		return single;
	}
	public static void cargarProductos(){
		try {			
			String sql= "SELECT * FROM inventario;";
			Statement st= c.createStatement();
			ResultSet r = st.executeQuery(sql);
			while (r.next()) {
				System.out.println(
						String.format("%1$-"+11+"s", r.getInt(0)+"")+" | "+
								String.format("%1$-"+22+"s", r.getString(1))+" | "+
								String.format("%1$-"+11+"s", r.getFloat(2)+"" )+" | "+
								String.format("%1$-"+1+"s", r.getInt(3)+"" )+" | "+
								String.format("%1$-"+11+"s", r.getFloat(4)+"" )+" | "+
								String.format("%1$-"+22+"s", r.getString(5)+"" )+" | "+
								String.format("%1$-"+22+"s", r.getString(6)+"" )+" | "+
								String.format("%1$-"+22+"s", r.getString(7)+"" )+" | "+
								String.format("%1$-"+22+"s", r.getString(8)+"" )+" | "+
								String.format("%1$-"+22+"s", r.getString(9)+"" )+" | "+
								String.format("%1$-"+22+"s", r.getString(10)+"" ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void addNuevoProducto(Producto producto){
		try {	
			PreparedStatement pst= c.prepareStatement("INSERT INTO inventario("
					+ "producto, precio, cantidad,"
					+ "peso, fecha_caducidad, lote, graduacion,"
					+ "origen, categoria, tipoProducto) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, producto.getNombre());
			pst.setFloat(2, (float) producto.getPrecio());
			pst.setInt(3, producto.getCantidad());
			pst.setFloat(4, (float) producto.getPeso());
			String fecha="-";
			String lote="-";
			String gradu="-";
			String orig="-";
			String cate="-";
			String tipo="-";
			if (producto instanceof FrutaHortaliza) {
				fecha=((FrutaHortaliza)producto).getFechacad();				
				orig=((FrutaHortaliza)producto).getOrigen();
				tipo="FrutaHortaliza";
			}
			if (producto instanceof Bebida) {
				fecha=((Bebida) producto).getFechacad();
				gradu=((Bebida) producto).getGraduacion();
				tipo="Bebida";
			}
			if (producto instanceof Lacteo) {
				fecha=((Lacteo)producto).getFechacad();
				lote=((Lacteo) producto).getLote();
				tipo="Lacteo";
			}
			if (producto instanceof Otros) {
				cate=((Otros)producto).getCategoria();				
				tipo="Otros";
			}
			if (producto instanceof Herramienta) {
				tipo="Herramienta";
			}
			pst.setString(5, fecha);
			pst.setString(6, lote);
			pst.setString(7, gradu);
			pst.setString(8, orig);
			pst.setString(9, cate);
			pst.setString(10, tipo);
			pst.executeUpdate();
			pst.getGeneratedKeys();
			producto.imprimir();
			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			System.out.println("\nProducto añadido, número "+rs.getInt(1) );
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void mostrarProductos(){
		System.out.printf("**************************\n");
		System.out.printf("** Listado de productos **\n");
		try {			
			String sql= "SELECT * FROM inventario;";
			Statement st= c.createStatement();
			ResultSet r = st.executeQuery(sql);
			System.out.println(
					String.format("%1$-"+5+"s", "ID")+" | "+
							String.format("%1$-"+20+"s", "Nombre")+" | "+
							String.format("%1$-"+11+"s", "Precio" )+" | "+
							String.format("%1$-"+8+"s", "Cantidad" )+" | "+
							String.format("%1$-"+11+"s", "Peso" )+" | "+
							String.format("%1$-"+20+"s", "Fecha_Caducidad" )+" | "+
							String.format("%1$-"+20+"s", "Lote" )+" | "+
							String.format("%1$-"+20+"s", "Graduacion" )+" | "+
							String.format("%1$-"+20+"s", "Origen" )+" | "+
							String.format("%1$-"+20+"s", "Categoria" )+" | "+
							String.format("%1$-"+20+"s", "Tipo_producto" ));
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________\n");
			while (r.next()) {
				System.out.println(
						String.format("%1$-"+5+"s", r.getInt(1)+"")+" | "+
								String.format("%1$-"+20+"s", r.getString(2))+" | "+
								String.format("%1$-"+11+"s", r.getFloat(3)+"" )+" | "+
								String.format("%1$-"+8+"s", r.getInt(4)+"" )+" | "+
								String.format("%1$-"+11+"s", r.getFloat(5)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(6)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(7)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(8)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(9)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(10)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(11)+"" ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("**************************\n");
	}

	public static Producto getProducto(int id){
		try {
			PreparedStatement pst= c.prepareStatement("SELECT * FROM inventario where id=?");			
			pst.setInt(1, id);
			ResultSet r = pst.executeQuery();
			r.next();
			switch (r.getString("tipoProducto")) {
			case "Lacteo": {
				return new Lacteo(r.getInt(id),r.getString("Nombre"),r.getDouble("precio"),r.getInt("cantidad"),r.getDouble("peso"),r.getString("fecha_caducidad"),r.getString("lote"));	
			}
			case "Bebida": {
				return new FrutaHortaliza(r.getInt(id),r.getString("Nombre"),r.getDouble("precio"),r.getInt("cantidad"),r.getDouble("peso"),r.getString("fecha_caducidad"),r.getString("graduacion"));
			}
			case "Herramienta": {
				return new Herramienta(r.getInt(id),r.getString("Nombre"),r.getDouble("precio"),r.getInt("cantidad"),r.getDouble("peso"));
			}
			case "Otros": {
				return new Otros(r.getInt(id),r.getString("Nombre"),r.getDouble("precio"),r.getInt("cantidad"),r.getDouble("peso"),r.getString("categoria"));
			}
			default:
				return new FrutaHortaliza(r.getInt(id),r.getString("Nombre"),r.getDouble("precio"),r.getInt("cantidad"),r.getDouble("peso"),r.getString("fecha_caducidad"),r.getString("origen"));
			}
		} catch (Exception e) {
			return null;
		}
	}
	public static void actualizarCantidad(int codigo, int cantidad){
		try {
			PreparedStatement pst= c.prepareStatement("UPDATE inventario SET cantidad=? where id=?");			
			pst.setInt(1, cantidad);
			pst.setInt(2, codigo);
			pst.executeUpdate();
			Producto p= getProducto(codigo);
			p.imprimir();
		} catch (Exception e) {	}
	}
	public static int tamaño(){
		try {
			String sql= "SELECT COUNT(*) FROM inventario;";
			Statement st= c.createStatement();
			ResultSet r = st.executeQuery(sql);
			r.next();
			return r.getInt(1);
		} catch (Exception e) {
			return 0;
		}
	}
	public static void mostrarProductosEnviables(){
		System.out.printf("**************************\n");
		System.out.printf("** Listado de productos **\n");
		try {			
			String sql= "SELECT * FROM inventario where categoria='-';";
			Statement st= c.createStatement();
			ResultSet r = st.executeQuery(sql);
			System.out.println(
					String.format("%1$-"+5+"s", "ID")+" | "+
							String.format("%1$-"+20+"s", "Nombre")+" | "+
							String.format("%1$-"+11+"s", "Precio" )+" | "+
							String.format("%1$-"+8+"s", "Cantidad" )+" | "+
							String.format("%1$-"+11+"s", "Peso" )+" | "+
							String.format("%1$-"+20+"s", "Fecha_Caducidad" )+" | "+
							String.format("%1$-"+20+"s", "Lote" )+" | "+
							String.format("%1$-"+20+"s", "Graduacion" )+" | "+
							String.format("%1$-"+20+"s", "Origen" )+" | "+
							String.format("%1$-"+20+"s", "Categoria" )+" | "+
							String.format("%1$-"+20+"s", "Tipo_producto" ));
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________\n");
			while (r.next()) {
				System.out.println(
						String.format("%1$-"+5+"s", r.getInt(1)+"")+" | "+
								String.format("%1$-"+20+"s", r.getString(2))+" | "+
								String.format("%1$-"+11+"s", r.getFloat(3)+"" )+" | "+
								String.format("%1$-"+8+"s", r.getInt(4)+"" )+" | "+
								String.format("%1$-"+11+"s", r.getFloat(5)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(6)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(7)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(8)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(9)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(10)+"" )+" | "+
								String.format("%1$-"+20+"s", r.getString(11)+"" ));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.printf("**************************\n");
	}
	public static void eliminarProducto(int id){
		try {
			PreparedStatement pst= c.prepareStatement("DELETE FROM inventario WHERE id=?");			
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("Producto eliminado...");
		} catch (Exception e) {}
	}
	public static Connection bbdd(){
		Connection c=null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3333/superonline",user,pass);

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return c;
	}
}