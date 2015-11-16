/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.Canvas;
import core.Element;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 *
 * @author douxm_000
 */
public class Line extends Element {

    double length;
    Color color;
    float grosor;
    
    public Line(double length, float angle, Color color) {
        this.length = length;
        this.color = color;
    }
    
    public Line(int finX, int finY, Color color) { //Contructor alternativo a traves de coordenadas
        length = Math.sqrt(Math.pow(finY-posY, 2)+Math.pow(finX-posX, 2)); //Distancia entre dos puntos
        incline = Math.atan((finY-posY)/(finX-posX));  //Calcula el angulo a traves de ARCTAN
        this.color = color;
    }
    
    public Line(){
	
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Stroke s = new BasicStroke(grosor);
        g2.setStroke(s);
        if(state!=AVAILABLE)
            g2.setColor(Util.negative(color));
        else
            g2.setColor(color);
        g2.draw(area);
        g2.fill(area);
    }
    
    @Override
    public void getArea()
    {
        java.awt.Rectangle r2d = new java.awt.Rectangle(posX, posY, (int)(length), (int)grosor);
        AffineTransform atr = new AffineTransform();
        atr.setToRotation(incline, posX+r2d.width/2, posY+r2d.height/2);
        area = new Area(r2d);
        area.transform(atr);

    }
    @Override
   public void doZoom(float escala)
    {
        this.length *= (1+escala);  
        getArea();
    }
    
    @Override
    public void rotate(java.awt.Point e) {
        double Y = posY - e.getY();
        double X = (posX + length/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }
}
