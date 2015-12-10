
package graphic;

import core.ConexionServer;
import core.Mensaje;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Angeles
 */
public class AdministradorColaboradores extends JDialog
{
    private final JList<String> listaColaboradores;
    private final JButton boton;
    private final JButton cancelar;
    private int accion;
    private String titulo;
    private final JScrollPane js;
    private final JComboBox permisos ;
    
    //agregarColaboradores = 0
    //eliminarColaboradores = 1
    
    public AdministradorColaboradores()
    {
        listaColaboradores = new JList<>();
        listaColaboradores.setLocation(30, 70);
        listaColaboradores.setSize(200,120);
        js = new JScrollPane();
        js.setSize(listaColaboradores.getSize());
        js.setViewportView(listaColaboradores);
        js.setLocation(listaColaboradores.getLocation());
        this.getContentPane().add(js);
        
        Object [] items ={"Editar", "Ver"};
        permisos = new JComboBox(items);
        permisos.setSize(100,30);
        permisos.setLocation(80, 200);
        getContentPane().add(permisos);
        
        boton = new JButton();
        boton.setSize(80, 30);
        boton.setLocation(20,250);
        
        getContentPane().add(boton);
        //getContentPane().add(listaColaboradores);
        boton.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)
                { 
                    if(accion == 0)
                        agregarColaboradores();
                    else if(accion == 1)
                        eliminarColaboradores();
                }
           });
        cancelar = new JButton("Cancelar");
        cancelar.setSize(80,30);
        cancelar.setLocation(140,250);
        cancelar.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)
                { cerrar(); }
           });
        
        
        getContentPane().add(cancelar);
        setSize(250, 300);
        setLocation(100,100);
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(Util.normalColor);
        setShape(new RoundRectangle2D.Double(0,0,250.0,300.0, 15,15));
        setLocationRelativeTo(GUI.pc);
        
        boton.setText(titulo);
    }
    
    
    public void cerrar()
    {this.setVisible(false);}
    
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setColor(Util.normalColor);
        g2.fill(getShape());
        
        g2.setColor(Color.black);
        Stroke s = new BasicStroke(5);
        g2.setStroke(new BasicStroke(5));
        g2.draw(new RoundRectangle2D.Double(0,0,249.0,299.0, 15,15));
        g2.fill(new RoundRectangle2D.Double(0,0,249.0,50, 15,15));
        
        g2.setFont(new Font("Verdana", BOLD, 16));
        g2.setColor(Color.white);
        String aux = titulo + " colaboradores";
        g2.drawChars(aux.toCharArray(), 0, aux.length(), 20, 30);
        //super.paint(g2);
        listaColaboradores.repaint();
        boton.repaint();
        cancelar.repaint();
        permisos.repaint();
        js.getVerticalScrollBar().repaint();
        
    }
    
    
    public void setVisible(boolean b, int a)
    {
        accion = a;
        setData();
        setLocationRelativeTo(GUI.getPanelColaboradores());
        super.setVisible(b);
    }
    
    private void setData()
    {
        if(accion == 0)
        {
            titulo = "Agregar";
            boton.setText("Agregar");
            obtenerUsuarios();
            permisos.setVisible(true);
        }
        else if(accion == 1)
        {
            titulo = "Eliminar";
            boton.setText("Eliminar");
            obtenerColaboradores();
            permisos.setVisible(false);
        }
    }
    
    private void obtenerUsuarios()
    {
        ConexionServer cs = new ConexionServer();
        Mensaje m = new Mensaje(ConexionServer.enviarListaUsuarios, "",GUI.getFile().getName());
        cs.enviarMensaje(m);
        m = cs.recibirMensaje();
        cs.cerrarConexion();
        java.util.ArrayList<String> al = (java.util.ArrayList<String>)m.getObject();
        al.remove(GUI.dibujante.nomUsuario);
        String [] aux = new String[al.size()];
        al.toArray(aux);
        listaColaboradores.setListData(aux);
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
        java.util.List<String> aux = listaColaboradores.getSelectedValuesList();
        String a[] = new String[aux.size()];
        String nomArch = GUI.getFile().getName();
        if(nomArch == null)
        {   JOptionPane.showMessageDialog(null, "Es necesario que se guarde el archivo"); return;}
        boolean permiso = permisos.getSelectedItem().equals("Editar");
        Mensaje m = new Mensaje(ConexionServer.agregarColaborador, nomArch, GUI.getFile().getOwner(),aux.toArray(a));
        m.setBoolean(permiso);
        ConexionServer cs  = new ConexionServer();
        cs.enviarMensaje(m);

        m = cs.recibirMensaje();
        if(m.getConfirmacion())
        {
            JOptionPane.showMessageDialog(null, "Colaboradores agregados");
            setVisible(false);
            GUI.getFile().addColaborators(a,permiso);
        }
        else
            JOptionPane.showMessageDialog(null, "No se han podido agregar los colaboradores");
    }
    
    private void eliminarColaboradores()
    {
        ConexionServer cs = new ConexionServer();
        String [] colaboradores = new String [listaColaboradores.getSelectedValuesList().size()];
        cs.enviarMensaje(new Mensaje(ConexionServer.eliminarColaborador,GUI.getFile().getName(),GUI.getFile().getOwner(),listaColaboradores.getSelectedValuesList().toArray(colaboradores)));
        Mensaje conf = cs.recibirMensaje();
        if(conf.getConfirmacion())
            JOptionPane.showMessageDialog(GUI.frame,"Colaboradores eliminados");
        else   
            JOptionPane.showMessageDialog(GUI.frame, "No se han podido eliminar los colaboradores");
        GUI.getFile().removeColaborators(colaboradores);
        setVisible(false);
    }
}
