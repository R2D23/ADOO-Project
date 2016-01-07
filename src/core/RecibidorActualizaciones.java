
package core;

import graphic.Canvas;
import graphic.GUI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Angeles
 */
public class RecibidorActualizaciones implements Runnable
{
    public static int puertoRecibeActualizaciones = 8887;
    private ServerSocket ss;
    private Socket cliente;
    private ObjectInputStream streamEntrada;
    private Mensaje recibido;
    
    @Override
    public void run()
    {
        this.iniciarRecibidor();
        System.out.println("Se inicio el recibidor");
        for(;;)
        {
            try
            {
                cliente = ss.accept();
                streamEntrada = new ObjectInputStream(cliente.getInputStream());
                
                recibido = (Mensaje)streamEntrada.readObject();
                System.out.println("Se ha recibido una notificacion");
                
                System.out.println(recibido.getOpCode());
                switch(recibido.getOpCode())
                {
                    case Notificador.notificarAgregacion :
                        notificarAgregacion();
                    break;
                    case Notificador.notificarEliminacion :    
                        notificarEliminacion();
                    break;
                    case Notificador.notificarActualizacion :
                        notificarActualizacion();
                    break;
                    case Notificador.notificarGuardado :
                        notificarGuardado();
                    break;
                }
            }
            catch(Exception e)
            {e.printStackTrace();}
            
        }
    
    }
    
    public void notificarAgregacion()
    {
        try
        {
            streamEntrada.close();
            cliente.close();
            String mensaje = "Has sido invitado a colaborar en el archivo " + recibido.getOtherData()[0]
                    + " de " + recibido.getOtherData()[1] + " como " + (recibido.getPermiso().equals("11")? "editor" : "observador")
                    + "\nPuedes acceder a este archivo a través del menú abrir";
            JOptionPane.showMessageDialog(GUI.frame, mensaje);
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
    
    public void notificarEliminacion()
    {
        try
        {
            streamEntrada.close();
            cliente.close();
           
            if((GUI.getFile().getName() == null) || !GUI.getFile().getName().equals(recibido.getArchivo()) || !GUI.getFile().getOwner().equals(recibido.getOwner())) // si no es el archivo sobre el que está trabjando
            {
                String mensaje = "Has removido como colaborador del archivo " + recibido.getArchivo() + " de " + recibido.getOwner()
                    + "\nYa no puedes acceder a este archivo";

                JOptionPane.showMessageDialog(GUI.frame, mensaje);

            }
            else /* Si el archivo del que le quietaron el permiso es en el que está trabajando, se debe cerrar el archivo */
            {
                String mensaje = "Has sido removido como colaborador de este archivo. Ya no tienes acceso a los elementos compartidos"
                        + " pero puedes guardar tus archivos temporales en un nuevo archivo. ¿Guardar elementos temporales?";
                int op = JOptionPane.showConfirmDialog(GUI.frame, mensaje, "Has sido eliminado", JOptionPane.YES_NO_OPTION);
                if(op == JOptionPane.OK_OPTION)
                {
                    GUI.getFile().setName(null);
                    Canvas.compartidos = Canvas.elements;
                    GUI.getFile().saveFile();
                }
                else /* Si no lo quiere guardar */
                {
                    Canvas.compartidos = new ArrayList<>();
                    Canvas.elements = new ArrayList<>();
                    GUI.setFile(new File());
                }
            }
            Canvas.repaint();
        }
        catch(Exception e)
        {e.printStackTrace();}
    
    }
    
    public void notificarActualizacion()
    {
        System.out.println("Se ha recibido una actualizacion de seleccionar/deseleccionar");
        try
        {
            Canvas.lockCompartidos.lock();
                Element aux = recibirElemento(recibido.getTamFile());
                int indice = Integer.parseInt(recibido.getOtherData()[0]);
                Action.createAction(Action.TRANSFORM, indice, true);
                Action.undoStack.lastElement().setNext(aux);
                Canvas.compartidos.set(indice, aux);
                

        }
        finally
        {   Canvas.lockCompartidos.unlock(); }
        
        try
        { streamEntrada.close(); cliente.close();}
        catch(Exception e){}
        
        Canvas.repaint();
    }
    
    public void notificarGuardado()
    {
        String msj = "El archivo " + GUI.getFile().getName() + " ha sido guardado por " + recibido.getUsuario()
                + "\nEl archivo se actualizará a los cambios";
        JOptionPane.showMessageDialog(GUI.frame, msj);
        Canvas.lockCompartidos.lock();
        try
        {
            Canvas.compartidos = recibirListaElementos(recibido.getTamFile());
            System.out.println("Se ha recibido la lista");
        }
        finally
        {   Canvas.lockCompartidos.unlock();    }
        
        try
        { streamEntrada.close(); cliente.close();}
        catch(Exception e){}
        Canvas.repaint();
    }
    
    public void iniciarRecibidor()
    {
        try
        {ss = new ServerSocket(puertoRecibeActualizaciones,5,InetAddress.getLocalHost());}
        catch(Exception e)
        {e.printStackTrace();}
    }
    
    public Element recibirElemento(int tam)
    {
        try
        {
            System.out.println("Tamaño de lo que va a recibir : " + tam);
            int recibidos = 0;
            byte [] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while(recibidos < tam)
            {
                int n = streamEntrada.read(buf);
                baos.write(buf);
                recibidos += n;
            }
            streamEntrada.close();
            cliente.close();
            
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            return (Element)ois.readObject();
        }
        catch(Exception e)
        {e.printStackTrace();}
        return null;
    }
    
    
    public ArrayList<Element> recibirListaElementos(int tam)
    {
        try
        {
            System.out.println("Tamaño de lo que va a recibir : " + tam);
            int recibidos = 0;
            byte [] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while(recibidos < tam)
            {
                int n = streamEntrada.read(buf);
                baos.write(buf);
                recibidos += n;
            }
            streamEntrada.close();
            cliente.close();
            
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            return (ArrayList<Element>)ois.readObject();
        }
        catch(Exception e)
        {e.printStackTrace();}
        return null;
    }
}
