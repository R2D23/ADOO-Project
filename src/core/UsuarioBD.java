
package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* @author Alexis */
public class UsuarioBD {
    private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}
    public boolean insertar(String txt, String txt1, String txt2,String txt3,String txt4 )
	{
		try {
			Statement sentenciaInsertar = this.con.createStatement();
			String aux = "insert into Usuario values('"+txt+"','"+txt1+"','"+txt2+"','"+txt3+"','"+txt4+"')";
			System.out.println(aux);
			sentenciaInsertar.execute(aux);		
			//this.con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
    
    public boolean buscar(String user, String pass)
	{
		boolean bandera=false;
		try {
			String igu="";
			String igu1="";
			String aux="select usuario, Contrasena from Usuario where usuario='"+user+"'";
			Statement sentenciaBuscar = this.con.createStatement();
			ResultSet r=sentenciaBuscar.executeQuery(aux);	
			while (r.next()) {
				igu=r.getString(1);
				igu1=r.getString(2);									
			}	
			if (igu.equals(user)&&igu1.equals(pass))
				bandera=true;
			//this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bandera;
	}
    
    public boolean buscar(String user)
	{
		boolean bandera=false;
		try {
			String igu="";
			String aux="select usuario from Usuario where usuario='"+user+"'";
			Statement sentenciaBuscar = this.con.createStatement();
			ResultSet r=sentenciaBuscar.executeQuery(aux);	
			while (r.next()) {
				igu=r.getString(1);												
			}	
			if (igu.equals(user))
				bandera=true;
			//this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bandera;
	}
    public boolean buscarcorreo(String mail)
	{
		boolean bandera=false;
		try {
			String igu="";
			String aux="select Correo from Usuario where Correo='"+mail+"'";
			Statement sentenciaBuscar = this.con.createStatement();
			ResultSet r=sentenciaBuscar.executeQuery(aux);	
			while (r.next()) {
				igu=r.getString(1);												
			}	
			if (igu.equals(mail))
				bandera=true;
			//this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bandera;
	}
    
}
