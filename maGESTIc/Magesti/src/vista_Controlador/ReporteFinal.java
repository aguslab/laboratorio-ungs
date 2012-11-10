package vista_Controlador;

import java.util.ArrayList;

public class ReporteFinal 
{
	private String nroOT, nombreOT;
	private ArrayList<FilaRetiros> retiros;

	public ReporteFinal()
	{
		
	}
	
	
	public ReporteFinal(String nroOT, String nombreOT, ArrayList<FilaRetiros> retiros) 
	{
		this.nroOT = nroOT;
		this.nombreOT = nombreOT;
		this.retiros = retiros;
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
	
	
}
