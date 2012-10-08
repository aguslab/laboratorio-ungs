package Modelo;

import java.sql.*;

import javax.swing.JOptionPane;

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
            String BaseDeDatos = "jdbc:mysql://localhost/Magesti?user=tp_labo&password=laboratorio";
            //String BaseDeDatos = "jdbc:mysql://localhost/Magesti?user=root&password=magesti2012";
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
