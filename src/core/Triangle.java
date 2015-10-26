/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author douxm_000
 */
public class Triangle extends Figure{

    double base;
    double height;
    int type;
    public int pointsX[];
    public int pointsY[];
    public static final int EQUILATERAL_TRIANGLE = 1;
    public static final int RIGHT_TRIANGLE = 2;
    public static final int ISOSCELES_TRIANGLE = 3;
    
    public Triangle(double base, double height, int type) {
        this.base = base;
        this.height = height;
        this.type = type;
        pointsX = new int[3];
        pointsY = new int[3];
    }
    
    public Triangle(double base) {
        this.base = base;
        this.height = Math.sqrt(Math.pow(base, 2)-Math.pow(base/2, 2)); //Pitágoras
        type = EQUILATERAL_TRIANGLE;
        pointsX = new int[3];
        pointsY = new int[3];
    }

    public Triangle() {}
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(bgcolor);
        g2.fillPolygon(pointsX, pointsY, pointsX.length);
        //gBuffer.setColor(lncolor);
        g2.drawPolygon(pointsX, pointsY, pointsX.length);
        //g2.drawImage(imag, null, 0, 0); 
    }

    @Override
    public void configure(Canvas canvas) {
        //type=EQUILATERAL_TRIANGLE;
        type = ISOSCELES_TRIANGLE;
        //type = RIGHT_TRIANGLE;
        base=50.0;
        height=100.0;
        //height=Math.sqrt(Math.pow(base, 2)-Math.pow(base/2, 2));
        bgcolor = Color.YELLOW;
        lncolor = Color.BLACK;
        incline = 0.0f;
        state = true;
        //Las coordenadas se asignan en el lugar que el usuario hizo clic
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        canvas.addElement(this);
    }

    public int[] getCoordsX() {
        int coordX[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: case ISOSCELES_TRIANGLE:
                coordX[0] = posX;
                coordX[1] = (int)(posX+base/2);
                coordX[2] = (int)(posX+base);
            break;
            case RIGHT_TRIANGLE:
                coordX[0] = posX;
                coordX[1] = posX;
                coordX[2] = (int)(posX+base);
            break;
        }
        return coordX;
    }

    public int[] getCoordsY() {
        int coordY[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: 
                coordY[0] = (int)(posY+base*Math.sin(Math.PI/3));
                coordY[1] = posY;
                coordY[2] = (int)(posY+base*Math.sin(Math.PI/3));
            break;
            case ISOSCELES_TRIANGLE:
                coordY[0] = (int)(posY+height);
                coordY[1] = posY;
                coordY[2] = (int)(posY+height);
            break;    
            case RIGHT_TRIANGLE:
                coordY[0] = posY;
                coordY[1] = (int)(posY+height);
                coordY[2] = (int)(posY+height);
            break;
        }
        return coordY;
    }
    
}