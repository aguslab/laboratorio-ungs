package Modelo;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Cliente {

	private Integer id_cliente;
	private String razon_social;
	private String cuit;
	private String cond_iva;
	private String direccion;
	private String telefono;
	private String mail;
	private String nombre_contacto;
	private String telefono_contacto;
	private String mail_contacto;
	private String direccion_entrega;
	private boolean activo;

	// Se usa para crear objetos a la hora de buscar
	public Cliente(Integer id_cliente, String razon_social, String cuit,
			String cond_iva, String direccion, String telefono, String mail,
			String nombre_contacto, String telefono_contacto,
			String mail_contacto, String direccion_entrega, boolean activo) {
		super();
		this.id_cliente = id_cliente;
		this.razon_social = razon_social;
		this.cuit = cuit;
		this.cond_iva = cond_iva;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
		this.nombre_contacto = nombre_contacto;
		this.telefono_contacto = telefono_contacto;
		this.mail_contacto = mail_contacto;
		this.direccion_entrega = direccion_entrega;
		this.activo = activo;
	}

	// Se usa para crear objetos a la hora de insertar.El campo id_cliente es
	// autonumerico
	public Cliente(String razon_social, String cuit, String cond_iva,
			String direccion, String telefono, String mail,
			String nombre_contacto, String telefono_contacto,
			String mail_contacto, String direccion_entrega, boolean activo) {
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
		this.direccion_entrega = direccion_entrega;
		this.activo = activo;
	}

	
	
	
	
	public static  String[] getClientes(){
		ArrayList<String> clien=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT razon_social FROM cliente where activo = true");
		
		if (resultado != null) {
			try {
				while (resultado.next()) {
					clien.add(resultado.getString("razon_social"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String [] clientes= new String[clien.size()];
		
		for(int i=0;i<clien.size();i++){
			clientes[i]=clien.get(i);
		}
		
		return clientes;
	}
	
	
	public static Integer getId_cliente(String cliente) {
		Integer id_cliente=null;
		cliente="'"+cliente+"'";
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_cliente FROM cliente where razon_social="+cliente);
		
		if (resultado != null) {
			try {//por si llega a haber mas de un cliente con = RazonSocial
				while (resultado.next()) {
					id_cliente=resultado.getInt(1);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		return id_cliente;
	}
	
	
	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCond_iva() {
		return cond_iva;
	}

	public void setCond_iva(String cond_iva) {
		this.cond_iva = cond_iva;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre_contacto() {
		return nombre_contacto;
	}

	public void setNombre_contacto(String nombre_contacto) {
		this.nombre_contacto = nombre_contacto;
	}

	public String getTelefono_contacto() {
		return telefono_contacto;
	}

	public void setTelefono_contacto(String telefono_contacto) {
		this.telefono_contacto = telefono_contacto;
	}

	public String getMail_contacto() {
		return mail_contacto;
	}

	public void setMail_contacto(String mail_contacto) {
		this.mail_contacto = mail_contacto;
	}

	public String getDireccion_entrega() 
	{
		return direccion_entrega;
	}

	public void setDireccion_entrega(String direccion_entrega) 
	{
		this.direccion_entrega = direccion_entrega;
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
		String ccuit="'"+this.getCuit()+"'";
		String c_iva = "'" + this.getCond_iva() + "'";
		String direc = "'" + this.getDireccion() + "'";
		String tel = "'" + this.getTelefono() + "'";
		String email = "'" + this.getMail() + "'";
		String nom_cont = "'" + this.getNombre_contacto() + "'";
		String tel_cont = "'" + this.getTelefono_contacto() + "'";
		String mail_contacto = "'" + this.getMail_contacto() + "'";
		String dir_entrega = "'" + this.getDireccion_entrega() + "'";
		boolean clienteActivo = this.getActivo();

		if (ConexionDB.getbaseDatos().ejecutar("INSERT INTO cliente VALUES(DEFAULT,"
				+ razon_soc + "," + ccuit + "," + c_iva + "," + direc + ","
				+ tel + "," + email+"," + nom_cont + "," + tel_cont + ","
				+ mail_contacto + "," + dir_entrega + "," + clienteActivo + ");")) 
		{
			return true;
		} 
		else 
		{
			return false;
		}

	}

	public ArrayList<Cliente> Buscar() {

		ResultSet resultado = ConexionDB.getbaseDatos().consultar("SELECT * FROM cliente");

		ArrayList<Cliente> list_clientes = new ArrayList<Cliente>();
		if (resultado != null) {

			try {

				while (resultado.next()) {
					Cliente clientes = new Cliente(new Integer(
							resultado.getInt("id_cliente")),
							resultado.getString("razon_social"),
							resultado.getString("cuit"),
							resultado.getString("cond_iva"),
							resultado.getString("direccion"),
							resultado.getString("telefono"),
							resultado.getString("mail"),
							resultado.getString("nombre_contacto"),
							resultado.getString("telefono_contacto"),
							resultado.getString("mail_contacto"),
							resultado.getString("direccion_entrega"),
							resultado.getBoolean("activo"));
					list_clientes.add(clientes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_clientes;
	}

	
	public static boolean updateDatosCliente(String id, String razon_social,String cuit, String cond_iva, String direccion, String telefono, String mail, boolean activo ){
	
		boolean r=ConexionDB.getbaseDatos().ejecutar(
				"UPDATE cliente SET razon_social = " +
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
						+ " WHERE id_cliente ="
						+ Integer.parseInt(id));
		
		return r;
	}
	
	
	public static boolean updateDatosContactoCliente(String id, String nombre,String telefono, String mail,String dir_entrega){
		
		boolean r=ConexionDB.getbaseDatos().ejecutar(
				"UPDATE cliente SET nombre_contacto =" +
						"'"+ nombre + "'"
						+ ",telefono_contacto = "+
						"'"+telefono+"'"
						+",mail_contacto = " +
						"'"+ mail+ "'"
						+ ",direccion_entrega = " +
						"'"+ dir_entrega + "'"
						+ " WHERE id_cliente ="+ Integer.parseInt(id));
		
		return r;
	}

	public static Integer getCantClientes() {
		Integer maxId = null;
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT COUNT(id_cliente) FROM cliente");

		if (resultado != null) {
			try {
				while (resultado.next()) {
					// como solo devuelve un valor, le pido el del registro (1)
					maxId = resultado.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return maxId;
	}
	
	
	
}

