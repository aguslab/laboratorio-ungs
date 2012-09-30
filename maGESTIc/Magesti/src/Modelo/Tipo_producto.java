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

		if (ConexionDB.baseDatos
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

		ResultSet resultado = ConexionDB.baseDatos.consultar("SELECT * FROM tipo_producto");

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
}
