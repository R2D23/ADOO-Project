
package core;

import core.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
            /*g2.setColor(bgcolor);
            g2.fillOval(posX, posY, (int)radio, (int)radio);
            g2.setColor(lncolor);
            g2.drawOval(posX, posY, (int)radio, (int)radio);
            if(state!=AVAILABLE) {
                g2.setColor(Util.negative(bgcolor));
                g2.fill(area);
            }*/
            if(state!=AVAILABLE) {
                g2.setColor(Util.negative(bgcolor));
            } else {
                g2.setColor(bgcolor);
            }
            area = new Area(new Ellipse2D.Double(posX, posY, (int)radio,(int)radio));
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

    @Override
    public void configure(Canvas canvas) {
        area = new Area(new Ellipse2D.Double(posX, posY, (int)radio,(int)radio));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+radio/2, posY+radio/2);
        area.transform(rot);
    }
}
