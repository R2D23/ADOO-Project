
package core;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author douxm_000
 */
public class Circle extends Figure{
    
    private double radio;
    
    private static double recentRadio = 50;
    
    public Circle() {
	super();
	radio = recentRadio;
    }
    
    public Circle(double radio) {
	super();
        this.radio = Math.abs(radio);
    }
    
    public Circle(Circle c){
	super(c);
	this.radio = c.radio;
    }
    
    public double getRadius() {
	return radio;
    }

    public void setRadius(Double aDouble) {
	radio = aDouble;
	recentRadio = radio;
	repaint();
    }

    @Override
    public Object clone() {
	return new Circle(this);
    }

    @Override
    public void refreshArea() {
	setArea(new Area(
	new Ellipse2D.Double(getPos().x, getPos().y, radio * 2, radio * 2)));
	transformArea();
    }
}
