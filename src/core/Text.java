package core;

import static core.Element.AVAILABLE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.Serializable;

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
        //posX = posY = 0;
        mensaje = m;
        colorLetra = c;
        tamanio = t;
        
    }
    
    public Text(String m)
    {
        mensaje = m;
        tamanio = 50;
        
        posX=posY = 200;
        area = getArea();
        incline = 0;
    }
    
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        //g2.setColor(color);
        g2.rotate(incline, posX +  mensaje.length()*tamanio/2, posY+tamanio/2);
	g2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, tamanio));
	g2.drawChars(mensaje.toCharArray(), 0, mensaje.length(),posX, posY);
        //g2.drawString(mensaje, posX, posX);
        //g2.fillRect(posX, posY, (int)width, (int)height);
        //g2.setColor(lncolor);
        //g2.drawRect(posX, posY, (int)width, (int)height);
        /*if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgcolor));
        } else {
            g2.setColor(bgcolor);
        }*/
        //area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        //g2.fill(area);
        /*if(state!=AVAILABLE) {
            g2.setColor(Util.negative(lncolor));
        } else {
            g2.setColor(lncolor);
        }*/
        //g2.draw(area);
    }

    public void configure(Canvas canvas) {
        area = new Area(new java.awt.Rectangle(posX+ mensaje.length()*tamanio, posY + tamanio, mensaje.length()*tamanio, tamanio));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX +  mensaje.length()*tamanio/2, posY+tamanio/2);
        area.transform(rot);
        
    }
    public Area getArea()
    {
        area = new Area(new java.awt.Rectangle(posX, posY - tamanio, mensaje.length()*tamanio, tamanio));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX +  mensaje.length()*tamanio/2, posY+tamanio/2);
        area.transform(rot);
        return area;
    }
    
    public void doZoom(float escala)
    {
        this.tamanio *= (1+escala);  
        area = getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = (posY + tamanio/2) - e.getY();
        double X = (posX + mensaje.length()*tamanio/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        area = getArea();
    }
    
    public void move(int x, int y)
    {
        posX = (int)(x - mensaje.length()*tamanio/2);
        posY = (int)(y - tamanio/2);
        area = getArea();
    }
    
    
}
