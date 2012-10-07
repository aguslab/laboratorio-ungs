package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Elemento {

	private Integer id_elemento;
	private Integer id_orden_trabajo;
	private String tipo_elemento;
	private Integer cantidad;
	
	
		
	
	public Elemento(Integer id_elemento, Integer id_orden_trabajo,
			String tipo_elemento, Integer cantidad) {
		super();
		this.id_elemento = id_elemento;
		this.id_orden_trabajo = id_orden_trabajo;
		this.tipo_elemento = tipo_elemento;
		this.cantidad = cantidad;
	}

	public Elemento(Integer id_orden_trabajo, String tipo_elemento,
			Integer cantidad) {
		super();
		this.id_orden_trabajo = id_orden_trabajo;
		this.tipo_elemento = tipo_elemento;
		this.cantidad = cantidad;
	}
		
	public static Integer getMaxId_elemento()
	{
		Integer maxId = null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar
				("SELECT MAX(id_elemento) FROM elemento");

		if (resultado != null) 
		{
			try 
			{
				while (resultado.next())
				{
					// como solo devuelve un valor, le pido el del registro (1)
					maxId = resultado.getInt(1);
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return maxId;
	}
		
	public Integer getId_elemento() {
		return id_elemento;
	}

	public void setId_elemento(Integer id_elemento) {
		this.id_elemento = id_elemento;
	}

	public Integer getId_orden_trabajo() {
		return id_orden_trabajo;
	}

	public void setId_orden_trabajo(Integer id_orden_trabajo) {
		this.id_orden_trabajo = id_orden_trabajo;
	}

	public String getTipo_elemento() {
		return tipo_elemento;
	}

	public void setTipo_elemento(String tipo_elemento) {
		this.tipo_elemento = tipo_elemento;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public static ArrayList<String> cosasDeElemento(Integer id_OT)
	{
		ArrayList<String> valores = new ArrayList<String>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT tipo_elemento, cantidad FROM elemento WHERE id_orden_trabajo ="+ id_OT);
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					valores.add(resultado.getString("tipo_elemento"));
					Integer cant=resultado.getInt("cantidad");
					valores.add(cant.toString());
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
		
		return valores;
	}
	
	public static Integer getMaxId_Elemento() {
		Integer maxId = null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT MAX(id_elemento) FROM elemento");

		if (resultado != null) {

			try {

				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					maxId = resultado.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return maxId + 1;
	}
	
	public boolean Alta() {
		Integer id_ot = this.getId_orden_trabajo();
		String tipo_elem = "'" + this.getTipo_elemento() + "'";
		Integer cant = this.getCantidad();

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO elemento VALUES(DEFAULT," + id_ot + ","
						+ tipo_elem + "," + cant + ");")) {
			return true;
		} else {
			return false;
		}
	}
		
		
		public ArrayList<Elemento> Buscar() {

			ResultSet resultado = ConexionDB.getbaseDatos().consultar(
					"SELECT * FROM elemento");

			ArrayList<Elemento> list_Elementos= new ArrayList<Elemento>();
			if (resultado != null) {

				try {

					while (resultado.next()) {
						Elemento Elem= new Elemento(new Integer(
								resultado.getInt("id_elemento")),new Integer(
										resultado.getInt("id_orden_trabajo")),
								resultado.getString("tipo_elemento"),new Integer(resultado.getInt("cantidad")));
						list_Elementos.add(Elem);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return list_Elementos;
		}
	
	
	
}
