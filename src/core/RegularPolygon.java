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
public class RegularPolygon extends Figure {

    int numSides;
    double longSide;
    int pointsX[];
    int pointsY[];
    
    public RegularPolygon(int numSides, double longSide) {
        /*Poligono Regular de numSides lados, donde cada lado mide longSide
          numSides >= 3   */
        this.numSides = numSides;
        this.longSide = longSide;
        pointsX = new int[numSides];
        pointsY = new int[numSides];
    }
    
    public RegularPolygon() {}

    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        /*g2.setColor(bgcolor);
        g2.fillPolygon(pointsX, pointsY, pointsX.length);
        g2.setColor(lncolor);
        g2.drawPolygon(pointsX, pointsY, pointsX.length);
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgcolor));
            g2.fill(area);
        }
        */
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgcolor));
        } else {
            g2.setColor(bgcolor);
        }
        //area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
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
        rot.setToRotation(incline, posX, posY);
        area.transform(rot);
    }
    
    public int[] getCoordsX() {
        double littleAngle = 2*Math.PI/numSides; //Es el ángulo de uno de los sectores
        double radius = longSide/(2*Math.sin(littleAngle/2)); //Radio del Poligono
        int coordX[] = new int[numSides];
        for(int i=0; i<numSides; i++) {
            coordX[i] = posX + (int)(radius * Math.cos(littleAngle*i));
        }
        return coordX;
    }

    public int[] getCoordsY() {
        double littleAngle = 2*Math.PI/numSides; //Es el ángulo de uno de los sectores
        double radius = longSide/(2*Math.sin(littleAngle/2)); //Radio del Poligono
        int coordY[] = new int[numSides];
        for(int i=0; i<numSides; i++) {
            coordY[i] = posY + (int)(radius * Math.sin(littleAngle*i));
        }
        return coordY;
    }
    public Area getArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX, posY);
        area.transform(rot);
        return area;
        
    }
    
    public void doZoom(float escala)
    {
        super.doZoom(escala);
        this.longSide *=(1+escala);
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = getArea();
    }
    
    public void move(int x, int y)
    {   posX = (int)(x);
        posY = (int)(y);
        area = getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = posY - e.getY();
        double X = posX - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        area = getArea();
    }
}
