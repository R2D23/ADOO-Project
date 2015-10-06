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
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author LuisArturo
 */
public class FigureMenu extends JComponent implements MouseListener{
    
    private static final int SIZE = 200;
    private Point location;
    private Point center;
    private int area;
    
    public FigureMenu(){
	setSize(SIZE, SIZE);
	location = new Point();
	center = new Point();
	
	this.addMouseMotionListener(new MouseAdapter(){
	    @Override
	    public void mouseMoved(MouseEvent me){
		double angle = Math.atan2(-me.getY() + SIZE/2, me.getX() - SIZE/2)/Math.PI;
		if(angle < 0)
		    angle += 2;
		if((0 < angle)&&(angle <= 0.25))
		    area = 0;
		if((0.25 < angle)&&(angle <= 0.5))
		    area = 1;
		if((0.5 < angle)&&(angle <= 0.75))
		    area = 2;
		if((0.75 < angle)&&(angle <= 1))
		    area = 3;
		if((1 < angle)&&(angle <= 1.25))
		    area = 4;
		if((1.25 < angle)&&(angle <= 1.5))
		    area = 5;
		if((1.5 < angle)&&(angle <= 2) || angle == 0)
		    area = 6;
		double dif;
		dif = me.getPoint().distance(SIZE/2, SIZE/2);
		if((dif < SIZE/6)||(dif > SIZE/2))
		    area = -1;
		repaint();
	    }
	});
    }

    @Override
    public void mouseClicked(MouseEvent me) {
	if(me.getButton() == MouseEvent.BUTTON3){
	    System.out.print("Click");
	    this.setSize(SIZE, SIZE);
	    this.setVisible(true);
	    location.setLocation(me.getX() - SIZE / 2, me.getY() - SIZE / 2);
	    center.setLocation(me.getPoint());
	    this.setLocation(location);
	    this.repaint();
	} else
	    this.setVisible(false);
    }

    @Override
    public void mousePressed(MouseEvent me) {
	
    }

    @Override
    public void mouseReleased(MouseEvent me) {
	
    }

    @Override
    public void mouseEntered(MouseEvent me) {
	
    }

    @Override
    public void mouseExited(MouseEvent me) {
	area = -1;
	repaint();
    }
    
    @Override
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	Area s = new Area(new Ellipse2D.Double(0, 0, 200, 200));
	s.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));
	g2.translate(0, 0);
	g2.setColor(Util.normalColor);
	g2.fill(s);
	
	Polygon p = new Polygon();
	if(area == 0){
	    p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE, SIZE/2);
	    p.addPoint(SIZE, 0);
	}
	if(area == 1){
	    p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, 0);
	    p.addPoint(SIZE, 0);
	}
	if(area == 2){
	    p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, 0);
	    p.addPoint(0, 0);
	}
	if(area == 3){
	    p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE/2);
	    p.addPoint(0, 0);
	}
	if(area == 4){
	    p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE/2);
	    p.addPoint(0, SIZE);
	}
	if(area == 5){
	    p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, SIZE);
	    p.addPoint(0, SIZE);
	}
	if(area == 6){
	    p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, SIZE);
	    p.addPoint(SIZE, SIZE);
	    p.addPoint(SIZE, SIZE/2);
	}
	s.intersect(new Area(p));
	if(area >= 0){
	    g2.setColor(Color.blue);
	    g2.fill(s);
	}
	
	g2.setColor(Color.black);
	g2.drawOval(SIZE/3, SIZE/3, SIZE/3, SIZE/3);
	g2.drawOval(0, 0, SIZE, SIZE);
    }
}
