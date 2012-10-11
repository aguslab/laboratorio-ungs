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
	
	 
	 
	 
	 
	 
	 
}
