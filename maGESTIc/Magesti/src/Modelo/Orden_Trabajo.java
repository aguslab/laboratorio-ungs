package Modelo;


import java.sql.ResultSet;
import java.util.ArrayList;


public class Orden_Trabajo {

		private Integer id_orden_trabajo;
		private String nombre_producto;
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



		public Orden_Trabajo(Integer id_orden_trabajo, String nombre_Producto, Integer id_cliente,
				String f_confeccion, String f_prometida, String nombre_trabajo,
				String descripcion, Integer cantidad_preimpresion,Integer ancho,Integer alto,
				boolean apaisado,String estado) {
			super();
			this.id_orden_trabajo= id_orden_trabajo;
			this.nombre_producto = nombre_Producto;
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

		public Orden_Trabajo(String nombre_Producto, Integer id_cliente,
				String f_confeccion, String f_prometida, String nombre_trabajo,
				String descripcion, Integer cantidad_preimpresion,Integer ancho,Integer alto,
				boolean apaisado,String estado) {
			super();
			this.id_orden_trabajo=getUltOT();//ver si va, es posible q cause problemas
			this.nombre_producto = nombre_Producto;
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
		
	public static Integer getUltOT() {
		Integer maxId = null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT MAX(id_orden_trabajo) FROM orden_trabajo");

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
	
		public static String [] getNomColum() {
			String columnas[]={"Cliente", "fecha Conf", "fecha prom", "nombre trabajo", "Descripcion", "cant Preimpr", "ancho","alto", "apaisado", "Estado"};
			
			return columnas;
		}
	
	
		public Integer getId_orden_trabajo() {
			return id_orden_trabajo;
		}

		public void setId_orden_trabajo(Integer id_orden_trabajo) {
			this.id_orden_trabajo = id_orden_trabajo;
		}

		public String getNombre_Producto() {
			return nombre_producto;
		}

		public void setNombre_Producto(String nombre_Producto) {
			this.nombre_producto = nombre_Producto;
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


		public String getEstado() 
		{
			return estado;
		}

		public void setEstado(String estado) 
		{
			this.estado = estado;
		}
		//metodo trucho que solo muestra unos datos para ver que anda xD
		public void mostrarDatos(){
			System.out.println();
			System.out.print(this.getId_orden_trabajo()+"    ");
			System.out.print(this.getNombre_Producto()+"     ");
			System.out.print(this.getF_prometida()+"     ");
			System.out.print(this.getNombre_trabajo()+"     ");
			System.out.print(this.getEstado()+"     ");
			
		}
		
		
		// R: una orden de trabajo
		// A: devuelve true si se inserto la orden de trabajo en la tabla. False
		// lo contrario.
		public boolean Alta() 
 {

		String nombre_prod = getNombre_Producto();
		Integer id_cli = getId_cliente();
		String f_conf = getF_confeccion();
		String f_prom = getF_prometida();
		String nom_trabajo = getNombre_trabajo();
		String descr = getDescripcion();
		Integer cant_preimpr = getCantidad_preimpresion();
		Integer ancho = getAncho();
		Integer alto = getAlto();
		boolean apaisado = isApaisado();
		String status = getEstado();

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO orden_trabajo VALUES(default," + "'"+nombre_prod+"'" + ","
						+ id_cli + "," + "'" + f_conf + "'" + "," + "'"
						+ f_prom + "'" + "," + "'" + nom_trabajo + "'" + ","
						+ "'" + descr + "'" + "," + cant_preimpr + "," + ancho
						+ "," + alto + "," + apaisado + "," + "'" + status
						+ "'" + ");")) {
			return true;
		} else {
			return false;
		}

	}
		
		
		
	public ArrayList<Orden_Trabajo> Buscar() {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM orden_trabajo WHERE estado != 'cerrado'");

		ArrayList<Orden_Trabajo> list_OT = new ArrayList<Orden_Trabajo>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Orden_Trabajo ot = new Orden_Trabajo(new Integer(
							resultado.getInt("id_orden_trabajo")),
							resultado.getString("nombre_producto"),
							new Integer(resultado.getInt("id_cliente")),
							resultado.getString("f_confeccion"),
							resultado.getString("f_prometida"),
							resultado.getString("nombre_trabajo"),
							resultado.getString("descripcion"), new Integer(
									resultado.getInt("cantidad_preimpresion")),
							new Integer(resultado.getInt("ancho")),
							new Integer(resultado.getInt("alto")),
							resultado.getBoolean("apaisado"),
							resultado.getString("estado"));
					list_OT.add(ot);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_OT;
	}
	
		
		
}
