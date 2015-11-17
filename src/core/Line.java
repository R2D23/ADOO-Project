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
    Point [] puntos;
    
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
	puntos = new Point[2];
        grosor = 5; 
        color = Color.red;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Stroke s = new BasicStroke(grosor);
        g2.setStroke(s);
        if(state!=GETTINGPOINTS) {
            g2.setColor(Color.BLACK);
            g2.draw(area);
            g2.fill(area);
            if(state!=AVAILABLE)
                g2.setColor(Util.negative(color));
            else
                g2.setColor(color);
            //g2.drawLine(puntos[0].x,puntos[0].y, puntos[1].x,puntos[1].y);
            
        } 
    }
    
    @Override
    public void getArea()
    {
        
        length = puntos[0].distance(puntos[1]);
        java.awt.Rectangle r2d = new java.awt.Rectangle(posX,posY, (int)(length), (int)grosor);
        AffineTransform atr = new AffineTransform();
        atr.setToRotation(incline, posX, posY);
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
    
    @Override
    public void move(Point p)
    {
    }
    
    @Override
    public void setLast(Point p)
    {
        Point nuevoPunto = new Point((int)p.getX(),(int)( p.getY() + GUI.GAP/2));
        puntos[1] = nuevoPunto;
        state = Element.AVAILABLE;
        posX = puntos[0].x;
        posY = puntos[0].y;
        
        double Y = puntos[1].y - puntos[0].y;
        double X = puntos[1].x - puntos[0].x;
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente);
        if(X < 0)
            this.incline += Math.PI;
        getArea();
        Canvas.seleccionado = null;
        
    }
    
    @Override
    public void setFirst(Point p)
    {puntos[0] = p;}
    
    public Point getLast()
    {return puntos[0];}
}
