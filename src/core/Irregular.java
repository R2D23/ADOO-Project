/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author douxm_000
 */
public class Irregular extends Figure {

    public ArrayList<Point> vertices;
    public Point first;
    
    public Irregular() {
        vertices = new ArrayList<Point>();
        this.bgColor = Color.BLUE;
        this.lnColor = Color.CYAN;
    }
    
    public void newPoint(Point p) {
        vertices.add(p);
    }
    
   
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(state!=GETTINGPOINTS) {
            if(state!=AVAILABLE) {
                g2.setColor(Util.negative(bgColor));
                g2.fill(area);
                g2.setColor(Util.negative(lnColor));
                g2.draw(area);
            } else {
                g2.setColor(bgColor);
                g2.fill(area);
                g2.setColor(lnColor);
                g2.draw(area);
            }
        } else {
            for(int i=0; i<vertices.size()-1; i++) {
                g2.drawLine(vertices.get(i).x, vertices.get(i).y, vertices.get(i+1).x, vertices.get(i+1).y);
                g2.fillOval(vertices.get(i).x-5, vertices.get(i).y-5, 10, 10);
                g2.fillOval(vertices.get(i+1).x-5, vertices.get(i+1).y-5, 10, 10);
            }
        }
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

    @Override
    public void getArea() {
        int[] pointsX;
        int[] pointsY;
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, first.x, first.y);
        area.transform(rot);
    }
    
    public void setFirst(Point p)
    {
        first = p;
        vertices.add(first);
    }
    
    public Point getFirst()
    {return first;}
    
    public Point getLast()
    {return vertices.get(vertices.size() - 1);}
    
    @Override
    public void move(int posX, int posY)
    {
        int dx = posX - first.x;
        int dy = posY - first.y;
        for (Point vertice : vertices) 
            vertice.translate(dx, dy);
        getArea();
    }
    
    @Override
    public void rotate(Point p)
    {
        double Y = first.y - p.getY();
        double X = first.x- p.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }
}
