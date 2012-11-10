package vista_Controlador;

public class FilaRetiros 
{
	private String nroSC, f_hora, empleado;
	private String  fConfeccion, fEntrega, f_h_recibido, hojasRestantes;
	private Integer cantRetirada;
	
	public FilaRetiros()
	{
		
	}
	

	public FilaRetiros(String nroSC,String f_h_recibido, String fConfeccion, String fEntrega, String f_hora,Integer cantRetirada,
			String remanente,String empleado) 
	{
		this.nroSC = Metodos.EnteroAFactura(Integer.parseInt(nroSC));
		this.f_h_recibido = f_h_recibido;
		this.f_hora = f_hora;
		this.empleado = empleado;
		this.fConfeccion = fConfeccion;
		this.fEntrega = fEntrega;
		this.cantRetirada = cantRetirada;
		this.hojasRestantes = remanente;
	}



	public String getHojasRestantes()
	{
		return hojasRestantes;
	}


	public void setHojasRestantes(String remanente) 
	{
		this.hojasRestantes = remanente;
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
	
	public String getF_h_recibido() 
	{
		return f_h_recibido;
	}


	public void setF_h_recibido(String f_h_recibido)
	{
		this.f_h_recibido = f_h_recibido;
	}

}
