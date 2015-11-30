
package core;

import java.io.Serializable;

/**
 *
 * @author Angeles
 */
public class Mensaje implements Serializable{
    private int opCode; //indica que operacion se hara, o causa de error
    private String remitente;//usuario
    private String sData;//password,nombreArchivo
    boolean confirmacion;
    Object multiusos;//agregarCol
    
    public Mensaje(int oc, String r, String s)
    {
        opCode = oc;
        remitente = r;
        sData = s;
    }
    
    public Mensaje(boolean co)
    {confirmacion = co;}
    
    public Mensaje(boolean co, int opCode)
    {confirmacion = co;}
    
    public Mensaje(int oc, String r, Object o)
    {
        opCode = oc;
        remitente = r;
        multiusos = o;
    }
    
    public int getOpCode()
    {return opCode;}
    
    public String getRemitente()
    {return remitente;}

    public String getSData()
    {return sData;}    
    
    public boolean getConfirmacion()
    {return confirmacion;}
    
    public Object getObject()
    {return multiusos;}
}
