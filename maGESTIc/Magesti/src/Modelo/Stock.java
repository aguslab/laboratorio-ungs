package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

import vista_Controlador.Metodos;

public class Stock {

	 private Integer id_stock;
	 private Integer id_orden_trabajo;
	 private Integer id_solicitud_compra;
	 private Integer cant_hojas_totales;
	 private Integer cant_hojas_usadas;
	 private String marca;
	 private Integer id_calidad;
	 private Integer id_formato;
	 private Integer id_variante;
	 private Integer gramaje;
	 private Integer remanente;
	 private boolean activo;
	
	 
	 
	 public Stock(Integer id_orden_trabajo,
			Integer id_solicitud_compra, Integer cant_hojas_totales,
			Integer cant_hojas_usadas, String marca, Integer id_calidad,
			Integer id_formato, Integer id_variante, Integer gramaje,
			Integer remanente,boolean activo) {
		super();
		this.id_orden_trabajo = id_orden_trabajo;
		this.id_solicitud_compra = id_solicitud_compra;
		this.cant_hojas_totales = cant_hojas_totales;
		this.cant_hojas_usadas = cant_hojas_usadas;
		this.marca = marca;
		this.id_calidad = id_calidad;
		this.id_formato = id_formato;
		this.id_variante = id_variante;
		this.gramaje = gramaje;
		this.remanente = remanente;
		this.activo=activo;
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



	public Integer getCant_hojas_totales() {
		return cant_hojas_totales;
	}



	public void setCant_hojas_totales(Integer cant_hojas_totales) {
		this.cant_hojas_totales = cant_hojas_totales;
	}



	public Integer getCant_hojas_usadas() {
		return cant_hojas_usadas;
	}



	public void setCant_hojas_usadas(Integer cant_hojas_usadas) {
		this.cant_hojas_usadas = cant_hojas_usadas;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public Integer getId_calidad() {
		return id_calidad;
	}



	public void setId_calidad(Integer id_calidad) {
		this.id_calidad = id_calidad;
	}



	public Integer getId_formato() {
		return id_formato;
	}



	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}



	public Integer getId_variante() {
		return id_variante;
	}



	public void setId_variante(Integer id_variante) {
		this.id_variante = id_variante;
	}



	public Integer getGramaje() {
		return gramaje;
	}



	public void setGramaje(Integer gramaje) {
		this.gramaje = gramaje;
	}



	public Integer getRemanente() {
		return remanente;
	}



	public void setRemanente(Integer remanente) {
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
		Integer cant_hojas_tot = this.getCant_hojas_totales();
		Integer cant_hojas_us = this.getCant_hojas_usadas();
		String smarca = "'"+this.getMarca()+"'";
		Integer id_cal = this.getId_calidad();
		Integer id_for = this.getId_formato();
		Integer id_var = this.getId_variante();
		Integer gram = this.getGramaje();
		Integer reman = this.getRemanente();
		Boolean act= this.isActivo();

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO stock VALUES(default," + id_ot + "," + id_sc + ","
						+ cant_hojas_tot + "," + cant_hojas_us + "," + smarca
						+ "," + id_cal + "," + id_for + "," + id_var + ","
						+ gram + "," + reman +"," + act+ ");")) {
			return true;
		} else {
			return false;
		}
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
				"SELECT cant_hojas_totales FROM stock WHERE id_stock="+id_Stock);

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
	
	
}
