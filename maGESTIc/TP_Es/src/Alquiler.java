
public class Alquiler {

	private Inmueble Inmueble;
	private Locatario Locatario;
	private Integer Id_Alquiler;
	private String FechaFirma;
	private String FechaInicio;
	private String FechaFinalizacion;
	private Double MontoMensual;
	private String Estado;
	
	
	public Alquiler(Inmueble inmueble, Locatario locatario,
			Integer id_Alquiler, String fechaFirma, String fechaInicio,
			String fechaFinalizacion,Double MontoMensual, String Estado) {
	
		this.Inmueble = inmueble;
		this.Locatario = locatario;
		this.Id_Alquiler = id_Alquiler;
		this.FechaFirma = fechaFirma;
		this.FechaInicio = fechaInicio;
		this.FechaFinalizacion = fechaFinalizacion;
		this.MontoMensual=MontoMensual;
		this.Estado=Estado;
	}


	public Inmueble getInmueble() {
		return Inmueble;
	}


	public void setInmueble(Inmueble inmueble) {
		Inmueble = inmueble;
	}


	public Locatario getLocatario() {
		return Locatario;
	}


	public void setLocatario(Locatario locatario) {
		Locatario = locatario;
	}


	public Integer getId_Alquiler() {
		return Id_Alquiler;
	}


	public void setId_Alquiler(Integer id_Alquiler) {
		Id_Alquiler = id_Alquiler;
	}


	public String getFechaFirma() {
		return FechaFirma;
	}


	public void setFechaFirma(String fechaFirma) {
		FechaFirma = fechaFirma;
	}


	public String getFechaInicio() {
		return FechaInicio;
	}


	public void setFechaInicio(String fechaInicio) {
		FechaInicio = fechaInicio;
	}


	public String getFechaFinalizacion() {
		return FechaFinalizacion;
	}


	public void setFechaFinalizacion(String fechaFinalizacion) {
		FechaFinalizacion = fechaFinalizacion;
	}


	public Double getMontoMensual() {
		return MontoMensual;
	}


	public void setMontoMensual(Double montoMensual) {
		MontoMensual = montoMensual;
	}


	public String getEstado() {
		return Estado;
	}


	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
	
	
	
}
