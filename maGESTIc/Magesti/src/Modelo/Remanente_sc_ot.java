package Modelo;

import java.sql.ResultSet;

public class Remanente_sc_ot {

	private Integer id_remanente_sc_ot;
	private Integer id_orden_trabajo;
	private Integer id_solicitud_compra;
	private Integer remanente;

	
	public Remanente_sc_ot(Integer id_remanente_sc_ot,
			Integer id_orden_trabajo, Integer id_solicitud_compra,
			Integer remanente) {
		this.id_remanente_sc_ot = id_remanente_sc_ot;
		this.id_orden_trabajo = id_orden_trabajo;
		this.id_solicitud_compra = id_solicitud_compra;
		this.remanente = remanente;
	}
	
	
	public Remanente_sc_ot(Integer id_orden_trabajo, Integer id_solicitud_compra,
			Integer remanente) {
		this.id_orden_trabajo = id_orden_trabajo;
		this.id_solicitud_compra = id_solicitud_compra;
		this.remanente = remanente;
	}


	public Integer getId_remanente_sc_ot() {
		return id_remanente_sc_ot;
	}


	public void setId_remanente_sc_ot(Integer id_remanente_sc_ot) {
		this.id_remanente_sc_ot = id_remanente_sc_ot;
	}


	public Integer getId_orden_trabajo() {
		return id_orden_trabajo;
	}


	public void setId_orden_trabajo(Integer id_orden_trabajo) {
		this.id_orden_trabajo = id_orden_trabajo;
	}


	public Integer getId_solicitud_compra() {
		return id_solicitud_compra;
	}


	public void setId_solicitud_compra(Integer id_solicitud_compra) {
		this.id_solicitud_compra = id_solicitud_compra;
	}


	public Integer getRemanente() {
		return remanente;
	}


	public void setRemanente(Integer remanente) {
		this.remanente = remanente;
	}
	
	
	public boolean Alta() {

		Integer id_ot = this.getId_orden_trabajo();
		Integer id_sc = this.getId_solicitud_compra();
		Integer remanente = this.getRemanente();

		return ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO remanente_sc_ot VALUES(default," + id_ot + ","+ id_sc + "," + remanente + ");");

	}

	
	
	public static void llenarTablaRemanente_SC_OT(Integer id_OT) {

		Remanente_sc_ot rem;
		ResultSet resultado = ConexionDB
				.getbaseDatos()
				.consultar(
						"SELECT s.id_orden_trabajo, s.id_solicitud_compra, (d.cantidad-s.cant_hojas_usadas) AS Remanente from stock s inner join detalle d on s.id_solicitud_compra=d.id_solicitud_compra  WHERE id_orden_trabajo="
								+ id_OT);
		if (resultado != null) {
			try {
				while (resultado.next()) {
					rem = new Remanente_sc_ot(
							resultado.getInt("id_orden_trabajo"),
							resultado.getInt("id_solicitud_compra"),
							resultado.getInt("remanente"));
					rem.Alta();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
}
