package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Materiales {


	
	/*
	 * 
	 * 
	 * 
	 * DEJO ESTA CLASE PARA DESPUES PORQUE ACA HABRIA QUE
	 * AUTOCALCULAR HOJAS Y PLIEGOS_NETOS, PERO NO SE COMO
	 * HAY QUE HACER PARA CALCULARLOS, NO SE CON QUE DATOS SE SACA
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	private Integer id_materiales;
	private Integer gramaje;
	private Integer poses_x_pliego;
	private Integer pliegos_netos;
	private Integer pliegos_en_demasia;
	private Integer pliegos_x_hoja;
	private Integer hojas;
	private Integer id_calidad;
	private Integer id_variante;
	private Integer id_formato_papel;
	
	
	
	public Materiales(Integer id_materiales, Integer gramaje,
			Integer poses_x_pliego, Integer pliegos_netos,
			Integer pliegos_en_demasia, Integer pliegos_x_hoja, Integer hojas,
			Integer id_calidad, Integer id_variante, Integer id_formato_papel) {
		super();
		this.id_materiales = id_materiales;
		this.gramaje = gramaje;
		this.poses_x_pliego = poses_x_pliego;
		this.pliegos_netos = pliegos_netos;
		this.pliegos_en_demasia = pliegos_en_demasia;
		this.pliegos_x_hoja = pliegos_x_hoja;
		this.hojas = hojas;
		this.id_calidad = id_calidad;
		this.id_variante = id_variante;
		this.id_formato_papel = id_formato_papel;
	}
	
	
	public Materiales(Integer gramaje,
			Integer poses_x_pliego, Integer pliegos_netos,
			Integer pliegos_en_demasia, Integer pliegos_x_hoja, Integer hojas,
			Integer id_calidad, Integer id_variante, Integer id_formato_papel) {
		super();
		this.gramaje = gramaje;
		this.poses_x_pliego = poses_x_pliego;
		this.pliegos_netos = pliegos_netos;
		this.pliegos_en_demasia = pliegos_en_demasia;
		this.pliegos_x_hoja = pliegos_x_hoja;
		this.hojas = hojas;
		this.id_calidad = id_calidad;
		this.id_variante = id_variante;
		this.id_formato_papel = id_formato_papel;
	}


	public Integer getId_materiales() {
		return id_materiales;
	}


	public void setId_materiales(Integer id_materiales) {
		this.id_materiales = id_materiales;
	}


	public Integer getGramaje() {
		return gramaje;
	}


	public void setGramaje(Integer gramaje) {
		this.gramaje = gramaje;
	}


	public Integer getPoses_x_pliego() {
		return poses_x_pliego;
	}


	public void setPoses_x_pliego(Integer poses_x_pliego) {
		this.poses_x_pliego = poses_x_pliego;
	}


	public Integer getPliegos_netos() {
		return pliegos_netos;
	}


	public void setPliegos_netos(Integer pliegos_netos) {
		this.pliegos_netos = pliegos_netos;
	}


	public Integer getPliegos_en_demasia() {
		return pliegos_en_demasia;
	}


	public void setPliegos_en_demasia(Integer pliegos_en_demasia) {
		this.pliegos_en_demasia = pliegos_en_demasia;
	}


	public Integer getPliegos_x_hoja() {
		return pliegos_x_hoja;
	}


	public void setPliegos_x_hoja(Integer pliegos_x_hoja) {
		this.pliegos_x_hoja = pliegos_x_hoja;
	}


	public Integer getHojas() {
		return hojas;
	}


	public void setHojas(Integer hojas) {
		this.hojas = hojas;
	}


	public Integer getId_calidad() {
		return id_calidad;
	}


	public void setId_calidad(Integer id_calidad) {
		this.id_calidad = id_calidad;
	}


	public Integer getId_variante() {
		return id_variante;
	}


	public void setId_variante(Integer id_variante) {
		this.id_variante = id_variante;
	}


	public Integer getId_formato_papel() {
		return id_formato_papel;
	}


	public void setId_formato_papel(Integer id_formato_papel) {
		this.id_formato_papel = id_formato_papel;
	}
	
	

	/*
	
	
	public boolean Alta() {
		
		private Integer gramaje= this.getGramaje();
		private Integer poses_x_pliego=this.getPoses_x_pliego();
		private Integer pliegos_netos=this.getPliegos_netos();
		private Integer pliegos_en_demasia=this.getPliegos_en_demasia();
		private Integer pliegos_x_hoja=this.getpli;
		private Integer hojas;
		private Integer id_calidad;
		private Integer id_variante;
		private Integer id_formato_papel;
		
		
		
		if (ConexionDB.baseDatos
				.ejecutar("INSERT INTO formato_papel VALUES(default," + ancho
						+ "," + alto + ");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Formato_Papel> Buscar() {

		ResultSet resultado = ConexionDB.baseDatos.consultar(
				"SELECT * FROM formato_papel");

		ArrayList<Formato_Papel> list_formato = new ArrayList<Formato_Papel>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Formato_Papel for_papel= new Formato_Papel(new Integer(
							resultado.getInt("id_formato_papel")),new Integer(
									resultado.getInt("ancho")), new Integer(
											resultado.getInt("alto")));
					list_formato.add(for_papel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_formato;
	}
	
	
	*/
	
	
	
	
}
