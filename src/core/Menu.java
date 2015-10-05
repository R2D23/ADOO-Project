/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.geom.Ellipse2D; 
import javax.swing.JButton;


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
        this.setShape(new Ellipse2D.Float(0,0,getWidth(),getHeight()));
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(b);   
    }
    
    public void actionPerformed(ActionEvent ae) {
	System.out.println(ae.getActionCommand());
    }

}

