
package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

    public Circle() {
	super();
	radio = 50;
    }
    
    @Override
    public void getArea()
    {
        area = new Area(new Ellipse2D.Double(posX, posY, (int)radio,(int)radio));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+radio/2, posY+radio/2);
        area.transform(rot);
    }
    
    @Override
    public void doZoom(float escala)
    {   
        super.doZoom(escala);
        this.radio *= (1+escala);
        getArea();
    }
    
    @Override
     public void rotate(Point e) {
        double Y = (posY + radio/2) - e.getY();
        double X = (posX + radio/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }

    public double getRadius() {
	return radio;
    }

    public void setRadius(Double aDouble) {
	radio = aDouble;
	getArea();
    }
}
