package vista_Controlador;

public class FilaElementos 
{
	private String elemento;
	private Integer cantidad;
	private Integer hojasPrevistas;
	private Integer hojasUtilizada;
	
	public FilaElementos(String elemento, Integer cantidad,
			Integer hojasEstimada, Integer hojasUtilizada) 
	{
		this.elemento = elemento;
		this.cantidad = cantidad;
		this.hojasPrevistas = hojasEstimada;
		this.hojasUtilizada = hojasUtilizada;
	}

	public String getElemento() 
	{
		return elemento;
	}

	public void setElemento(String elemento) 
	{
		this.elemento = elemento;
	}

	public Integer getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(Integer cantidad) 
	{
		this.cantidad = cantidad;
	}

	public Integer getHojasEstimada() 
	{
		return hojasPrevistas;
	}

	public void setHojasEstimada(Integer hojasEstimada) 
	{
		this.hojasPrevistas = hojasEstimada;
	}

	public Integer getHojasUtilizada() 
	{
		return hojasUtilizada;
	}

	public void setHojasUtilizada(Integer hojasUtilizada) 
	{
		this.hojasUtilizada = hojasUtilizada;
	}
	
	
	
	
}
