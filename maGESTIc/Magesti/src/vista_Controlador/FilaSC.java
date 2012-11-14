package vista_Controlador;

public class FilaSC
{
	private String nroSC,calidad,formato,variante,marca;
	private String f_h_recibido, fConfeccion, fEntrega;
	private Integer gramaje,cantCompradas,hojasRestantes;
	
	

	public FilaSC(String nroSC,
			String fConfeccion, String fEntrega, String f_h_recibido, Integer gramaje,String calidad, String formato,
			String variante,  String marca, Integer cantCompradas,
			Integer hojasRestantes) 
	{
		this.nroSC = nroSC;
		this.calidad = calidad;
		this.formato = formato;
		this.variante = variante;
		this.gramaje = gramaje;
		this.marca = marca;
		this.f_h_recibido = f_h_recibido;
		this.fConfeccion = fConfeccion;
		this.fEntrega = fEntrega;
		this.cantCompradas = cantCompradas;
		this.hojasRestantes = hojasRestantes;
	}

	public String getNroSC() 
	{
		return nroSC;
	}

	public void setNroSC(String nroSC) 
	{
		this.nroSC = nroSC;
	}

	public String getF_h_recibido() 
	{
		return f_h_recibido;
	}

	public void setF_h_recibido(String f_h_recibido) 
	{
		this.f_h_recibido = f_h_recibido;
	}

	public String getfConfeccion() 
	{
		return fConfeccion;
	}

	public void setfConfeccion(String fConfeccion) 
	{
		this.fConfeccion = fConfeccion;
	}

	public String getfEntrega()
	{
		return fEntrega;
	}

	public void setfEntrega(String fEntrega) 
	{
		this.fEntrega = fEntrega;
	}

	public Integer getCantCompradas() 
	{
		return cantCompradas;
	}

	public void setCantCompradas(Integer cantCompradas) 
	{
		this.cantCompradas = cantCompradas;
	}

	public Integer getHojasRestantes()
	{
		return hojasRestantes;
	}

	public void setHojasRestantes(Integer hojasRestantes) 
	{
		this.hojasRestantes = hojasRestantes;
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
	
	
}
