package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Procesos_x_OT {
	
	private Integer id_proceso;
	private Integer id_orden_trabajo;
	private boolean tercerizada;
	private Integer id_proveedor;
	private boolean cumplida;
	private String observacion;
	
	
	
	public Procesos_x_OT(Integer id_proceso, Integer id_orden_trabajo, boolean tercerizada,
			Integer id_proveedor,boolean estado, String observacion) {
		super();
		this.id_proceso = id_proceso;
		this.id_orden_trabajo = id_orden_trabajo;
		this.tercerizada= tercerizada;
		this.id_proveedor = id_proveedor;
		this.cumplida=estado;
		this.observacion = observacion;
	}
	
		

	public boolean isTercerizada() {
		return tercerizada;
	}



	public void setTercerizada(boolean tercerizada) {
		this.tercerizada = tercerizada;
	}



	public Integer getId_proceso() {
		return id_proceso;
	}


	public void setId_proceso(Integer id_proceso) {
		this.id_proceso = id_proceso;
	}


	public Integer getId_orden_trabajo() {
		return id_orden_trabajo;
	}


	public void setId_orden_trabajo(Integer id_orden_trabajo) {
		this.id_orden_trabajo = id_orden_trabajo;
	}


	public Integer getId_proveedor() {
		return id_proveedor;
	}


	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}


		
	public boolean isCumplida() {
		return cumplida;
	}

	public void setCumplida(boolean cumplida) {
		this.cumplida = cumplida;
	}

	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	public boolean Alta() {
		Integer id_proc=this.getId_proceso();
		Integer id_ot=this.getId_orden_trabajo();
		Integer id_prov=this.getId_proveedor();
		boolean status=this.isCumplida();
		String obser="'"+this.getObservacion()+"'";
		

		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO procesos_x_orden_trabajo VALUES("+id_proc+"," + id_ot+"," + id_prov+","
						+ status+","+obser + ");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Procesos_x_OT> Buscar() {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM procesos_x_orden_trabajo");

		ArrayList<Procesos_x_OT> list_prox_x_orden = new ArrayList<Procesos_x_OT>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Procesos_x_OT proc_x_ot = new Procesos_x_OT(new Integer(
							resultado.getInt("id_proceso")), new Integer(
							resultado.getInt("id_orden_trabajo")),
							resultado.getBoolean("tercerizada"), new Integer(
									resultado.getInt("id_proveedor")),
							resultado.getBoolean("cumplida"),
							resultado.getString("observacion"));
					list_prox_x_orden.add(proc_x_ot);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_prox_x_orden;
	}
	
	
	public static ArrayList<String> BuscarProc_x_OT(Integer id_OT) {

		ResultSet resultado = ConexionDB.getbaseDatos()
				.consultar("SELECT id_proceso FROM procesos_x_orden_trabajo where id_orden_trabajo="
						+ id_OT);
		
		ArrayList<String> list_proc = new ArrayList<String>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Integer id_proc = resultado.getInt("id_proceso");
					ResultSet process=ConexionDB.getbaseDatos().consultar("SELECT nombre from proceso where id_proceso="+id_proc);
					
					if(process != null){
						try {
							while(process.next()){
								String nomb=process.getString("nombre");
								list_proc.add(nomb);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list_proc;
	}
	
	

}
