package Modelo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Calidad 
{
	private Integer id_calidad;
	private String nombre;
	
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
	
	public static  String[] getCalidades()
	{
		ArrayList<String> cal=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT nombre FROM calidad");
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					cal.add(resultado.getString("nombre"));
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		String [] cals= new String[cal.size()];
		
		for(int i=0;i<cal.size();i++)
		{
			cals[i]=cal.get(i);
		}
		
		return cals;
	}
	
	public boolean Alta()
	{
		String nom = this.getNombre();

		if (ConexionDB.getbaseDatos().ejecutar("INSERT INTO formato_papel VALUES(default," + nom + ");")) 
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
		if (ConexionDB.getbaseDatos().ejecutar("DELETE FROM calidad where id_calidad = id_cal,"+ "'"+ ");")) 
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
		
		ResultSet resultado= ConexionDB.getbaseDatos().consultar("SELECT * FROM calidad");
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

	public static Integer getId_Calidad(String nombreElegido)
	{
		Integer id_cal=null;
		nombreElegido="'"+nombreElegido+"'";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_calidad FROM calidad where nombre="+nombreElegido);
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					id_cal = resultado.getInt(1);
					break;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
		return id_cal;
	}
	
	public static ArrayList<String> getNombre(Integer id_cal)
	{
		ArrayList<String> valores = new ArrayList<String>();
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT nombre FROM calidad WHERE id_calidad="+ id_cal);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valores.add(resultado.getString("nombre"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valores;
	}
}
