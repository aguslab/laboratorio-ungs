package Modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Proveedor 
{
	private Integer id_proveedor;
	private String razon_social;
	private String cuit;
	private String cond_iva;
	private String direccion;
	private String telefono;
	private String mail;
	private String nombre_contacto;
	private String telefono_contacto;
	private String mail_contacto;
	private String direccion_retiro;
	private boolean activo;

	// Se usa para crear objetos a la hora de buscar
	public Proveedor(Integer id_proveedor, String razon_social, String cuit,
			String cond_iva, String direccion, String telefono, String mail,
			String nombre_contacto, String telefono_contacto,
			String mail_contacto, String direccion_retiro, boolean activo) 
	{
		super();
		this.id_proveedor = id_proveedor;
		this.razon_social = razon_social;
		this.cuit = cuit;
		this.cond_iva = cond_iva;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
		this.nombre_contacto = nombre_contacto;
		this.telefono_contacto = telefono_contacto;
		this.mail_contacto = mail_contacto;
		this.direccion_retiro = direccion_retiro;
		this.activo = activo;
	}

	// Se usa para crear objetos a la hora de insertar.El campo id_proveedor es
	// autonumerico
	public Proveedor(String razon_social, String cuit, String cond_iva,
			String direccion, String telefono, String mail,
			String nombre_contacto, String telefono_contacto,
			String mail_contacto, String direccion_retiro, boolean activo) 
	{
		super();
		this.razon_social = razon_social;
		this.cuit = cuit;
		this.cond_iva = cond_iva;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
		this.nombre_contacto = nombre_contacto;
		this.telefono_contacto = telefono_contacto;
		this.mail_contacto = mail_contacto;
		this.direccion_retiro = direccion_retiro;
		this.activo = activo;
	}

	
	
	
	
	public static String[] getProveedores()
 {
		ArrayList<String> pros = new ArrayList<String>();

		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT razon_social FROM proveedor where activo = true");

		if (resultado != null) {
			try {
				while (resultado.next()) {
					pros.add(resultado.getString("razon_social"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String[] pro = new String[pros.size()];

		for (int i = 0; i < pros.size(); i++) {
			pro[i] = pros.get(i);
		}

		return pro;
	}
	
	
	
	
	
	public static Integer getId_Proveedor(String razonSocial)
	{
		Integer id_pro = null;
		razonSocial = "'" + razonSocial + "'";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_proveedor FROM proveedor WHERE razon_social ="+ razonSocial);
		
		if (resultado != null) 
		{
			try 
			{
				while (resultado.next()) 
				{
					id_pro = resultado.getInt(1);
					break;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
		
		return id_pro;
	}
	
	public static String getRazonSocial(Integer id_Proveedor)
	{
		String valor = "";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT razon_social FROM proveedor WHERE id_proveedor="+ id_Proveedor);

		if (resultado != null)
		{
			try
			{
				while (resultado.next())
				{
						valor = resultado.getString("razon_social");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return valor;
	}
	
	public Integer getId_proveedor() 
	{
		return id_proveedor;
	}

	public void setId_proveedor(Integer id_proveedor) 
	{
		this.id_proveedor = id_proveedor;
	}

	public String getRazon_social() 
	{
		return razon_social;
	}

	public void setRazon_social(String razon_social) 
	{
		this.razon_social = razon_social;
	}

	public String getCuit() 
	{
		return cuit;
	}

	public void setCuit(String cuit) 
	{
		this.cuit = cuit;
	}

	public String getCond_iva() 
	{
		return cond_iva;
	}

	public void setCond_iva(String cond_iva) {
		
		this.cond_iva = cond_iva;
	}

	public String getDireccion() 
	{
		return direccion;
	}

	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	public String getTelefono() 
	{
		return telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getMail() 
	{
		return mail;
	}

	public void setMail(String mail) 
	{
		this.mail = mail;
	}

	public String getNombre_contacto() 
	{
		return nombre_contacto;
	}

	public void setNombre_contacto(String nombre_contacto) 
	{
		this.nombre_contacto = nombre_contacto;
	}

	public String getTelefono_contacto() {
		return telefono_contacto;
	}

	public void setTelefono_contacto(String telefono_contacto) 
	{
		this.telefono_contacto = telefono_contacto;
	}

	public String getMail_contacto() 
	{
		return mail_contacto;
	}

	public void setMail_contacto(String mail_contacto) 
	{
		this.mail_contacto = mail_contacto;
	}

	public String getDireccion_retiro() 
	{
		return direccion_retiro;
	}

	public void setDireccion_retiro(String direccion_retiro) 
	{
		this.direccion_retiro = direccion_retiro;
	}
	
	public boolean getActivo()
	{
		return this.activo;
	}
	
	public void setActivo(boolean estado)
	{
		this.activo = estado;
	}

	public boolean Alta() 
	{

		String razon_soc = "'" + this.getRazon_social() + "'";
		String cuit = "'" + this.getCuit()+ "'";
		String c_iva = "'" + this.getCond_iva() + "'";
		String direc = "'" + this.getDireccion() + "'";
		String tel = "'" + this.getTelefono() + "'";
		String email = "'" + this.getMail() + "'";
		String nom_cont = "'" + this.getNombre_contacto() + "'";
		String tel_cont = "'" + this.getTelefono_contacto() + "'";
		String mail_contacto = "'" + this.getMail_contacto() + "'";
		String dir_retiro = "'" + this.getDireccion_retiro() + "'";
		boolean activo = this.getActivo();

		if (ConexionDB.getbaseDatos().ejecutar("INSERT INTO proveedor VALUES(DEFAULT,"
				+ razon_soc + "," + cuit + "," + c_iva + "," + direc + ","
				+ tel + "," + email+"," + nom_cont + "," + tel_cont + ","
				+ mail_contacto + "," + dir_retiro + "," + activo + ");")) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}

	public ArrayList<Proveedor> Buscar() 
	{

		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT * FROM proveedor");

		ArrayList<Proveedor> list_proveedores = new ArrayList<Proveedor>();
		if (resultado != null) 
		{

			try 
			{

				while (resultado.next()) 
				{
					Proveedor proveedores = new Proveedor(new Integer(
							resultado.getInt("id_proveedor")),
							resultado.getString("razon_social"),
							resultado.getString("cuit"),
							resultado.getString("cond_iva"),
							resultado.getString("direccion"),
							resultado.getString("telefono"),
							resultado.getString("mail"),
							resultado.getString("nombre_contacto"),
							resultado.getString("telefono_contacto"),
							resultado.getString("mail_contacto"),
							resultado.getString("direccion_retiro"),
							resultado.getBoolean("activo"));
					list_proveedores.add(proveedores);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return list_proveedores;
	}
	
	public void mostrarDatos()
	{
		System.out.println();
		System.out.print(this.getId_proveedor()+"    ");
		System.out.print(this.getRazon_social()+"     ");
		System.out.print(this.getCuit()+"     ");
		System.out.print(this.getCond_iva()+"    ");
		System.out.print(this.getDireccion()+"     ");
		System.out.print(this.getTelefono()+"     ");
		System.out.print(this.getMail()+"    ");
		System.out.print(this.getNombre_contacto()+"     ");
		System.out.print(this.getTelefono_contacto()+"     ");
		System.out.println(this.getMail_contacto()+"     ");
		System.out.print(this.getDireccion_retiro()+"     ");
	}

	public static boolean updateDatosProveedor(String id, String razon_social,
			String cuit, String cond_iva, String direccion,
			String telefono, String mail, boolean activo) {
		
		boolean r=ConexionDB.getbaseDatos().ejecutar(
				"UPDATE proveedor SET razon_social = " +
						"'"+razon_social+"'" +
						",cuit = "+
						"'"+cuit+"'" 
						+",cond_iva = " +
						"'"+ cond_iva+ "'" +
						",direccion = "+
						"'" + direccion + "'"
						+ ",telefono = " +
						"'" + telefono+ "'" 
						+ ",mail = " +
						"'" + mail+ "'"
						+ ", activo="+activo
						+ " WHERE id_proveedor ="
						+ Integer.parseInt(id));
		
		return r;		
	}

	
	public static boolean updateDatosContactoProveedor(String id,
			String nombre, String telefono,
			String mail, String dir_retiro) {

		boolean r=ConexionDB.getbaseDatos().ejecutar(
				"UPDATE proveedor SET nombre_contacto =" +
						"'"+ nombre + "'"
						+ ",telefono_contacto = "+
						"'"+telefono+"'"
						+",mail_contacto = " +
						"'"+ mail+ "'"
						+ ",direccion_retiro = " +
						"'"+ dir_retiro + "'"
						+ " WHERE id_proveedor ="+ Integer.parseInt(id));
		
		return r;
	}
}
