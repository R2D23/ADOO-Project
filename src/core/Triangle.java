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
public class Triangle extends Figure{

    double base;
    double height;
    int type;
    public int pointsX[];
    public int pointsY[];
    public static final int EQUILATERAL_TRIANGLE = 1;
    public static final int RIGHT_TRIANGLE = 2;
    public static final int ISOSCELES_TRIANGLE = 3;
    
    public Triangle(double base, double height, int type) {
        this.base = base;
        this.height = height;
        this.type = type;
        pointsX = new int[3];
        pointsY = new int[3];
    }
    
    public Triangle(double base) {
        this.base = base;
        this.height = Math.sqrt(Math.pow(base, 2)-Math.pow(base/2, 2)); //Pitágoras
        type = EQUILATERAL_TRIANGLE;
        pointsX = new int[3];
        pointsY = new int[3];
    }
    
    

    public Triangle() {}
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgcolor));
        } else {
            g2.setColor(bgcolor);
        }
        g2.fill(area);
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(lncolor));
        } else {
            g2.setColor(lncolor);
        }
        g2.draw(area);
    }

    public void configure(Canvas canvas) {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+base/2, posY+height/2);
        area.transform(rot);
    }

    public int[] getCoordsX() {
        int coordX[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: case ISOSCELES_TRIANGLE:
                coordX[0] = posX;
                coordX[1] = (int)(posX+base/2);
                coordX[2] = (int)(posX+base);
            break;
            case RIGHT_TRIANGLE:
                coordX[0] = posX;
                coordX[1] = posX;
                coordX[2] = (int)(posX+base);
            break;
        }
        return coordX;
    }

    public int[] getCoordsY() {
        int coordY[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: 
                coordY[0] = (int)(posY+base*Math.sin(Math.PI/3));
                coordY[1] = posY;
                coordY[2] = (int)(posY+base*Math.sin(Math.PI/3));
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
    public Area getArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+base/2, posY+height/2);
        area.transform(rot);
        return area;
    }
    
    public void move(int x, int y)
    {
        posX = (int)(x - base/2);
        posY = (int)(y - height/2);
        area = getArea();
    }
    
    public void doZoom(float escala)
    {
        super.doZoom(escala);
        this.base *= (1+escala);     
        this.height *= (1+escala);
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = getArea();
    }
    
    public void rotate(Point e) {
         double Y = (posY + height/2) - e.getY();
        double X = (posX + base/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        area = getArea();
    }
}
