package Modelo;


public class Main_OT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Orden_Trabajo ot1= new Orden_Trabajo(1, 1, "2012-9-29", "2012-10-29", "tasda", "descripchd", 4,true);
		System.out.println(ot1.alta());
	}

}



