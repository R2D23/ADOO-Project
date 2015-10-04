/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author LuisArturo
 */
public class GUI extends JFrame implements Runnable, ActionListener{
    //The Components
    private JPanel canvas;
    private Members members;
    private ArrayList<JButton> toolKit;
    
    //The UI parameters
    private final static int gap = 50;
    
    public GUI(){
	canvas = new JPanel();
	toolKit = new ArrayList<JButton>();{
	    toolKit.add(new ToolButton(GraphicDrawer.FILE));
		toolKit.get(0).addActionListener(this);
		toolKit.get(0).setActionCommand("file");
	}
	
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
	
	this.getContentPane().add(members);
	this.getContentPane().add(canvas);
	for(int i = 0; i < toolKit.size(); i++)
	    getContentPane().add(toolKit.get(i));
	pack();
	updateGUI();
	showGUI();
    }
    
    public void updateGUI(){
	canvas.setBackground(Color.WHITE);
	canvas.setLocation(gap, gap);
	canvas.setSize(
		new Dimension(	getContentPane().getWidth()  - gap*2,
				getContentPane().getHeight() - gap*2));
	canvas.updateUI();
	
	for(int i = 0; i < toolKit.size(); i++){
	    toolKit.get(0).setLocation
	(getContentPane().getWidth() - gap, gap * (1 + i));
	    toolKit.get(0).setSize(gap, gap);
	    toolKit.get(0).updateUI();
	}
    }
    
    private void showGUI(){
	this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
	super.paint(g);
	updateGUI();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	System.out.println(ae.getActionCommand());
    }
}
