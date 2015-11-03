/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;

/**
 *
 * @author LuisArturo
 */
public class Util {
    static final Color normalColor = new Color(230, 230, 230);
    static final Color rollOverColor = new Color(200, 200, 200);
    static final Color pressedColor = new Color(110, 110, 110);
    
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
