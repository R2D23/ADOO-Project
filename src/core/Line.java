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
import java.awt.image.BufferedImage;

/**
 *
 * @author douxm_000
 */
public class Line extends Element {

    double length;
    float angle;
    Color color;
    float grosor;
    
    public Line(double length, float angle, Color color) {
        this.length = length;
        this.angle = angle;
        this.color = color;
    }
    
    public Line(int finX, int finY, Color color) { //Contructor alternativo a traves de coordenadas
        length = Math.sqrt(Math.pow(finY-posY, 2)+Math.pow(finX-posX, 2)); //Distancia entre dos puntos
        angle = (float)Math.atan((finY-posY)/(finX-posX));  //Calcula el angulo a traves de ARCTAN
        this.color = color;
    }
    
    public Line() {}

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke s = new BasicStroke(grosor);
        
        g2.setStroke(s);
        g2.setColor(color);
        g2.drawLine(posX, posY, posX+(int)(length*Math.cos(angle)), posY-(int)(length*Math.sin(angle)));
        
        
    }

    @Override
    public void configure(Canvas canvas) {
        length = 100.2;
        angle = (float)(4*Math.PI/6);
        color = Color.BLUE;
        incline = 0.0f;
        state = true;
        //Las coordenadas se asignan en el lugar que el usuario hizo clic
        canvas.addElement(this);
    }
}
