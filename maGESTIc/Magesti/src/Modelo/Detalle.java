package Modelo;

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
	
	
	public Detalle(Integer id_detalle, Integer id_solicitud_compra,
			Integer cantidad, String marca, Integer id_calidad,
			Integer id_formato_papel, Integer id_variante, Integer gramaje,
			Double precio_unitario, String unidad_medida_del_precio,
			Double importe) {
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
	}
	
	public Detalle(Integer id_solicitud_compra,
			Integer cantidad, String marca, Integer id_calidad,
			Integer id_formato_papel, Integer id_variante, Integer gramaje,
			Double precio_unitario, String unidad_medida_del_precio,
			Double importe) {
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

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO detalle VALUES(default," + id_solicitud_compra + ","
						+ cantidad+ "," + marca+ "," + id_calidad+ "," + id_formato_papel
						+ "," +id_variante+ "," + gramaje+ "," + precio_unitario
						+ "," + unidad_medida_del_precio+ "," + importe+ ");")) {
			return true;
		} else {
			return false;
		}

	}
	
	
	
	
}
