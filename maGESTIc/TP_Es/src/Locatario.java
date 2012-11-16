
public class Locatario {

	private Integer Id_Locatario;
	private String Nombre;
	private String CUIT_CUIL_Locatario;
	
	
	public Locatario(Integer id_Locatario, String nombre,
			String cUIT_CUIL_Locatario) {
		this.Id_Locatario = id_Locatario;
		this.Nombre = nombre;
		this.CUIT_CUIL_Locatario = cUIT_CUIL_Locatario;
	}


	public Integer getId_Locatario() {
		return Id_Locatario;
	}


	public void setId_Locatario(Integer id_Locatario) {
		Id_Locatario = id_Locatario;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getCUIT_CUIL_Locatario() {
		return CUIT_CUIL_Locatario;
	}


	public void setCUIT_CUIL_Locatario(String cUIT_CUIL_Locatario) {
		CUIT_CUIL_Locatario = cUIT_CUIL_Locatario;
	}

	
	
	
	
}
