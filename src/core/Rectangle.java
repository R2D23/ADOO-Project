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
public class Rectangle extends Figure {

    double width;
    double height;
            
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    Rectangle() {}

    @Override
    public void draw(Canvas canvas) {
        Graphics2D g2 = (Graphics2D) canvas.getGraphics();
        BufferedImage imag= new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gBuffer = imag.getGraphics();
        gBuffer.setColor(bgcolor);
        gBuffer.fillRect(posX, posY, (int)width, (int)height);
        gBuffer.setColor(lncolor);
        gBuffer.drawRect(posX, posY, (int)width, (int)height);
        g2.drawImage(imag, null, 0, 0);
        /*g2.setColor(bgcolor);
        g2.fillRect(posX, posY, (int)width, (int)height);
        g2.setColor(lncolor);
        g2.drawRect(posX, posY, (int)width, (int)height);*/
    }

    @Override
    public void configure(Canvas canvas) {
        //Aqui debemos desplegar la emergente para configurar Rectángulo
        //Por ejemplo despues de una configuracion puede quedar asi:
        width = 100.5;
        height = 250.5;
        bgcolor = Color.CYAN;
        lncolor = Color.MAGENTA;
        incline = 0.0f;
        state = true;
        //Las coordenadas se asignan en el lugar que el usuario hizo clic
        canvas.addElement(this);
    }
    
}
