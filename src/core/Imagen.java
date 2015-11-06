
package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Angeles
 */

public class Imagen extends Element implements Serializable{
    int width;
    int height;
    String src;
    ImageIcon img;
    public Imagen(String s)
    {
        src = s;
        img = new ImageIcon(src);
        width = img.getIconWidth();
        height = img.getIconHeight();
        area = getArea();
        this.posX = 0;
        this.posY = 0;
        this.incline = 0;
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)(g.create()); 
        g2.rotate(incline, posX + width/2,posY + height/2);
        g2.drawImage(img.getImage(), posX, posY, width, height,null);
        //g2.rotate(0,0,0);
    }

    public void configure(Canvas canvas) {
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
    }

    @Override
    public Area getArea() {
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
        return area;
    }
    
    @Override
    public void doZoom(float escala)
    {
        this.height *= (1+escala);  
        this.width *= (1+escala);
        area = getArea();
    }

    public void rotate(java.awt.Point e) {
        double Y = (posY + height/2) - e.getY();
        double X = (posX + width/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        area = getArea();
    }
    
    public void move(int x, int y)
    {
        posX = x - width/2;
        posY = y - height/2;
        area = getArea();
    }
}