
package graphic;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;

/**
 *
 * @author LuisArturo
 */
public class Util {
    public static final Color normalColor = new Color(230, 230, 230);
    public static final Color rollOverColor = new Color(200, 200, 200);
    public static final Color pressedColor = new Color(110, 110, 110);
    public static final Color selectedColor = Color.LIGHT_GRAY;
    
    public static boolean isPointInDim(Point p, Dimension d){
	if(p.getX() <= d.width)
	    if(p.getY() <= d.height)
		return true;
	return false;
    }
    public static Color negative(Color c) {
        Color neg = new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
        return neg;
    }
}
