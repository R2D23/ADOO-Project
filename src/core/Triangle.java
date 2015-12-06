/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 *
 * @author douxm_000
 */
public class Triangle extends Rectangle{

   
    int type;
    public int pointsX[];
    public int pointsY[];
    public static final int EQUILATERAL_TRIANGLE = 1;
    public static final int RIGHT_TRIANGLE = 2;
    public static final int ISOSCELES_TRIANGLE = 3;
    
    public Triangle(int base, int height, int type) {
        this.width = base;
        this.height = height;
        this.type = type;
        pointsX = new int[3];
        pointsY = new int[3];
    }
    
    public Triangle(int base) {
        this.height = base;
        this.height = (int) Math.sqrt(Math.pow(base, 2)-Math.pow(base/2, 2)); //Pitágoras
        type = EQUILATERAL_TRIANGLE;
        pointsX = new int[3];
        pointsY = new int[3];
    }
    
    public Triangle(){
	super();
	type = Triangle.EQUILATERAL_TRIANGLE;
	width = 50;
	height = 50;
    }

    public int[] getCoordsX() {
        int coordX[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: case ISOSCELES_TRIANGLE:
                coordX[0] = getPos().x;
                coordX[1] = (int)(getPos().x+width/2);
                coordX[2] = (int)(getPos().x+width);
            break;
            case RIGHT_TRIANGLE:
                coordX[0] = getPos().x;
                coordX[1] = getPos().x;
                coordX[2] = (int)(getPos().x+width);
            break;
        }
        return coordX;
    }

    public int[] getCoordsY() {
        int coordY[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: 
                coordY[0] = (int)(getPos().y+width*Math.sin(Math.PI/3));
                coordY[1] = getPos().y;
                coordY[2] = (int)(getPos().y+width*Math.sin(Math.PI/3));
            break;
            case ISOSCELES_TRIANGLE:
                coordY[0] = (int)(getPos().y+height);
                coordY[1] = getPos().y;
                coordY[2] = (int)(getPos().y+height);
            break;    
            case RIGHT_TRIANGLE:
                coordY[0] = getPos().y;
                coordY[1] = (int)(getPos().y+height);
                coordY[2] = (int)(getPos().y+height);
            break;
        }
        return coordY;
    }
    
    public void setType(int i){
	type = i;
	repaint();
    }
    
    public int getType(){
	return type;
    }
    
    @Override
    public void refreshArea(){
	pointsX = getCoordsX();
        pointsY = getCoordsY();
        setArea(new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length)));
	transformArea();
    }
}
