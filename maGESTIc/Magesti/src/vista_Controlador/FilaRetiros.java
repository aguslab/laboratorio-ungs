package vista_Controlador;

public class FilaRetiros 
{
	private String nroSC, f_hora, empleado;
	private Integer cantRetirada;
	
	public FilaRetiros()
	{
		
	}
	
	public FilaRetiros(String nroSC,String f_hora, Integer cantRetirada,String empleado) 
	{
		this.nroSC = Metodos.EnteroAFactura(Integer.parseInt(nroSC));
		this.f_hora = f_hora;
		this.empleado = empleado;
		this.cantRetirada = cantRetirada;
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
}
