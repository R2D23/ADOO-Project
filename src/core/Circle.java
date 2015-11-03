
package core;

import core.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author douxm_000
 */
public class Circle extends Figure{

    double radio;
    
    public Circle(double radio){
	super();
        this.radio = radio;
    }
    
    public Circle(){
	super();
    }
    
    public Circle(Circle c){
	super(c);
	radio = c.radio;
    }
   
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        try{
            g2.setColor(bgColor);
            
        g2.fillOval(posX, posY, (int)radio, (int)radio);
        g2.setColor(lnColor);
        g2.drawOval(posX, posY, (int)radio, (int)radio);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void configure(Canvas canvas) {
        radio = 150.5;
        bgColor = Color.ORANGE;
        lnColor = Color.RED;
        incline = 0.0f;
        state = true;
        //Las coordenadas se asignan en el lugar que el usuario hizo clic
        //canvas.elements.add(this);
        //canvas.repaint();
    }
    
}
