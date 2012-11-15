package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vista_Controlador.Metodos;

public class Stock {

	 private Integer id_stock;
	 private Integer id_orden_trabajo;
	 private Integer id_solicitud_compra;
	 private Integer id_detalle;
	 private Integer cant_hojas_usadas;
	 private Boolean remanente;
	 private boolean activo;
	
	 
	 
	public Stock(Integer id_stock, Integer id_orden_trabajo,
			Integer id_solicitud_compra, Integer id_detalle,
			Integer cant_hojas_usadas, Boolean remanente, boolean activo) {
		this.id_stock = id_stock;
		this.id_orden_trabajo = id_orden_trabajo;
		this.id_solicitud_compra = id_solicitud_compra;
		this.id_detalle = id_detalle;
		this.cant_hojas_usadas = cant_hojas_usadas;
		this.remanente = remanente;
		this.activo = activo;
	}

	
	public Stock( Integer id_orden_trabajo,
			Integer id_solicitud_compra, Integer id_detalle,
			Integer cant_hojas_usadas, Boolean remanente, boolean activo) {
		this.id_orden_trabajo = id_orden_trabajo;
		this.id_solicitud_compra = id_solicitud_compra;
		this.id_detalle = id_detalle;
		this.cant_hojas_usadas = cant_hojas_usadas;
		this.remanente = remanente;
		this.activo = activo;
	}
	

	public Integer getId_stock() {
		return id_stock;
	}




	public void setId_stock(Integer id_stock) {
		this.id_stock = id_stock;
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




	public Integer getId_detalle() {
		return id_detalle;
	}




	public void setId_detalle(Integer id_detalle) {
		this.id_detalle = id_detalle;
	}




	public Integer getCant_hojas_usadas() {
		return cant_hojas_usadas;
	}




	public void setCant_hojas_usadas(Integer cant_hojas_usadas) {
		this.cant_hojas_usadas = cant_hojas_usadas;
	}




	public Boolean getRemanente() {
		return remanente;
	}




	public void setRemanente(Boolean remanente) {
		this.remanente = remanente;
	}




	public boolean isActivo() {
		return activo;
	}




	public void setActivo(boolean activo) {
		this.activo = activo;
	}




	public boolean Alta() {

		Integer id_ot = this.getId_orden_trabajo();
		Integer id_sc = this.getId_solicitud_compra();
		Integer id_det = this.getId_detalle();
		Integer cant_hojas_us = this.getCant_hojas_usadas();
		Boolean reman = this.getRemanente();
		Boolean act = this.isActivo();

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO stock VALUES(default," + id_ot + "," + id_sc + ","
						+  id_det + "," + cant_hojas_us + "," + reman + ","
						+ act + ");")) {
			return true;
		} else {
			return false;
		}
	}
	 
	
	
	public static ArrayList<Integer> getIdStockSegunOT(Integer id_OT) {
		ArrayList<Integer> ids_stock=new ArrayList<Integer>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_stock FROM stock WHERE id_orden_trabajo="+id_OT+" ORDER BY id_stock");

		if (resultado != null) {
			try {
				while (resultado.next()) {
					ids_stock.add(resultado.getInt("id_stock"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ids_stock;
	}
	
	
	
	public static Integer getCantFilasSC(Integer id_SC) {
		Integer cantfilas = 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT COUNT(id_solicitud_compra) FROM Stock where id_solicitud_compra="
						+ id_SC);

		if (resultado != null) {
			try {
				while (resultado.next()) {

					cantfilas = resultado.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return cantfilas;
	}



	public static boolean SacarHojas(Integer idStock, Integer cantUsadas) {

		return ConexionDB.getbaseDatos().ejecutar("UPDATE stock set cant_hojas_usadas="+cantUsadas+" WHERE id_stock="+idStock);
		
	}



	public static Integer getHojasUsadas(Integer id_Stock) {
		Integer hojasUsadas = 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT cant_hojas_usadas FROM stock WHERE id_stock="+id_Stock);

		if (resultado != null) {
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					hojasUsadas = resultado.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hojasUsadas;
	}



	public static Integer getHojasTotales(Integer id_Stock) {
		Integer hojasTotales= 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT d.cantidad FROM stock s inner join detalle d on s.id_detalle=d.id_detalle WHERE id_stock="+id_Stock);

		if (resultado != null) {
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					hojasTotales = resultado.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hojasTotales;
	}



	public static boolean setStockComoRemanente(Integer id_Stock) {

		return ConexionDB.getbaseDatos().ejecutar("UPDATE stock set remanente=true WHERE id_stock="+id_Stock);

	}



	public static boolean setStockInactivo(Integer id_Stock) {

		return ConexionDB.getbaseDatos().ejecutar("UPDATE stock set activo=false WHERE id_stock="+id_Stock);
		
	}



	public static String[] getGramajesEnStock() {
		
		String[] gramajes= null;
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT DISTINCT d.gramaje FROM stock s inner join detalle d on s.id_detalle=d.id_detalle WHERE s.activo=true");
		try 
		{
			resultado.last();
			int cantGramajes = resultado.getRow();
			gramajes= new String[cantGramajes];
			resultado.beforeFirst();
		} 
		catch (SQLException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		if (resultado != null) {
			int i=0;
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					Integer gr=resultado.getInt(1);
					gramajes[i] = gr.toString();
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gramajes;
	}



	public static String[] getVariantesDeStock() {
		String[] variantes= null;
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT DISTINCT d.id_variante FROM stock s inner join detalle d on s.id_detalle=d.id_detalle WHERE activo=true");
		try 
		{
			resultado.last();
			int cantVariantes= resultado.getRow();
			variantes= new String[cantVariantes];
			resultado.beforeFirst();
		} 
		catch (SQLException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		if (resultado != null) {
			int i=0;
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					Integer var=resultado.getInt(1);
					variantes[i] = var.toString();
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return variantes;
	}



	public static String[] getFormatosEnStock() {

		String[] formatos= null;
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT DISTINCT d.id_formato_papel FROM stock s inner join detalle d on s.id_detalle=d.id_detalle WHERE activo=true");
		try 
		{
			resultado.last();
			int cantFormatos= resultado.getRow();
			formatos= new String[cantFormatos];
			resultado.beforeFirst();
		} 
		catch (SQLException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		if (resultado != null) {
			int i=0;
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					Integer form=resultado.getInt(1);
					formatos[i] = form.toString();
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return formatos;
	}



	public static String[] getOTsDeStock() {
		
		String[] OTs= null;
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT DISTINCT s.id_orden_trabajo,o.nombre_trabajo FROM stock s,orden_trabajo o WHERE s.activo=true AND s.id_orden_trabajo=o.id_orden_trabajo");
		try 
		{
			resultado.last();
			int cantOTs= resultado.getRow();
			OTs= new String[cantOTs];
			resultado.beforeFirst();
		} 
		catch (SQLException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		if (resultado != null) {
			int i=0;
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					Integer id_ot=resultado.getInt("id_orden_trabajo");
					String id_OT_Formato="";
					if(id_ot!=1){
						id_OT_Formato = Metodos.EnteroAFactura(id_ot) +" - ";
					}
					String nom_ot=resultado.getString("nombre_trabajo");
					OTs[i]=id_OT_Formato+nom_ot;
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return OTs;
		
	}



	public static ArrayList<Integer> dameIdsStock(Integer id_SC) {
		
		ArrayList<Integer> idsStock= new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_stock FROM stock WHERE id_solicitud_compra="+id_SC+" AND activo=true ORDER BY id_stock" );

		if (resultado != null) {
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					idsStock.add(resultado.getInt("id_stock"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idsStock;
	}



	public static Boolean isRemanente(Integer id_Stock) {
		Boolean isRemanente = null;
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT remanente FROM stock WHERE id_stock="+id_Stock);

		if (resultado != null) {
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					isRemanente=resultado.getBoolean("remanente");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isRemanente;
	}


	public static Stock getStock(Integer id_stock) {
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM Stock WHERE id_stock="+id_stock+ " AND activo=true");
		Stock stock=null;
		if (resultado != null) {

			try {
				while (resultado.next()) {
					stock= new Stock(new Integer(resultado.getInt("id_stock")),
							new Integer(resultado.getInt("id_orden_trabajo")),new Integer(resultado.getInt("id_solicitud_compra")),
							new Integer(resultado.getInt("id_detalle")),
							new Integer(resultado.getInt("cant_hojas_usadas")),
							resultado.getBoolean("remanente"), resultado.getBoolean("activo"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return stock;
	}	
	
	
}
