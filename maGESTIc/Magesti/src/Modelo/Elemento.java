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

	public static ArrayList<String> nombreDeElemento(Integer id_OT)
	{
		ArrayList<String> valores = new ArrayList<String>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT tipo_elemento FROM elemento WHERE id_orden_trabajo ="+ id_OT);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getString("tipo_elemento"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}

	
	public static Integer cantidadFilas(Integer id_OT)
	{
		Integer cantidad = 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT COUNT(*) FROM elemento WHERE id_orden_trabajo =" + id_OT);
		
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
	public static ArrayList<Integer> cantidadDeElemento(Integer id_OT)
	{
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT cantidad FROM elemento WHERE id_orden_trabajo ="+ id_OT);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(Integer.parseInt(resultado.getString("cantidad")));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	public static Integer getMaxId_Elemento() 
	{
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
	
	public static ArrayList<String> getTipoElemento(Integer id_elem)
	{
		ArrayList<String> valores = new ArrayList<String>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT tipo_elemento FROM elemento WHERE id_elemento="+ id_elem);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getString("tipo_elemento"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
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

	public static ArrayList<Integer> getIdElementos(int clave) {
		ArrayList<Integer> id_elem = new ArrayList<Integer>();

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_elemento FROM elemento where id_orden_trabajo="
						+ clave);

		if (resultado != null) {

			try {

				while (resultado.next()) {
					id_elem.add(resultado.getInt("id_elemento"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return id_elem;
	}
	
	
	
}
