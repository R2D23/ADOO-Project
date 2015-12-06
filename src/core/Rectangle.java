/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Area;

/**
 *
 * @author douxm_000
 */
public class Rectangle extends core.Figure {

    int width;
    int height;

    public Rectangle() {}
    
    public void setWidth(int b){
	width = b;
	repaint();
    }
    
    public int getWidth(){
	return width;
    }
    
    public int getHeight(){
	return height;
    }
    
    public void setHeight(int h){
	height = h;
	repaint();
    }
        
    public void setSize(Point p){
	width = p.x;
	height = p.y;
    }
    
    public Dimension getSize(){
	return new Dimension(width, height);
    }
    
    @Override
    public void refreshArea() {
	setArea(new Area(new java.awt.Rectangle(
		getPos().x, getPos().y, width, height)));
	transformArea();
    }
}
