package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Variante {
	
	private Integer id_variante;
	private String nombre;
	
	
	public Variante(Integer id_variante, String nombre) {
		super();
		this.id_variante = id_variante;
		this.nombre = nombre;
	}

	public Variante(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	public Integer getId_variante() {
		return id_variante;
	}


	public void setId_variante(Integer id_variante) {
		this.id_variante = id_variante;
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
				.ejecutar("INSERT INTO variante VALUES(default," + "'" + nombre
						+ "'" + ");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Variante> Buscar() {

		ResultSet resultado = ConexionDB.baseDatos.consultar(
				"SELECT * FROM variante");

		ArrayList<Variante> list_Var = new ArrayList<Variante>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Variante variante = new Variante(new Integer(
							resultado.getInt("id_variante")),
							resultado.getString("nombre"));
					list_Var.add(variante);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_Var;
	}
	
	
	
	
	
	
}
