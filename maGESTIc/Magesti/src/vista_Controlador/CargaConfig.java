package vista_Controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import Modelo.ConexionDB;
 
public class CargaConfig 
{
	private String qVERSION = "v12.11.22";
	private String qTITULO = "MAGESTI® " + qVERSION ;
	private String qCLIENTE = "";
	private String qSUCURSAL = "";
	private Double IVA= 21.0;
	private Integer Resma;
	private Integer limiteNumerico= 999;
	private String qSERVIDOR = "";
	private String qBASE= "";
	private String qUSUARIO = "";
	private String qPASSWORD= "";
	    CargaConfig()
	    {
	        Properties prop = new Properties();
	        InputStream is = null;
	 
	        try 
	        {
	            is = new FileInputStream("Magesti.properties");
	            prop.load(is);
	            
	            //qVERSION = prop.getProperty("qVERSION");
		        qCLIENTE = prop.getProperty("qCLIENTE");
		        qSUCURSAL = prop.getProperty("qSUCURSAL");
		        IVA = Double.valueOf(prop.getProperty("IVA"));
		        Resma = Integer.valueOf(prop.getProperty("Resma"));
		        //limiteNumerico = Integer.valueOf(prop.getProperty("limiteNumerico"));
		        qSERVIDOR = prop.getProperty("qSERVIDOR");
		        qBASE = prop.getProperty("qBASE");
		        qUSUARIO = prop.getProperty("qUSUARIO");
		        qPASSWORD = prop.getProperty("qPASSWORD");
	        } 
	        catch(IOException e) 
	        {
	            System.out.println(e.toString());
	            JOptionPane.showMessageDialog 
				(
					null, 
					"Ha ocurrido un error al leer las propiedades del archivo, verifque o contacte a representantes de la empresa",
					qTITULO + " - ERROR AL LEER ARCHIVO 'MAGESTI.PROPERTIES'", 
					JOptionPane.WARNING_MESSAGE
				);
				
	            ConexionDB.getbaseDatos().cerrar();
				System.exit (0);
	        }
	 

	        

	    }
		public String getqVERSION() {
			return qVERSION;
		}
		public String getqTITULO() {
			return qTITULO;
		}
		public String getqCLIENTE() {
			return qCLIENTE;
		}
		public String getqSUCURSAL() {
			return qSUCURSAL;
		}
		public Double getIVA() {
			return IVA;
		}
		public Integer getResma() {
			return Resma;
		}
		public Integer getLimiteNumerico() {
			return limiteNumerico;
		}
		public String getqSERVIDOR() {
			return qSERVIDOR;
		}
		public String getqBASE() {
			return qBASE;
		}
		public String getqUSUARIO() {
			return qUSUARIO;
		}
		public String getqPASSWORD() {
			return qPASSWORD;
		}
		
	    
	    
	    
	    
	    
	}



