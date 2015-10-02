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
<<<<<<< HEAD
public abstract class CustomButton extends JButton{
    
    public CustomButton(){
=======
public abstract class CustomButton extends JComponent{
    private ActionListener al;
    private ActionEvent ae;
    
    public CustomButton(ActionListener al){
	this.al = al;
>>>>>>> origin/master
	setBackground(new Color(230, 230, 230));
	
	this.addMouseListener(new MouseAdapter(){
	    @Override
<<<<<<< HEAD
=======
	    public void mouseClicked(MouseEvent me){
		al.actionPerformed(ae);
	    }
	    
	    @Override
>>>>>>> origin/master
	    public void mousePressed(MouseEvent me){
		setBackground(new Color(110, 110, 110));
		repaint();
	    }
	    
	    @Override
	    public void mouseReleased(MouseEvent me){
		setBackground(new Color(230, 230, 230));
		repaint();
	    }
	    
<<<<<<< HEAD
	    @Override
	    public void mouseEntered(MouseEvent me){
		repaint();
	    }
	});
    }
=======
	});
    }
    
    public void setActionDesc(String s){
	ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, s);
    }
>>>>>>> origin/master
}
