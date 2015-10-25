/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author douxm_000
 */
class Line extends Element {

    double length;
    float angle;
    Color color;
    
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
    
    Line() {}

    @Override
    public void draw(Canvas canvas) {
        Graphics2D g2 = (Graphics2D) canvas.getGraphics();
        BufferedImage imag= new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gBuffer = imag.getGraphics();
        gBuffer.setColor(color);
        gBuffer.drawLine(posX, posY, posX+(int)(length*Math.cos(angle)), posY-(int)(length*Math.sin(angle)));
        g2.drawImage(imag, null, 0, 0);
        
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
