
package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.io.Serializable;
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
    public Imagen(String s){
	super();
        src = s;
        img = new ImageIcon(src);
        width = img.getIconWidth();
        height = img.getIconHeight();
    }
    
    @Override
    public void paint(Graphics g){
	refreshArea();
        Graphics2D g2 = (Graphics2D)(g.create()); 
        g2.rotate(getInclination(), getPos().x + width/2, getPos().y + height/2);
        g2.drawImage(img.getImage(), getPos().x, getPos().y, width, height,null);
    }

    @Override
    public void refreshArea() {
	setArea(new Area(new java.awt.Rectangle(getPos().x, getPos().y,
		(int) width, (int) height)));
        transformArea();
    }
}