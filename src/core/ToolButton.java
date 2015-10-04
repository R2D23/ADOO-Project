/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 *
 * @author LuisArturo
 */
public class ToolButton extends CustomButton{
    private GraphicDrawer gd;
    private int id;
    public ToolButton(int id){
	this.setSize(100, 100);
	gd = new GraphicDrawer();
	this.id = id;
	setContentAreaFilled(false);
    }
    
    @Override
    public void paint(Graphics g){
	paintRules();
	g.setColor(this.getParent().getBackground());
	//g.fillRect(0, 0, getWidth(), getHeight());
	g.setColor(getBackground());
	g.fillOval(0, 0, getWidth(), getHeight());
	g.setColor(Color.black);
	g.drawOval(0, 0, getWidth(), getHeight());
	gd.paint(id, getBounds(), g);
    }
}
