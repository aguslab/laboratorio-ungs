package Modelo;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Cliente {

	private Integer id_cliente;
	private String razon_social;
	private BigInteger cuit;
	private String cond_iva;
	private String direccion;
	private String telefono;
	private String mail;
	private String nombre_contacto;
	private String telefono_contacto;
	private String mail_contacto;
	private String direccion_entrega;

	// Se usa para crear objetos a la hora de buscar
	public Cliente(Integer id_cliente, String razon_social, BigInteger cuit,
			String cond_iva, String direccion, String telefono, String mail,
			String nombre_contacto, String telefono_contacto,
			String mail_contacto, String direccion_entrega) {
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
	}

	// Se usa para crear objetos a la hora de insertar.El campo id_cliente es
	// autonumerico
	public Cliente(String razon_social, BigInteger cuit, String cond_iva,
			String direccion, String telefono, String mail,
			String nombre_contacto, String telefono_contacto,
			String mail_contacto, String direccion_entrega) {
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
	}

	
	
	
	
	public static  String[] getClientes(){
		ArrayList<String> clien=new ArrayList<String>();
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT razon_social FROM cliente");
		
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
		
		ResultSet resultado = ConexionDB.getbaseDatos().consultar(
				"SELECT id_cliente FROM cliente");
		
		if (resultado != null) {
			try {
				while (resultado.next()) {
					id_cliente=resultado.getInt("razon_social");
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

	public BigInteger getCuit() {
		return cuit;
	}

	public void setCuit(BigInteger cuit) {
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

	public String getDireccion_entrega() {
		return direccion_entrega;
	}

	public void setDireccion_entrega(String direccion_entrega) {
		this.direccion_entrega = direccion_entrega;
	}

	public boolean Alta() {

		String razon_soc = "'" + this.getRazon_social() + "'";
		BigInteger cuit = this.getCuit();
		String c_iva = "'" + this.getCond_iva() + "'";
		String direc = "'" + this.getDireccion() + "'";
		String tel = "'" + this.getTelefono() + "'";
		String email = "'" + this.getMail() + "'";
		String nom_cont = "'" + this.getNombre_contacto() + "'";
		String tel_cont = "'" + this.getTelefono_contacto() + "'";
		String mail_contacto = "'" + this.getMail_contacto() + "'";
		String dir_entrega = "'" + this.direccion_entrega + "'";

		if (ConexionDB.getbaseDatos().ejecutar("INSERT INTO cliente VALUES(default,"
				+ razon_soc + "," + cuit + "," + c_iva + "," + direc + ","
				+ tel + "," + email+"," + nom_cont + "," + tel_cont + ","
				+ mail_contacto + "," + dir_entrega + ");")) {
			return true;
		} else {
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
							new BigInteger(resultado.getString("cuit")),
							resultado.getString("cond_iva"),
							resultado.getString("direccion"),
							resultado.getString("telefono"),
							resultado.getString("mail"),
							resultado.getString("nombre_contacto"),
							resultado.getString("telefono_contacto"),
							resultado.getString("mail_contacto"),
							resultado.getString("direccion_entrega"));
					list_clientes.add(clientes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list_clientes;
	}

}

