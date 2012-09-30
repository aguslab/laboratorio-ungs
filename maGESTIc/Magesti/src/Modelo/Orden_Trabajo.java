package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;


public class Orden_Trabajo {


		private Integer id_Producto;
		private Integer id_cliente;
		private String f_confeccion;
		private String f_prometida;
		private String nombre_trabajo;
		private String descripcion;
		private Integer cantidad_preimpresion;
		private Integer ancho;
		private Integer alto;
		private boolean apaisado;
		private String estado;



		static MySQLBD baseDatos = new MySQLBD().conectar();

		public Orden_Trabajo(Integer id_Producto, Integer id_cliente,
				String f_confeccion, String f_prometida, String nombre_trabajo,
				String descripcion, Integer cantidad_preimpresion,Integer ancho,Integer alto,
				boolean apaisado,String estado) {
			super();
			this.id_Producto = id_Producto;
			this.id_cliente = id_cliente;
			this.f_confeccion = f_confeccion;
			this.f_prometida = f_prometida;
			this.nombre_trabajo = nombre_trabajo;
			this.descripcion = descripcion;
			this.cantidad_preimpresion = cantidad_preimpresion;
			this.ancho= ancho;
			this.alto= alto;
			this.apaisado = apaisado;
			this.estado= estado;
		}

		public Integer getId_Producto() {
			return id_Producto;
		}

		public void setId_Producto(Integer id_Producto) {
			this.id_Producto = id_Producto;
		}

		public Integer getId_cliente() {
			return id_cliente;
		}

		public void setId_cliente(Integer id_cliente) {
			this.id_cliente = id_cliente;
		}

		public String getF_confeccion() {
			return f_confeccion;
		}

		public void setF_confeccion(String f_confeccion) {
			this.f_confeccion = f_confeccion;
		}

		public String getF_prometida() {
			return f_prometida;
		}

		public void setF_prometida(String f_prometida) {
			this.f_prometida = f_prometida;
		}

		public String getNombre_trabajo() {
			return nombre_trabajo;
		}

		public void setNombre_trabajo(String nombre_trabajo) {
			this.nombre_trabajo = nombre_trabajo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Integer getCantidad_preimpresion() {
			return cantidad_preimpresion;
		}

		public void setCantidad_preimpresion(Integer cantidad_preimpresion) {
			this.cantidad_preimpresion = cantidad_preimpresion;
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

		public void setAlto(Integer alto) {
			this.alto = alto;
		}

		public boolean isApaisado() {
			return apaisado;
		}

		public void setApaisado(boolean apaisado) {
			this.apaisado = apaisado;
		}

		public MySQLBD getBaseDatos() {
			return baseDatos;
		}

		public static void setBaseDatos(MySQLBD baseDatos) {
			Orden_Trabajo.baseDatos = baseDatos;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		
		
		// R: una orden de trabajo
		// A: devuelve true si se inserto la orden de trabajo en la tabla. False
		// lo contrario.
		public boolean Alta() {
			
			Integer id_prod = getId_Producto();
			Integer id_cli = getId_cliente();
			String f_conf = getF_confeccion();
			String f_prom = getF_prometida();
			String nom_trabajo = getNombre_trabajo();
			String descr = getDescripcion();
			Integer cant_preimpr = getCantidad_preimpresion();
			Integer ancho = getAncho();
			Integer alto = getAlto();
			boolean apaisado = isApaisado();
			String status= estado;
			

			/*	
			if (baseDatos
					.ejecutar("insert into orden_trabajo VALUES(DEFAULT,2,1,'2012/09/01','2012/10/11 08:00:34','Revista Ofertas','Orden de trabajo para la Palelera Lorenzo',5,600,700,true);")) {
				return true;
			} else {
				return false;
			}
			
			*/
			
		if (baseDatos.ejecutar("INSERT INTO orden_trabajo VALUES(default,"
				+ id_prod + "," + id_cli + "," + "'" + f_conf + "'" + "," + "'"
				+ f_prom + "'" + "," + "'" + nom_trabajo + "'" + "," + "'"
				+ descr + "'" + "," + cant_preimpr + "," + ancho + "," + alto
				+ "," + apaisado +","+"'"+status+"'"+ ");")) {
			return true;
		} else {
			return false;
		}
			 
		}
		
		
		
		public ArrayList<Orden_Trabajo> Buscar(Integer id_Ot){
			
			ResultSet resultado= this.getBaseDatos().consultar("SELECT * FROM orden_trabajo WHERE estado != cerrado");
			
			ArrayList<Orden_Trabajo> list_OT= new ArrayList<Orden_Trabajo>();
			if (resultado != null) 
	        {
				
	            try 
	            {
	         	            	
	                while (resultado.next()) 
	                {
	                    Orden_Trabajo ot= new Orden_Trabajo(new Integer(resultado.getInt("id_producto")), new Integer(resultado.getInt("id_cliente")),resultado.getString("f_confeccion"), resultado.getString("f_prometida"), resultado.getString("nombre_trabajo"), resultado.getString("descripcion"), new Integer(resultado.getInt("cantidad_preimpresion")), new Integer(resultado.getInt("ancho")), new Integer(resultado.getInt("alto")), resultado.getBoolean("apaisado"), resultado.getString("estado"));
	                }
	            } 
	            catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	        }

			return list_OT;
		}



}
