
package core;

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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author Angeles
 */
public class AdministradorColaboradores extends JDialog
{
    private JList<String> listaColaboradores;
    private JButton boton;
    private JButton cancelar;
    private int accion;
    private String titulo;
    
    //agregarColaboradores = 0
    //eliminarColaboradores = 1
    
    public AdministradorColaboradores(int a)
    {
        listaColaboradores = new JList<>();
        listaColaboradores.setLocation(20, 70);
        listaColaboradores.setSize(200,150);
        JScrollPane js = new JScrollPane();
        js.setSize(listaColaboradores.getSize());
        js.setViewportView(listaColaboradores);
        js.setLocation(listaColaboradores.getLocation());
        this.getContentPane().add(js);
        
        
        boton = new JButton();
        boton.setSize(80, 30);
        boton.setLocation(20,250);
        getContentPane().add(boton);
        //getContentPane().add(listaColaboradores);
        
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
        setLocation(30,100);
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(Util.normalColor);
        setShape(new RoundRectangle2D.Double(0,0,250.0,300.0, 15,15));
        accion = a;
        if(a == 0)
            titulo = "Agregar";
        if(a == 1)
            titulo = "Eliminar";
        
        boton.setText(titulo);
    }
    
    
    public void cerrar()
    {this.dispose();}
    
    
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
    }
    
    
}
