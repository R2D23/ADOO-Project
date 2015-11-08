/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 *
 * @author douxm_000
 */
public class Irregular extends Figure {

    ArrayList<Point> vertices;
    Point first;
    
    public Irregular() {
        vertices = new ArrayList<Point>();
    }
    
    public void newPoint(Point p) {
        if(vertices.isEmpty())
            first = p;
        vertices.add(p);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(state!=GETTINGPOINTS) {
            if(state!=AVAILABLE) {
                g2.setColor(Util.negative(bgColor));
            } else {
                g2.setColor(bgColor);
            }
            //area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
            g2.fill(area);
            if(state!=AVAILABLE) {
                g2.setColor(Util.negative(lnColor));
            } else {
                g2.setColor(lnColor);
            }
            g2.draw(area);
        } else {
            //g2.drawRect(vertices.get(0).x-5, vertices.get(0).y-5, 10, 10);
            for(int i=0; i<vertices.size()-1; i++) {
                g2.drawLine(vertices.get(i).x, vertices.get(i).y, vertices.get(i+1).x, vertices.get(i+1).y);
                g2.fillOval(vertices.get(i).x-5, vertices.get(i).y-5, 10, 10);
                g2.fillOval(vertices.get(i+1).x-5, vertices.get(i+1).y-5, 10, 10);
            }
        }
    }

    @Override
    public void configure(Canvas canvas) {
        int[] pointsX;
        int[] pointsY;
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX, posY);
        area.transform(rot);
    }

    private int[] getCoordsX() {
        int[] coord = new int [vertices.size()];
        for(int i=0; i<vertices.size(); i++)
            coord[i] = vertices.get(i).x;
        return coord;
    }

    private int[] getCoordsY() {
        int[] coord = new int [vertices.size()];
        for(int i=0; i<vertices.size(); i++)
            coord[i] = vertices.get(i).y;
        return coord;
    }
    
}
