/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author LuisArturo
 */
public class Util {
    public static boolean isPointInDim(Point p, Dimension d){
	if(p.getX() <= d.width)
	    if(p.getY() <= d.height)
		return true;
	return false;
    }
}
