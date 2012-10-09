package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Procesos_x_OT 
{
	
	private Integer id_proceso;
	private Integer id_orden_trabajo;
	private boolean tercerizada;
	private Integer id_proveedor;
	private boolean cumplida;
	private String observacion;
	
	
	
	public Procesos_x_OT(Integer id_proceso, Integer id_orden_trabajo, boolean tercerizada,
			Integer id_proveedor,boolean estado, String observacion) 
	{
		super();
		this.id_proceso = id_proceso;
		this.id_orden_trabajo = id_orden_trabajo;
		this.tercerizada= tercerizada;
		this.id_proveedor = id_proveedor;
		this.cumplida=estado;
		this.observacion = observacion;
	}
	
		

	public boolean isTercerizada() 
	{
		return tercerizada;
	}



	public void setTercerizada(boolean tercerizada) 
	{
		this.tercerizada = tercerizada;
	}



	public Integer getId_proceso() 
	{
		return id_proceso;
	}


	public void setId_proceso(Integer id_proceso) 
	{
		this.id_proceso = id_proceso;
	}


	public Integer getId_orden_trabajo() 
	{
		return id_orden_trabajo;
	}


	public void setId_orden_trabajo(Integer id_orden_trabajo) 
	{
		this.id_orden_trabajo = id_orden_trabajo;
	}


	public Integer getId_proveedor() 
	{
		return id_proveedor;
	}


	public void setId_proveedor(Integer id_proveedor) 
	{
		this.id_proveedor = id_proveedor;
	}


		
	public boolean isCumplida() 
	{
		return cumplida;
	}

	public void setCumplida(boolean cumplida) 
	{
		this.cumplida = cumplida;
	}

	public String getObservacion() 
	{
		return observacion;
	}


	public void setObservacion(String observacion) 
	{
		this.observacion = observacion;
	}
	
	
	public boolean Alta() {
		Integer id_proc=this.getId_proceso();
		Integer id_ot=this.getId_orden_trabajo();
		Integer id_prov=this.getId_proveedor();
		boolean status=this.isCumplida();
		boolean tercerizada = this.isTercerizada();
		String obser="'"+this.getObservacion()+"'";
		

		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO procesos_x_orden_trabajo VALUES("+id_proc+"," + id_ot+"," + tercerizada+"," + id_prov+","
						+ status+","+obser + ");")) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	public ArrayList<Procesos_x_OT> Buscar() 
	{

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM procesos_x_orden_trabajo");

		ArrayList<Procesos_x_OT> list_prox_x_orden = new ArrayList<Procesos_x_OT>();
		if (resultado != null) 
		{

			try 
			{

				while (resultado.next()) 
				{
					Procesos_x_OT proc_x_ot = new Procesos_x_OT(new Integer(
							resultado.getInt("id_proceso")), new Integer(
							resultado.getInt("id_orden_trabajo")),
							resultado.getBoolean("tercerizada"), new Integer(
									resultado.getInt("id_proveedor")),
							resultado.getBoolean("cumplida"),
							resultado.getString("observacion"));
					list_prox_x_orden.add(proc_x_ot);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		return list_prox_x_orden;
	}
	
	
	public static ArrayList<String> BuscarProc_x_OT(Integer id_OT) 
	{

		ResultSet resultado = ConexionDB.getbaseDatos()
				.consultar("SELECT id_proceso FROM procesos_x_orden_trabajo where id_orden_trabajo="
						+ id_OT);
		
		ArrayList<String> list_proc = new ArrayList<String>();
		if (resultado != null) 
		{
			try
			{
				while (resultado.next()) 
				{
					Integer id_proc = resultado.getInt("id_proceso");
					ResultSet process=ConexionDB.getbaseDatos().consultar("SELECT nombre from proceso where id_proceso="+id_proc);
					
					if(process != null)
					{
						try 
						{
							while(process.next())
							{
								String nomb=process.getString("nombre");
								list_proc.add(nomb);
							}
							
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}					
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return list_proc;
	}
	
	public static ArrayList<Boolean> getTercerizada(Integer id_OT)
	{
		ArrayList<Boolean> valores = new ArrayList<Boolean>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT tercerizada FROM procesos_x_orden_trabajo WHERE id_orden_trabajo="+ id_OT);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getBoolean("tercerizada"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}

	public static ArrayList<Integer> getProveedor(Integer id_OT)
	{
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_proveedor FROM procesos_x_orden_trabajo WHERE id_orden_trabajo="+ id_OT);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("id_proveedor"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	public static boolean setAvanceOT(Integer id_OT,Integer id_Proc,boolean n){
		if(ConexionDB.getbaseDatos().ejecutar("UPDATE procesos_x_orden_trabajo SET cumplida="+n+ " WHERE id_orden_trabajo="+id_OT+" AND id_proceso="+id_Proc)){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static ArrayList<Boolean> getCumplida(Integer id_OT)
	{
		ArrayList<Boolean> valores = new ArrayList<Boolean>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT cumplida FROM procesos_x_orden_trabajo WHERE id_orden_trabajo="+ id_OT);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getBoolean("cumplida"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	public static ArrayList<String> getObservaciones(Integer id_OT)
	{
		ArrayList<String> valores = new ArrayList<String>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT observacion FROM procesos_x_orden_trabajo WHERE id_orden_trabajo="+ id_OT);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					//Pongo esta condicion para que no salga "null" en la columna
					if(!resultado.getString("observacion").equals("null"))
					{

						valores.add(resultado.getString("observacion"));
					}
					else
					{
						valores.add("");
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	public static Integer getCantidadFilas(Integer id_OT)
	{
		Integer cantidad = 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT COUNT(*) FROM procesos_x_orden_trabajo WHERE id_orden_trabajo="+ id_OT);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					cantidad=resultado.getInt(1);
					break;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return cantidad;
	}
}
