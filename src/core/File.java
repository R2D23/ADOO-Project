
package core;

import graphic.Canvas;
import graphic.GUI;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author LuisAguilar
 */
public class File {
    private String name;
    private String owner;
    private ArrayList<String> colaboradores;
    
    public File()
    {colaboradores = new ArrayList<>();}
    
   
    public void saveFile(){
        String nombre = JOptionPane.showInputDialog(GUI.frame,"Introduzca el nombre del archivo","Guardar Archivo",JOptionPane.PLAIN_MESSAGE);
        
        if(nombre == null)
            return;
        
        while(nombre.equals(""))
        {
            JOptionPane.showMessageDialog(GUI.frame, "Por favor, introduzca un nombre de archivo", "Sin nombre de archivo", JOptionPane.INFORMATION_MESSAGE);
            nombre = JOptionPane.showInputDialog(GUI.frame,"Introduzca el nombre del archivo","Guardar Archivo",JOptionPane.PLAIN_MESSAGE);
            if (nombre == null) return;
        }
        
        boolean ft = false;
        if(GUI.getFile().getName() == null)//si es la primera vez que se guarda
            ft = true;
        Mensaje m = new Mensaje(ConexionServer.guardarArchivo, GUI.dibujante.nomUsuario, nombre, ft,Canvas.elements);
        ConexionServer cs = new ConexionServer();
        cs.enviarMensaje(m);
        m = cs.recibirMensaje();
        cs.cerrarConexion();
        
        if(!m.getConfirmacion())
            JOptionPane.showMessageDialog(GUI.frame, "Error de Conexion con Base de Datos", "Error", JOptionPane.INFORMATION_MESSAGE);
        //JOptionPane.showi
        /*JFileChooser fcs = new JFileChooser();
        int opc = fcs.showSaveDialog(null);
        if(opc == JFileChooser.APPROVE_OPTION)
        {
            name = fcs.getSelectedFile().getName();
            try {
                ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(name+".lz"));
                salida.writeObject(Canvas.elements);
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        
    }
    
    public void readFile(){
        JFileChooser fco = new JFileChooser();
        int opc = fco.showOpenDialog(null);
        if(opc == JFileChooser.APPROVE_OPTION){
            try {
              ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fco.getSelectedFile().getPath()));
              Canvas.elements = (ArrayList<Element>)entrada.readObject();
              for(int i = 0; i < Canvas.elements.size(); i++)
                Canvas.elements.get(i).getArea();
              Canvas.repaint();
              name = fco.getSelectedFile().getName();
            } catch (Exception ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
            } 

           name = fco.getSelectedFile().getName();
        }
    }
    
    public void setName(String s)
    {name = s;}
    
    public String getName()
    {return name;}
    
    public String getOwner()
    {return owner;}
    
    public void setOwner(String s)
    {owner = s;}
    
    public void addColaborator(String col)
    {colaboradores.add(col);}
    
    public void removeColaborator(String col)
    {colaboradores.remove(col);}
}
