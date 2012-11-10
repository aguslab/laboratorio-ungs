package vista_Controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
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
			System.out.println(childs[i].getTitle());
			if (childs[i].getTitle().equalsIgnoreCase (title)) 
			{
				childs[i].show ();
				return true;
			}
		}
		return false;

	}
	
//	static String fabricaReporte (int OT) 
//	{
//
//		String data;
//		String margen = "        ";
//		String data0 = "                        "+qCLIENTE+"                   \n";
//		String data1 = "           Reporte de Orden de Trabajo          \n\n";
//		String data2 = "   Orden No.: " + OT +"\n"; 
//		String data3 = "   Cliente: \n"; 
//		String data4 = "   Desc. Trabajo:  \n"; 
//		String data5 = "   Otro dato:   \n"; 
//		String data6 = "   Y otro dato:   \n"; 
//		String data7 = "   ... :   \n"; 
//		String data8 = "   Copyright © 2012 De Napoli, Godoy, Jiménez y Asociados   \n";	
//		 String sep0 = " -----------------------------------------------------------\n";
//		 String sep1 = " -----------------------------------------------------------\n\n";
//
//		data = margen + data0 + margen +sep0 + margen +data1 + margen +data2 + margen +data3 + margen +data4 + margen +data5 + margen +data6 + margen +data7 + margen +sep1 + margen +data8;
//		return data;
//
//	}
	
//	static void imprimir (String rec, Magesti magesti) 
//	{
//
//		StringReader sr = new StringReader (rec);
//		LineNumberReader lnr = new LineNumberReader (sr);
//		Font typeface = new Font ("Courier", Font.PLAIN, 12);
//		Properties p = new Properties ();
//		PrintJob pJob = magesti.getToolkit().getPrintJob (magesti, "Imprime reporte", p);
//
//
//		if (pJob != null) 
//		{
//			Graphics gr = pJob.getGraphics ();
//			if (gr != null) 
//			{
//				FontMetrics fm = gr.getFontMetrics (typeface);
//				int margin = 20;
//				int pageHeight = pJob.getPageDimension().height - margin;
//    				int fontHeight = fm.getHeight();
//	    			int fontDescent = fm.getDescent();
//    				int curHeight = margin;
//				String nextLine;
//				gr.setFont (typeface);
//
//				try 
//				{
//					do 
//					{
//						nextLine = lnr.readLine ();
//						if (nextLine != null) 
//						{         
//							if ((curHeight + fontHeight) > pageHeight) 
//							{	//New Page.
//								gr.dispose();
//								gr = pJob.getGraphics ();
//								curHeight = margin;
//							}							
//							curHeight += fontHeight;
//							if (gr != null) 
//							{
//								gr.setFont (typeface);
//								gr.drawString (nextLine, margin, curHeight - fontDescent);
//							}
//						}
//					}
//					while (nextLine != null);					
//				}
//				catch (EOFException eof) { }
//				catch (Throwable t) { }
//			}
//			gr.dispose();
//		}
//		if (pJob != null)
//			pJob.end ();
//	}
	
	
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
	
	public static boolean esNumero(String posibleNumero)
	{
		boolean esNumero=posibleNumero.length()>0;
		for (int j = 0; j < posibleNumero.length(); j++)
		{
			esNumero=esNumero && (posibleNumero.charAt(j)>='0' && posibleNumero.charAt(j)<='9');
		}

		return esNumero;
	}
	
	static boolean noEsVacio(String cadena)
	{
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
    	if(valor == null)
    	{
    		return "";
    	}
    	else
    	{
    		 DecimalFormat elFormato = new DecimalFormat("00000000");
    	     String salida = qSUCURSAL + elFormato.format(valor);
    	     return salida;
    	}
      
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


    //parametro==0 --> para la base de datos
	public static String getDateTimeActual(int n) 
	{
		String f_h_actual = null;
		ResultSet resultado;
		
		if(n == 0){
			resultado=ConexionDB.getbaseDatos().consultar("select CURRENT_TIMESTAMP()");	
		}else{
			resultado=ConexionDB.getbaseDatos().consultar("select DATE_FORMAT(NOW(),'%d/%m/%Y %H:%i:%s')");	
		}
		
		
		if(resultado != null)
		{
			try 
			{
				while(resultado.next())
				{
						f_h_actual=resultado.getString(1);
				}
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return f_h_actual;
	}

	
	//	parametro==0 --> para la base de datos
	public static String getDateActual(int n) 
	{
		String f_actual = null;
		ResultSet resultado;
		
		if(n == 0){
			resultado=ConexionDB.getbaseDatos().consultar("SELECT curdate();");	
		}else{
			resultado=ConexionDB.getbaseDatos().consultar("SELECT DATE_FORMAT(curdate(),'%d/%m/%Y')");
		}
		
		
		if(resultado != null)
		{
			try 
			{
				while(resultado.next())
				{
					f_actual=resultado.getString(1);
				}
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return f_actual;
	}
	
	public static String YMDaDMY(String ymd) 
	{
    	//Dada una fecha 'yyyy-mm-dd' la pasa a 'dd/mm/yyyy'
    	String[] dmy = ymd.split("\\-");
    	return dmy[2]+"/"+dmy[1]+"/"+dmy[0];
	}
	
	
	public static String DMYaYMD(String dmy) 
	{
    	//Dada una fecha 'dd/mm/yyyy' la pasa a 'yyyy-mm-dd'  
    	String[] ymd = dmy.split("\\-");
    	return ymd[2]+"/"+ymd[1]+"/"+ymd[0];
	}
	public static String valorAncho(String cadena) 
	{
		int ind=0;
		for(int i=0;i<cadena.length();i++){
			if(cadena.charAt(i)=='.'){
				ind=i;
				break;
			}
		}
		boolean f=true;
		while(cadena.length()!=6){
			if(cadena.substring(ind).length()!=3 && f){
				cadena=cadena+"0";
				f=false;
			}else{
				cadena="0"+cadena;
			}
		}
		return cadena;
	}
	
	public static String getDiaDeHoy(){
		Calendar fecha= Calendar.getInstance();
		Integer dd=fecha.get(Calendar.DATE);
		String dia=dd.toString();
		if(dd < 10){
			dia="0"+dd;
		}
		return dia;
	}
	
	public static String getMesActual(){
		Calendar fecha= Calendar.getInstance();
		Integer mm=fecha.get(Calendar.MONTH)+1;
		
		return mm.toString();
	}
	
	public static String getAnioActual(){
		Calendar fecha= Calendar.getInstance();
		Integer aaaa=fecha.get(Calendar.YEAR);
		
		return aaaa.toString();
	}
	
	public static boolean isFechaActualMenorFechaPrometida(String factual,

			String fprometida) {
		 String[] ReParseIntA;
		 ReParseIntA = factual.split("\\-");
		 Integer anioActual = Integer.parseInt(ReParseIntA[2]);
		 Integer mesActual = Integer.parseInt(ReParseIntA[1]);
		 Integer diaActual = Integer.parseInt(ReParseIntA[0]);
		 String[] ReParseIntP;
		 ReParseIntP = fprometida.split("\\-");
		 Integer anioPrometido = Integer.parseInt(ReParseIntP[2]);
		 Integer mesPrometido = Integer.parseInt(ReParseIntP[1]);
		 Integer diaPrometido = Integer.parseInt(ReParseIntP[0]);


		if (anioPrometido < anioActual) 
		{
			return false;
		} 
		else if (anioPrometido.equals(anioActual)) 
		{
			if (mesPrometido < mesActual) 
			{
				return false;
			} 
			else if (mesPrometido.equals(mesActual)) 
			{
				if (diaPrometido < diaActual) 
				{
					return false;
				} 
				else 
				{
					return true;
				}
			} 
			else 
			{
				return true;
			}
		}
		else 
		{
			return true;
		}
	}

	public static String getTextoEnCombo(JComboBox combo) 
	{

		String otSelec=(String) combo.getSelectedItem();
		
		return otSelec.substring(16);
	}
	

	public static String getNombreOTsinNumero(String cadena) {		
		return cadena.substring(16);
	}

	
	static Integer getIdOTsinNombre(String cadena)
	{
		String id="";
		for(int i=0;cadena.charAt(i)!= ' ';i++)
		{
				id=id+cadena.charAt(i);
		}
		return FacturaAEntero(id);
	}
	
	//Devuelve "E-00000". La cantidad de ceros depende de la cantidad de digitos del parametro recibido.
	//Si es mayor 4, no muestra ceros, solo agrega el prefijo "E-" al parametro recibido.
	public static String formatoElemento(Integer id_elemento){
		String formatoElem=id_elemento.toString();

		for(int i=0;formatoElem.length()<5;i++){
			formatoElem="0"+formatoElem;
		}
		
		return "E-"+formatoElem;
	}
	
	public static Integer volverA_IdElemento(String formatoElem){
		int pos=2;
		//empieza de la pos 2 xq posiciones 0 y 1 son E-
		for(int i=2;formatoElem.charAt(i)==0;i++){
			pos=i;
		}
				
		return Integer.parseInt(formatoElem.substring(pos));
	}
	
}
