package Modelo;
import java.sql.Date;

public class Orden_Trabajo
{
	//private Integer id_ordenTrabajo; es autonum
	private Integer id_Producto;
	private Integer id_cliente;
	private Date f_confeccion;
	private Date f_prometida;
	private String nombre_trabajo;
	private String descripcion;
	private Integer cantidad_preimpresion;
	private Integer ancho;
	private Integer alto;
	private boolean apaisado;
	
	static MySQLBD baseDatos = new MySQLBD().conectar();
	
	public Orden_Trabajo(Integer id_Producto,
			Integer id_cliente, Date f_confeccion, Date f_prometida,
			String nombre_trabajo, String descripcion,
			Integer cantidad_preimpresion, Boolean apaisado) 
	{
		super();
		//this.id_ordenTrabajo = id_ordenTrabajo;
		this.id_Producto = id_Producto;
		this.id_cliente = id_cliente;
		this.f_confeccion = f_confeccion;
		this.f_prometida = f_prometida;
		this.nombre_trabajo = nombre_trabajo;
		this.descripcion = descripcion;
		this.cantidad_preimpresion = cantidad_preimpresion;
	}

	/*public Integer getId_ordenTrabajo() 
	{
		return id_ordenTrabajo;
	}

	public void setId_ordenTrabajo(Integer id_ordenTrabajo) 
	{
		this.id_ordenTrabajo = id_ordenTrabajo;
	}*/

	public Integer getId_Producto() 
	{
		return id_Producto;
	}

	public void setId_Producto(Integer id_Producto) 
	{
		this.id_Producto = id_Producto;
	}

	public Integer getId_cliente() 
	{
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) 
	{
		this.id_cliente = id_cliente;
	}

	public Date getF_confeccion() 
	{
		return f_confeccion;
	}

	public void setF_confeccion(Date f_confeccion) 
	{
		this.f_confeccion = f_confeccion;
	}

	public Date getF_prometida() 
	{
		return f_prometida;
	}

	public void setF_prometida(Date f_prometida) 
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
	
	public boolean alta_OT(Integer id_ordenTrabajo, Integer id_Producto,
			Integer id_cliente, Date f_confeccion, Date f_prometida,
			String nombre_trabajo, String descripcion,
			Integer cantidad_preimpresion) 
			{
				
		if (baseDatos.ejecutar("INSERT INTO TEST(IDENTIFICADOR,DESCRIPCION) VALUES(3,'TRES')")) 
		{
            System.out.println("Ejecucion correcta!");
        } 
        else 
        {
            System.out.println("Ocurrió un problema al ejecutar!");
        }
		
		return false;
	}

	public Integer getAncho()
	{
		return ancho;
	}

	public void setAncho(Integer ancho) 
	{
		this.ancho = ancho;
	}

	public Integer getAlto() 
	{
		return alto;
	}

	public void setAlto(Integer alto) 
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

	public static MySQLBD getBaseDatos() 
	{
		return baseDatos;
	}

	public static void setBaseDatos(MySQLBD baseDatos) 
	{
		Orden_Trabajo.baseDatos = baseDatos;
	}

	//R: una orden de trabajo
	//A: devuelve true si se inserto la orden de trabajo en la tabla.False lo contrario. 
	public boolean alta(Orden_Trabajo ot) 
	{
		Integer id_prod=ot.getId_Producto();
		Integer id_cli=ot.getId_cliente();
		Date f_conf=ot.getF_confeccion();
		Date f_prom=ot.getF_prometida();
		String nom_trabajo=ot.getNombre_trabajo();
		String descr= ot.getDescripcion();
		Integer cant_preimpr=ot.getCantidad_preimpresion();
		Integer ancho=ot.getAncho();
		Integer alto=ot.getAlto();
		boolean apaisado=ot.isApaisado();
		
		if (baseDatos.ejecutar("insert into orden_trabajo(id_orden_trabajo,id_producto,id_cliente,f_confeccion,f_prometida,nombre_trabajo,descripcion,cantidad_preimpresion,ancho,alto,apaisado) VALUES(DEFAULTi,2,1,'a2012/09/01','2012/10/11','Revista Ofertas','Orden de trabajo para la Palelera Lorenzo',5,600,700,true")) {
            return true;
        } 
		else 
        {
            return false;
        }
		
		
		/*
		if (baseDatos.ejecutar("INSERT INTO orden_trabajo VALUES(default,"+id_prod.toString() +","+ id_cli.toString()+"," + 'f_conf'+"," +f_prom+"," +nom_trabajo+"," +descr+cant_preimpr.toString()+")")) {
            return true;
        } else {
            return false;
        }
        */
	}

	}

