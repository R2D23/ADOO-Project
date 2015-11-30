
package core;

import java.io.Serializable;

/**
 *
 * @author Angeles
 */
public class Mensaje implements Serializable{
    private int opCode;
    private String remitente;
    private String sData;
    boolean confirmacion;
    
    public Mensaje(int oc, String r, String s)
    {
        opCode = oc;
        remitente = r;
        sData = s;
    }
    
    public Mensaje(boolean co)
    {confirmacion = co;}
    
    public int getOpCode()
    {return opCode;}
    
    public String getRemitente()
    {return remitente;}

    public String getSData()
    {return sData;}    
    
    public boolean getConfirmacion()
    {return confirmacion;}
}
