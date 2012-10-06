package Modelo;

//clase final para que no pueda ser sobreescrita
public final class ConexionDB {
	
	private static MySQLBD baseDatos = new MySQLBD().conectar();
	
	private ConexionDB() {}
	
	
	// creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciaci�n m�ltiple 
	private synchronized static void createInstance() {
        if (baseDatos == null) { 
            baseDatos= new MySQLBD();
        }
    }
	
	public static MySQLBD getbaseDatos(){
		createInstance();
		return baseDatos;
	}

	//El m�todo "clone" es sobreescrito por el siguiente que arroja una excepci�n para evitar
	// que se clone esta clase y todo sea al pedo, igual nadie va a hacer eso xD
	public Object clone() throws CloneNotSupportedException {
	        throw new CloneNotSupportedException(); 
	}
	
}
