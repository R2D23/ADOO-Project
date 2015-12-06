/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.geom.Area;

/**
 * 
 * @author douxm_000
 */
public class RegularPolygon extends Figure {

    private int numSides;
    private int longSide;
    int pointsX[];
    int pointsY[];
    
    public RegularPolygon(int numSides, int longSide) {
        /*Poligono Regular de numSides lados, donde cada lado mide longSide
          numSides >= 3   */
        this.numSides = numSides;
        this.longSide = longSide;
        pointsX = new int[numSides];
        pointsY = new int[numSides];
    }
    
    public RegularPolygon() {
	longSide = 30;
	numSides = 5;
    }
    
    public int[] getCoordsX() {
        double littleAngle = 2*Math.PI/numSides; //Es el ángulo de uno de los sectores
        double radius = longSide/(2*Math.sin(littleAngle/2)); //Radio del Poligono
        int coordX[] = new int[numSides];
        for(int i=0; i<numSides; i++) {
            coordX[i] = getPos().x + (int)(radius * Math.cos(littleAngle*i));
        }
        return coordX;
    }

    public int[] getCoordsY() {
        double littleAngle = 2*Math.PI/numSides; //Es el ángulo de uno de los sectores
        double radius = longSide/(2*Math.sin(littleAngle/2)); //Radio del Poligono
        int coordY[] = new int[numSides];
        for(int i=0; i<numSides; i++) {
            coordY[i] = getPos().y + (int)(radius * Math.sin(littleAngle*i));
        }
        return coordY;
    }

    public int getNumSides() {
	return numSides;
    }

    public void setNumSides(int numSides) {
	this.numSides = numSides;
	repaint();
    }

    public int getLongSide() {
	return longSide;
    }

    public void setLongSide(int longSide) {
	this.longSide = longSide;
	repaint();
    }

    @Override
    public void refreshArea() {
	pointsX = getCoordsX();
        pointsY = getCoordsY();
        setArea(new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length)));
        transformArea();
    }
}
