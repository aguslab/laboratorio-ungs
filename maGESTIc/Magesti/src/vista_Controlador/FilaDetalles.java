package vista_Controlador;

public class FilaDetalles 
{
	private Integer cantHojas, gramaje;
	private String marca, calidad, variante, formato, UnidadMedida;
	private Double precioUnitario, Importe;
	
	public FilaDetalles(Integer cantHojas,Integer gramaje, String calidad, 
			String formato, String variante, String marca, Double precioUnitario,String unidadMedida, Double importe)
	{
		this.cantHojas = cantHojas;
		this.gramaje = gramaje;
		this.marca = marca;
		this.calidad = calidad;
		this.variante = variante;
		this.formato = formato;
		UnidadMedida = unidadMedida;
		this.precioUnitario = precioUnitario;
		Importe = importe;
	}

	public Integer getCantHojas() 
	{
		return cantHojas;
	}

	public void setCantHojas(Integer cantHojas) 
	{
		this.cantHojas = cantHojas;
	}

	public Integer getGramaje() 
	{
		return gramaje;
	}

	public void setGramaje(Integer gramaje)
	{
		this.gramaje = gramaje;
	}

	public String getMarca() 
	{
		return marca;
	}

	public void setMarca(String marca) 
	{
		this.marca = marca;
	}

	public String getCalidad()
	{
		return calidad;
	}

	public void setCalidad(String calidad) 
	{
		this.calidad = calidad;
	}

	public String getVariante()
	{
		return variante;
	}

	public void setVariante(String variante) 
	{
		this.variante = variante;
	}

	public String getFormato() 
	{
		return formato;
	}

	public void setFormato(String formato) 
	{
		this.formato = formato;
	}

	public String getUnidadMedida() 
	{
		return UnidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) 
	{
		UnidadMedida = unidadMedida;
	}

	public Double getPrecioUnitario() 
	{
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) 
	{
		this.precioUnitario = precioUnitario;
	}

	public Double getImporte() 
	{
		return Importe;
	}

	public void setImporte(Double importe) 
	{
		Importe = importe;
	}
	
	
}
