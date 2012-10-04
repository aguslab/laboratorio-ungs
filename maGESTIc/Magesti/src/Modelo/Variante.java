package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Variante {
	
	private Integer id_variante;
	private String nombre;
	
	
	public Variante(Integer id_variante, String nombre) {
		super();
		this.id_variante = id_variante;
		this.nombre = nombre;
	}

	public Variante(String nombre) {
		super();
		this.nombre = nombre;
	}

	public static Integer getId_Variante(String nombreElegido)
	{
		Integer id_var = null;
		nombreElegido = "'" + nombreElegido + "'";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_variante FROM variante WHERE nombre ="+ nombreElegido);
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					id_var = resultado.getInt(1);
					break;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
		
		return id_var;
	}
	
	public Integer getId_variante() {
		return id_variante;
	}


	public void setId_variante(Integer id_variante) {
		this.id_variante = id_variante;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public static  String[] getVariantes()
	{
		ArrayList<String> var=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT nombre FROM variante");
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					var.add(resultado.getString("nombre"));
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		String [] vars= new String[var.size()];
		
		for(int i=0;i<var.size();i++)
		{
			vars[i]=var.get(i);
		}
		
		return vars;
	}
	
	public boolean Alta() {
		String nombre = this.getNombre();

		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO variante VALUES(default," + "'" + nombre
						+ "'" + ");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Variante> Buscar() {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM variante");

		ArrayList<Variante> list_Var = new ArrayList<Variante>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Variante variante = new Variante(new Integer(
							resultado.getInt("id_variante")),
							resultado.getString("nombre"));
					list_Var.add(variante);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_Var;
	}
	
	
	
	
	
	
}
