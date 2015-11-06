
package core;

import core.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author douxm_000
 */
public class Circle extends Figure{

    double radio;
    
    public Circle(double radio) {
        this.radio = radio;
    }
    
    public Circle() {}
    
   
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        try{
            if(state!=AVAILABLE) {
                g2.setColor(Util.negative(bgcolor));
            } else {
                g2.setColor(bgcolor);
            }
            g2.fill(area);
            if(state!=AVAILABLE) {
                g2.setColor(Util.negative(lncolor));
            } else {
                g2.setColor(lncolor);
            }
            g2.draw(area);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void configure(Canvas canvas) {
        area = new Area(new Ellipse2D.Double(posX, posY, (int)radio,(int)radio));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+radio/2, posY+radio/2);
        area.transform(rot);
    }
    
    public Area getArea()
    {
        area = new Area(new Ellipse2D.Double(posX, posY, (int)radio,(int)radio));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+radio/2, posY+radio/2);
        area.transform(rot);
        return area;
    }
    
    public void doZoom(float escala)
    {   
        super.doZoom(escala);
        this.radio *= (1+escala);
        area = getArea();
    }
    
     public void rotate(Point e) {
        double Y = (posY + radio/2) - e.getY();
        double X = (posX + radio/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        area = getArea();
        
    }
     
    public void move(int x, int y)
    {
        posX = (int)(x - radio/2);
        posY = (int)(y - radio/2);
        area = getArea();
    }
}
