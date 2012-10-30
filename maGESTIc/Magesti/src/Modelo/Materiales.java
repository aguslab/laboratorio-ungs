package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Materiales {


	private Integer id_materiales;
	private Integer id_elemento;
	private Integer gramaje;
	private Integer poses_x_pliego;
	private Integer pliegos_netos;
	private Integer pliegos_en_demasia;
	private Integer pliegos_x_hoja;
	private Integer hojas;
	private Integer id_calidad;
	private Integer id_variante;
	private Integer id_formato_papel;


	public Materiales(Integer id_materiales, Integer id_elemento,
			Integer gramaje, Integer poses_x_pliego, Integer pliegos_netos,
			Integer pliegos_en_demasia, Integer pliegos_x_hoja, Integer hojas,
			Integer id_calidad, Integer id_variante, Integer id_formato_papel) {
		super();
		this.id_materiales = id_materiales;
		this.id_elemento = id_elemento;
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




	public Materiales(Integer id_elemento, 
			Integer gramaje, Integer id_formato_papel, Integer id_variante,
			Integer id_calidad,Integer pliegos_en_demasia, Integer poses_x_pliego,
			Integer pliegos_x_hoja, Integer hojas,Integer pliegos_netos) 
	{
		super();
		this.id_elemento = id_elemento;
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
	
	

	public Integer getId_elemento() {
		return id_elemento;
	}

	public void setId_elemento(Integer id_elemento) {
		this.id_elemento = id_elemento;
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
	
	
	//devuelve los id_materiales de la OT pasada como parametro
	private static String getSelectToGetId_Materiales(Integer id_OT)
	{
		return 	"(select id_materiales from materiales where id_elemento in (select id_elemento from elemento where id_orden_trabajo="+id_OT+"))";
	}
	
	
	
	public static ArrayList<Integer> getID_Materiales(Integer id_OT)
	{
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"select id_materiales from materiales where id_elemento in (select id_elemento from elemento where id_orden_trabajo="+id_OT+")");
		
		
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("id_materiales"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	
	
	
	public static ArrayList<Integer> getGramaje(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT gramaje FROM materiales WHERE id_materiales in "+consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("gramaje"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	public static ArrayList<Integer> getPoses_x_pliego(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT poses_x_pliego FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("poses_x_pliego"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	public static ArrayList<Integer> getPliegos_netos(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT pliegos_netos FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("pliegos_netos"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	
	public static ArrayList<Integer> getPliegos_en_demasia(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT pliegos_en_demasia FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("pliegos_en_demasia"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	public static ArrayList<Integer> getHojas(Integer id_OT) {
		
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT hojas FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("hojas"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
		
	}

	
	public static ArrayList<Integer> getPliegos_x_Hojas(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT pliegos_x_hoja FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("pliegos_x_hoja"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	

	public static ArrayList<Integer> getID_Calidad(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_calidad FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("id_calidad"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	public static ArrayList<Integer> getID_Variante(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_variante FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("id_variante"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
	
	
	
	public static ArrayList<Integer> getId_formato_papel(Integer id_OT)
	{
		String consulta=Materiales.getSelectToGetId_Materiales(id_OT);
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_formato_papel FROM materiales WHERE id_materiales in "+ consulta);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("id_formato_papel"));
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

		Integer gram = this.getGramaje();
		Integer id_elem = this.getId_elemento();
		Integer poses_x_plie = this.getPoses_x_pliego();
		Integer plie_netos = this.getPliegos_netos();
		Integer plie_en_demasia = this.getPliegos_en_demasia();
		Integer plie_x_hoja = this.getPliegos_x_hoja();
		Integer hojaS = this.getHojas();
		Integer id_calidad = this.getId_calidad();
		Integer id_variante = this.getId_variante();
		Integer id_formato_papel = this.getId_formato_papel();

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO materiales VALUES(default," + id_elem + "," + gram
						+ "," + poses_x_plie + "," + plie_netos + ","
						+ plie_en_demasia + "," + plie_x_hoja + "," + hojaS
						+ "," + id_calidad + "," + id_variante + ","
						+ id_formato_papel + ");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Materiales> Buscar() {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM materiales");

		ArrayList<Materiales> list_Materiales = new ArrayList<Materiales>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Materiales mater = new Materiales(new Integer(
							resultado.getInt("id_materiales")), new Integer(
							resultado.getInt("id_elemento")), new Integer(
							resultado.getInt("gramaje")), new Integer(
							resultado.getInt("poses_x_pliego")), new Integer(
							resultado.getInt("pliegos_netos")), new Integer(
							resultado.getInt("pliegos_en_demasia")),
							new Integer(resultado.getInt("pliegos_x_hoja")),
							new Integer(resultado.getInt("hojas")),
							new Integer(resultado.getInt("id_calidad")),
							new Integer(resultado.getInt("id_variante")),
							new Integer(resultado.getInt("id_formato_papel")));
					list_Materiales.add(mater);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_Materiales;
	}

	
	public static Materiales Buscar(Integer id_Materiales) {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM materiales where id_materiales="+id_Materiales);

		Materiales Material= null;
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Material = new Materiales(new Integer(
							resultado.getInt("id_materiales")), new Integer(
							resultado.getInt("id_elemento")), new Integer(
							resultado.getInt("gramaje")), new Integer(
							resultado.getInt("poses_x_pliego")), new Integer(
							resultado.getInt("pliegos_netos")), new Integer(
							resultado.getInt("pliegos_en_demasia")),
							new Integer(resultado.getInt("pliegos_x_hoja")),
							new Integer(resultado.getInt("hojas")),
							new Integer(resultado.getInt("id_calidad")),
							new Integer(resultado.getInt("id_variante")),
							new Integer(resultado.getInt("id_formato_papel")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return Material;
	}
	
	
	public static ArrayList<Materiales> getMateriales(Integer id_OT) {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM materiales WHERE id_elemento in (SELECT id_elemento FROM elemento WHERE id_orden_trabajo="+id_OT+")");

		ArrayList<Materiales> list_Materiales = new ArrayList<Materiales>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Materiales mater = new Materiales(new Integer(
							resultado.getInt("id_materiales")), new Integer(
							resultado.getInt("id_elemento")), new Integer(
							resultado.getInt("gramaje")), new Integer(
							resultado.getInt("poses_x_pliego")), new Integer(
							resultado.getInt("pliegos_netos")), new Integer(
							resultado.getInt("pliegos_en_demasia")),
							new Integer(resultado.getInt("pliegos_x_hoja")),
							new Integer(resultado.getInt("hojas")),
							new Integer(resultado.getInt("id_calidad")),
							new Integer(resultado.getInt("id_variante")),
							new Integer(resultado.getInt("id_formato_papel")));
					list_Materiales.add(mater);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_Materiales;
	}
	
	
	
}
