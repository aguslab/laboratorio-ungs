package Modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vista_Controlador.Config;

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
		private String sApaisado;

		public Orden_Trabajo
		(
			Integer id_orden_trabajo, 
			String nombre_Producto, 
			Integer id_cliente,
			String f_confeccion, 
			String f_prometida, 
			String nombre_trabajo,
			String descripcion,
			Integer cantidad_a_entregar, 
			Integer cantidad_preimpresion,
			Double ancho,
			Double alto,
			boolean apaisado,
			String estado,
			Integer hojas_utilizadas
		) 
		{
			super();
			this.id_orden_trabajo= id_orden_trabajo;
			this.nombre_producto = nombre_Producto;
			this.id_cliente = id_cliente;
			this.f_confeccion = f_confeccion;
			this.f_prometida = f_prometida;
			this.nombre_trabajo = nombre_trabajo;
			this.descripcion = descripcion;
			this.cantidad_a_entregar=cantidad_a_entregar;
			this.cantidad_preimpresion = cantidad_preimpresion;
			this.ancho= ancho;
			this.alto= alto;
			this.apaisado = apaisado;
			this.estado= estado;
			this.hojas_utilizadas=hojas_utilizadas;
		}

		public Orden_Trabajo
		(
			String nombre_Producto, 
			Integer id_cliente,
			String f_confeccion, 
			String f_prometida, 
			String nombre_trabajo,
			String descripcion, 
			Integer cantidad_a_entregar,
			Integer cantidad_preimpresion, 
			Double ancho, 
			Double alto,
			boolean apaisado, 
			String estado,
			Integer hojas_utilizadas
		) 
		{
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
			this.hojas_utilizadas=hojas_utilizadas;
		}
		
		public Orden_Trabajo
		(
			String nombre_Producto, 
			Integer id_cliente,
			String f_confeccion, 
			String f_prometida, 
			String nombre_trabajo,
			String descripcion, 
			Integer cantidad_a_entregar,
			Integer cantidad_preimpresion, 
			Double ancho, 
			Double alto,
			String sApaisado, 
			String estado,
			Integer hojas_utilizadas
		) 
		{
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
			this.sApaisado = sApaisado;
			this.estado = estado;
			this.hojas_utilizadas=hojas_utilizadas;
		}
		
		public static Integer getUltOT(){
			Integer maxId = null;
			ResultSet resultado = ConexionDB.getbaseDatos().consultar
			(
				"SELECT MAX(id_orden_trabajo) FROM orden_trabajo"
			);

		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					// como solo devuelve un valor, le pido el del registro (1)
					maxId = resultado.getInt(1);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return maxId + 1;
	}
	
		
	public static String [] getId_nom_OT(){
		
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo,nombre_trabajo FROM orden_trabajo WHERE estado='Pendiente'");
		String[] id_nom_ot = null;
		try {
			resultado.last();
			int cantOTPendiente = resultado.getRow();
			id_nom_ot= new String[cantOTPendiente+1];
			resultado.beforeFirst();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		if(resultado != null){
			int i=0;
			try {
				while(resultado.next()){
					
						Integer id_ot=resultado.getInt("id_orden_trabajo");
						String id_OT_Formato=EnteroAFactura(id_ot);
						String nom_ot=resultado.getString("nombre_trabajo");
						id_nom_ot[i]=id_OT_Formato+"  -  "+nom_ot;
						i++;
				}
			} catch (SQLException e) {
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
			"Nro Orden Trabajo", 
			"Producto", 
			"Cliente",
			"Fecha de confeccion", 
			"Fecha prometida", 
			"Nombre del trabajo", 
			"Descripcion",
			"Cantidad a entregar",
			"Preimpresiones", 
			"Ancho", 
			"Alto", 
			"Apaisado", 
			"Estado",
			"Hojas Utilizadas"
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

		
		
		public static boolean CambiarEstado(Integer id_OT,String estado){
			estado="'"+estado+"'";
			if(ConexionDB.getbaseDatos().ejecutar("UPDATE orden_trabajo set estado="+estado+"where id_orden_trabajo="+id_OT)){
				return true;
			}else{
				return false;
			}
		}
		
		
		public static boolean CambiarCantHojasUtil(Integer id_OT,Integer cantHojas){
			if(ConexionDB.getbaseDatos().ejecutar("UPDATE orden_trabajo set hojas_utilizadas="+cantHojas+" where id_orden_trabajo="+id_OT)){
				return true;
			}else{
				return false;
			}
		}
		
		// R: una orden de trabajo
		// A: devuelve true si se inserto la orden de trabajo en la tabla. False
		// lo contrario.
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

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO orden_trabajo VALUES(default," + "'" + nombre_prod
						+ "'" + "," + id_cli + "," + "'" + f_conf + "'" + ","
						+ "'" + f_prom + "'" + "," + "'" + nom_trabajo + "'"
						+ "," + "'" + descr + "'" + "," + cant_a_ent + ","
						+ cant_preimpr + "," + ancho + "," + alto + ","
						+ apaisa + "," + "'" + status + "'" + ","
						+ hojas_utiliz + ");")) {
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
				while (resultado.next()) 
				{
					Orden_Trabajo ot = new Orden_Trabajo
					(
						new Integer(resultado.getInt("id_orden_trabajo")),
						resultado.getString("nombre_producto"),
						new Integer(resultado.getInt("id_cliente")),
						resultado.getString("f_confeccion"),
						resultado.getString("f_prometida"),
						resultado.getString("nombre_trabajo"),
						resultado.getString("descripcion"), 
						new Integer(resultado.getInt("cantidad_a_entregar")),
						new Integer(resultado.getInt("cantidad_preimpresion")),
						new Double(resultado.getDouble("ancho")),
						new Double(resultado.getDouble("alto")),
						resultado.getBoolean("apaisado"),
						resultado.getString("estado"),
						new Integer(resultado.getInt("hojas_utilizadas"))
					);
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
	
    static public String EnteroAFactura(Integer valor ) 
    {
       DecimalFormat elFormato = new DecimalFormat("00000000");
       String salida = qSUCURSAL + elFormato.format(valor);
       return salida;
    }
	
    static public Integer FacturaAEntero(String valor ) 
    {
       valor=valor.replaceAll("0001-","");
       return Integer.parseInt(valor);
    }
    
    static public String esApaisadaS (boolean dato)
    {
    	if (dato)
    	{
    		return "SI";
    	}
    	else
    	{
    		return "NO";
    	}
    	
    }
    
    static public boolean esApaisadaB (String dato)
    {
    	if (dato.equalsIgnoreCase("SI"))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

	public static String getId_Con_nom_OT(String nom_ot) {
		
		String Id_Con_nom_OT="";
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo FROM orden_trabajo WHERE nombre_trabajo="+"'"+nom_ot+"'");

		if(resultado != null){
			try {
				while(resultado.next()){
					
						Integer id_ot=resultado.getInt("id_orden_trabajo");
						String id_OT_Formato=EnteroAFactura(id_ot);
						Id_Con_nom_OT=id_OT_Formato+"  -  "+nom_ot;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return Id_Con_nom_OT;
	}

	public static Date getDateTimeActual() {
		Date f_h_actual = null;
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("select CURRENT_TIMESTAMP()");
		if(resultado != null){
			try {
				while(resultado.next()){
					
						f_h_actual=resultado.getDate(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f_h_actual;
	}
	
	
	public static ArrayList<String> getId_Nom_OTSegunFecha(String fechaprom){
		fechaprom="'"+fechaprom+"'";
		ArrayList<String> id_nomOT=new ArrayList<String>();
		
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("SELECT id_orden_trabajo, nombre_trabajo FROM orden_trabajo WHERE f_prometida="+fechaprom);

		if(resultado != null){
			
			try {
				while(resultado.next()){
						Integer id_ot=resultado.getInt("id_orden_trabajo");
						id_nomOT.add(id_ot.toString());
						id_nomOT.add(resultado.getString("nombre_trabajo"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return id_nomOT;	
	}
	
	
		
}
