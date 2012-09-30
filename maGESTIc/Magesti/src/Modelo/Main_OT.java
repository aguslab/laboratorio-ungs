package Modelo;


public class Main_OT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Orden_Trabajo ot1= new Orden_Trabajo(1, 1, "2012-9-29", "2012-10-29", "Javier", "esta es una descripcion", 9,12,34,false,"en proceso");
		System.out.println(ot1.Alta());
	}

}



