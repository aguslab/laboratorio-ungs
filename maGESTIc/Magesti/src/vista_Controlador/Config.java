package vista_Controlador;

public interface Config 
{
	CargaConfig cc = new CargaConfig();
	static final String qVERSION = cc.qVERSION;
	static final String qTITULO = "MAGESTI® " + qVERSION ;
	static final String qCLIENTE = cc.qCLIENTE;
	static final String qSUCURSAL = cc.qSUCURSAL; //Si cambia la sucursal, cambia este número
	static final Double IVA= cc.IVA;
	static final Integer Resma= cc.Resma;
	static final Integer limiteNumerico=cc.limiteNumerico;
	static final String qSERVIDOR = cc.qSERVIDOR;
	static final String qBASE = cc.qBASE;
	static final String qUSUARIO = cc.qUSUARIO;
	static final String qPASSWORD = cc.qPASSWORD;
}
