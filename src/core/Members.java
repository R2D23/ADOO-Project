/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author LuisArturo
 */
public class Members extends CustomButton{
    
    public Members() {
	super();
	this.setSize(50, 200);
	this.setLocation(0, 75);
    }
    
    @Override
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(getBackground());
	g2.fillRoundRect(-10, 0, getWidth() + 9, getHeight() - 1, 20, 20);
	g2.setColor(Color.black);
	g2.drawRoundRect(-10, 0, getWidth() + 9, getHeight() - 1, 20, 20);
	
	g2.rotate(Math.PI*3/2);
	g2.setColor(Color.black);
	g2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 30));
	g2.drawString("Collaborators", -190, 35);
    }
}
