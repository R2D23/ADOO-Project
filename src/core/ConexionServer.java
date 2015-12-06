
package core;
import java.io.*;
import java.net.*;
/**
 *
 * @author Angeles
 */
public class ConexionServer {
    public static final String SERVER = "192.168.0.9";
    public static final int puertoRecibe = 5678;
    public static final int puertoEnvia = 7777;
    
    public static final int iniciarSesion = 0;
    public static final int agregarColaborador = 1;
    public static final int eliminarColaborador = 2;
    public static final int guardarArchivo = 3;
    public static final int abrirArchivo = 4;
    public static final int modificaciones = 5;
    public static final int registrarUsuario = 6;
    public static final short enviarListaUsuarios = 7;
    public static final short enviarListaColaboradores = 8;
    
    
    
    private DatagramSocket cl = null;
    private InetAddress dirServer = null;
    
    
    public ConexionServer()
    {
        try
        {
            cl = new DatagramSocket();
            dirServer = InetAddress.getByName(SERVER);
            cl.setReuseAddress(true);
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
    /*public void establecerConexion()
    {
        try
        { cl = new Socket(ConexionServer.SERVER, ConexionServer.PTOU);}
        catch(Exception e)
        {e.printStackTrace();}
    }*/
    
    public void enviarMensaje(Mensaje m)
    {
        try
        {
            //DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            cl = new DatagramSocket();
            
            //cl.setReuseAddress(true);
            //dirServer = InetAddress.getByName(SERVER);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(m);
            oos.flush();
            
            byte [] buf = baos.toByteArray();
            
            DatagramPacket p = new DatagramPacket(buf, buf.length, dirServer, puertoEnvia);
            cl.send(p);
            cl.close();
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
    
    public Mensaje recibirMensaje()
    {
        try
        {
            cl = new DatagramSocket(puertoRecibe);
            //cl.setReuseAddress(true);
            DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
            cl.receive(p);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(p.getData()));
            Mensaje recibido = (Mensaje)ois.readObject();
            cl.close();
            return recibido;
        }
        catch(Exception e)
        { e.printStackTrace();}
        
        return null;
    }
    
    public void cerrarConexion()
    {cl.close();}
    
}
