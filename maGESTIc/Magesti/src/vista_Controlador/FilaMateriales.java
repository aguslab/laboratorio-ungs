package vista_Controlador;

public class FilaMateriales 
{
	private String elemento, formato, variante, calidad;
	private Integer cantidad, gramaje,pliegosDemasia,posesXpliego,pliegosXhoja,pliegosNetos,hojas;
	
	public FilaMateriales(String elemento, Integer cantidad, Integer gramaje,String calidad, String formato,
			String variante,Integer pliegosDemasia, Integer posesXpliego,
			Integer pliegosXhoja,Integer pliegosNetos, Integer hojas)
	{
		super();
		this.elemento = elemento;
		this.formato = formato;
		this.variante = variante;
		this.calidad = calidad;
		this.cantidad = cantidad;
		this.gramaje = gramaje;
		this.pliegosDemasia = pliegosDemasia;
		this.posesXpliego = posesXpliego;
		this.pliegosXhoja = pliegosXhoja;
		this.pliegosNetos = pliegosNetos;
		this.hojas = hojas;
	}

	public String getElemento() 
	{
		return elemento;
	}

	public void setElemento(String elemento) 
	{
		this.elemento = elemento;
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

	public String getCalidad() 
	{
		return calidad;
	}

	public void setCalidad(String calidad)
	{
		this.calidad = calidad;
	}

	public Integer getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(Integer cantidad)
	{
		this.cantidad = cantidad;
	}

	public Integer getGramaje() 
	{
		return gramaje;
	}

	public void setGramaje(Integer gramaje)
	{
		this.gramaje = gramaje;
	}

	public Integer getPliegosDemasia()
	{
		return pliegosDemasia;
	}

	public void setPliegosDemasia(Integer pliegosDemasia)
	{
		this.pliegosDemasia = pliegosDemasia;
	}

	public Integer getPosesXpliego() 
	{
		return posesXpliego;
	}

	public void setPosesXpliego(Integer posesXpliego) 
	{
		this.posesXpliego = posesXpliego;
	}

	public Integer getPliegosXhoja() 
	{
		return pliegosXhoja;
	}

	public void setPliegosXhoja(Integer pliegosXhoja) 
	{
		this.pliegosXhoja = pliegosXhoja;
	}

	public Integer getPliegosNetos()
	{
		return pliegosNetos;
	}

	public void setPliegosNetos(Integer pliegosNetos) 
	{
		this.pliegosNetos = pliegosNetos;
	}

	public Integer getHojas()
	{
		return hojas;
	}

	public void setHojas(Integer hojas) 
	{
		this.hojas = hojas;
	}
	
	public void mostrar()
	{
		System.out.println(this.elemento);
		System.out.println(this.formato);
		System.out.println(this.variante);
		System.out.println(this.calidad);
		System.out.println(this.cantidad);
		System.out.println(this.gramaje);
		System.out.println(this.pliegosDemasia);
		System.out.println(this.posesXpliego);
		System.out.println(this.pliegosXhoja);
		System.out.println(this.pliegosNetos);
		System.out.println(this.hojas);
	}
}

