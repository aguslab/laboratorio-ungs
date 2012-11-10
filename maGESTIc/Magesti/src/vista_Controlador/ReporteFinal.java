package vista_Controlador;

import java.util.ArrayList;

public class ReporteFinal 
{
	private String nroOT, nombreOT;
	private ArrayList<FilaRetiros> retiros;
	private ArrayList<FilaElementos> elementos;
	

	public ReporteFinal()
	{
		
	}
	
	
	public ReporteFinal(String nroOT, String nombreOT, ArrayList<FilaRetiros> retiros,  ArrayList<FilaElementos> elementos) 
	{
		this.nroOT = nroOT;
		this.nombreOT = nombreOT;
		this.retiros = retiros;
		this.elementos = elementos;
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

}
