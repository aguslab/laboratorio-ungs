package Modelo;

import java.sql.ResultSet;

public class Solicitud_compra {

	private Integer id_solicitud_compra;
	private String f_confeccion;
	private Integer id_proveedor;
	private String vendedor;
	private Integer id_orden_trabajo;
	private boolean envia_proveedor;
	private String direccion_retiro;
	private String f_entrega;
	private String horario_entrega;
	private Double subtotal;
	private Double porcentaje_iva;
	private Double monto_iva;
	private Double total;
	
	
	public Solicitud_compra(Integer id_solicitud_compra, String f_confeccion,
			Integer id_proveedor, String vendedor, Integer id_orden_trabajo,
			boolean envia_proveedor, String direccion_retiro, String f_entrega,
			String horario_entrega, Double subtotal, Double porcentaje_iva,
			Double monto_iva, Double total) {
		super();
		this.id_solicitud_compra = id_solicitud_compra;
		this.f_confeccion = f_confeccion;
		this.id_proveedor = id_proveedor;
		this.vendedor = vendedor;
		this.id_orden_trabajo = id_orden_trabajo;
		this.envia_proveedor = envia_proveedor;
		this.direccion_retiro = direccion_retiro;
		this.f_entrega = f_entrega;
		this.horario_entrega = horario_entrega;
		this.subtotal = subtotal;
		this.porcentaje_iva = porcentaje_iva;
		this.monto_iva = monto_iva;
		this.total = total;
	}
	
	public Solicitud_compra(String f_confeccion,
			Integer id_proveedor, String vendedor, Integer id_orden_trabajo,
			boolean envia_proveedor, String direccion_retiro, String f_entrega,
			String horario_entrega, Double subtotal, Double porcentaje_iva,
			Double monto_iva, Double total) {
		super();
		this.f_confeccion = f_confeccion;
		this.id_proveedor = id_proveedor;
		this.vendedor = vendedor;
		this.id_orden_trabajo = id_orden_trabajo;
		this.envia_proveedor = envia_proveedor;
		this.direccion_retiro = direccion_retiro;
		this.f_entrega = f_entrega;
		this.horario_entrega = horario_entrega;
		this.subtotal = subtotal;
		this.porcentaje_iva = porcentaje_iva;
		this.monto_iva = monto_iva;
		this.total = total;
	}

	public Integer getId_solicitud_compra() {
		return id_solicitud_compra;
	}

	public void setId_solicitud_compra(Integer id_solicitud_compra) {
		this.id_solicitud_compra = id_solicitud_compra;
	}

	public String getF_confeccion() {
		return f_confeccion;
	}

	public void setF_confeccion(String f_confeccion) {
		this.f_confeccion = f_confeccion;
	}

	public Integer getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public Integer getId_orden_trabajo() {
		return id_orden_trabajo;
	}

	public void setId_orden_trabajo(Integer id_orden_trabajo) {
		this.id_orden_trabajo = id_orden_trabajo;
	}

	public boolean isEnvia_proveedor() {
		return envia_proveedor;
	}

	public void setEnvia_proveedor(boolean envia_proveedor) {
		this.envia_proveedor = envia_proveedor;
	}

	public String getDireccion_retiro() {
		return direccion_retiro;
	}

	public void setDireccion_retiro(String direccion_retiro) {
		this.direccion_retiro = direccion_retiro;
	}

	public String getF_entrega() {
		return f_entrega;
	}

	public void setF_entrega(String f_entrega) {
		this.f_entrega = f_entrega;
	}

	public String getHorario_entrega() {
		return horario_entrega;
	}

	public void setHorario_entrega(String horario_entrega) {
		this.horario_entrega = horario_entrega;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getPorcentaje_iva() {
		return porcentaje_iva;
	}

	public void setPorcentaje_iva(Double porcentaje_iva) {
		this.porcentaje_iva = porcentaje_iva;
	}

	public Double getMonto_iva() {
		return monto_iva;
	}

	public void setMonto_iva(Double monto_iva) {
		this.monto_iva = monto_iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	//Devuelve el maximo nro de SC+1,el cual sera el nro de toda nueva SC
	public static Integer getUltId_SC(){
		Integer maxId = null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar
		(
			"SELECT MAX(id_solicitud_compra) FROM solicitud_compra"
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
	
	
	
	public boolean Alta() {

		String f_conf = "'" + this.getF_confeccion() + "'";
		Integer id_prov = this.getId_proveedor();
		String vend = "'" + this.getVendedor() + "'";
		Integer id_ot = this.getId_orden_trabajo();
		boolean envia_prov = this.isEnvia_proveedor();
		String dir_retiro = "'" + this.getDireccion_retiro() + "'";
		String f_entr = "'" + this.getF_entrega() + "'";
		String horario_entr = "'" + this.getHorario_entrega() + "'";
		Double sub_total = this.getSubtotal();
		Double porc_iva = this.getPorcentaje_iva();
		Double montoiva = this.getMonto_iva();
		Double tot = this.getTotal();

		if (ConexionDB.getbaseDatos().ejecutar(
				"INSERT INTO solicitud_compra VALUES(default," + f_conf + ","
						+ id_prov + "," + vend + "," + id_ot + "," + envia_prov
						+ "," + dir_retiro + "," + f_entr + "," + horario_entr
						+ "," + sub_total + "," + porc_iva + "," + montoiva
						+ "," + tot + ");")) {
			return true;
		} else {
			return false;
		}

	}

	public static String[] getNomColum() 
	{
		String columnas[] = 
			{ 
				"Nro Solicitud Compra", 
				"Fecha de confeccion", 
				"Proveedor", 
				"Vendedor",
				"Nombre trabajo Ralacionado", 
				"Envia a Proveedor",
				"Direccion de Retiro",
				"Fecha de Entrega",
				"Horario Entrega",
				"subtotal",
				"% IVA",
				"monto IVA",
				"Total"
			};
			return columnas;
	}

	public static String enviaProveedor(Boolean dato) {
		if (dato)
    	{
    		return "SI";
    	}
    	else
    	{
    		return "NO";
    	}
	}
	
	
	
	
	
	
}
