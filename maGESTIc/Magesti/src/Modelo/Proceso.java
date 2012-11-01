package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Proceso 
{
	
	private Integer id_proceso;
	private String nombre;
	private Boolean activo;
	
	public Proceso(Integer id_proceso, String nombre, Boolean activo) 
	{
		super();
		this.id_proceso = id_proceso;
		this.nombre = nombre;
		this.activo=activo;
	}
	
	

	public Proceso(String nombre,Boolean activo) 

	{
		super();
		this.nombre = nombre;
		this.activo=activo;
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
	
	public Boolean getActivo() 
	{
		return this.activo;
	}


	public void setActivo(Boolean activo) 
	{
		this.activo = activo;
	}
	

	public static boolean updateProceso(String id, String nombre, Boolean activo)
	{
		boolean r=ConexionDB.getbaseDatos().ejecutar(
				"UPDATE proceso SET nombre = " + "'"+nombre+"'" + ", activo=" + activo
						+ " WHERE id_proceso ="
						+ Integer.parseInt(id));
		return r;
	}
	
	
	public boolean Alta() 
	{
		String nombre = "'"+this.getNombre()+"'";
		Boolean act=this.getActivo();
		
		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO proceso VALUES(default," +nombre+","+act+");")) 
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
							resultado.getString("nombre"),resultado.getBoolean("activo"));
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

	public static  String[] getProcesos()
	{
		ArrayList<String> pros = new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT nombre FROM proceso WHERE activo = true");
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					pros.add(resultado.getString("nombre"));
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		String [] procesos= new String[pros.size()];
		
		for(int i=0;i<pros.size();i++)
		{
			procesos[i]=pros.get(i);
		}
		
		return procesos;
	}
	
	
	public static String getNombreProceso(Integer id_proc) 
	{
		String nombre_proces = "";

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT nombre FROM proceso WHERE id_proceso=" + id_proc);

		if (resultado != null) 
		{
			try {// por si llega a haber mas de un proceso con = nombre
				while (resultado.next()) 
				{
					nombre_proces = resultado.getString(1);
					break;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		return nombre_proces;
	}
	
}
