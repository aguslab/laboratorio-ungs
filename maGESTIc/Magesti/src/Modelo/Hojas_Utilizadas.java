package Modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Hojas_Utilizadas {

	
	private Integer id_hojas_utilizadas;
	private Integer id_elemento;
	private Integer cantidad;
	private String f_hora;
	private String estado_OT;
	
	
	public Hojas_Utilizadas(Integer id_hojas_utilizadas, Integer id_elemento, Integer cantidad, String f_hora,
			String estado_OT) {
		super();
		this.id_hojas_utilizadas = id_hojas_utilizadas;
		this.cantidad=cantidad;
		this.f_hora = f_hora;
		this.estado_OT = estado_OT;
	}
	
	public Hojas_Utilizadas(Integer id_elemento, Integer cantidad, String f_hora,
			String estado_OT) {
		super();
		this.id_elemento=id_elemento;
		this.cantidad=cantidad;
		this.f_hora = f_hora;
		this.estado_OT = estado_OT;
	}

	
	
	public Integer getId_hojas_utilizadas() {
		return id_hojas_utilizadas;
	}

	public void setId_hojas_utilizadas(Integer id_hojas_utilizadas) {
		this.id_hojas_utilizadas = id_hojas_utilizadas;
	}

	public String getF_hora() {
		return f_hora;
	}

	public void setF_hora(String f_hora) {
		this.f_hora = f_hora;
	}

	public String getEstado_OT() {
		return estado_OT;
	}

	public void setEstado_OT(String estado_OT) {
		this.estado_OT = estado_OT;
	}
	
		
	public Integer getId_elemento() {
		return id_elemento;
	}

	public void setId_elemento(Integer id_elemento) {
		this.id_elemento = id_elemento;
	}
	
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public boolean Alta() {

		Integer id_elem= this.getId_elemento();
		Integer cant=this.cantidad;
		String f_hora="'"+ this.getF_hora()+"'";
		String estado_OT= "'"+this.getEstado_OT()+"'";
		
		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO hojas_utilizadas VALUES(default," + id_elem+","+ cant+","+ f_hora
						+ "," + estado_OT + ");")) {
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<Integer> getCantHojas(Integer id_OT) {

		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB
				.getbaseDatos()
				.consultar(
						"select h.cantidad from hojas_utilizadas h inner join elemento e where h.id_elemento=e.id_elemento AND e.id_orden_trabajo="
								+ id_OT);

		if (resultado != null) {
			try {
				while (resultado.next()) {
					valores.add(resultado.getInt("cantidad"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	
}
