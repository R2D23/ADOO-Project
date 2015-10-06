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
    
    public FigureMenu(){
	setSize(SIZE, SIZE);
	location = new Point();
	center = new Point();
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
	
    }
    
    @Override
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	Area s = new Area(new Ellipse2D.Double(0, 0, 200, 200));
	s.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));
	g2.translate(0, 0);
	g2.setColor(Util.normalColor);
	g2.fill(s);
	g2.setColor(Color.black);
	g2.drawOval(SIZE/3, SIZE/3, SIZE/3, SIZE/3);
	g2.drawOval(0, 0, SIZE, SIZE);
    }
}
