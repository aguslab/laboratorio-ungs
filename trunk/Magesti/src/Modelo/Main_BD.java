package Modelo;

import java.sql.ResultSet;

public class Main_BD 
{

	/**
	 * @param args
	 * @return 
	 */
	
	
	
	public static void main(String[] args) 
	{
        MySQLBD baseDatos = new MySQLBD().conectar();
        
        
        /*ResultSet resultados = baseDatos.consultar("select * from orden_trabajo");
        
        if (resultados != null) 
        {
            try 
            {
                System.out.println("ATRIBUTOSSSSSSSSS");
                System.out.println("--------------------------------");
                while (resultados.next()) 
                {
                    System.out.println(""+resultados.getBigDecimal("id_orden_trabajo")+"       "+resultados.getString("nombre_trabajo"));
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        
        
        /*
        if (baseDatos.ejecutar("INSERT INTO TEST(IDENTIFICADOR,DESCRIPCION) VALUES(3,'TRES')")) {
            System.out.println("Ejecucion correcta!");
        } else {
            System.out.println("Ocurrió un problema al ejecutar!");
        }

        ResultSet resultados = baseDatos.consultar("SELECT * FROM TEST where descripcion='tres'");

        if (resultados != null) 
        {
            try 
            {
                System.out.println("IDENTIFICADOR       DESCRIPCION");
                System.out.println("--------------------------------");
                while (resultados.next()) 
                {
                    System.out.println(""+resultados.getBigDecimal("IDENTIFICADOR")+"       "+resultados.getString("DESCRIPCION"));
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        
        
        
        
        //baseDatos.ejecutar("create table persona(nombre varchar(30),apellido varchar(30), edad smallint);");
        baseDatos.ejecutar("insert into persona(nombre, apellido,edad) values('javier','godoy',20)");
        
        ResultSet resultados2 = baseDatos.consultar("SELECT * FROM persona");

        if (resultados2 != null) {
            try {
                System.out.println("Nombre      Apellido  	Edad");
                System.out.println("--------------------------------");
                while (resultados2.next()) {
                    System.out.println(""+resultados2.getString("Nombre")+"       "+resultados2.getString("apellido")+"          "+resultados2.getInt("edad") );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        //baseDatos.consultar("select * from persona");
        */
    }

}
