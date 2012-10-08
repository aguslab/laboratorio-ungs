package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Proceso 
{
	
	private Integer id_proceso;
	private String nombre;
	
	
	public Proceso(Integer id_proceso, String nombre) 
	{
		super();
		this.id_proceso = id_proceso;
		this.nombre = nombre;
	}
	
	
	public Proceso(String nombre) 
	{
		super();
		this.nombre = nombre;
	}
	
	
	public static  String[] getProcesos()
	{
		ArrayList<String> proc=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT nombre FROM proceso");
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					proc.add(resultado.getString("nombre"));
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		String [] Nombre_procesos= new String[proc.size()];
		
		for(int i=0;i<proc.size();i++)
		{
			Nombre_procesos[i]=proc.get(i);
		}
		
		return Nombre_procesos;
	}
	
	
	
	public static Integer getIdProceso(String nombre) 
	{
		Integer id_proces = null;

		nombre = "'" + nombre + "'";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_proceso FROM proceso WHERE nombre=" + nombre);

		if (resultado != null) 
		{
			try {// por si llega a haber mas de un proceso con = nombre
				while (resultado.next()) 
				{
					id_proces = resultado.getInt(1);
					break;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		return id_proces;
	}


	public Integer getId_proceso() 
	{
		return id_proceso;
	}


	public void setId_proceso(Integer id_proceso) 
	{
		this.id_proceso = id_proceso;
	}


	public String getNombre() 
	{
		return nombre;
	}


	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	
	public boolean Alta() 
	{
		String nombre = this.getNombre();

		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO proceso VALUES(default," + "'"+nombre+"'"+");")) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	
	public ArrayList<Proceso> Buscar() 
	{

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM proceso");

		ArrayList<Proceso> list_Proc = new ArrayList<Proceso>();
		if (resultado != null) 
		{

			try 
			{

				while (resultado.next()) 
				{
					Proceso proceso= new Proceso(new Integer(
							resultado.getInt("id_proceso")),
							resultado.getString("nombre"));
					list_Proc.add(proceso);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		return list_Proc;
	}

}
