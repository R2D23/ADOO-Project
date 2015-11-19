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
                coordX[0] = posX;
                coordX[1] = (int)(posX+width/2);
                coordX[2] = (int)(posX+width);
            break;
            case RIGHT_TRIANGLE:
                coordX[0] = posX;
                coordX[1] = posX;
                coordX[2] = (int)(posX+width);
            break;
        }
        return coordX;
    }

    public int[] getCoordsY() {
        int coordY[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: 
                coordY[0] = (int)(posY+width*Math.sin(Math.PI/3));
                coordY[1] = posY;
                coordY[2] = (int)(posY+width*Math.sin(Math.PI/3));
            break;
            case ISOSCELES_TRIANGLE:
                coordY[0] = (int)(posY+height);
                coordY[1] = posY;
                coordY[2] = (int)(posY+height);
            break;    
            case RIGHT_TRIANGLE:
                coordY[0] = posY;
                coordY[1] = (int)(posY+height);
                coordY[2] = (int)(posY+height);
            break;
        }
        return coordY;
    }
    
    public void getArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
    }
    
    @Override
    public void doZoom(float escala)
    {
        super.doZoom(escala);
        this.width *= (1+escala);     
        this.height *= (1+escala);
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        getArea();
    }
    
    @Override
    public void rotate(Point e) {
        double Y = (posY + height/2) - e.getY();
        double X = (posX + width/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }
    
    public void setType(int i){
	type = i;
	getArea();
    }
    
    public int getType(){
	return type;
    }
}
