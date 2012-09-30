package Modelo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Calidad 
{
	private Integer id_calidad;
	private String nombre;
	
	static MySQLBD baseDatos = new MySQLBD().conectar();
	
	public Calidad(String nombre) 
	{
		super();
		this.nombre = nombre;
	}
	
	public Calidad(Integer calidad, String nombre) 
	{
		super();
		this.id_calidad = calidad;
		this.nombre = nombre;
	}
	
	public Integer getId_calidad()
	{
		return id_calidad;
	}
	
	public void setId_calidad(Integer id_calidad)
	{
		this.id_calidad = id_calidad;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public boolean Alta()
	{
		//Integer id_cal = calidad.getId_calidad();
		String nom = getNombre();
		
		if (baseDatos.ejecutar("INSERT INTO calidad VALUES(DEFAULT,"+ "'"+nom+"'"+ ");"))
		{
            return true;
        } 
		else 
        {
            return false;
        }
	}
	/*public boolean editar() 
	{
		if(//mostrar form de alta)
		{
			return true;
		}
		else
		{
			return false;
		}
	}*/
	
	public boolean Baja(Calidad c)
	{
		Integer id_cal = c.getId_calidad();
		String nom = getNombre();
		
		if (baseDatos.ejecutar("DELETE FROM calidad where id_calidad = id_cal,"+ "'"+ ");"))
		{
            return true;
        } 
		else 
        {
            return false;
        }
	}
	
	public ArrayList<Calidad> Buscar()
	{
		
		ResultSet resultado= this.getBaseDatos().consultar("SELECT * FROM calidad");
		ArrayList<Calidad> list_calidad= new ArrayList<Calidad>();
		if (resultado != null) 
        {
			try 
            {
         	    while (resultado.next()) 
                {
                    Calidad c = new Calidad(new Integer(resultado.getInt("id_calidad")),resultado.getString("nombre"));
                    list_calidad.add(c);
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
		return list_calidad;
	}

	private MySQLBD getBaseDatos() 
	{
		return baseDatos;
	}

}
