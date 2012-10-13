package Modelo;

import java.sql.Date;

public class Recepcion_pedido {

	 private Integer id_solicitud_compra;
	 private String estado;
	 private Date f_h_recibido;
	 private String incidente;
	
	 
	 public Recepcion_pedido(Integer id_solicitud_compra, String estado,
			Date f_h_recibido, String incidente) {
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


	public Date getF_h_recibido() {
		return f_h_recibido;
	}


	public void setF_h_recibido(Date f_h_recibido) {
		this.f_h_recibido = f_h_recibido;
	}


	public String getIncidente() {
		return incidente;
	}


	public void setIncidente(String incidente) {
		this.incidente = incidente;
	}	
	
		 
	 
	 
	 
	 
}
