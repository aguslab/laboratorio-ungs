package vista_Controlador;

public class FilaRetiros 
{
	private String nroSC, f_hora, empleado;
	private String  fConfeccion, fEntrega, remanente;
	private Integer cantRetirada;
	
	public FilaRetiros()
	{
		
	}
	

	public FilaRetiros(String nroSC,String fConfeccion, String fEntrega, String f_hora,Integer cantRetirada,
			String remanente,String empleado) 
	{
		this.nroSC = Metodos.EnteroAFactura(Integer.parseInt(nroSC));
		this.f_hora = f_hora;
		this.empleado = empleado;
		this.fConfeccion = fConfeccion;
		this.fEntrega = fEntrega;
		this.cantRetirada = cantRetirada;
		this.remanente = remanente;
	}



	public String getRemanente()
	{
		return remanente;
	}


	public void setRemanente(String remanente) 
	{
		this.remanente = remanente;
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

	public Integer getCantRetirada()
	{
		return cantRetirada;
	}

	public void setCantRetirada(Integer cantRetirada) 
	{
		this.cantRetirada = cantRetirada;
	}
}
