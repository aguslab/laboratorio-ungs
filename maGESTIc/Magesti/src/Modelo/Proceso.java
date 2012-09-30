package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Proceso {
	
	private Integer id_proceso;
	private String nombre;
	
	
	public Proceso(Integer id_proceso, String nombre) {
		super();
		this.id_proceso = id_proceso;
		this.nombre = nombre;
	}
	
	
	public Proceso(String nombre) {
		super();
		this.nombre = nombre;
	}


	public Integer getId_proceso() {
		return id_proceso;
	}


	public void setId_proceso(Integer id_proceso) {
		this.id_proceso = id_proceso;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public boolean Alta() {
		String nombre = this.getNombre();

		if (ConexionDB.baseDatos
				.ejecutar("INSERT INTO proceso VALUES(default," + "'"+nombre+"'"+");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Proceso> Buscar() {

		ResultSet resultado = ConexionDB.baseDatos.consultar(
				"SELECT * FROM proceso");

		ArrayList<Proceso> list_Proc = new ArrayList<Proceso>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Proceso proceso= new Proceso(new Integer(
							resultado.getInt("id_proceso")),
							resultado.getString("nombre"));
					list_Proc.add(proceso);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_Proc;
	}

}
