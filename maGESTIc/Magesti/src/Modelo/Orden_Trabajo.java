package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vista_Controlador.Config;
import vista_Controlador.Metodos;

public class Orden_Trabajo implements Config
{
		private Integer id_orden_trabajo;
		private String nombre_producto;
		private Integer id_cliente;
		private String f_confeccion;
		private String f_prometida;
		private String nombre_trabajo;
		private String descripcion;
		private Integer cantidad_a_entregar;
		private Integer cantidad_preimpresion;
		private Double ancho;
		private Double alto;
		private boolean apaisado;
		private String estado;
		private Integer hojas_utilizadas;
		private String f_cierre;
		private String f_entrega;
		

	public Orden_Trabajo(Integer id_orden_trabajo, String nombre_Producto,
			Integer id_cliente, String f_confeccion, String f_prometida,
			String nombre_trabajo, String descripcion,
			Integer cantidad_a_entregar, Integer cantidad_preimpresion,
			Double ancho, Double alto, boolean apaisado, String estado,
			Integer hojas_utilizadas,String f_cierre,String f_entrega) {
		super();
		this.id_orden_trabajo = id_orden_trabajo;
		this.nombre_producto = nombre_Producto;
		this.id_cliente = id_cliente;
		this.f_confeccion = f_confeccion;
		this.f_prometida = f_prometida;
		this.nombre_trabajo = nombre_trabajo;
		this.descripcion = descripcion;
		this.cantidad_a_entregar = cantidad_a_entregar;
		this.cantidad_preimpresion = cantidad_preimpresion;
		this.ancho = ancho;
		this.alto = alto;
		this.apaisado = apaisado;
		this.estado = estado;
		this.hojas_utilizadas = hojas_utilizadas;
		this.f_cierre=f_cierre;
		this.f_entrega=f_entrega;
	}

	public Orden_Trabajo(String nombre_Producto, Integer id_cliente,
			String f_confeccion, String f_prometida, String nombre_trabajo,
			String descripcion, Integer cantidad_a_entregar,
			Integer cantidad_preimpresion, Double ancho, Double alto,
			boolean apaisado, String estado, Integer hojas_utilizadas,String f_cierre,String f_entrega) {
		super();
		this.id_orden_trabajo = getUltOT();
		this.nombre_producto = nombre_Producto;
		this.id_cliente = id_cliente;
		this.f_confeccion = f_confeccion;
		this.f_prometida = f_prometida;
		this.nombre_trabajo = nombre_trabajo;
		this.descripcion = descripcion;
		this.cantidad_a_entregar = cantidad_a_entregar;
		this.cantidad_preimpresion = cantidad_preimpresion;
		this.ancho = ancho;
		this.alto = alto;
		this.apaisado = apaisado;
		this.estado = estado;
		this.hojas_utilizadas = hojas_utilizadas;
		this.f_cierre=f_cierre;
		this.f_entrega=f_entrega;
		
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
	
		
		public static String [] getId_nom_OT()
		{
			ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo,nombre_trabajo FROM orden_trabajo WHERE estado!='Cerrada'");
			String[] id_nom_ot = null;
			try 
			{
				resultado.last();
				int cantOTPendiente = resultado.getRow();
				id_nom_ot= new String[cantOTPendiente];
				resultado.beforeFirst();
			} 
			catch (SQLException e2) 
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			if(resultado != null)
			{
				int i=0;
				try 
				{
					while(resultado.next())
					{
						
						Integer id_ot=resultado.getInt("id_orden_trabajo");
						String id_OT_Formato = Metodos.EnteroAFactura(id_ot);
						String nom_ot=resultado.getString("nombre_trabajo");
						id_nom_ot[i]=id_OT_Formato+"  -  "+nom_ot;
						i++;
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}

			}
			return id_nom_ot;
		}
		
		
		
		public static String [] getId_nom_OT_SinStock()
		{
			ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo,nombre_trabajo FROM orden_trabajo WHERE id_orden_trabajo!=1 AND (estado='Pendiente' OR estado='En Proceso')");
			String[] id_nom_ot = null;
			try 
			{
				resultado.last();
				int cantOTPendiente = resultado.getRow();
				id_nom_ot= new String[cantOTPendiente];
				resultado.beforeFirst();
			} 
			catch (SQLException e2) 
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			if(resultado != null)
			{
				int i=0;
				try 
				{
					while(resultado.next())
					{
						
						Integer id_ot=resultado.getInt("id_orden_trabajo");
						String id_OT_Formato = Metodos.EnteroAFactura(id_ot);
						String nom_ot=resultado.getString("nombre_trabajo");
						id_nom_ot[i]=id_OT_Formato+"  -  "+nom_ot;
						i++;
					}
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return id_nom_ot;
		}
		
	public static String[] getNomColum() 
	{
		String columnas[] = 
		{ 
			"<html>Nro Orden<br> Trabajo</html>", 
			"Producto", 
			"Cliente",
			"<html>Fecha de<br> confeccion</html>", 
			"<html>Fecha<br> prometida</html>", 
			"<html>Nombre del<br> trabajo</html>", 
			"Descripcion",
			"<html>Cantidad a<br> entregar</html>",
			"<html>Preimpresiones </html>", 
			"Ancho", 
			"Alto", 
			"Apaisado", 
			"Estado",
			"<html>Hojas<br> Utilizadas</html>"
		};
		return columnas;
	}
	
	public static String[] getNomColumTop5()
	{
		String columnas[] = 
			{ 
				"<html>Nro Orden<br> Trabajo</html>",
				"Cliente",
				"<html>Cantidad a<br> entregar</html>",
				"<html>Fecha de<br> confeccion</html>", 
				"<html>Fecha<br> prometida</html>", 
				"Estado",
				"<html>Hojas<br> Utilizadas</html>",
				"<html>Tarea en<br> ejecucion<html>",
				"<html>Proveedor<html>"
			};
			return columnas;
	}
	
	public String getNombre_producto()
	{
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) 
	{
		this.nombre_producto = nombre_producto;
	}

	public Integer getCantidad_a_entregar() 
	{
		return cantidad_a_entregar;
	}

	public void setCantidad_a_entregar(Integer cantidad_a_entregar) 
	{
		this.cantidad_a_entregar = cantidad_a_entregar;
	}

	public Integer getId_orden_trabajo() 
	{
			return id_orden_trabajo;
	}

	public void setId_orden_trabajo(Integer id_orden_trabajo) 
	{
			this.id_orden_trabajo = id_orden_trabajo;
	}

	public String getNombre_Producto() 
	{
			return nombre_producto;
	}

	public void setNombre_Producto(String nombre_Producto) 
	{
			this.nombre_producto = nombre_Producto;
	}

	public Integer getId_cliente() 
	{
			return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) 
	{
			this.id_cliente = id_cliente;
	}

	public String getF_confeccion() 
	{
			return f_confeccion;
	}

	public void setF_confeccion(String f_confeccion) 
	{
			this.f_confeccion = f_confeccion;
	}

	public String getF_prometida() 
	{
			return f_prometida;
	}

	public void setF_prometida(String f_prometida) 
	{
			this.f_prometida = f_prometida;
	}

		public String getNombre_trabajo() 
		{
			return nombre_trabajo;
		}

		public void setNombre_trabajo(String nombre_trabajo) 
		{
			this.nombre_trabajo = nombre_trabajo;
		}

		public String getDescripcion() 
		{
			return descripcion;
		}

		public void setDescripcion(String descripcion) 
		{
			this.descripcion = descripcion;
		}

		public Integer getCantidad_preimpresion() 
		{
			return cantidad_preimpresion;
		}

		public void setCantidad_preimpresion(Integer cantidad_preimpresion) 
		{
			this.cantidad_preimpresion = cantidad_preimpresion;
		}

		public Double getAncho() 
		{
			return ancho;
		}

		public void setAncho(Double ancho) 
		{
			this.ancho = ancho;
		}

		public Double getAlto() 
		{
			return alto;
		}

		public void setAlto(Double alto) 
		{
			this.alto = alto;
		}

		public boolean isApaisado() 
		{
			return apaisado;
		}

		public void setApaisado(boolean apaisado) 
		{
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
		
		
		public Integer getHojas_utilizadas() 
		{
			return hojas_utilizadas;
		}

		public void setHojas_utilizadas(Integer hojas_utilizadas) 
		{
			this.hojas_utilizadas = hojas_utilizadas;
		}
				
		public String getF_cierre() {
			return f_cierre;
		}

		public void setF_cierre(String f_cierre) {
			this.f_cierre = f_cierre;
		}

		public String getF_entrega() {
			return f_entrega;
		}

		public void setF_entrega(String f_entrega) {
			this.f_entrega = f_entrega;
		}

		public static boolean CambiarEstado(Integer id_OT,String estado){
			estado="'"+estado+"'";
			if(ConexionDB.getbaseDatos().ejecutar("UPDATE orden_trabajo set estado="+estado+"where id_orden_trabajo="+id_OT)){
				return true;
			}else{
				return false;
			}
		}
		
		
		public static boolean CambiarCantHojasUtil(Integer id_OT,Integer cantHojas){
			if(ConexionDB.getbaseDatos().ejecutar("UPDATE orden_trabajo set hojas_utilizadas=hojas_utilizadas+"+cantHojas+" WHERE id_orden_trabajo="+id_OT)){
				return true;
			}else{
				return false;
			}
		}
		
		// A: devuelve true si se inserto la orden de trabajo en la tabla. False lo contrario.
		public boolean Alta() {

		String nombre_prod = getNombre_Producto();
		Integer id_cli = getId_cliente();
		String f_conf = getF_confeccion();
		String f_prom = getF_prometida();
		String nom_trabajo = getNombre_trabajo();
		String descr = getDescripcion();
		Integer cant_a_ent = getCantidad_a_entregar();
		Integer cant_preimpr = getCantidad_preimpresion();
		Double ancho = getAncho();
		Double alto = getAlto();
		boolean apaisa = isApaisado();
		String status = getEstado();
		Integer hojas_utiliz = this.getHojas_utilizadas();
		String f_cierre=this.getF_cierre();
		if(f_cierre != null)
		{
			f_cierre="'"+f_cierre+"'";			
		}
		String f_entrega=getF_entrega();
		if(f_entrega!=null)
		{
			f_entrega="'"+f_entrega+"'";			
		}
	
		System.out.println("ancho" + ancho);
		System.out.println("alto" + alto);
		
		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO orden_trabajo VALUES(default," + "'" + nombre_prod
						+ "'" + "," + id_cli + "," + "'" + f_conf + "'" + ","
						+ "'" + f_prom + "'" + "," + "'" + nom_trabajo + "'"
						+ "," + "'" + descr + "'" + "," + cant_a_ent + ","
						+ cant_preimpr + "," + ancho + "," + alto + ","
						+ apaisa + "," + "'" + status + "'" + ","
						+ hojas_utiliz +"," +f_cierre + "," +f_entrega + ");")) {
			return true;
		} else {
			return false;
		}

	}
		
		
		
	public ArrayList<Orden_Trabajo> Buscar() {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM orden_trabajo WHERE estado != 'cerrado'");

		ArrayList<Orden_Trabajo> list_OT = new ArrayList<Orden_Trabajo>();
		if (resultado != null) 
		{

			try 
 {
				while (resultado.next()) {
					Orden_Trabajo ot = new Orden_Trabajo(new Integer(
							resultado.getInt("id_orden_trabajo")),
							resultado.getString("nombre_producto"),
							new Integer(resultado.getInt("id_cliente")),
							resultado.getString("f_confeccion"),
							resultado.getString("f_prometida"),
							resultado.getString("nombre_trabajo"),
							resultado.getString("descripcion"), new Integer(
									resultado.getInt("cantidad_a_entregar")),
							new Integer(resultado
									.getInt("cantidad_preimpresion")),
							new Double(resultado.getDouble("ancho")),
							new Double(resultado.getDouble("alto")),
							resultado.getBoolean("apaisado"),
							resultado.getString("estado"), new Integer(
									resultado.getInt("hojas_utilizadas")),
							resultado.getString("f_cierre"),
							resultado.getString("f_entrega"));
					list_OT.add(ot);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		return list_OT;
	}
	
	public static ArrayList<String> getId_OTSegunFecha(String fechaprom){
		fechaprom="'"+fechaprom+"'";
		ArrayList<String> id_nomOT=new ArrayList<String>();
		
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo FROM orden_trabajo WHERE f_prometida="+fechaprom + " AND id_orden_trabajo != 1");

		if(resultado != null){
			
			try {
				while(resultado.next()){
						Integer id_ot=resultado.getInt("id_orden_trabajo");
						id_nomOT.add(id_ot.toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return id_nomOT;	
	}
	
	public static boolean setF_h_cierre(Integer id_OT, String f_h_cierre) 
	{
		String fhcierre = "'" + f_h_cierre + "'";
		boolean r=ConexionDB.getbaseDatos().ejecutar(
				"UPDATE orden_trabajo SET f_cierre =" + fhcierre +
						 " WHERE id_orden_trabajo ="+ id_OT);
		return r;
	}
	
	public static ArrayList<String> getOT_SegunID(String id)
	{
		id="'"+id+"'";
		ArrayList<String> OT =new ArrayList<String>();
		
		ResultSet resultado=ConexionDB.getbaseDatos().consultar
		(
				"SELECT * FROM orden_trabajo WHERE id_orden_trabajo="+id);

		if(resultado != null)
		{
			
			try {
				while(resultado.next())
				{
					for (int i=1; i<15; i++)
						OT.add(resultado.getObject(i).toString());
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return OT;	
	}
	
	public static String getId_Con_nom_OT(Integer id_OT) 
	{
		
		String Id_Con_nom_OT="";
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT nombre_trabajo FROM orden_trabajo WHERE id_orden_trabajo="+id_OT);

		if(resultado != null){
			try {
				while(resultado.next()){
					
						String nom_ot=resultado.getString("nombre_trabajo");
						String id_OT_Formato = Metodos.EnteroAFactura(id_OT);
						Id_Con_nom_OT=id_OT_Formato+"  -  "+nom_ot;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return Id_Con_nom_OT;
	}
	//////
	
	public static String  getOTFechaProm(int id_ot) 
	{
		
		String f_prom=null, f_promR=null;
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo,f_prometida FROM orden_trabajo WHERE id_orden_trabajo="+ id_ot);
		
		if(resultado != null){
			try {
				while(resultado.next()){
					
						f_prom= resultado.getString("f_prometida");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		String[] retorno;
		retorno = f_prom.split("\\-");
		f_promR = retorno[2]+"-"+retorno[1]+"-"+retorno[0];
		
		return f_promR;
	}
	
	
	public static Orden_Trabajo getOT(Integer id_OT) {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM orden_trabajo WHERE id_orden_trabajo=" + id_OT);

		Orden_Trabajo OT = null;

		if (resultado != null) {

			try {
				while (resultado.next()) {
					OT = new Orden_Trabajo(new Integer(
							resultado.getInt("id_orden_trabajo")),
							resultado.getString("nombre_producto"),
							new Integer(resultado.getInt("id_cliente")),
							resultado.getString("f_confeccion"),
							resultado.getString("f_prometida"),
							resultado.getString("nombre_trabajo"),
							resultado.getString("descripcion"), new Integer(
									resultado.getInt("cantidad_a_entregar")),
							new Integer(resultado
									.getInt("cantidad_preimpresion")),
							new Double(resultado.getDouble("ancho")),
							new Double(resultado.getDouble("alto")),
							resultado.getBoolean("apaisado"),
							resultado.getString("estado"), new Integer(
									resultado.getInt("hojas_utilizadas")),
							resultado.getString("f_cierre"),
							resultado.getString("f_entrega"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return OT;
	}

	public static Integer getHojasUtilizadas(Integer id_OT) {

		Integer hojas=0;
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT hojas_utilizadas FROM orden_trabajo WHERE id_orden_trabajo="+id_OT);

		if(resultado != null){
			
			try {
				while(resultado.next()){
					hojas=resultado.getInt("hojas_utilizadas");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return hojas;	
	}

	public static String getEstadoOT(Integer id_OT) {

		String estado = "";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT estado FROM orden_trabajo WHERE id_orden_trabajo="
						+ id_OT);

		if (resultado != null) {

			try {
				while (resultado.next()) {
					estado = resultado.getString("estado");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return estado;
	}
	
}
