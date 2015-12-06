
package core;

import static core.Element.AVAILABLE;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import graphic.Util;

/**
 *
 * @author douxm_000
 */
public abstract class Figure extends core.Element {
    public Color bgColor; //Color del area de la figura
    public Color lnColor; //Color de las lineas de la figura
    
    public Figure(){
	super();
	//Colores por Defecto
	bgColor = new Color(255, 255, 255, 0); //Transparente
	lnColor = Color.BLACK;
    }
    
    public Figure(Figure f){
	super(f);
	bgColor = new Color(f.bgColor.getRGB());
	lnColor = new Color(f.lnColor.getRGB());
    }
    
    public void setBgColor(Color c){
	bgColor = new Color(c.getRGB());
	getArea();
    }
    
    public void setLnColor(Color c){
	lnColor = new Color(c.getRGB());
	getArea();
    }
    
    public Color getBgColor(){return bgColor;}
    public Color getLnColor(){return lnColor;}
    
    @Override
    public void draw(Graphics g){
	Graphics2D g2 = (Graphics2D)g.create();
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgColor));
            g2.fill(area);
            g2.setColor(Util.negative(lnColor));
            g2.draw(area);
        } else {
            g2.setColor(bgColor);
            g2.fill(area);
            g2.setColor(lnColor);
            g2.draw(area);
        }
    }
}
