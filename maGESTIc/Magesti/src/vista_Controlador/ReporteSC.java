package vista_Controlador;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReporteSC 
{
	private  String idSC,NroOT,vendedor, proveedor;
	private  Double total, subtotal, montoIVA, IVA;
	private  String direccionRetiro,retirar,envioProveedor,maniana, tarde;	
	private  String fechaEntrega,fechaConfec,descripcionIncidencia;
	private ArrayList<ReporteDetalles> detalles;
	
	public ReporteSC(String idSC, String nroOT, String vendedor,
			String proveedor, Double total, Double subtotal, Double montoIVA,
			Double iVA, String direccionRetiro,String fechaEntrega, String fechaConfec, String retirar,
			String envioProveedor, String maniana, String tarde,
			String descripcionIncidencia, ArrayList<ReporteDetalles> detalles) 
	{
		this.idSC = idSC;
		this.NroOT = nroOT;
		this.vendedor = vendedor;
		this.proveedor = proveedor;
		this.total = total;
		this.subtotal = subtotal;
		this.montoIVA = montoIVA;
		this.IVA = iVA;
		this.direccionRetiro = direccionRetiro;
		this.fechaEntrega = fechaEntrega;
		this.fechaConfec = fechaConfec;
		this.retirar = retirar;
		this.envioProveedor = envioProveedor;
		this.maniana = maniana;
		this.tarde = tarde;
		this.descripcionIncidencia = descripcionIncidencia;
		this.detalles = detalles;
	}

	public String getIdSC()
	{
		return idSC;
	}

	public void setIdSC(String idSC) 
	{
		this.idSC = idSC;
	}

	public String getNroOT() 
	{
		return NroOT;
	}

	public void setNroOT(String nroOT)
	{
		NroOT = nroOT;
	}

	public String getVendedor() 
	{
		return vendedor;
	}

	public void setVendedor(String vendedor)
	{
		this.vendedor = vendedor;
	}

	public String getProveedor() 
	{
		return proveedor;
	}

	public void setProveedor(String proveedor) 
	{
		this.proveedor = proveedor;
	}

	public Double getTotal()
	{
		return total;
	}

	public void setTotal(Double total) 
	{
		this.total = total;
	}

	public Double getSubtotal() 
	{
		return subtotal;
	}

	public void setSubtotal(Double subtotal) 
	{
		this.subtotal = subtotal;
	}

	public Double getMontoIVA() {
		return montoIVA;
	}

	public void setMontoIVA(Double montoIVA) {
		this.montoIVA = montoIVA;
	}

	public Double getIVA()
	{
		return IVA;
	}

	public void setIVA(Double iVA) 
	{
		IVA = iVA;
	}

	public String getDireccionRetiro() 
	{
		return direccionRetiro;
	}

	public void setDireccionRetiro(String direccionRetiro) 
	{
		this.direccionRetiro = direccionRetiro;
	}

	public String getFechaEntrega() 
	{
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) 
	{
		this.fechaEntrega = fechaEntrega;
	}

	public String getFechaConfec() 
	{
		return fechaConfec;
	}

	public void setFechaConfec(String fechaConfec)
	{
		this.fechaConfec = fechaConfec;
	}

	public String getRetirar()
	{
		return retirar;
	}

	public void setRetirar(String retirar) 
	{
		this.retirar = retirar;
	}

	public String getEnvioProveedor() 
	{
		return envioProveedor;
	}

	public void setEnvioProveedor(String envioProveedor)
	{
		this.envioProveedor = envioProveedor;
	}

	public String getManiana()
	{
		return maniana;
	}

	public void setManiana(String maniana) 
	{
		this.maniana = maniana;
	}

	public String getTarde()
	{
		return tarde;
	}

	public void setTarde(String tarde) 
	{
		this.tarde = tarde;
	}

	public String getDescripcionIncidencia() 
	{
		return descripcionIncidencia;
	}

	public void setDescripcionIncidencia(String descripcionIncidencia) 
	{
		this.descripcionIncidencia = descripcionIncidencia;
	}

	public ArrayList<ReporteDetalles> getDetalles()
	{
		return detalles;
	}

	public void setDetalles(ArrayList<ReporteDetalles> detalles) 
	{
		this.detalles = detalles;
	}
	
	public JRDataSource getDetallesDS()    
	{
		    return new JRBeanCollectionDataSource(detalles);   
	}
	
}
