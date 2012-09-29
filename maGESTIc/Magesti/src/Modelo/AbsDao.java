package Modelo;

public abstract class AbsDao 
{
	Integer id_objeto;
	
	static MySQLBD baseDatos = new MySQLBD().conectar(); 

	public AbsDao(Integer id_objeto) 
	{
		this.id_objeto=id_objeto;
	}
	
	abstract public boolean alta(AbsDao obj);
	abstract public boolean baja(AbsDao obj);
	abstract public boolean edit(AbsDao obj);
	abstract public AbsDao buscar(AbsDao obj);
	abstract public AbsDao buscar();
}
