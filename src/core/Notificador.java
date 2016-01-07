
package core;

import graphic.Canvas;
import graphic.GUI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Angeles
 */
/* 
    Notifica al servidor cuando:
        - se agrega un colaborador
        - se elimina un colaborador
        - se guarda el archivo
        - se bloquea un elemento compartido
        - se desbloquea un elemento compartido
*/
public class Notificador implements Runnable{
    
    public static final int puertoEnviaActualizacion = 8888;
    public static final char notificarGuardado = 'h';
    public static final char notificarAgregacion = 'i';
    public static final char notificarEliminacion = 'j';
    public static final char notificarActualizacion = 'k';
    
    Element cambiado;
    
    public Notificador(Element e)
    {cambiado = e;}
    
    
    @Override
    public void run()
    {
        enviarActualizacion();
    }
    
    
    
    public void enviarActualizacion()
    {
        System.out.println("Enviando actualización");
        try
        {
            InetAddress dirServer = InetAddress.getByName(ConexionServer.SERVER);
            System.out.println("Server : "+ ConexionServer.SERVER);
            Canvas.lockCompartidos.lock();
                    
                    
                    System.out.println("Se está conectando a " + dirServer +":" + puertoEnviaActualizacion);
                    Socket cl = new Socket(dirServer, puertoEnviaActualizacion); 
                    System.out.println("Se ha establecido conexión con el recibidor");
                    ObjectOutputStream streamSalida = new ObjectOutputStream(cl.getOutputStream());
                    
                    
                    /* Se escribe en un arreglo de bytes el elemento que ha sido seleccionado */
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(cambiado);
                    oos.flush();
                    
                    /* Se mandan los detalles de la actualizacion */
                    String [] datosArchivo = {GUI.getFile().getName(),GUI.getFile().getOwner(),Canvas.compartidos.indexOf(cambiado) + ""};
                    Mensaje m = new Mensaje(Notificador.notificarActualizacion, GUI.dibujante.nomUsuario, baos.toByteArray().length, datosArchivo);
                    streamSalida.writeObject(m);
                    streamSalida.flush();
                    System.out.println("Se ha enviado el tamaño de la lista");
                    
                    int tam = baos.toByteArray().length;
                    int enviados = 0;
                    byte [] buf = new byte[1024];
                    System.out.println("Tamaño de lo que se va a enviar : " + tam);
                    
                    
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                    
                    while(enviados < tam)
                    {
                        int n = bais.read(buf);
                        streamSalida.write(buf, 0, n);
                        streamSalida.flush();
                        System.out.println("Se han notificado : " + n);
                        enviados += n;
                    }
                    
                    /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(Canvas.compartidos);
                    oos.flush();
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                    
                    
                    
                    String [] datosArchivo = {GUI.getFile().getName(),GUI.getFile().getOwner()};
                    Mensaje m = new Mensaje(Notificador.notificarActualizacion, GUI.dibujante.nomUsuario, baos.toByteArray().length, datosArchivo);
                    streamSalida.writeObject(m);
                    streamSalida.flush();
                    System.out.println("Se ha enviado el tamaño de la lista");
                    
                    int tam = baos.toByteArray().length;
                    int enviados = 0;
                    byte [] buf = new byte[1024];
                    System.out.println("Tamaño de lo que se va a enviar : " + tam);
                           
                    while(enviados < tam)
                    {
                        int n = bais.read(buf);
                        streamSalida.write(buf, 0, n);
                        streamSalida.flush();
                        System.out.println("Se han notificado : " + n);
                        enviados += n;
                    }
                    
                    /*ois = new ObjectOutputStream(cl.getOutputStream());*/
                    //String [] datosArchivo = {GUI.getFile().getName(),GUI.getFile().getOwner()};
                    //Mensaje m = new Mensaje(Notificador.notificarActualizacion, GUI.dibujante.nomUsuario, baos.toByteArray().length, datosArchivo);
                    //ois.writeObject(m);
                    //ois.flush();
                    
                    System.out.println("Se ha enviado notificacion 2");
           
        }
        catch(Exception e)
        {e.printStackTrace();}
        finally
        {Canvas.lockCompartidos.unlock();}
    }
    
    
    
    

}
