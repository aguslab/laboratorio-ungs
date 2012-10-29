package vista_Controlador;

public class ReporteHojas 
{
	private String nroOT;
	private Integer cantHojasUsadas;
	private String nroSC;
	private String nombreOT;
	
	public ReporteHojas()
	{
		
	}
	
	public ReporteHojas(String nroOT, String nombreOT, Integer cantHojasUsadas,String nroSC) 
	{
		super();
		this.nroOT = nroOT;
		this.nombreOT = nombreOT;
		this.cantHojasUsadas = cantHojasUsadas;
		this.nroSC = nroSC;
	}
	
	public String getNroOT() 
	{
		return nroOT;
	}
	public void setNroOT(String nroOT) 
	{
		this.nroOT = nroOT;
	}
	public Integer getCantHojasUsadas() 
	{
		return cantHojasUsadas;
	}
	public void setCantHojasUsadas(Integer cantHojasUsadas) 
	{
		this.cantHojasUsadas = cantHojasUsadas;
	}
	public String getNroSC() 
	{
		return nroSC;
	}
	public void setNroSC(String nroSC) 
	{
		this.nroSC = nroSC;
	}
	
	public String getNombreOT() 
	{
		return nombreOT;
	}
	public void setNombreOT(String nombreOT) 
	{
		this.nombreOT = nombreOT;
	}
}
