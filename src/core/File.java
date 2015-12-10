
package core;

import graphic.Canvas;
import graphic.GUI;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author LuisAguilar
 */
public class File {
    private String name;
    private String owner;
    private ArrayList<String> editores;//Los colaboradores con permiso de editar
    private ArrayList<String> observadores;//Los colaboradores sin permiso de editar
    
    public File()
    {name = null; owner = null; editores = new ArrayList<>();   observadores = new ArrayList<>();}
    
   
    public void saveFile(){
        boolean ft = false;
        String nombre = name;
        if(GUI.getFile().getName() == null)//si es la primera vez que se guarda
        {
            ft = true;
            nombre = JOptionPane.showInputDialog(GUI.frame,"Introduzca el nombre del archivo","Guardar Archivo",JOptionPane.PLAIN_MESSAGE);
        
            if(nombre == null)//si dio cancelar
            return;
        
            while(nombre.equals(""))//si dio aceptar con un nombre en blanco
            {
                JOptionPane.showMessageDialog(GUI.frame, "Por favor, introduzca un nombre de archivo", "Sin nombre de archivo", JOptionPane.INFORMATION_MESSAGE);
                nombre = JOptionPane.showInputDialog(GUI.frame,"Introduzca el nombre del archivo","Guardar Archivo",JOptionPane.PLAIN_MESSAGE);
                if (nombre == null) return;
            }
        }
        
        
        //Mensaje para guardar(opCode, el dueño, el nombre, si es la primera vez que se guarda)
        Mensaje m = new Mensaje(ConexionServer.guardarArchivo, GUI.dibujante.nomUsuario, nombre, ft);
        System.out.println("Se ha enviado la peticion de guardar");
        ConexionServer cs = new ConexionServer();
        cs.enviarMensaje(m);
        cs.enviarListaElementos(Canvas.elements);
        m = cs.recibirMensaje();
        cs.cerrarConexion();
        System.out.println("se recibio confirmacion");
        if(!m.getConfirmacion())
            switch(m.getOpCode())
            {
                case 0 :
                    JOptionPane.showMessageDialog(GUI.frame, "Error de Conexion con Base de Datos", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 1 :
                    JOptionPane.showMessageDialog(GUI.frame, "Ya ha creado un lienzo con ese nombre", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        else
        {
            if(ft)//Es la primera vez que se guarda, y se deben poner los valores en el panel de colaboradores
            {
                name = nombre;
                owner = GUI.dibujante.nomUsuario;
                GUI.getPanelColaboradores().actualizarValores(owner, name, "Editar");
            }
            JOptionPane.showMessageDialog(GUI.frame, "Guardado Exitoso");
        }
        
        
    }
    
    public void readFile(){
        
        JList listaArchivos = new JList();
        JScrollPane js = new JScrollPane(listaArchivos);
        JPanel jp = new JPanel();
        jp.add(js);
        Object [] options = {"Abrir","Cancelar"};
        listaArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ConexionServer cs = new ConexionServer();
        cs.enviarMensaje(new Mensaje(ConexionServer.enviarListaArchivos,GUI.dibujante.nomUsuario));
        Mensaje respuesta = cs.recibirMensaje();
        String [] valoresLista = new String[((ArrayList<String>)respuesta.getObject()).size()];
        ((ArrayList<String>)respuesta.getObject()).toArray(valoresLista);
        listaArchivos.setListData(valoresLista);
        
        int op = JOptionPane.showOptionDialog(GUI.frame,
                 jp,
                 "Abrir Archivo",
                 JOptionPane.YES_NO_CANCEL_OPTION,
                 JOptionPane.PLAIN_MESSAGE,
                 null,
                 options,
                 null);
        
        if(op == 0)//si se dio abrir
        {
            System.out.println(listaArchivos.getSelectedValue());
            cs.enviarMensaje(new Mensaje(ConexionServer.abrirArchivo,GUI.dibujante.nomUsuario, listaArchivos.getSelectedValue().toString()));
            respuesta = cs.recibirMensaje();
            try{Thread.sleep(1);}catch(Exception e){e.printStackTrace();}
            ArrayList<Element> aux = cs.recibirListaElementos();
            if(respuesta.getConfirmacion())
            {
                Canvas.elements = aux;
                for(Element e : Canvas.elements)
                    e.getArea();

                Canvas.repaint();
                owner = respuesta.getRemitente();
                name = listaArchivos.getSelectedValue().toString();
                editores = new ArrayList<>();
                observadores = new ArrayList<>();
                GUI.permiso = owner.equals(GUI.dibujante.nomUsuario) ? "Editar" : (respuesta.getSData().equals("1") ? "Editar" : "Observar");
                GUI.frame.setTitle(name + " - iDraw");
                GUI.getPanelColaboradores().actualizarValores(owner, name, GUI.permiso);
                definirColaboradores((String [][])respuesta.getObject());
            }
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
    
    public void addColaborators(String [] cols, boolean permiso)
    {
        if(permiso)
            for(String s : cols)
                editores.add(s);
        else
            for(String s : cols)
                observadores.add(s);
        
        GUI.getPanelColaboradores().actualizarListaColaboradores(editores, observadores);
    }
    
    public void removeColaborators(String []col)
    {
        for(String s : col)
            editores.remove(s);
        for(String s : col)
            observadores.remove(s);
        GUI.getPanelColaboradores().actualizarListaColaboradores(editores, observadores);
    }
    
    public void definirColaboradores(String[][] colab)
    {
        for (String[] colab1 : colab) 
            if (colab1[1].equals("1")) 
                editores.add(colab1[0]);
             else 
                observadores.add(colab1[0]);
        
        ArrayList<String> aux = (ArrayList<String>)editores.clone();
        aux.addAll(observadores);
        GUI.getPanelColaboradores().actualizarListaColaboradores(editores, observadores);
    }
    
    public ArrayList<String> getEditores()
    {return editores;}
    
    public ArrayList<String> getObservadores()
    {return observadores;}
}
