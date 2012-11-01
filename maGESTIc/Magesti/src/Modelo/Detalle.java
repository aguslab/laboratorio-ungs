package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Detalle {
	private Integer id_detalle;
	private Integer id_solicitud_compra;
	private Integer cantidad;
	private String marca;
	private Integer id_calidad;
	private Integer id_formato_papel;
	private Integer id_variante;
	private Integer gramaje;
	private Double precio_unitario;
	private String unidad_medida_del_precio;
	private Double importe;
	private boolean recibido;
	
	
	public Detalle(Integer id_detalle, Integer id_solicitud_compra,
			Integer cantidad, String marca, Integer id_calidad,
			Integer id_formato_papel, Integer id_variante, Integer gramaje,
			Double precio_unitario, String unidad_medida_del_precio,
			Double importe,boolean recibido) {
		super();
		this.id_detalle = id_detalle;
		this.id_solicitud_compra = id_solicitud_compra;
		this.cantidad = cantidad;
		this.marca = marca;
		this.id_calidad = id_calidad;
		this.id_formato_papel = id_formato_papel;
		this.id_variante = id_variante;
		this.gramaje = gramaje;
		this.precio_unitario = precio_unitario;
		this.unidad_medida_del_precio = unidad_medida_del_precio;
		this.importe = importe;
		this.recibido= recibido;
	}
	
	public Detalle(Integer id_solicitud_compra,
			Integer cantidad, String marca, Integer id_calidad,
			Integer id_formato_papel, Integer id_variante, Integer gramaje,
			Double precio_unitario, String unidad_medida_del_precio,
 Double importe, boolean recibido) {
		super();
		this.id_solicitud_compra = id_solicitud_compra;
		this.cantidad = cantidad;
		this.marca = marca;
		this.id_calidad = id_calidad;
		this.id_formato_papel = id_formato_papel;
		this.id_variante = id_variante;
		this.gramaje = gramaje;
		this.precio_unitario = precio_unitario;
		this.unidad_medida_del_precio = unidad_medida_del_precio;
		this.importe = importe;
		this.recibido = recibido;
	}

	public Integer getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(Integer id_detalle) {
		this.id_detalle = id_detalle;
	}

	public Integer getId_solicitud_compra() {
		return id_solicitud_compra;
	}

	public void setId_solicitud_compra(Integer id_solicitud_compra) {
		this.id_solicitud_compra = id_solicitud_compra;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public Integer getId_formato_papel() {
		return id_formato_papel;
	}

	public void setId_formato_papel(Integer id_formato_papel) {
		this.id_formato_papel = id_formato_papel;
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

	public Double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(Double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public String getUnidad_medida_del_precio() {
		return unidad_medida_del_precio;
	}

	public void setUnidad_medida_del_precio(String unidad_medida_del_precio) {
		this.unidad_medida_del_precio = unidad_medida_del_precio;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	public boolean isRecibido() {
		return recibido;
	}

	public void setRecibido(boolean recibido) {
		this.recibido = recibido;
	}

	public boolean Alta() {

		Integer id_solicitud_compra=this.getId_solicitud_compra();
		Integer cantidad=this.getCantidad();
		String marca="'"+this.getMarca()+"'";
		Integer id_calidad=this.getId_calidad();
		Integer id_formato_papel=this.getId_formato_papel();
		Integer id_variante=this.getId_variante();
		Integer gramaje=this.getGramaje();
		Double precio_unitario=this.getPrecio_unitario();
		String unidad_medida_del_precio="'"+this.getUnidad_medida_del_precio()+"'";
		Double importe=this.getImporte();
		boolean recibido=this.isRecibido();

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO detalle VALUES(default," + id_solicitud_compra + ","
						+ cantidad+ "," + marca+ "," + id_calidad+ "," + id_formato_papel
						+ "," +id_variante+ "," + gramaje+ "," + precio_unitario
						+ "," + unidad_medida_del_precio+ "," + importe+","+recibido+ ");")) {
			return true;
		} else {
			return false;
		}

	}

	public static Integer cantidadFilas(Integer id_SC) {
		Integer cantidad = 0;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT COUNT(*) FROM detalle WHERE id_solicitud_compra=" + id_SC);
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					cantidad=resultado.getInt(1);
					break;
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	
		return cantidad;
	}

	public static ArrayList<Integer> cantidadDeDetalle(Integer id_SC) {
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT cantidad FROM detalle WHERE id_solicitud_compra ="+ id_SC);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getInt("cantidad"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}

	public static ArrayList<String> marcaDeDetalle(Integer id_SC) {
		
		ArrayList<String> marcas = new ArrayList<String>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT marca FROM detalle WHERE id_solicitud_compra="+ id_SC);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					marcas.add(resultado.getString("marca"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return marcas;
	}

	public static ArrayList<Integer> calidadDeDetalle(Integer id_SC) {
		
		ArrayList<Integer> calidades = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT id_calidad FROM detalle WHERE id_solicitud_compra ="+ id_SC);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					calidades.add(resultado.getInt("id_calidad"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return calidades;
	}

	public static ArrayList<Integer> formato_papel_DeDetalle(Integer id_SC) {
		
		ArrayList<Integer> formatos = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT id_formato_papel FROM detalle WHERE id_solicitud_compra ="+ id_SC);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					formatos.add(resultado.getInt("id_formato_papel"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return formatos;
	}

	public static ArrayList<Integer> varianteDeDetalle(Integer id_SC) {
		ArrayList<Integer> variantes = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT id_variante FROM detalle WHERE id_solicitud_compra ="+ id_SC);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					variantes.add(resultado.getInt("id_variante"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return variantes;
	}

	public static ArrayList<Integer> gramajeDeDetalle(Integer id_SC) {
		
		ArrayList<Integer> gramajes = new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT gramaje FROM detalle WHERE id_solicitud_compra ="+ id_SC);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					gramajes.add(resultado.getInt("gramaje"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return gramajes;
	}

	public static ArrayList<Double> precioUnitarioDeDetalle(Integer id_SC) {
		ArrayList<Double> precios_unitario = new ArrayList<Double>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT precio_unitario FROM detalle WHERE id_solicitud_compra ="+ id_SC);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					precios_unitario .add(resultado.getDouble("precio_unitario"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return precios_unitario;
	}

	public static ArrayList<String> unidadMedidaDeDetalle(Integer id_SC) {
		
		ArrayList<String> unidad_medidas = new ArrayList<String>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT unidad_medida_del_precio FROM detalle WHERE id_solicitud_compra="+ id_SC);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					unidad_medidas.add(resultado.getString("unidad_medida_del_precio"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return unidad_medidas;
	}

	public static ArrayList<Double> importeDeDetalle(Integer id_SC) {
		ArrayList<Double> importes = new ArrayList<Double>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
		"SELECT importe FROM detalle WHERE id_solicitud_compra ="+ id_SC);
	
		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					importes .add(resultado.getDouble("importe"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return importes;
	}

	public static boolean isRecibido(Integer id_detalle) {
		boolean estado=false;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT recibido FROM detalle WHERE id_detalle="+ id_detalle);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							estado=resultado.getBoolean("recibido");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return estado;
	}
	
	public static Integer dameIdDetalle(Integer SC,Integer cant,String marca,Integer id_cal,Integer id_formato,Integer id_var,Integer gram,Double precioUnit,String unidadMed,Double importe){
		Integer idDetalle=null;
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_detalle FROM detalle WHERE id_solicitud_compra="+SC+" AND cantidad="+cant+" AND marca="+"'"+marca+"'"+" AND id_calidad="+id_cal+
				" AND id_formato_papel="+id_formato+" AND id_variante="+id_var+" AND gramaje="+gram+" AND precio_unitario="+precioUnit+ " AND unidad_medida_del_precio="+"'"+unidadMed+"'"+
				" AND importe="+importe);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							idDetalle=resultado.getInt(1);
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return idDetalle;
	}
	
	
	public static void setAllAsRecibidos(Integer id_SC) {

		ConexionDB.getbaseDatos().ejecutar(
				"UPDATE detalle set recibido=true WHERE id_solicitud_compra="+ id_SC);
	}
	
	
	public static void setAsRecibido(Integer id_detalle,boolean estado) {

		ConexionDB.getbaseDatos().ejecutar(
				"UPDATE detalle set recibido="+estado+" WHERE id_detalle="+ id_detalle);
	}

	public static Integer getCantHojas(Integer id_det) {
		Integer cantHojas=null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT cantidad FROM detalle WHERE id_detalle="+ id_det);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							cantHojas=resultado.getInt("cantidad");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return cantHojas;
	}

	public static String getMarca(Integer id_det) {
		String marca=null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT marca FROM detalle WHERE id_detalle="+ id_det);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							marca=resultado.getString("marca");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return marca;
	}

	public static Integer getidCalidad(Integer id_det) {
		Integer id_cal=null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_calidad FROM detalle WHERE id_detalle="+ id_det);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							id_cal=resultado.getInt("id_calidad");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return id_cal;
	}
	
	
	public static Integer getidFormato(Integer id_det) {
		Integer id_for=null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_formato_papel FROM detalle WHERE id_detalle="+ id_det);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							id_for=resultado.getInt("id_formato_papel");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return id_for;
	}
	
	
	public static Integer getidVariante(Integer id_det) {
		Integer id_var=null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_variante FROM detalle WHERE id_detalle="+ id_det);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							id_var=resultado.getInt("id_variante");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return id_var;
	}
	
	
	public static Integer getGramaje(Integer id_det) {
		Integer gramaje=null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT gramaje FROM detalle WHERE id_detalle="+ id_det);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							gramaje=resultado.getInt("gramaje");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return gramaje;
	}

	public static ArrayList<Integer> getIdDetalles(Integer id_SC) {

		ArrayList<Integer> id_det=new ArrayList<Integer>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_detalle FROM detalle WHERE id_solicitud_compra="+ id_SC);
			
				if (resultado != null)
				{
					try
					{
						while (resultado.next())
						{
							id_det.add(resultado.getInt("id_detalle"));
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
		return id_det;	
	}
	
	
	public static ArrayList<Detalle> getDetallesRecibidos(Integer id_SC) {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT * FROM detalle WHERE id_solicitud_compra=" + id_SC + " AND recibido=true");

		ArrayList<Detalle> detalles = new ArrayList<Detalle>();

		if (resultado != null) {

			try {
				while (resultado.next()) {
					Detalle det = new Detalle(new Integer(
							resultado.getInt("id_detalle")),new Integer(
									resultado.getInt("id_solicitud_compra")),new Integer(
											resultado.getInt("cantidad")),
							resultado.getString("marca"),
							new Integer(resultado.getInt("id_calidad")),new Integer(
									resultado.getInt("id_formato_papel")),new Integer(
											resultado.getInt("id_variante")),new Integer(
													resultado.getInt("gramaje")),
							new Double(resultado.getDouble("precio_unitario")),resultado.getString("unidad_medida_del_precio"),
							new Double(resultado.getDouble("importe")),
							resultado.getBoolean("recibido"));
					
					detalles.add(det);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return detalles;
	}
	
	
}
