/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author douxm_000
 */
public class Circle extends Figure{

    double radio;
    
    public Circle(double radio) {
        this.radio = radio;
    }
    
    Circle() {}
    
    @Override
    public void draw(Canvas canvas) {
        Graphics2D g2 = (Graphics2D) canvas.getGraphics();
        g2.setColor(bgcolor);
        g2.fillOval(posX, posY, (int)radio, (int)radio);
        g2.setColor(lncolor);
        g2.drawOval(posX, posY, (int)radio, (int)radio);
    }

    @Override
    public void configure(Canvas canvas) {
        radio = 150.5;
        bgcolor = Color.ORANGE;
        lncolor = Color.RED;
        incline = 0.0f;
        state = true;
        //Las coordenadas se asignan en el lugar que el usuario hizo clic
        canvas.addElement(this);
    }
    
}
