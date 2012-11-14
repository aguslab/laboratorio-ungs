package vista_Controlador;

import java.util.ArrayList;

public class ReporteFinal 
{
	private String nroOT, nombreOT;
	private Integer cantCompradas;
	private ArrayList<FilaSC> scs;
	private ArrayList<FilaRetiros> retiros;
	private ArrayList<FilaElementos> elementos;
	private ArrayList<FilaOEjecucion> ordenEjecucion;

	public ReporteFinal()
	{
		
	}
	
	public ReporteFinal(String nroOT, String nombreOT, Integer cantCompradas,
			ArrayList<FilaSC> scs, ArrayList<FilaRetiros> retiros,
			ArrayList<FilaElementos> elementos,
			ArrayList<FilaOEjecucion> ordenEjecucion)
	{
		this.nroOT = nroOT;
		this.nombreOT = nombreOT;
		this.cantCompradas = cantCompradas;
		this.scs = scs;
		this.retiros = retiros;
		this.elementos = elementos;
		this.ordenEjecucion = ordenEjecucion;
	}

	public String getNroOT() 
	{
		return nroOT;
	}
	
	public void setNroOT(String nroOT) 
	{
		this.nroOT = nroOT;
	}
	
	public String getNombreOT() 
	{
		return nombreOT;
	}
	
	public void setNombreOT(String nombreOT) 
	{
		this.nombreOT = nombreOT;
	}

	public ArrayList<FilaRetiros> getRetiros()
	{
		return retiros;
	}


	public void setRetiros(ArrayList<FilaRetiros> retiros) 
	{
		this.retiros = retiros;
	}
	
	public ArrayList<FilaElementos> getElementos() 
	{
		return elementos;
	}


	public void setElementos(ArrayList<FilaElementos> elementos)
	{
		this.elementos = elementos;
	}


	public Integer getCantCompradas() 
	{
		return cantCompradas;
	}


	public void setCantCompradas(Integer cantCompradas)
	{
		this.cantCompradas = cantCompradas;
	}


	public ArrayList<FilaSC> getScs() 
	{
		return scs;
	}


	public void setScs(ArrayList<FilaSC> scs)
	{
		this.scs = scs;
	}


	public ArrayList<FilaOEjecucion> getOrdenEjecucion() 
	{
		return ordenEjecucion;
	}


	public void setOrdenEjecucion(ArrayList<FilaOEjecucion> ordenEjecucion)
	{
		this.ordenEjecucion = ordenEjecucion;
	}
	
	

}
