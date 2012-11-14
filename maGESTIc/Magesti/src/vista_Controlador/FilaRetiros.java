package vista_Controlador;

public class FilaRetiros 
{
	private String nroSC, f_hora, empleado, calidad, formato, variante, marca;
	private Integer cantRetirada, gramaje;
	
	public FilaRetiros()
	{
		
	}
	
	public FilaRetiros(String nroSC,Integer gramaje, String calidad, String formato, String variante,
			String marca,String f_hora,Integer cantRetirada, String empleado) 
	{
		this.nroSC = nroSC;
		this.f_hora = f_hora;
		this.empleado = empleado;
		this.calidad = calidad;
		this.formato = formato;
		this.variante = variante;
		this.marca = marca;
		this.cantRetirada = cantRetirada;
		this.gramaje = gramaje;
	}



	public String getNroSC() 
	{
		return nroSC;
	}

	public void setNroSC(String nroSC)
	{
		this.nroSC = nroSC;
	}

	public String getF_hora() 
	{
		return f_hora;
	}

	public void setF_hora(String f_hora)
	{
		this.f_hora = f_hora;
	}

	public String getEmpleado() 
	{
		return empleado;
	}

	public void setEmpleado(String empleado) 
	{
		this.empleado = empleado;
	}

	public Integer getCantRetirada()
	{
		return cantRetirada;
	}

	public void setCantRetirada(Integer cantRetirada) 
	{
		this.cantRetirada = cantRetirada;
	}

	public String getCalidad() 
	{
		return calidad;
	}

	public void setCalidad(String calidad) 
	{
		this.calidad = calidad;
	}

	public String getFormato()
	{
		return formato;
	}

	public void setFormato(String formato) 
	{
		this.formato = formato;
	}

	public String getVariante() 
	{
		return variante;
	}

	public void setVariante(String variante) 
	{
		this.variante = variante;
	}

	public String getMarca() 
	{
		return marca;
	}

	public void setMarca(String marca) 
	{
		this.marca = marca;
	}

	public Integer getGramaje() 
	{
		return gramaje;
	}

	public void setGramaje(Integer gramaje) 
	{
		this.gramaje = gramaje;
	}
	
	
}
