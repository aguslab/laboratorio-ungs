package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Elemento_x_Producto {

	private Integer id_producto;
	private Integer id_elementoProducto;
	
	
	
	public Elemento_x_Producto(Integer id_producto, Integer id_elementoProducto) {
		super();
		this.id_producto = id_producto;
		this.id_elementoProducto = id_elementoProducto;
	}



	public Integer getId_producto() {
		return id_producto;
	}



	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}



	public Integer getId_elementoProducto() {
		return id_elementoProducto;
	}



	public void setId_elementoProducto(Integer id_elementoProducto) {
		this.id_elementoProducto = id_elementoProducto;
	}
	
	
	public boolean Alta() {
		
		Integer id_prod= this.getId_producto();
		Integer id_elProd=this.getId_elementoProducto();
		

		if (ConexionDB.baseDatos
				.ejecutar("INSERT INTO elemento_x_producto VALUES("+id_prod+"," + id_elProd + ");")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<Elemento_x_Producto> Buscar() {

		ResultSet resultado = ConexionDB.baseDatos
				.consultar("SELECT * FROM elementos_x_producto");

		ArrayList<Elemento_x_Producto> list_elem_x_prod = new ArrayList<Elemento_x_Producto>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Elemento_x_Producto elem_x_prod= new Elemento_x_Producto(new Integer(
							resultado.getInt("id_producto")), new Integer(
							resultado.getInt("id_elementoProducto")));
					list_elem_x_prod.add(elem_x_prod);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_elem_x_prod;
	}
	
	
	
	
	
	
	
}
