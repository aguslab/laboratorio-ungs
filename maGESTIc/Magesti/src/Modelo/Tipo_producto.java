package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Tipo_producto 
{
	private Integer id_producto;
	private String nombre;
	private Integer id_materiales;
	
	public Tipo_producto(Integer id_producto, String nombre, Integer id_materiales) 
	{
		super();
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.id_materiales = id_materiales;
	}
	
	public Tipo_producto(String nombre, Integer id_materiales) 
	{
		super();
		this.nombre = nombre;
		this.id_materiales = id_materiales;
	}

	
	public static String[] getTiposProd(){
		ArrayList<String> Prod=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT nombre FROM tipo_producto");
		
		if (resultado != null) {
			try {
				while (resultado.next()) {
					Prod.add(resultado.getString("nombre"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String [] productos= new String[Prod.size()];
		
		for(int i=0;i<Prod.size();i++){
			productos[i]=Prod.get(i);
		}
		
		return productos;
	}
	
	
	
	
	public Integer getId_producto() 
	{
		return id_producto;
	}

	public void setId_producto(Integer id_producto) 
	{
		this.id_producto = id_producto;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public Integer getId_materiales() 
	{
		return id_materiales;
	}

	public void setId_materiales(Integer id_materiales) 
	{
		this.id_materiales = id_materiales;
	}
	
	
	public boolean Alta() 
	{
		String nombre = this.getNombre();
		Integer id_materiales = this.getId_materiales();

		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO tipo_producto VALUES(DEFAULT," + "'" + nombre + "'"+"," + id_materiales + ");"))

		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	
	public ArrayList<Tipo_producto> Buscar() 
	{

		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT * FROM tipo_producto");

		ArrayList<Tipo_producto> list_productos = new ArrayList<Tipo_producto>();
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					Tipo_producto tipo_productos= new Tipo_producto(new Integer(
							new Integer(resultado.getInt("id_producto"))),resultado.getString("nombre"), resultado.getInt("id_materiales"));
					list_productos.add(tipo_productos);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return list_productos;
		}
	
	public void mostrarDatos(){
		System.out.println();
		System.out.print(this.getId_producto()+"    ");
		System.out.print(this.getNombre()+"     ");
		System.out.print(this.getId_materiales()+"     ");
	}

	public static Integer dameIDProducto(String tipoProducto) {
		Integer id_Producto=null;
		tipoProducto="'"+tipoProducto+"'";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_producto FROM tipo_producto where nombre="+tipoProducto);
		
		if (resultado != null) {
			try {//por si llega a haber mas de un cliente con = nombreDeProducto
				while (resultado.next()) {
					id_Producto=resultado.getInt(1);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		return id_Producto;
	}
}
