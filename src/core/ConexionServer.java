
package core;
import graphic.GUI;
import java.io.*;
import java.net.*;
/**
 *
 * @author Angeles
 */

public class ConexionServer{
    public static final String SERVER = "192.168.1.67";
    public static final int puertoRecibe = 5678;
    public static final int puertoEnvia = 7777;
    
    
//codigos de operacion
    public static final char iniciarSesion = 'a';
    public static final char registrarUsuario = 'b';
    public static final char abrirArchivo = 'c';
    public static final char guardarArchivo = 'd';
    public static final char agregarColaboradores = 'e';
    public static final char eliminarColaboradores = 'f';
    public static final char cerrarSesion = 'g';
    
    
    //codigos de error
    public static final char usuarioYaExiste = 'a';
    public static final char correoYaExiste = 'b';
    public static final char errorEnBD = 'c';

    //private DatagramSocket cl = null;
    private Socket cl = null;
    //private InetAddress dirServer = null;
    private ObjectInputStream streamEntrada;
    private ObjectOutputStream streamSalida;
    
    
    public ConexionServer() /* Para cuando se debe establecer una conexión TCP */
    {
    }
    
    public boolean establecerConexion()
    {
        try
        { 
            cl = new Socket(ConexionServer.SERVER, ConexionServer.puertoEnvia); 
            streamSalida = new ObjectOutputStream(cl.getOutputStream());
            streamEntrada = new ObjectInputStream(cl.getInputStream());
            System.out.println("Conectado a :" + cl.getRemoteSocketAddress());
            return true;}
        catch(Exception e)
        {e.printStackTrace(); return false;}
    }
    
    public void enviarMensaje(Mensaje m)
    {
        try
        {
            streamSalida.writeObject(m);
            streamSalida.flush();
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
    
    public Mensaje recibirMensaje()
    {
        try
        {
            System.out.println("a punto de recibirse algo");
            Mensaje recibido = (Mensaje)streamEntrada.readObject();
            System.out.println("Se ha recibido algo");
            return recibido;
        }
        catch(Exception e)
        { e.printStackTrace();}
        
        return null;
    }
    
    public boolean cerrarConexion()
    {
        try
        {
            streamEntrada.close();
            streamSalida.close();
            cl.close();
            System.out.println("Se ha cerrado la conexion");
            return true;
        }
        catch(IOException e)
       {e.printStackTrace(); return false;}
    }
    
    public void enviarListaElementos(java.util.ArrayList<Element> lista)
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(lista);
            oos.flush();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            streamSalida.writeInt(baos.toByteArray().length);
            streamSalida.flush();
            System.out.println("Se ha enviado el tamaño de la lista : " + baos.toByteArray().length);

            int tam = baos.toByteArray().length;
            int enviados = 0;
            byte [] buf = new byte [1024];
            while(enviados < tam)
            {
                int n = bais.read(buf);
                System.out.println("Se han enviado : " + n);
                streamSalida.write(buf,0,n);
                streamSalida.flush();
                enviados += n;
            }

        }
        catch(Exception e)
        {e.printStackTrace();}
    }
    
    public java.util.ArrayList<Element> recibirListaElementos()
    {
        try
        {
            long tam = streamEntrada.readLong();
            System.out.println("Tamaño de la lista : " + tam);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            byte [] buf = new byte[1024];
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);

            int recibidos = 0;
            while(recibidos < tam)
            {
                int n = streamEntrada.read(buf);
                baos.write(buf,0,n);
                
                recibidos += n;
                System.out.println("Se han recibido " + n);
            }
            
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            return (java.util.ArrayList<Element>)ois.readObject();
        }
        catch(Exception e)
        { e.printStackTrace();}
        
        return null;
    }
    
    public void conectarNotificacion()
    {
        try
        {
            cl = new Socket(ConexionServer.SERVER, RecibidorActualizaciones.puertoRecibeActualizaciones); 
            System.out.println("Conexion recibidor establecida");
            streamSalida = new ObjectOutputStream(cl.getOutputStream());
            streamEntrada = new ObjectInputStream(cl.getInputStream());
        }
        catch(Exception e)
        { e.printStackTrace();}
    }

    
    public static void mostrarError()
    {javax.swing.JOptionPane.showMessageDialog(GUI.frame, "No se ha podido conectar al servidor");}
}
