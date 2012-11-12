package vista_Controlador;

public class FilaSC
{
	private String nroSC;
	private String f_h_recibido, fConfeccion, fEntrega;
	private Integer cantCompradas,hojasRestantes;
	
	public FilaSC(String nroSC, String f_h_recibido, String fConfeccion,
			String fEntrega, Integer cantCompradas, Integer hojasRestantes) 
	{
		this.nroSC = nroSC;
		System.out.println("sc: "+nroSC);
		this.f_h_recibido = f_h_recibido;
		System.out.println(f_h_recibido);
		this.fConfeccion = fConfeccion;
		System.out.println(fConfeccion);
		this.fEntrega = fEntrega;
		System.out.println(fEntrega);
		this.cantCompradas = cantCompradas;
		System.out.println(cantCompradas);
		this.hojasRestantes = hojasRestantes;
		System.out.println(hojasRestantes);
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
	
	
}
