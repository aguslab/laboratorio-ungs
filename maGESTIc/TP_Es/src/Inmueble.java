
public class Inmueble {

	private Locador Locador;
	private Integer Id_Inmueble;
	private Provincia Provincia;
	private String Municipio;
	private String Localidad;
	private Integer CodigoPostal;
	private String Calle;
	private Integer Numero;
	private Integer Piso;
	private Double SuperficieTotal;
	private Double SuperficieCubierta;
	private Integer CantHabitaciones;
	private Boolean TieneCochera;
	private Boolean TienePileta;
	private Integer CantBanios;
	private String EstadoEdificio;
	private String Telefono;
	private Boolean Internet_Wifi;
	
	
	public Inmueble(Locador locador, Integer id_Inmueble, Provincia provincia,
			String municipio, String localidad, Integer codigoPostal,
			String calle, Integer numero, Integer piso, Double superficieTotal,
			Double superficieCubierta, Integer cantHabitaciones,
			Boolean tieneCochera, Boolean tienePileta, Integer cantBanios,
			String estadoEdificio, String telefono, Boolean internet_Wifi) {
		this.Locador = locador;
		this.Id_Inmueble = id_Inmueble;
		this.Provincia = provincia;
		this.Municipio = municipio;
		this.Localidad = localidad;
		this.CodigoPostal = codigoPostal;
		this.Calle = calle;
		this.Numero = numero;
		this.Piso = piso;
		this.SuperficieTotal = superficieTotal;
		this.SuperficieCubierta = superficieCubierta;
		this.CantHabitaciones = cantHabitaciones;
		this.TieneCochera = tieneCochera;
		this.TienePileta = tienePileta;
		this.CantBanios = cantBanios;
		this.EstadoEdificio = estadoEdificio;
		this.Telefono = telefono;
		this.Internet_Wifi = internet_Wifi;
	}


	public Locador getLocador() {
		return Locador;
	}


	public void setLocador(Locador locador) {
		Locador = locador;
	}


	public Integer getId_Inmueble() {
		return Id_Inmueble;
	}


	public void setId_Inmueble(Integer id_Inmueble) {
		Id_Inmueble = id_Inmueble;
	}


	public Provincia getProvincia() {
		return Provincia;
	}


	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}


	public String getMunicipio() {
		return Municipio;
	}


	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}


	public String getLocalidad() {
		return Localidad;
	}


	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}


	public Integer getCodigoPostal() {
		return CodigoPostal;
	}


	public void setCodigoPostal(Integer codigoPostal) {
		CodigoPostal = codigoPostal;
	}


	public String getCalle() {
		return Calle;
	}


	public void setCalle(String calle) {
		Calle = calle;
	}


	public Integer getNumero() {
		return Numero;
	}


	public void setNumero(Integer numero) {
		Numero = numero;
	}


	public Integer getPiso() {
		return Piso;
	}


	public void setPiso(Integer piso) {
		Piso = piso;
	}


	public Double getSuperficieTotal() {
		return SuperficieTotal;
	}


	public void setSuperficieTotal(Double superficieTotal) {
		SuperficieTotal = superficieTotal;
	}


	public Double getSuperficieCubierta() {
		return SuperficieCubierta;
	}


	public void setSuperficieCubierta(Double superficieCubierta) {
		SuperficieCubierta = superficieCubierta;
	}


	public Integer getCantHabitaciones() {
		return CantHabitaciones;
	}


	public void setCantHabitaciones(Integer cantHabitaciones) {
		CantHabitaciones = cantHabitaciones;
	}


	public Boolean getTieneCochera() {
		return TieneCochera;
	}


	public void setTieneCochera(Boolean tieneCochera) {
		TieneCochera = tieneCochera;
	}


	public Boolean getTienePileta() {
		return TienePileta;
	}


	public void setTienePileta(Boolean tienePileta) {
		TienePileta = tienePileta;
	}


	public Integer getCantBanios() {
		return CantBanios;
	}


	public void setCantBanios(Integer cantBanios) {
		CantBanios = cantBanios;
	}


	public String getEstadoEdificio() {
		return EstadoEdificio;
	}


	public void setEstadoEdificio(String estadoEdificio) {
		EstadoEdificio = estadoEdificio;
	}


	public String getTelefono() {
		return Telefono;
	}


	public void setTelefono(String telefono) {
		Telefono = telefono;
	}


	public Boolean getInternet_Wifi() {
		return Internet_Wifi;
	}


	public void setInternet_Wifi(Boolean internet_Wifi) {
		Internet_Wifi = internet_Wifi;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
