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
import javax.swing.border.Border;


/**
 *
 * @author Angeles
 */
public class Menu extends JComponent implements ActionListener{
    JButton b;
    Area shape;
    public Menu(JButton b)
    {
        this.b = b;
	this.setVisible(false);
	b.addActionListener(this);
//	this.addMouseListener(new MouseAdapter(){
//	    
//	});
    }
    
    @Override
    public void updateUI(){
	super.updateUI();
	shape = new Area(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
	shape.subtract(new Area(new Ellipse2D.Double(75, 75, getWidth() - 150, getHeight() - 150)));
	this.setLocation(b.getLocation().x - 100, b.getLocation().y - 100);
	this.setSize(b.getWidth() + 200, b.getHeight() + 200);
    }
    
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Util.normalColor);
	Border b;
	g2.fill(shape);
	g2.setColor(Color.black);
	g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
	g2.drawOval(75, 75, getWidth() - 150, getHeight() - 150);
	g2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
	g2.drawLine(0, getWidth()/2, 75, getWidth()/2);
	//g2.drawLine((int) ((cos(PI*5/4)/2) * getWidth()), (int) (-(sin(PI*5/4)/2 + .5) * getHeight()),(int) (75 + ((cos(PI*5/4)/2 + .5) * 150)),(int) (225 + (-(sin(PI*5/4)/2 + .5) * 150)));
	g2.drawString("Nuevo", 20, 80);
	g2.drawString("Abrir", 20, 150);
	g2.drawString("Guardar", 80, 220);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	if(ae.getActionCommand().equals(b.getActionCommand())){
	    this.setVisible(!isVisible());
	    updateUI();
	}
    }
}

