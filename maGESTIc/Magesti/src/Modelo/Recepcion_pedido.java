package Modelo;

import java.sql.Date;
import java.sql.ResultSet;

public class Recepcion_pedido {

	 private Integer id_recepcion_pedido;
	 private Integer id_solicitud_compra;
	 private String estado;
	 private String f_h_recibido;
	 private String incidente;
	
	 
	 public Recepcion_pedido(Integer id_solicitud_compra, String estado,
			 String f_h_recibido, String incidente) {
		super();
		this.id_solicitud_compra = id_solicitud_compra;
		this.estado = estado;
		this.f_h_recibido = f_h_recibido;
		this.incidente = incidente;
	}


	public Integer getId_solicitud_compra() {
		return id_solicitud_compra;
	}


	public void setId_solicitud_compra(Integer id_solicitud_compra) {
		this.id_solicitud_compra = id_solicitud_compra;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getF_h_recibido() {
		return f_h_recibido;
	}


	public void setF_h_recibido(String f_h_recibido) {
		this.f_h_recibido = f_h_recibido;
	}


	public String getIncidente() {
		return incidente;
	}


	public void setIncidente(String incidente) {
		this.incidente = incidente;
	}	
	
	public boolean Alta() {

		Integer id_solicitud_compra = this.getId_solicitud_compra();
		String estado = "'" + this.getEstado() + "'";
		String f_h_recibido = null;
		if (this.getF_h_recibido() != null) {
			f_h_recibido = "'"+this.getF_h_recibido()+"'";
		}
		String incidente = "'" + this.getIncidente() + "'";

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO recepcion_pedido VALUES(default," + id_solicitud_compra
						+ "," + estado + "," + f_h_recibido + "," + incidente
						+ ");")) {
			return true;
		} else {
			return false;
		}
	}


	public static String dameEstado(Integer id_SC) {
		String estado="";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT estado FROM recepcion_pedido WHERE id_solicitud_compra="+ id_SC);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							estado=resultado.getString("estado");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return estado;
	}


	public static String dametxtDescripcion(Integer id_SC) {
		String txtDescripcion="";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT incidente FROM recepcion_pedido WHERE id_solicitud_compra="+ id_SC);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							txtDescripcion=resultado.getString("incidente");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return txtDescripcion;
	}


	public static Integer getCantidadFilasRecibidas(Integer id_SC) {
		Integer cantidad = 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT COUNT(*) FROM detalle WHERE id_solicitud_compra="+ id_SC+" AND recibido=true");

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
