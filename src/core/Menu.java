/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.geom.*;
import static java.lang.Math.*;


/**
 *
 * @author Angeles
 */
public class Menu extends JFrame implements Runnable, ActionListener
{
    JButton b;
    public Menu(JButton b)
    {
        this.b = b;
        this.run();
    }
    
    public void run() {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        JButton boton = new JButton();
        this.add(boton);
        boton.setVisible(true);
        this.setMinimumSize(new Dimension(100, 100));
        this.getContentPane().setLayout(null);
	Area s = new Area(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
	s.subtract(new Area(new Ellipse2D.Double(10, 10, getWidth() - 20, getHeight() - 20)));
	this.setShape(s);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(b);   
    }
    
    public void actionPerformed(ActionEvent ae) {
	System.out.println(ae.getActionCommand());
    }
}

