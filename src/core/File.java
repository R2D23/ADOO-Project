
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
        
        
        //Mensaje para guardar(opCode, quienLoGuarda, [archivo, dueno], ft)
        String [] aux = {nombre, owner};
        Mensaje m = new Mensaje(ConexionServer.guardarArchivo, GUI.dibujante.nomUsuario, aux, ft);
        ConexionServer cs = new ConexionServer();
        
        if(!cs.establecerConexion())
        {ConexionServer.mostrarError(); return;}
        
        cs.enviarMensaje(m);//se envia mensaje de solicitud de guardar
        System.out.println("Se ha enviado la peticion de guardar");
        
        if(ft) //si es la primera vez, se debe recibir confirmacion
        {
            m = cs.recibirMensaje();
            System.out.println("Se ha recibido conf");
            if(!m.getConfirmacion())
            { JOptionPane.showMessageDialog(GUI.frame, "Ya ha creado un lienzo con ese nombre"); return;}
        }
        
        /*si no existe ningún archivo con ese nombre, se envia la lista de elementos*/
        /* se ponen todos los elementos como disponibles */
        
        Canvas.liberarElementos();
        ArrayList<Element> temporal = Canvas.compartidos;
        temporal.addAll(Canvas.elements);
        cs.enviarListaElementos(temporal);
        //cs.enviarListaElementos(Canvas.elements,ConexionServer.puertoEnvia);
        m = cs.recibirMensaje();
        if(!m.getConfirmacion())
            JOptionPane.showMessageDialog(GUI.frame, "Ha ocurrido un error al guardar", "Error", JOptionPane.INFORMATION_MESSAGE);
        else
        {
            if(ft)//Es la primera vez que se guarda, y se deben poner los valores en el panel de colaboradores
            {
                name = nombre;
                owner = GUI.dibujante.nomUsuario;
                Canvas.compartidos = (ArrayList<Element>)Canvas.elements.clone();
                GUI.getPanelColaboradores().actualizarValores(owner, name, "Editar");
                GUI.setTitle(name);
            }
            Canvas.compartidos.addAll(Canvas.elements);
            Canvas.elements = new ArrayList<>();
            Action.undoStack.clear();
            Action.redoStack.clear();
            JOptionPane.showMessageDialog(GUI.frame, "Guardado Exitoso");
        }   
        cs.cerrarConexion();
        System.out.println("se recibio confirmacion");
    }
    
    public void readFile(){
        
        JList listaArchivos = new JList();
        JScrollPane js = new JScrollPane(listaArchivos);
        JPanel jp = new JPanel();
        jp.add(js);
        Object [] options = {"Abrir","Cancelar"};
        listaArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ConexionServer cs = new ConexionServer();
        cs.establecerConexion();
        cs.enviarMensaje(new Mensaje(ConexionServer.abrirArchivo, GUI.dibujante.nomUsuario));
        Mensaje resp = cs.recibirMensaje();
        listaArchivos.setListData(resp.getOtherData());
        /*cs.enviarMensaje(new Mensaje(ConexionServer.enviarListaArchivos,GUI.dibujante.nomUsuario));
        Mensaje respuesta = cs.recibirMensaje();
        String [] valoresLista = new String[((ArrayList<String>)respuesta.getObject()).size()];
        ((ArrayList<String>)respuesta.getObject()).toArray(valoresLista);
        listaArchivos.setListData(valoresLista);*/
        
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
            cs.enviarMensaje(new Mensaje(listaArchivos.getSelectedValue().toString()));
            resp = cs.recibirMensaje(); //recibe el mensaje con los datos
            if(resp.getConfirmacion())
            {
                /* Actualizar listas de colaboradores */
                editores = new ArrayList<>();//se limpia la lista de editores
                observadores = new ArrayList<>();//se limpia la lista de observadores
                for(String s : resp.getOtherData())
                {
                    String [] aux = s.split("-");
                    if(aux[1].equals("11"))
                        editores.add(aux[0]);
                    else if(aux[1].equals("01"))
                        observadores.add(aux[0]);
                }
                
                /* Recibir la lista de elementos */
                ArrayList<Element> aux = cs.recibirListaElementos();
                System.out.println("Se ha recibido la lista");
                Canvas.compartidos = aux;
                for(Element e : Canvas.compartidos)
                    e.getArea();
                Canvas.repaint();
                
                
                /* Actualizar valores del archivo */
                String [] datosArchivo = listaArchivos.getSelectedValue().toString().split(" - ");
                name = datosArchivo[0];
                owner = datosArchivo[1];
                GUI.permiso = resp.getPermiso().equals("11");
                GUI.frame.setTitle(name + " - iDraw");
                GUI.getPanelColaboradores().actualizarValores(owner, name, (GUI.permiso ? "Editar" : "Observar"));
                GUI.getPanelColaboradores().actualizarListaColaboradores(editores, observadores);
            }
        }
        else
            cs.enviarMensaje(new Mensaje((String)null));
        
        cs.cerrarConexion();
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
    
    public void removeColaborators(String [] col)
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
        {
            System.out.println("colab : " + colab1[0] + " per : " + colab1[1]);
            if (colab1[1].equals("1")) 
                editores.add(colab1[0]);
             else 
                observadores.add(colab1[0]);
        }
        ArrayList<String> aux = (ArrayList<String>)editores.clone();
        aux.addAll(observadores);
        GUI.getPanelColaboradores().actualizarListaColaboradores(editores, observadores);
    }
    
    public ArrayList<String> getEditores()
    {return editores;}
    
    public ArrayList<String> getObservadores()
    {return observadores;}
}
