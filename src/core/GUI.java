/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author LuisArturo
 */
public class GUI extends JFrame implements Runnable, ActionListener{
    private JPanel canvas;
    private JPanel toolkit;
    private Members members;
    
    public GUI(){
	canvas = new JPanel();
	toolkit = new JPanel();
	members = new Members();{
	    members.addActionListener(this);
	    members.setActionCommand("collaborators");
	}
    }
    
    @Override
    public void run() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setMinimumSize(new Dimension(800, 600));
	this.getContentPane().setLayout(null);
	
	
	canvas.setBackground(Color.WHITE);
	canvas.setLocation(50, 50);
	canvas.setSize(this.getWidth() - 100, this.getHeight() - 100);
	this.getContentPane().add(members);
	this.getContentPane().add(canvas);
	
	this.pack();
	
	showGUI();
    }
    
    private void showGUI(){
	this.setVisible(true);
    }
    
    @Override
    public void update(Graphics g){
	super.update(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	if(ae.getActionCommand().equals("collaborators"))
	    System.out.println("Collaborator Window");
    }
}
