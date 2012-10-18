package vista_Controlador;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.io.EOFException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;


public class Metodos implements Config
{
	static void borrarFilas(DefaultTableModel tabla)
	{
		tabla.setRowCount(0);
	}
	
	static String separar(String fecha, int numero)
	{
		StringTokenizer s = new StringTokenizer(fecha, "-");
		int cantidadChars = 0;
		int numeroIdentificador = numero;
		String parte = "";
		while(s.hasMoreTokens())
		{
			String elemento = s.nextToken();
			if (cantidadChars < 1 && numeroIdentificador == 0 )
			{
				parte=elemento;
				break;
			}
			else if((cantidadChars >= 1 && cantidadChars < 2) && numeroIdentificador == 1)
			{
				parte=elemento;
				break;
			}
			else if((cantidadChars >=2 && cantidadChars < 3) && numeroIdentificador == 2)
			{
				parte=elemento;
				break;
			}
				cantidadChars++;
		}
		return parte;
	}

	
	static String dameMes(String mes)
	{
		if(mes.equals("01") | mes.equals("1"))
		{
			return "Enero";
		}
		else if(mes.equals("02") | mes.equals("2"))
		{
			return "Febrero";
		}
		else if(mes.equals("03") | mes.equals("3"))
		{
			return "Marzo";
		}
		else if(mes.equals("04") | mes.equals("4"))
		{
			return "Abril";
		}
		else if(mes.equals("05") | mes.equals("5"))
		{
			return "Mayo";
		}
		else if(mes.equals("06") | mes.equals("6"))
		{
			return "Junio";
		}
		else if(mes.equals("07") | mes.equals("7"))
		{
			return "Julio";
		}
		else if(mes.equals("08") | mes.equals("8"))
		{
			return "Agosto";
		}
		else if(mes.equals("09") | mes.equals("9"))
		{
			return "Septiembre";
		}
		else if(mes.equals("10"))
		{
			return "Octubre";
		}
		else if(mes.equals("11"))
		{
			return "Noviembre";
		}
		else
		{
			return "Diciembre";
		}
	}
	

	public static String dameNumeroMes(String mes)
	{
		if(mes == "Enero")
		{
			return "01";
		}
		else if(mes == "Febrero")
		{
			return "02";
		}
		else if(mes == "Marzo")
		{
			return "03";
		}
		else if(mes == "Abril")
		{
			return "04";
		}else if(mes == "Mayo")
		{
			return "05";
		}
		else if(mes=="Junio")
		{
			return "06";
		}
		else if(mes=="Julio")
		{
			return "07";
		}
		else if(mes=="Agosto")
		{
			return "08";
		}
		else if(mes=="Septiembre")
		{
			return "09";
		}
		else if(mes=="Octubre")
		{
			return "10";
		}
		else if(mes=="Noviembre")
		{
			return "11";
		}
		else
		{
			return "12";
		}
	}
	
	static boolean openChildWindow (String title) 
	{

		JInternalFrame[] childs = Magesti.getEscritorio().getAllFrames ();
		for (int i = 0; i < childs.length; i++) 
		{
			if (childs[i].getTitle().equalsIgnoreCase (title)) 
			{
				childs[i].show ();
				return true;
			}
		}
		return false;

	}
	
	static String fabricaReporte (int OT) 
	{

		String data;
		String margen = "        ";
		String data0 = "                        "+qCLIENTE+"                   \n";
		String data1 = "           Reporte de Orden de Trabajo          \n\n";
		String data2 = "   Orden No.: " + OT +"\n"; 
		String data3 = "   Cliente: \n"; 
		String data4 = "   Desc. Trabajo:  \n"; 
		String data5 = "   Otro dato:   \n"; 
		String data6 = "   Y otro dato:   \n"; 
		String data7 = "   ... :   \n"; 
		String data8 = "   Copyright © 2012 De Napoli, Godoy, Jiménez y Asociados   \n";	
		 String sep0 = " -----------------------------------------------------------\n";
		 String sep1 = " -----------------------------------------------------------\n\n";

		data = margen + data0 + margen +sep0 + margen +data1 + margen +data2 + margen +data3 + margen +data4 + margen +data5 + margen +data6 + margen +data7 + margen +sep1 + margen +data8;
		return data;

	}
	
	static void imprimir (String rec, Magesti magesti) 
	{

		StringReader sr = new StringReader (rec);
		LineNumberReader lnr = new LineNumberReader (sr);
		Font typeface = new Font ("Courier", Font.PLAIN, 12);
		Properties p = new Properties ();
		PrintJob pJob = magesti.getToolkit().getPrintJob (magesti, "Imprime reporte", p);


		if (pJob != null) {
			Graphics gr = pJob.getGraphics ();
			if (gr != null) {
				FontMetrics fm = gr.getFontMetrics (typeface);
				int margin = 20;
				int pageHeight = pJob.getPageDimension().height - margin;
    				int fontHeight = fm.getHeight();
	    			int fontDescent = fm.getDescent();
    				int curHeight = margin;
				String nextLine;
				gr.setFont (typeface);

				try {
					do {
						nextLine = lnr.readLine ();
						if (nextLine != null) {         
							if ((curHeight + fontHeight) > pageHeight) {	//New Page.
								gr.dispose();
								gr = pJob.getGraphics ();
								curHeight = margin;
							}							
							curHeight += fontHeight;
							if (gr != null) {
								gr.setFont (typeface);
								gr.drawString (nextLine, margin, curHeight - fontDescent);
							}
						}
					}
					while (nextLine != null);					
				}
				catch (EOFException eof) { }
				catch (Throwable t) { }
			}
			gr.dispose();
		}
		if (pJob != null)
			pJob.end ();
	}
	
	
	static String pasarAPesos(String df)
	{
		return "$ "+df;
	}
	
	static Double pasarADouble(String cant)
	{
		String monto=cant.substring(2);//saco el sigo $ 
		return Double.parseDouble(monto.replace(',', '.'));
	}
	
	static Integer getIdEnCombo(JComboBox combo)
	{
		String id="";
		String otSelec=(String) combo.getSelectedItem();
		for(int i=0;otSelec.charAt(i)!= ' ';i++)
		{
				id=id+otSelec.charAt(i);
		}
		return FacturaAEntero(id);
	}
	
	public static boolean esNumero(String posibleNumero){
		boolean esNumero=posibleNumero.length()>0;
		for (int j = 0; j < posibleNumero.length(); j++) {
			esNumero=esNumero && (posibleNumero.charAt(j)>='0' && posibleNumero.charAt(j)<='9');
		}

		return esNumero;
	}
	
	static boolean noEsVacio(String cadena){
		boolean NoesVacio=cadena.length()>0;
		
		for(int i=0;i<cadena.length();i++){
			if(cadena.charAt(i)==' '){
				return false;
			}
		}
		return NoesVacio;
	}
	
	
    static public String EnteroAFactura(Integer valor) 
    {
       DecimalFormat elFormato = new DecimalFormat("00000000");
       String salida = qSUCURSAL + elFormato.format(valor);
       return salida;
    }
	
    static public Integer FacturaAEntero(String valor ) 
    {
       valor = valor.replaceAll("0001-","");
       return Integer.parseInt(valor);
    }
    
    static public String esApaisadaS (boolean dato)
    {
    	if (dato)
    	{
    		return "SI";
    	}
    	else
    	{
    		return "NO";
    	}
    	
    }
    
    public static boolean esApaisadaB (String dato)
    {
    	if (dato.equalsIgnoreCase("SI"))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }



	public static Date getDateTimeActual() {
		Date f_h_actual = null;
		ResultSet resultado=ConexionDB.getbaseDatos().consultar("select CURRENT_TIMESTAMP()");
		if(resultado != null){
			try {
				while(resultado.next()){
					
						f_h_actual=resultado.getDate(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f_h_actual;
	}
	
	
	
	
	
}
