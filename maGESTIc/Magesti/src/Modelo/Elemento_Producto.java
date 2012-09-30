package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Elemento_Producto {

	private Integer id_elementoProducto;
	private String tipo_elemento;
	private Integer cantidad;
	
	
		public Elemento_Producto(Integer id_elementoProducto, String tipo_elemento,
			Integer cantidad) {
		super();
		this.id_elementoProducto = id_elementoProducto;
		this.tipo_elemento = tipo_elemento;
		this.cantidad = cantidad;
	}
	
	
		public Elemento_Producto(String tipo_elemento,
				Integer cantidad) {
			super();
			this.tipo_elemento = tipo_elemento;
			this.cantidad = cantidad;
		}


		public Integer getId_elementoProducto() {
			return id_elementoProducto;
		}


		public void setId_elementoProducto(Integer id_elementoProducto) {
			this.id_elementoProducto = id_elementoProducto;
		}


		public String getTipo_elemento() {
			return tipo_elemento;
		}


		public void setTipo_elemento(String tipo_elemento) {
			this.tipo_elemento = tipo_elemento;
		}


		public Integer getCantidad() {
			return cantidad;
		}


		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
	
	
		public boolean Alta() {
			String tipoElement= this.getTipo_elemento();
			Integer cant= this.getCantidad();

			if (ConexionDB.baseDatos
					.ejecutar("INSERT INTO elemento_producto VALUES(default," + "'"+tipoElement+"'"+","+cant+");")) {
				return true;
			} else {
				return false;
			}
		}
		
		
		public ArrayList<Elemento_Producto> Buscar() {

			ResultSet resultado = ConexionDB.baseDatos.consultar(
					"SELECT * FROM elemento_producto");

			ArrayList<Elemento_Producto> list_ElementProd = new ArrayList<Elemento_Producto>();
			if (resultado != null) {

				try {

					while (resultado.next()) {
						Elemento_Producto ElemProd= new Elemento_Producto(new Integer(
								resultado.getInt("id_elementoProducto")),
								resultado.getString("tipo_elemento"),new Integer(resultado.getInt("cantidad")));
						list_ElementProd.add(ElemProd);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return list_ElementProd;
		}
	
	
	
}
