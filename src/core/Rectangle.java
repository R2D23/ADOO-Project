/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 *
 * @author douxm_000
 */
public class Rectangle extends core.Figure {

    double width;
    double height;
            
    public Rectangle(double width, double height) {
	super();
        width = width;	    height = height;
    }
    
    public Rectangle(Rectangle r){
	super(r);
	width = r.width;    height = r.height;
    }

    public Rectangle() {super();}

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //g2.fillRect(posX, posY, (int)width, (int)height);
        //g2.setColor(lncolor);
        //g2.drawRect(posX, posY, (int)width, (int)height);
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgColor));
        } else {
            g2.setColor(bgColor);
        }
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        g2.fill(area);
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(lnColor));
        } else {
            g2.setColor(lnColor);
        }
        g2.draw(area);
    }

    @Override
    public void configure(Canvas canvas) {
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
    }
    
}
