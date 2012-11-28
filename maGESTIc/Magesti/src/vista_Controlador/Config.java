package vista_Controlador;

public interface Config 
{
	CargaConfig cc = new CargaConfig();
	static final String qVERSION = cc.getqVERSION();
	static final String qTITULO = "MAGESTI® " + qVERSION ;
	static final String qCLIENTE = cc.getqCLIENTE();
	static final String qSUCURSAL = cc.getqSUCURSAL(); //Si cambia la sucursal, cambia este número
	static final Double IVA= cc.getIVA();
	static final Integer Resma= cc.getResma();;
	static final String qSERVIDOR = cc.getqSERVIDOR();
	static final String qBASE = cc.getqBASE();
	static final String qUSUARIO = cc.getqUSUARIO();
	static final String qPASSWORD = cc.getqPASSWORD();
	static final Integer limiteNumerico=999999;
	
	
	
	
	
	
	
	
	
	
}
