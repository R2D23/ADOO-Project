package core;

import graphic.Canvas;
import graphic.GUI;
import graphic.PanelConfig;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Angeles
 */
public class Text extends Element implements Serializable{
    String mensaje;
    Color colorLetra;
    int tamanio;
    
    public Text(String m, Color c, int t)
    {
        mensaje = m;
        colorLetra = c;
        tamanio = t;
        
    }
    
    public Text(String m)
    {
        super();
        mensaje = m;
        tamanio = 50;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        refreshArea();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(getInclination(), getPos().x +  mensaje.length()*tamanio/2, getPos().y+tamanio/2);
        g2.setColor(colorLetra);
	g2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, tamanio));
	g2.drawChars(mensaje.toCharArray(), 0, mensaje.length(),getPos().x,getPos().y);
        //g2.draw(area);
    }
    
    @Override
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.TEXT);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = new Object[4];
        valores[0] = tamanio;
        valores[1] = mensaje;
        valores[2] = colorLetra;
        pc.setValoresText(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Circulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresText();
            tamanio = (int)datos[0];
            mensaje = datos[1].toString();
            colorLetra = (java.awt.Color)datos[2];
        }
    }

 
    
    @Override
    public void refreshArea()
    {
        setArea(new Area(new java.awt.Rectangle(getPos().x, getPos().y - tamanio, mensaje.length()*tamanio/2, tamanio)));
        transformArea();
    }
    
    public static void create(java.awt.Point p)
    {
        PanelConfig pc = new PanelConfig(PanelConfig.TEXT);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Crear","Cancelar"};
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Añadir Texto", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresText();
            Text t = new Text(datos[1].toString(),(Color)datos[2],(int)datos[0]);
            t.move(p.x, p.y);
            Canvas.addElement(t);
            Canvas.repaint();
        }
    }
}
