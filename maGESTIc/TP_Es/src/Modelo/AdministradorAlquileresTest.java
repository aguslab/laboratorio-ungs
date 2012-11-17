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
	private String f_8 = "01/01/2012";
	private String f_8_1 = "01/01/2011"; 
	private String f_9 = "01/12/2012";
	private String f_10 = "01/01/2013";
	private String f_10_1 = "01/01/2013"; 
	private String f_11 = "31/12/2013";
	private String f_12 = "20/03/2012";
	private String f_12_1 = "25/03/2012";
	private String f_13 = "25/05/2012";
	private String f_14 = "04/01/2013";
	private String f_14_1 = "30/01/2013"; 
	private String f_15 = "31/09/2013";
	
	//provincias de prueba
	private Provincia p1= new Provincia(1, "Bs. As.", "Buenos Aires");
	private Provincia p2= new Provincia(1, "Neuquen", "Neuquen");
	private Provincia p3= new Provincia(1, "San Luis", "San Luis");
	private Provincia p4= new Provincia(1, "T. del Fuego", "Tierra del Fuego");
	private Provincia p5= new Provincia(1, "Formosa", "Formosa");
	private Provincia p6 = new Provincia (2, "Cordoba", "Cordoba");
	private Provincia p7 = new Provincia (3, "Tucuman", "Tucuman");
	private Provincia p8 = new Provincia (4, "La Pampa", "La Pampa");
	
	//Locatarios de prueba
	private Locatario locatario1= new Locatario(1, "Agustina", "92341236576");
	private Locatario locatario2= new Locatario(2, "Carlos", "46255839244");
	private Locatario locatario3= new Locatario(3, "Javier", "20564321789");
	private Locatario locatario4 = new Locatario (4, "Eugenia", "125874698");
	private Locatario locatario5 = new Locatario (4, "Eduardo", "854931287");
	private Locatario locatario6 = new Locatario (4, "Lorena", "1123559647");
	
	//Locadores de prueba
	private Locador locador1= new Locador(1, "De Napoli", "95736285962", "44652167");
	private Locador locador2= new Locador(1, "Godoy", "26210576532", "44552812");
	private Locador locador3= new Locador(1, "Jimenez", "57294738274", "02320465323");
	private Locador locador4= new Locador(1, "Lorenzo", "865798642", "44655523");
	private Locador locador5= new Locador(1, "Mariano", "561128534", "44638522");
	private Locador locador6= new Locador(1, "Florencia", "332567895", "44652167");
	
	//Inmuebles de prueba
	private Inmueble inmueble1= new Inmueble(locador1, 1, p1, "San Miguel", "San Miguel", 1663, "Gutierrez", 1032, 3, 329.34, 200.12, 5, true, true, 6, "BUENO", "44556654", true);
	private Inmueble inmueble2 = new Inmueble(locador4, 2, p6, "Municipio 2", "Localidad 2", 1665, "Suiza", 5001, 0, 600.00, 500.50, 5, true, true, 3, "BUENO", "44228674", false);
	private Inmueble inmueble3 = new Inmueble(locador5, 2, p7, "Municipio 3", "Localidad 3", 1358, "G. Mistral", 406, 0, 300.00, 300.00, 3, true, false, 1, "ANTIGÜO", "44228674", false);
	private Inmueble inmueble4 = new Inmueble(locador6, 2, p8, "Municipio 4", "Localidad 4", 1025, "Av. Trabajador", 516, 2, 400.00, 300.00, 3, true, false, 3, "ANTIGÜO", "44565444", true);
	private Inmueble inmueble5= new Inmueble(locador2, 2, p2, "San mARTIN", "Santa Maria", 1668, "Defensa", 1919, 4, 600.34, 890.12, 8, false, true, 7, "Regular", "44872345", true);
	
	//alquileres de prueba
	private Alquiler alquiler1= new Alquiler(inmueble1, locatario1, 1, f_5, f_5, f_5, 1024.0, "Finalizado");
	private Alquiler alquiler2= new Alquiler(inmueble5, locatario2, 2, f_2, f_3, f_4, 1000.0, "Pagado");
	private Alquiler alquiler3 = new Alquiler(inmueble3, locatario4, 3, f_8, f_8_1, f_9, 2000.0, "En Proceso");
	private Alquiler alquiler4 = new Alquiler(inmueble4, locatario3, 4, f_12, f_12_1, f_13, 3500.0, "Finalizado");
	private Alquiler alquiler5 = new Alquiler(inmueble1, locatario1, 5, f_14, f_14_1, f_15, 4000.0, "En Proceso");
	private Alquiler alquiler6 = new Alquiler(inmueble2, locatario2, 6, f_10, f_10_1, f_11, 1050.0, "Finalizado");
	private Alquiler alquiler9= new Alquiler(inmueble1, locatario3, 7, f_2, f_3, f_6, 1000.0, "Señado");
	private Alquiler alquiler10= new Alquiler(inmueble5, locatario2, 8, "01/02/2010", "01/05/2010", "01/05/2013", 2000.0, "Finalizado");
	
	
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
		alquileres.add(alquiler9);
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
		alquileres.add(alquiler9);
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
		alquileres.add(alquiler9);
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
		alquileres.add(alquiler9);
		alquileres.add(alquiler9);
		alquileres.add(alquiler9);
		//alquiler 1 y no no devuelven nada
		//alquiler9 devuelve sus 10 mese a 1000
		
		Double actual=aa.calcularAlquileres(alquileres, "02/12/2000","02/03/2011" );
		Double esperado=10000.0 * 3;
		assertEquals(esperado, actual);
	}
	
	@Test
	public void calcularAlquileresTest10() {
		AdministradorAlquileres aa= new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
		alquileres.add(alquiler10);
		//ffirma: "01/02/2010"
		//finicio: "01/05/2010"
		//ffin: "01/05/2013"
		//11 meses * 2000
		alquileres.add(alquiler9);
		//ffirma=02/12/2000
		//finicio=03/03/2010
		//ffin=01/01/2011
		//8 meses * 1000
		Double actual=aa.calcularAlquileres(alquileres, "01/05/2010","02/03/2011" );
		Double esperado=(11 * 2000)+ (1000.0 * 8);
		assertEquals(esperado, actual);
	}
	
	
	
	
	
	
	
	
	@Test
	public void calcularAlquileresFechaInicioMenorFechaFirma()
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler3);
		//ffirma: "01/01/2012"
		//finicio:"01/01/2011"
		//ffin: "01/12/2012"
		Double actual=aa.calcularAlquileres(alquileres, "01/01/2011", "01/12/2012");
		Double esperado=0.0;
		assertEquals(esperado, actual);
	}
	
	@Test
	public void InmueblesSinAlquileresTest1()
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		alquileres.add(alquiler2);
		alquileres.add(alquiler3);
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		inmueble.add(inmueble1);
		inmueble.add(inmueble2);
		inmueble.add(inmueble3);
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "09/04/1999");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();////////////////////////////////
		esperado.add(inmueble1);
		esperado.add(inmueble3);		
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void InmueblesSinAlquileresTest2()
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		alquileres.add(alquiler2);
		alquileres.add(alquiler3);
		alquileres.add(alquiler4);
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		inmueble.add(inmueble1);
		inmueble.add(inmueble2);
		inmueble.add(inmueble3);
		inmueble.add(inmueble4);
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "24/05/2012");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();////////////////////////////////
		esperado.add(inmueble1);
		esperado.add(inmueble2);	
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void ListaInmuebleVacia()
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		alquileres.add(alquiler2);
		alquileres.add(alquiler3);
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "24/05/2012");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();////////////////////////////////
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void ListaAlquilerVacia() //Si la lista de alquileres esta vacia, deberia de devolver todos los inmuebles
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		inmueble.add(inmueble1);
		inmueble.add(inmueble2);
		inmueble.add(inmueble3);
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "24/05/2012");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();
		esperado.add(inmueble1);
		esperado.add(inmueble2);
		esperado.add(inmueble3);
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void ListaAlquilerEInmuebleVacias() 
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "24/05/2012");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();////////////////////////////////
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void ningunoAlquilado()
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler1);
		alquileres.add(alquiler2);
		alquileres.add(alquiler3);
		alquileres.add(alquiler4);
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		inmueble.add(inmueble1);
		inmueble.add(inmueble2);
		inmueble.add(inmueble3);
		inmueble.add(inmueble4);
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "19/07/1980");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();////////////////////////////////
		inmueble.add(inmueble1);
		inmueble.add(inmueble2);
		inmueble.add(inmueble3);
		inmueble.add(inmueble4);		
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void todosAlquilados()
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler5);
		alquileres.add(alquiler6);
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		inmueble.add(inmueble1);
		inmueble.add(inmueble2);
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "05/05/2013");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();////////////////////////////////	
		
		assertEquals(esperado, actual);
	}	
	
	
	private String f_16 = "24/05/2012";
	private String f_17 = "25/08/2012";
	private String f_18 = "26/08/2012";
	private String f_19 = "26/09/2012";
	private Alquiler alquiler7 = new Alquiler(inmueble4, locatario3, 7, f_16, f_13, f_17, 3500.0, "Finalizado");
	private Alquiler alquiler8 = new Alquiler(inmueble4, locatario3, 7, f_17, f_18, f_19, 3500.0, "Finalizado");
	
	@Test
	public void mismoInmuebleAlquiladoEnDistintasFechasPeroContiguas() //La fecha de finalizacion del alquiler4 es la misma que la fecha de inicio de alquiler 7, por lo que el metodo NO debe devolver el inmueble4 
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler4);
		alquileres.add(alquiler7);
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		inmueble.add(inmueble4);
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "24/08/2012");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void mismoInmuebleAlquiladoEnDistintasFechasCon1DiaDeDiferencia() //La fecha de inicio de alquiler8 es un dia despues de la fecha de finalizacion del alquiler7, por lo que el metodo mostrara el inmueble4 
	{
		AdministradorAlquileres aa = new AdministradorAlquileres();
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler7);
		alquileres.add(alquiler8);
		
		ArrayList<Inmueble> inmueble = new ArrayList<Inmueble>();
		inmueble.add(inmueble4);
		
		ArrayList<Inmueble> actual = aa.inmueblesSinAlquileres(inmueble, alquileres, "25/08/2012");
		
		ArrayList<Inmueble> esperado = new ArrayList<Inmueble>();////////////////////////////////	
		esperado.add(inmueble4);
		
		assertEquals(esperado, actual);
	}
	

}
