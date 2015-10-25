/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* @author Alexis */
public class conexion {
    private Connection con;
    public Connection getCon()
	{
		return con;
	}
	public static void main(String[] args) 
	{
		new conexion().conectar("jdbc:mysql://localhost:3306/paint", "root", "root");
	}
    
    public Connection conectar(String url, String usuario, String password) 
	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url, usuario, password);
			System.out.println("Se realizo la conexion");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) 
		{
		}
		return con;
	}
    
    
}
