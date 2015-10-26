/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.Figure;
import core.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
        g2.setColor(bgcolor);
        g2.fillPolygon(pointsX, pointsY, pointsX.length);
        g2.setColor(lncolor);
        g2.drawPolygon(pointsX, pointsY, pointsX.length);
        
    }

    @Override
    public void configure(Canvas canvas) {
        numSides = 12;
        longSide = 30.5;
        bgcolor = Color.GRAY;
        lncolor = Color.GREEN;
        incline = 0.0f;
        state = true;
        //Las coordenadas se asignan en el lugar que el usuario hizo clic
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        canvas.addElement(this);
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
    
}
