package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Formato_Papel {
	
	private Integer id_formato_papel;
	private String tamanio;
	
	
	public Formato_Papel(Integer id_formato, String tamanio) {
		super();
		this.id_formato_papel= id_formato;
		this.tamanio = tamanio;
	}
	
	public Formato_Papel(String tamanio) {
		super();
		this.tamanio= tamanio;
	}

	

	public Integer getId_formato_papel() {
		return id_formato_papel;
	}

	public void setId_formato_papel(Integer id_formato_papel) {
		this.id_formato_papel = id_formato_papel;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public static  String[] getFormatos()
	{
		ArrayList<String> fmt=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT tamanio FROM formato_papel");
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					fmt.add(resultado.getString("tamanio"));
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
	
	
	
	
	public boolean Alta() {
		String tam="'"+this.getTamanio()+"'";

		if (ConexionDB.getbaseDatos()
				.ejecutar("INSERT INTO formato_papel VALUES(default," + tam+ ");")) {
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
							resultado.getInt("id_formato_papel")),resultado.getString("tamanio"));
					list_formato.add(for_papel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_formato;
	}

	public static Integer getId_Formato(String tamanioElegido) 
	{
		Integer id_for=null;
		tamanioElegido="'"+tamanioElegido+"'";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_formato_papel FROM formato_papel where tamanio="+tamanioElegido);
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					id_for = resultado.getInt(1);
					break;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
		return id_for;
	}
	
	public static String getTamanio(Integer id_for)
	{
		String valor = "";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT tamanio FROM formato_papel WHERE id_formato_papel="+ id_for);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valor = resultado.getString("tamanio");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valor;
	}
}
