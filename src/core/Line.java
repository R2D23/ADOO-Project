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
	super();
        this.length = length;
        this.color = color;
    }
    
    public Line(int finX, int finY, Color color) { //Contructor alternativo a traves de coordenadas
        length = Math.sqrt(Math.pow(finY-posY, 2)+Math.pow(finX-posX, 2)); //Distancia entre dos puntos
        incline = Math.atan((finY-posY)/(finX-posX));  //Calcula el angulo a traves de ARCTAN
        this.color = color;
    }
    
    public Line(Line l){
	super(l);
	length = l.length;  incline = l.incline;
	color = new Color(l.color.getRGB());
	grosor = l.grosor;
    }
    
    public Line() {super();}

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke s = new BasicStroke(grosor);
        
        g2.setStroke(s);
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(color));
        } else {
            g2.setColor(color);
        }
        /*java.awt.Rectangle r2d = new java.awt.Rectangle(posX-(int)(grosor/2), posY-(int)(grosor/2), (int)(length + grosor), (int)grosor);
        AffineTransform atr = new AffineTransform();
        //area = new Area(r2d);
        area.transform(atr);*/
        g2.fill(area);
        //g2.drawLine(posX, posY, posX+(int)(length*Math.cos(incline)), posY-(int)(length*Math.sin(incline)));
        
    }

    @Override
    public void configure(Canvas canvas) {
        /*length = 100.2;
        angle = (float)(4*Math.PI/6);
        color = Color.BLUE;
        incline = 0.0f;
        state = 0;
        //Las coordenadas se asignan en el lugar que el usuario hizo clic
        canvas.addElement(this);*/
        java.awt.Rectangle r2d = new java.awt.Rectangle(posX-(int)(grosor/2), posY-(int)(grosor/2), (int)(length + grosor), (int)grosor);
        AffineTransform atr = new AffineTransform();
        atr.setToRotation(incline, posX+length/2, posY+grosor/2);
        area = new Area(r2d);
        area.transform(atr);
        //System.out.println(incline+"°");
        //area.transform(atr);
                
    }
}
