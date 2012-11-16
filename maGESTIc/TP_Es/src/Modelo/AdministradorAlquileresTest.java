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
	private String f_6="01/01/2011";
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
	private Inmueble inmueble2= new Inmueble(locador2, 2, p2, "San mARTIN", "Santa Maria", 1668, "Defensa", 1919, 4, 600.34, 890.12, 8, false, true, 7, "Regular", "44872345", true);

	
	//alquileres de prueba
	private Alquiler alquiler1= new Alquiler(inmueble1, locatario1, 1, f_5, f_5, f_5, 1024.0, "Finalizado");
	private Alquiler alquiler2= new Alquiler(inmueble2, locatario2, 2, f_2, f_3, f_4, 1000.0, "Pagado");
	private Alquiler alquiler3= new Alquiler(inmueble1, locatario3, 3, f_2, f_3, f_6, 1000.0, "Se�ado");
	private Alquiler alquiler4= new Alquiler(inmueble2, locatario2, 3, "01/02/2010", "01/05/2010", "01/05/2013", 2000.0, "Finalizado");

	
	@Test
	public void calcularAlquileresTestListaVacia() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		
		
		Double actual=aa.calcularAlquileres(alquileres, "01/01/2000", "01/01/3000");
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTestFechasIgualesEnParametro() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		//si no hay rango de tiempo, debe devolver 0
		Double actual=aa.calcularAlquileres(alquileres, "01/01/1990", "01/01/1990");
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	@Test
	public void calcularAlquileresTestFechasIgualesAlquiler() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		//fecha de firma, inicio y fin son iguales, debe devolver nada(0)
		Double actual=aa.calcularAlquileres(alquileres, "01/01/1990", "01/01/2000");
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTestFechaFinMEnorFechaInicio() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler2);
		//fecha finalizacion de alquiler menor a fecha firma.Cero meses cobrados
		Double actual=aa.calcularAlquileres(alquileres, "12/12/2012", "01/01/3000");
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTest4() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler2);
		alquileres.add(alquiler1);
		//lista con 2 alquileres que deben devolver 0 y 0 
		Double actual=aa.calcularAlquileres(alquileres, "01/01/1990", "01/10/1999");
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTest6() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler3);
		//ffirma=02/12/2000
		//finicio=03/03/2010
		//ffin=01/01/2011
		//10 meses de 1000
		Double actual=aa.calcularAlquileres(alquileres, "01/01/1900", "16/11/2012");
		Double esperado=10000.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTest7() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler3);
		//ffirma=02/12/2000
		//finicio=03/03/2010
		//ffin=01/01/2011
		//8 meses de 1000
		Double actual=aa.calcularAlquileres(alquileres, "01/05/2010", "01/01/2011");
		Double esperado=8000.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTest8() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler3);
		//ffirma=02/12/2000
		//finicio=03/03/2010
		//ffin=01/01/2011
		//0 meses de 1000
		Double actual=aa.calcularAlquileres(alquileres, "02/12/2000","02/03/2001" );
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	
	@Test
	public void calcularAlquileresTest9() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		alquileres.add(alquiler2);
		alquileres.add(alquiler3);
		alquileres.add(alquiler3);
		alquileres.add(alquiler3);
		//alquiler 1 y no no devuelven nada
		//alquiler3 devuelve sus 10 mese a 1000
		
		Double actual=aa.calcularAlquileres(alquileres, "02/12/2000","02/03/2011" );
		Double esperado=10000.0 * 3;
		assertEquals(esperado, actual);
	}
	
	@Test
	public void calcularAlquileresTest10() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler4);
		//ffirma: "01/02/2010"
		//finicio: "01/05/2010"
		//ffin: "01/05/2013"
		//11 meses * 2000
		alquileres.add(alquiler3);
		//ffirma=02/12/2000
		//finicio=03/03/2010
		//ffin=01/01/2011
		//8 meses * 1000
		Double actual=aa.calcularAlquileres(alquileres, "01/05/2010","02/03/2011" );
		Double esperado=(11 * 2000)+ (1000.0 * 8);
		assertEquals(esperado, actual);
	}

}
