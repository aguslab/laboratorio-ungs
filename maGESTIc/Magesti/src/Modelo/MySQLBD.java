package Modelo;

import java.sql.*;

import javax.swing.JOptionPane;

import vista_Controlador.Config;

public class MySQLBD 
{

    private Connection conexion;

    public Connection getConexion() 
    {
        return conexion;
    }

    public void setConexion(Connection conexion) 
    {
        this.conexion = conexion;
    }

    public MySQLBD conectar() 
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            String  ip=Config.qSERVIDOR,
            		baseDatos=Config.qBASE,
            		usuario=Config.qUSUARIO,
            		pass=Config.qPASSWORD;

            
            String BaseDeDatos = "jdbc:mysql://"+ip+"/"+baseDatos+"?user="+usuario+"&password="+pass;
            setConexion(DriverManager.getConnection(BaseDeDatos));
            if(getConexion() != null)
            {
                System.out.println("Conexion Exitosa!");
            }
            else
            {
                System.out.println("Conexion Fallida!");                
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog 
			(
				null, 
				"No se ha podido conectar a la base de datos, verifique que el servidor se está ejecutando",
				Config.qTITULO + " - ERROR CON LA BASE DE DATOS", 
				JOptionPane.WARNING_MESSAGE
			);
			System.exit (0);
        }
        
        return this;
    }
    
    /**
     * Cierra la conexión con la base de datos
     * @return true si se cerró correctamente, false si no
     */
    public boolean cerrar()
    {    
        try
        {
            if(getConexion()!=null)
            {
                getConexion().close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Realiza una consulta de tipo SELECT en la base de datos
     * @param sql La consulta SQL a realizar
     * @return El conjunto de resultados obtenidos, null si ha ocurrido un error
     */
    public ResultSet consultar(String sql) 
    {
        ResultSet resultado;
        try 
        {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
        } 
        catch (SQLException e) 
        {
        	e.printStackTrace();
            return null;
        }

        return resultado;
    }

    /**
     * Ejecuta una sentencia de tipo INSERT, UPDATE, DELETE, etc (excepto SELECT) en la base de datos 
     * @param sql La sentencia a ejecutar
     * @return true si se ejecutó correctamente, false si no
     */
    public boolean ejecutar(String sql) 
    {
        try 
        {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql);
            sentencia.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    
}
