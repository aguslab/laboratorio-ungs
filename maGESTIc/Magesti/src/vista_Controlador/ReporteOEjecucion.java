package vista_Controlador;

public class ReporteOEjecucion 
{
	private String proceso, tercerizada,proveedor,observaciones, cumplida;
	
	public ReporteOEjecucion(String proceso, String tercerizada,
			String proveedor, String observaciones, String cumplida) 
	{
		super();
		this.proceso = proceso;
		this.tercerizada = tercerizada;
		this.proveedor = proveedor;
		this.observaciones = observaciones;
		this.cumplida = cumplida;
	}

	public String getProceso()
	{
		return proceso;
	}

	public void setProceso(String proceso) 
	{
		this.proceso = proceso;
	}

	public String getTercerizada()
	{
		return tercerizada;
	}

	public void setTercerizada(String tercerizada) 
	{
		this.tercerizada = tercerizada;
	}

	public String getProveedor()
	{
		return proveedor;
	}

	public void setProveedor(String proveedor)
	{
		this.proveedor = proveedor;
	}

	public String getObservaciones()
	{
		return observaciones;
	}

	public void setObservaciones(String observaciones) 
	{
		this.observaciones = observaciones;
	}

	public String getCumplida()
	{
		return cumplida;
	}

	public void setCumplida(String cumplida) 
	{
		this.cumplida = cumplida;
	}
	
	
}
