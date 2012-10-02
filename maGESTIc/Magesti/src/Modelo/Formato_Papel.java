package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Formato_Papel {
	
	private Integer id_formato_papel;
	private Integer ancho;
	private Integer alto;
	
	
	public Formato_Papel(Integer id_formato, Integer ancho, Integer alto) {
		super();
		this.id_formato_papel= id_formato;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public Formato_Papel(Integer ancho, Integer alto) {
		super();
		this.ancho = ancho;
		this.alto = alto;
	}

	public Integer getId_variante() {
		return id_formato_papel;
	}

	public void setId_variante(Integer id_variante) {
		this.id_formato_papel = id_variante;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public Integer getAlto() {
		return alto;
	}

	public static  String[] getFormatos()
	{
		ArrayList<String> fmt=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT tamanio_formato FROM formato_papel");
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					fmt.add(resultado.getString("tamanio_formato"));
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		String [] fmts= new String[fmt.size()];
		
		for(int i=0;i<fmt.size();i++)
		{
			fmts[i]=fmt.get(i);
		}
		
		return fmts;
	}
	
	public void setAlto(Integer alto) {
		this.alto = alto;
	}
	
	
	public boolean Alta() {
		Integer ancho = this.getAncho();
		Integer alto = this.getAlto();

		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO formato_papel VALUES(default," + ancho
						+ "," + alto + ");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Formato_Papel> Buscar() {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
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
	
	
}
