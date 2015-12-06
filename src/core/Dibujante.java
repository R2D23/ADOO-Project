package core;

/**
 *
 * @author Angeles
 */
public class Dibujante {
    public String nomUsuario;
    private String password;
    
    public Dibujante(String nom)
    {nomUsuario = nom;}
    
    public Dibujante()
    {}
    public void changePassword(String p)
    {password = p;}
    
    public String getPassword()
    {return password;}
    
    public void setNomUsuario(String s)
    {nomUsuario = s;}
}
