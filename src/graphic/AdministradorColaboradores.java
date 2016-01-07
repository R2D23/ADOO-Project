
package graphic;

import core.ConexionServer;
import core.Mensaje;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

/**
 *
 * @author Angeles
 */
public class AdministradorColaboradores
{
    private final JList<String> listaColaboradores;
    private int accion;
    private final JScrollPane js;
    private final JComboBox permisos ;
    private ConexionServer cs;
    private final JPanel panel;
    private final Object [] options;
    //agregarColaboradores = 0
    //eliminarColaboradores = 1
    
    public AdministradorColaboradores()
    {
        panel = new JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        listaColaboradores = new JList<>();
        listaColaboradores.setLocation(30, 70);
        listaColaboradores.setSize(200,120);
        js = new JScrollPane();
        js.setSize(listaColaboradores.getSize());
        js.setViewportView(listaColaboradores);
        js.setLocation(listaColaboradores.getLocation());
        panel.add(js);
        
        Object [] items ={"Editar", "Ver"};
        permisos = new JComboBox(items);
        permisos.setSize(100,30);
        permisos.setLocation(80, 200);
        panel.add(permisos);
        
        
        panel.setSize(200, 300);
        panel.setBackground(Util.normalColor);
        options = new Object[2];
        options[1] = "Cancelar";
        
    }
    
    

    
    
    public void mostrar(int a)
    {
        accion = a;
        if(accion == 0)
        {
            obtenerUsuarios();
            options[0] = "Agregar";
            permisos.setVisible(true);
            int op = JOptionPane.showOptionDialog(GUI.pc,
                 panel,
                 "Agregar Colaboradores",
                 JOptionPane.YES_NO_CANCEL_OPTION,
                 JOptionPane.PLAIN_MESSAGE,
                 null,
                 options,
                 null);
            
            if(op == 0)
                agregarColaboradores();
            else
                cs.enviarMensaje(new Mensaje((String[])null));
            cs.cerrarConexion();
        }
        else if(accion == 1)
        {
            obtenerColaboradores();
            options[0] = "Eliminar";
            permisos.setVisible(false);
            int op = JOptionPane.showOptionDialog(GUI.pc,
                 panel,
                 "Eliminar Colaboradores",
                 JOptionPane.YES_NO_CANCEL_OPTION,
                 JOptionPane.PLAIN_MESSAGE,
                 null,
                 options,
                 null);
            if(op == 0)
                eliminarColaboradores();
        }
    }
    

    
    private void obtenerUsuarios()
    {
        cs = new ConexionServer();
        cs.establecerConexion();
        Mensaje m = new Mensaje(ConexionServer.agregarColaboradores, GUI.getFile().getOwner() ,GUI.getFile().getName());
        cs.enviarMensaje(m);
        m = cs.recibirMensaje();
        listaColaboradores.setListData(m.getOtherData());
    }
    
    private void obtenerColaboradores()
    {
        ArrayList<String> aux = new ArrayList<>();
        for(String s : GUI.getFile().getEditores())
            aux.add(s);
        for(String s : GUI.getFile().getObservadores())
            aux.add(s);
        
        listaColaboradores.setListData(aux.toArray(new String[aux.size()]));
    }
    
    
    private void agregarColaboradores()
    {
        
        String nomArch = GUI.getFile().getName();
        if(nomArch == null)
        {   JOptionPane.showMessageDialog(null, "Es necesario que se guarde el archivo"); return;}
        
        java.util.List<String> aux = listaColaboradores.getSelectedValuesList();
        String usuariosPorAgregar[] = new String[aux.size()];
        aux.toArray(usuariosPorAgregar);
        String permiso = permisos.getSelectedItem().equals("Editar") ? "11" : "01";
        
        cs.enviarMensaje(new Mensaje(usuariosPorAgregar, permiso));
        
        Mensaje m = cs.recibirMensaje();
        if(m.getConfirmacion())
        {
            JOptionPane.showMessageDialog(null, "Colaboradores agregados");
            //setVisible(false);
            GUI.getFile().addColaborators(usuariosPorAgregar, permiso.equals("11"));
        }
        else
            JOptionPane.showMessageDialog(null, "No se han podido agregar los colaboradores");
    }
    
    private void eliminarColaboradores()
    {
        cs = new ConexionServer();
        if(!cs.establecerConexion())
        {ConexionServer.mostrarError(); return;}
        
        String [] colaboradores = new String [listaColaboradores.getSelectedValuesList().size()];
        listaColaboradores.getSelectedValuesList().toArray(colaboradores);
        for(String s : colaboradores)
            System.out.println("Colaborador a eliminar " + s);
        cs.enviarMensaje(new Mensaje(ConexionServer.eliminarColaboradores,GUI.getFile().getName(), GUI.getFile().getOwner(), colaboradores));
        
        Mensaje conf = cs.recibirMensaje();
        if(conf.getConfirmacion())
        {
            JOptionPane.showMessageDialog(GUI.frame,"Colaboradores eliminados");
            GUI.getFile().removeColaborators(colaboradores);
        }
        else   
            JOptionPane.showMessageDialog(GUI.frame, "No se han podido eliminar los colaboradores");
        cs.cerrarConexion();
    }
}
