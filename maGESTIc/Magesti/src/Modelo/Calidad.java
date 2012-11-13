package Modelo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Calidad 
{
	private Integer id_calidad;
	private String nombre;
	private Boolean activo;
	
	public Calidad(String nombre, Boolean activo) 
	{
		super();
		this.nombre = nombre;
		this.activo = activo;
	}
	
	public Calidad(Integer calidad, String nombre, Boolean activo) 
	{
		super();
		this.id_calidad = calidad;
		this.nombre = nombre;
		this.activo = activo;
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
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT nombre FROM calidad WHERE activo = true");
		
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
		String nom = "'" + this.getNombre() + "'";
		Boolean activoCal = this.getActivo();

		if (ConexionDB.getbaseDatos().ejecutar("INSERT INTO calidad VALUES(default," + nom + "," + activoCal + ");")) 
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
                    Calidad c = new Calidad(new Integer(resultado.getInt("id_calidad")),resultado.getString("nombre"), resultado.getBoolean("activo"));
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
	
	public static String getNombre(Integer id_cal)
	{
		String valor = "";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT nombre FROM calidad WHERE id_calidad="+ id_cal);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
					valor = resultado.getString("nombre");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valor;
	}
	
	public Boolean getActivo() 
	{
		return activo;
	}

	public void setActivo(Boolean activo) 
	{
		this.activo = activo;
	}

	public static boolean updateCalidad(String id, String nombre, Boolean activo)
	{
		boolean r=ConexionDB.getbaseDatos().ejecutar(
				"UPDATE calidad SET nombre = " + "'"+nombre+"'" + ", activo=" + activo
						+ " WHERE id_calidad ="
						+ Integer.parseInt(id));
		return r;
	}
}
