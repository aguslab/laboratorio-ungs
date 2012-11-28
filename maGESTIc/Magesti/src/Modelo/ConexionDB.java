package Modelo;

//clase final para que no pueda ser sobreescrita
public final class ConexionDB {
	
	private static MySQLBD baseDatos = null;
	
	private ConexionDB() {}
	
	
	// creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple 
	private synchronized static void createInstance() {
        if (baseDatos == null) { 
            baseDatos= new MySQLBD().conectar();
            baseDatos.ejecutar("SET NAMES 'utf8'");
            baseDatos.ejecutar("SET CHARACTER SET utf8");
        }
    }
	
	public static MySQLBD getbaseDatos(){
		createInstance();
		return baseDatos;
	}

	//El método "clone" es sobreescrito por el siguiente que arroja una excepción para evitar
	// que se clone esta clase y todo sea al pedo, igual nadie va a hacer eso xD
	public Object clone() throws CloneNotSupportedException {
	        throw new CloneNotSupportedException(); 
	}
	
}
