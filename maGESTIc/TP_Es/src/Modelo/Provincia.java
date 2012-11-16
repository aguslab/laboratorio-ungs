package Modelo;

public class Provincia {

	private Integer Id_Provincia;
	private String Nombre; //nombre abreviado
	private String Descripcion; //nombre completo
	
	
	
	public Provincia(Integer id_Provincia, String nombre, String descripcion) {
		this.Id_Provincia = id_Provincia;
		this.Nombre = nombre;
		this.Descripcion = descripcion;
	}
	
	
	public Integer getId_Provincia() {
		return Id_Provincia;
	}
	public void setId_Provincia(Integer id_Provincia) {
		Id_Provincia = id_Provincia;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
}
