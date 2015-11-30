
package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Angeles
 */
public class PanelColaboradores extends JComponent{
    private JList<String> listaColaboradores;
    private JScrollPane js;
    private Area botonAgregar;
    private Area botonEliminar;
    
    public PanelColaboradores()
    {
        setLocation(0,0);
        setSize(new Dimension(300, 500));
        setVisible(false);
        
        listaColaboradores = new JList<>();
        listaColaboradores.setSize(200,200);
        listaColaboradores.setVisible(true);
        listaColaboradores.setLocation(50, 135);
        //String[] ele = {"usuario1", "usuario2", "usuario3", "skdfaj", "sdflakhjsdf","ñskdfljasdflñ", "ñslkajdfañsdlf", "sdñfkajsd", "asñdfkl", "sdñlkfjasd","ñsdklf"};
        //listaColaboradores.setListData(ele);

        js = new JScrollPane();
        js.setSize(listaColaboradores.getSize());
        js.setViewportView(listaColaboradores);
        js.setLocation(listaColaboradores.getLocation());
        this.add(js);

        botonAgregar = new Area(new java.awt.Rectangle(30, 380, 90, 90));
        botonEliminar = new Area(new java.awt.Rectangle(150, 380, 90, 90));
        
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
        
        String s = "Propietario:";
        g2.drawChars(s.toCharArray(), 0, s.length(), 30, 50);
        
        s = "Colaboradores:";
        g2.drawChars(s.toCharArray(), 0, s.length(), 30, 120);
        
        g2.setFont(new Font("Verdana", PLAIN, 16));
        s = GUI.getFile().getOwner();
        g2.drawChars(s.toCharArray(), 0, s.length(), 40, 80);

        MenuDrawer md = new MenuDrawer();
        md.paint(MenuDrawer.ADDUSER, g, botonAgregar);
        md.paint(MenuDrawer.DELETEUSER, g, botonEliminar);
        
    }
    
    public void activarBotones(Point p)
    {
        if(botonAgregar.contains(p))
        {
            /*try{
                Socket cl = new Socket(ConexionServer.SERVER, ConexionServer.PTOU);
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                ObjectOutputStream oos = new ObjectOutputStream(dos);
                Mensaje agregar = new Mensaje(ConexionServer.agregarColaborador,"GUI.getFile().getOwner()","");
                oos.writeObject(agregar);
                oos.flush();
                dos.close();
                cl.close();
            }
            catch(Exception e)
            {e.printStackTrace();}*/
            ConexionServer cs = new ConexionServer();
            //cs.establecerConexion();
            Mensaje agregar = new Mensaje(ConexionServer.agregarColaborador,GUI.getFile().getOwner(),"");
            cs.enviarMensaje(agregar);
            AdministradorColaboradores ac = new AdministradorColaboradores(0);
            ac.setVisible(true);

        }
        else if(botonEliminar.contains(p))
        {
            //JOptionPane.showMessageDialog(null, "Eliminar Colaboradores");
            ConexionServer cs = new ConexionServer();
            Mensaje eliminar = new Mensaje(ConexionServer.eliminarColaborador, GUI.getFile().getOwner(), "");
            cs.enviarMensaje(eliminar);
            AdministradorColaboradores ac = new AdministradorColaboradores(1);
            ac.setVisible(true);
        }
    }
}
