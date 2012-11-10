package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vista_Controlador.FilaRetiros;
import vista_Controlador.Metodos;

public class Egreso_Stock {

	 private Integer id_egreso_stock; 
	 private Integer id_stock;
	 private Integer id_materiales;
	 private String empleado;
	 private Integer cant_hojas_retiradas;
	 private String  fecha;

	
	 
	 public Egreso_Stock(Integer id_stock, Integer id_materiales, String empleado,
			Integer cant_hojas_retiradas, String fecha) {
		super();
		this.id_stock = id_stock;
		this.id_materiales=id_materiales;
		this.empleado=empleado;
		this.cant_hojas_retiradas = cant_hojas_retiradas;
		this.fecha = fecha;
	}
	 
	 
	 
	public Integer getId_egreso_stock() {
		return id_egreso_stock;
	}




	public void setId_egreso_stock(Integer id_egreso_stock) {
		this.id_egreso_stock = id_egreso_stock;
	}




	public Integer getId_stock() {
		return id_stock;
	}




	public void setId_stock(Integer id_stock) {
		this.id_stock = id_stock;
	}

	


	public String getEmpleado() {
		return empleado;
	}



	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}



	public Integer getCant_hojas_retiradas() {
		return cant_hojas_retiradas;
	}



	public void setCant_hojas_retiradas(Integer cant_hojas_retiradas) {
		this.cant_hojas_retiradas = cant_hojas_retiradas;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public Integer getId_materiales() {
		return id_materiales;
	}


	public void setId_materiales(Integer id_materiales) {
		this.id_materiales = id_materiales;
	}





	public boolean Alta() {

		Integer id_stk = this.getId_stock();
		Integer id_mater=this.getId_materiales();
		String empl="'"+this.getEmpleado()+"'";
		Integer cant_hojas_retiradas = this.getCant_hojas_retiradas();
		String fecha = "'" + this.getFecha() + "'";

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO egreso_stock VALUES(default," + id_stk + "," +id_mater+ ","
						+ empl+ "," + cant_hojas_retiradas + "," + fecha + ");")) {
			return true;
		} else {
			return false;
		}
	}
	 
	
	public static Integer getUltEgreso() {
		Integer maxId = null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT MAX(id_egreso_stock) FROM egreso_stock");

		if (resultado != null) {
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					maxId = resultado.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return maxId + 1;
	}
	
	
	public static Integer getHojasUsadas(Integer id_materiales) {
		Integer cantHojas = 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT cant_hojas_retiradas FROM egreso_stock WHERE id_materiales="+id_materiales);

		if (resultado != null) {
			try {
				while (resultado.next()) {
					cantHojas=cantHojas + resultado.getInt("cant_hojas_retiradas");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cantHojas;
	}


//	public static ArrayList<Integer> getIdStockSegunIdMaterial(Integer id_materiales) {
//		ArrayList<Integer> ids_stock=new ArrayList<Integer>();
//		
//		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
//				"SELECT id_stock FROM egreso_stock WHERE id_materiales="+id_materiales+" ORDER BY id_stock");
//
//		if (resultado != null) {
//			try {
//				while (resultado.next()) {
//					ids_stock.add(resultado.getInt("id_stock"));
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return ids_stock;
//	}

	
	public static ArrayList<FilaRetiros> getRetirosStock(Integer nroOT) 
	{
		String remanente = "";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT s.id_solicitud_compra, rp.f_h_recibido, sc.f_confeccion, sc.f_entrega, es.fecha,es.cant_hojas_retiradas, s.Remanente,es.empleado FROM egreso_stock es inner join stock s ON es.id_stock= s.id_stock" +
				" INNER JOIN solicitud_compra sc ON s.id_solicitud_compra = sc.id_solicitud_compra INNER JOIN recepcion_pedido rp ON rp.id_solicitud_compra = s.id_solicitud_compra  WHERE s.id_orden_trabajo =" + nroOT);
		ArrayList<FilaRetiros> retiros = new ArrayList<FilaRetiros>();
		if (resultado != null)
		{
			try 
			{
				while (resultado.next()) 
				{
					System.out.println("rema" + resultado.getBoolean("Remanente"));
					if(resultado.getBoolean("Remanente") == true)
					{
						remanente = "Si";
					}
					else
					{
						remanente = "No";
					}
					FilaRetiros fr = new FilaRetiros(resultado.getString("id_Solicitud_Compra"), resultado.getString("f_h_recibido"), resultado.getString("f_confeccion"), 
							resultado.getString("f_entrega"),resultado.getString("fecha"),new Integer(resultado.getInt("cant_hojas_retiradas")),
							remanente, resultado.getString("Empleado"));
					
					retiros.add(fr);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return retiros;
	}
	
}
