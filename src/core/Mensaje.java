
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
    boolean confirmacion;//indica si se a podido realizar la operacion, al guardar, indica si el usuario es el dueño
    Object multiusos;//agregarCol
    
    public Mensaje(int oc, String r, String s)
    {
        opCode = oc;
        remitente = r;
        sData = s;
    }
    
    public Mensaje(int oc, String t)
    {
        opCode = oc;
        remitente = t;
    }
    
    public Mensaje(boolean b, Object o)
    {confirmacion = b; multiusos = o;}
    
    public Mensaje(boolean co)//Este es para cuando se envian confirmaciones
    {confirmacion = co;}
    
    public Mensaje(boolean co, int op)//este para cuando se envian confirmaciones con causas de error
    {confirmacion = co; opCode = op;}
    
    public Mensaje(boolean b, String r, String s)
    {confirmacion = b; remitente = r; sData = s;}
    
    public Mensaje(int oc, String r, Object o)//Registro, el objeto son los datos extras, tambien se usa para obtener la lista de archivos
    {
        opCode = oc;
        remitente = r;
        multiusos = o;
    }
    
    
    public Mensaje(int oc, String r, String nom, boolean ft)
    {
        opCode = oc; remitente = r; sData = nom; confirmacion = ft;
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
