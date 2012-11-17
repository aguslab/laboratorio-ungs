package vista_Controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
public class CargaConfig 
{
	static String qVERSION = "";
	static String qTITULO = "MAGESTI® " + qVERSION ;
	static String qCLIENTE = "";
	static String qSUCURSAL = "";
	static Double IVA= 21.0;
	static Integer Resma= 500;
	static Integer limiteNumerico= 999;
	static String qSERVIDOR = "";
	static String qBASE= "";
	static String qUSUARIO = "";
	static String qPASSWORD= "";
	    CargaConfig()
	    {
	        Properties prop = new Properties();
	        InputStream is = null;
	 
	        try 
	        {
	            is = new FileInputStream("Magesti.properties");
	            prop.load(is);
	        } 
	        catch(IOException e) 
	        {
	            System.out.println(e.toString());
	        }
	 

	        qVERSION = prop.getProperty("qVERSION");
	        qCLIENTE = prop.getProperty("qCLIENTE");
	        qSUCURSAL = prop.getProperty("qSUCURSAL");
	        IVA = Double.valueOf(prop.getProperty("IVA"));
	        Resma = Integer.valueOf(prop.getProperty("Resma"));
	        limiteNumerico = Integer.valueOf(prop.getProperty("limiteNumerico"));
	        qSERVIDOR = prop.getProperty("qSERVIDOR");
	        qBASE = prop.getProperty("qBASE");
	        qUSUARIO = prop.getProperty("qUSUARIO");
	        qPASSWORD = prop.getProperty("qPASSWORD");

	    }
	}



