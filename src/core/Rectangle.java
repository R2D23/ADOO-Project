/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 *
 * @author douxm_000
 */
public class Rectangle extends core.Figure {

    int width;
    int height;
            
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {}

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) (g.create());
        if(state!=AVAILABLE) {
            g2.setBackground(Util.negative(bgColor));
            g2.setColor(Util.negative(lnColor));
        } else {
            g2.setBackground(bgColor);
            g2.setColor(lnColor);
        }
        g2.draw(area);
        g2.fill(area);
    }

    public void configure(Canvas canvas) {
        new ConfigurarCuadrado(canvas,this,new Point(this.posX, this.posY)).setVisible(true);
        
    }
    public void getArea()
    {
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
    }
    
    public void doZoom(float escala)
    {
        this.height *= (1+escala);  
        this.width *= (1+escala);
        getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = (posY + height/2) - e.getY();
        double X = (posX + width/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }
    
    public void setWidth(int b){
	this.width = b;
	getArea();
    }
    
    public double getWidth(){
	return width;
    }
    
    public void setHeight(int h){
	this.height = h;
	getArea();
    }
    
    public double getHeight(){
	return height;
    }
    
    public void setSize(Point p){
	width = p.x;
    }
    
    public Point getSize(){
	return new Point(width, height);
    }
}
