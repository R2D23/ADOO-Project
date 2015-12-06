
package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import graphic.Util;

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

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();        
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgColor));
            g2.fill(area);
	    g2.setColor(Util.negative(lnColor));
	    g2.draw(area);
        } else {
            g2.setColor(bgColor);
	    g2.fill(area);
            g2.setColor(lnColor);
	    g2.draw(area);
        }
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
    public void getArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX, posY);
        area.transform(rot);
    }
    
    public void doZoom(float escala)
    {
        super.doZoom(escala);
        this.longSide *=(1+escala);
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = posY - e.getY();
        double X = posX - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }

    public int getNumSides() {
	return numSides;
    }

    public void setNumSides(int numSides) {
	this.numSides = numSides;
	getArea();
    }

    public int getLongSide() {
	return longSide;
    }

    public void setLongSide(int longSide) {
	this.longSide = longSide;
	getArea();
    }
}
