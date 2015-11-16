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
    
    public Text(String m)
    {
        mensaje = m;
        tamanio = 50;
        incline = 0;
        posX=posY = 200;
        getArea();
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(incline, posX +  mensaje.length()*tamanio/2, posY+tamanio/2);
	g2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, tamanio));
	g2.drawChars(mensaje.toCharArray(), 0, mensaje.length(),posX, posY);
        //g2.draw(area);
    }

    @Override
    public void getArea()
    {
        area = new Area(new java.awt.Rectangle(posX, posY - tamanio, mensaje.length()*tamanio, tamanio));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX +  mensaje.length()*tamanio/2, posY+tamanio/2);
        area.transform(rot);
    }
    
    public void doZoom(float escala)
    {
        this.tamanio *= (1+escala);  
        getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = (posY + tamanio/2) - e.getY();
        double X = (posX + mensaje.length()*tamanio/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }
}
