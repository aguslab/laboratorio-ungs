
public class Locador {

	private Integer Id_Locador;
	private String Nombre;
	private String CUIT_Locador;
	private String Telefono;
	
	
	public Locador(Integer id_Locador, String nombre, String cUIT_Locador,
			String telefono) {
	
		this.Id_Locador = id_Locador;
		this.Nombre = nombre;
		this.CUIT_Locador = cUIT_Locador;
		this.Telefono = telefono;
	}


	public Integer getId_Locador() {
		return Id_Locador;
	}


	public void setId_Locador(Integer id_Locador) {
		Id_Locador = id_Locador;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getCUIT_Locador() {
		return CUIT_Locador;
	}


	public void setCUIT_Locador(String cUIT_Locador) {
		CUIT_Locador = cUIT_Locador;
	}


	public String getTelefono() {
		return Telefono;
	}


	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	
	
	
	
	
	
}
