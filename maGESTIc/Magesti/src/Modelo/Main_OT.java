package Modelo;

import java.util.ArrayList;


public class Main_OT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		Orden_Trabajo ot1= new Orden_Trabajo(1, 1, "2012-9-29", "2012-10-29", "Javier", "esta es una descripcion", 9,12,34,false,"pendiente");
		System.out.println(ot1.Alta());
		ArrayList<Orden_Trabajo> lot=ot1.Buscar();
		lot.get(0).mostrarDatos();
		lot.get(1).mostrarDatos();
	*/
		/*
		Variante v1= new Variante("hola");
	System.out.println(v1.Alta());
	ArrayList<Variante> vr=v1.Buscar();
	*/
	
	Proceso p= new Proceso("nombreProc");
	System.out.println(p.Alta());
	ArrayList<Proceso> lproc=p.Buscar();

		
	}

}



