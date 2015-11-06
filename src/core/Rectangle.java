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
        this.width = width;
        this.height = height;
    }

    public Rectangle() {}

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //g2.fillRect(posX, posY, (int)width, (int)height);
        //g2.setColor(lncolor);
        //g2.drawRect(posX, posY, (int)width, (int)height);
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgcolor));
        } else {
            g2.setColor(bgcolor);
        }
        //area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        g2.fill(area);
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(lncolor));
        } else {
            g2.setColor(lncolor);
        }
        g2.draw(area);
    }

    public void configure(Canvas canvas) {
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
        
    }
    public Area getArea()
    {
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
        return area;
    }
    
    public void doZoom(float escala)
    {
        this.height *= (1+escala);  
        this.width *= (1+escala);
        area = getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = (posY + height/2) - e.getY();
        double X = (posX + width/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        area = getArea();
    }
    
    public void move(int x, int y)
    {
        posX = (int)(x - width/2);
        posY = (int)(y - height/2);
        area = getArea();
    }
    
}
