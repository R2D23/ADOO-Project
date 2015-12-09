
package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author Angeles
 */
public class PanelColaboradores extends JComponent{
    private final JList<String> listaColaboradores;
    private final JScrollPane js;
    private final Area botonAgregar;
    private final Area botonEliminar;
    private String owner;
    private String title;
    private String permisos;
    
    public PanelColaboradores()
    {
        setLocation(0,0);
        setSize(new Dimension(300, 500));
        setVisible(false);
        listaColaboradores = new JList<>();
        listaColaboradores.setSize(230,150);
        listaColaboradores.setVisible(true);
        listaColaboradores.setLocation(40, 245);
        listaColaboradores.setBackground(Util.normalColor);
        String[] ele = {"usuario1", "usuario2", "usuario3", "skdfaj", "sdflakhjsdf","ñskdfljasdflñ", "ñslkajdfañsdlf", "sdñfkajsd", "asñdfkl", "sdñlkfjasd","ñsdklf"};
        listaColaboradores.setListData(ele);
        
        if(title == null)
        {
            title = "El archivo no ha sido guardado";
            owner = "El archivo no ha sido guardado";
            String [] aux = {"No hay colaboradores"};
            listaColaboradores.setListData(aux);
            permisos = "El archivo no ha sido guardado";
        }
        else
        {
            title = GUI.getFile().getName();
            owner = GUI.getFile().getOwner();
            permisos = "Editar";//if(owner = )
        }
        
        
        //listaColaboradores.setEnabled(false);
        //listaColaboradores;

        js = new JScrollPane();
        js.setSize(listaColaboradores.getSize());
        js.setViewportView(listaColaboradores);
        js.setLocation(listaColaboradores.getLocation());
        this.add(js);

        botonAgregar = new Area(new java.awt.Rectangle(60, 400, 60, 60));
        botonEliminar = new Area(new java.awt.Rectangle(180, 400, 60, 60));
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            { activarBotones(e.getPoint());}
        });
        
    }
    
    @Override
    public void paint(Graphics g)
    {
        System.out.println("Dibujando componente");
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setColor(Util.normalColor);
        g2.fillRect(0, 0, 300, 500);
        g2.setColor(Color.black);
        g2.drawLine(0,0, this.getWidth(), 0);
        g2.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, 50);
        g2.drawLine(this.getWidth() - 1, 250, this.getWidth() - 1, 500);
        g2.drawLine(0,this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
        
        listaColaboradores.setBackground(Util.normalColor);
        listaColaboradores.repaint();
        js.getVerticalScrollBar().repaint();
        
        g2.setFont(new Font("Verdana", BOLD, 16));
        g2.drawString("Archivo", 30, 40);
        g2.drawString("Propietario",30,90);
        g2.drawString("Usuario", 30,140);
        g2.drawString("Permisos",30,190);
        g2.drawString("Colaboradores",30,240);
        
        
        g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        g2.drawString(GUI.dibujante.nomUsuario, 50, 165);
        g2.setColor(Color.blue);
        g2.drawString(title,50,65);
        g2.drawString(owner,50, 115);
        g2.drawString(permisos, 50, 215);
        MenuDrawer md = new MenuDrawer();
        md.paint(MenuDrawer.ADDUSER, g, botonAgregar);
        md.paint(MenuDrawer.DELETEUSER, g, botonEliminar);
        
    }
    
    public void activarBotones(Point p)
    {
        if(botonAgregar.contains(p))
        {
            AdministradorColaboradores ac = new AdministradorColaboradores(0);
            ac.setVisible(true);
        }
        else if(botonEliminar.contains(p))
        {
            //JOptionPane.showMessageDialog(null, "Eliminar Colaboradores");
            /*ConexionServer cs = new ConexionServer();
            Mensaje eliminar = new Mensaje(ConexionServer.eliminarColaborador, GUI.getFile().getOwner(), "");
            cs.enviarMensaje(eliminar);*/
            AdministradorColaboradores ac = new AdministradorColaboradores(1);
            ac.setVisible(true);
        }
    }
    
    public void actualizarValores(String owner, String nombre, String permisos)
    {this.owner = owner;    this.title = nombre; this.permisos = permisos;}
}
