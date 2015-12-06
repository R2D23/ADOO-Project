package core;

import static core.Element.AVAILABLE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
    
    public Text(String m){
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
	g2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, tamanio));
	g2.drawChars(mensaje.toCharArray(), 0, mensaje.length(),getPos().x, getPos().y);
        //g2.draw(area);
    }

    @Override
    public void refreshArea() {
        setArea(new Area(new java.awt.Rectangle(getPos().x, getPos().y - tamanio, mensaje.length()*tamanio, tamanio)));
	transformArea();
    }
}
