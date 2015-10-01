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
public abstract class CustomButton extends JButton{
    
    public CustomButton(){
	setBackground(new Color(230, 230, 230));
	
	this.addMouseListener(new MouseAdapter(){
	    @Override
	    public void mousePressed(MouseEvent me){
		setBackground(new Color(110, 110, 110));
		repaint();
	    }
	    
	    @Override
	    public void mouseReleased(MouseEvent me){
		setBackground(new Color(230, 230, 230));
		repaint();
	    }
	    
	    @Override
	    public void mouseEntered(MouseEvent me){
		repaint();
	    }
	});
    }
}
