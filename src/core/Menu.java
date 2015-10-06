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
public class Menu extends Container implements ActionListener, MouseListener{
    JButton b;
    Area shape;
    Button boton = new Button();
    public Menu(JButton b)
    {
        this.b = b;
	this.setVisible(false);
	b.addActionListener(this);
    }
    
    public Menu()
    {
        this.setVisible(false);
        b = null;
        //this.setUndecorated(true);
        /*GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // El área de texto empieza en la columna cero.
        constraints.gridy = 0; // El área de texto empieza en la fila cero
        constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
        constraints.gridheight = 2; 
        this.setLayout(new BorderLayout());
        this.add(boton);
        boton.setSize(10,10);
        boton.doLayout();*/
    }
    
    
    public void updateUI(){
        
	shape = new Area(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
	shape.subtract(new Area(new Ellipse2D.Double(getWidth()/3, getHeight()/3, getWidth()/3, getHeight()/3)));
	if(b != null)
        {
            this.setLocation(b.getLocation().x - 100, b.getLocation().y - 100);
            this.setSize(b.getWidth() + 200, b.getHeight() + 200);
        }
        else
        {
            //this.setLocation(0, 0);
            this.setSize(200, 200);
        }
        
    }
    
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Util.normalColor);
	//Border b;
        //g2.setBackground(Color.yellow);
	g2.fill(shape);
	g2.setColor(Color.black);
	g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
        g2.drawOval(getWidth()/3, getHeight()/3, getWidth()/3, getHeight()/3);
	g2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        /*try{
        Thread.sleep(2000);}catch(Exception e){}*/
        if(this.b != null)
        {
            g2.drawLine(0, getWidth()/2, 75, getWidth()/2);
            g2.drawLine(28, 200,90,160);
            g2.drawString("Nuevo", 20, 80);
            g2.drawString("Abrir", 20, 150);
            g2.drawString("Guardar", 80, 220);
        }
    }

    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
	if(ae.getActionCommand().equals(b.getActionCommand())){
	    this.setVisible(!isVisible());
	    //updateUI(); 
	}
    }
    
    public void mouseClicked(MouseEvent me)
    {
        if((me.getButton() == 1) && (this.b == null))
        {
            this.setVisible(true);
            setLocation(me.getX()-this.getWidth()/2,me.getY()-this.getHeight()/2);
            //boton.setLocation(this.getX(),this.getY());
            //boton.setBackground(Color.red);
            //boton.setVisible(true);
            this.repaint();
            this.setVisible(true);
            

        }
        else
            this.setVisible(false);
    }
    
    public void mouseExited(MouseEvent me)
    {
        //System.out.println("El mouse ha salido");
    }
    
    public void mouseEntered(MouseEvent me)
    {
        //this.setVisible(false);
    }
    
    public void mouseReleased(MouseEvent me)
    {
        //System.out.println("El mouse ha SIDO LIBERADO");
    }
    
    public void mousePressed(MouseEvent me)
    {
        //System.out.println("El mouse ha SIDO LIBERADO");
    }
    
    
}

