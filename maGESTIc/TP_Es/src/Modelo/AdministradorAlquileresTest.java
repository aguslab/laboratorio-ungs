package Modelo;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class AdministradorAlquileresTest {

	//fechas de prueba
	private String f_1="01/04/1995";
	private String f_2="02/12/2000";
	private String f_3="03/03/2010";
	private String f_4="04/10/1982";
	private String f_5="05/01/1999";
	private String f_5_1="05/02/1999";
	private String f_5_2="10/04/1999";
	private String f_6="10/04/2010";
	private String f_7="16/09/2015";
	//provincias de prueba
	private Provincia p1= new Provincia(1, "Bs. As.", "Buenos Aires");
	private Provincia p2= new Provincia(1, "Neuquen", "Neuquen");
	private Provincia p3= new Provincia(1, "San Luis", "San Luis");
	private Provincia p4= new Provincia(1, "T. del Fuego", "Tierra del Fuego");
	private Provincia p5= new Provincia(1, "Formosa", "Formosa");
	//Locatarios de prueba
	private Locatario locatario1= new Locatario(1, "Agustina", "92341236576");
	private Locatario locatario2= new Locatario(2, "Carlos", "46255839244");
	private Locatario locatario3= new Locatario(3, "Javier", "20564321789");
	//Locadores de prueba
	private Locador locador1= new Locador(1, "De Napoli", "95736285962", "44652167");
	private Locador locador2= new Locador(1, "Godoy", "26210576532", "44552812");
	private Locador locador3= new Locador(1, "Jimenez", "57294738274", "02320465323");
	//Inmuebles de prueba
	private Inmueble inmueble1= new Inmueble(locador1, 1, p1, "San Miguel", "San Miguel", 1663, "Gutierrez", 1032, 3, 329.34, 200.12, 5, true, true, 6, "BUENO", "44556654", true);

	
	//alquileres de prueba
	private Alquiler alquiler1= new Alquiler(inmueble1, locatario1, 1, f_5, f_5, f_5, 1024.0, "Finalizado");
	private Alquiler alquiler2= new Alquiler(inmueble1, locatario2, 2, f_5, f_5_1, f_5_2, 1024.0, "Finalizado");

	@Test
	public void calcularAlquileresTestListaVacia() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		
		
		Double actual=aa.calcularAlquileres(alquileres, f_6, f_7);
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTestFechasIguales() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		
		Double actual=aa.calcularAlquileres(alquileres, f_5, f_5);
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTestFechaFinMEnorFechaInicio() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		
		Double actual=aa.calcularAlquileres(alquileres, f_6, f_5);
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	//ver a partir de cuando se cobra, si a partir de fechaFirma o FechaInicio
	@Test
	public void calcularAlquileresTest4() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler2);
		
		Double actual=aa.calcularAlquileres(alquileres, "01/01/1990", "01/10/1999");
		Double esperado=2048.0;/////////////////////////////////////////////////////
		assertEquals(esperado, actual);
	}


}
