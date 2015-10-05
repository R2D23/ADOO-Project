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
    private ArrayList<Menu> menus;
    
    //The UI parameters
    private final static int gap = 50;
    
    public GUI(){
	canvas = new JPanel();
	toolKit = new ArrayList<>();{
	    menus = new ArrayList<>();
	    
	    toolKit.add(new ToolButton(GraphicDrawer.FILE));
		toolKit.get(toolKit.size() - 1).addActionListener(this);
		toolKit.get(toolKit.size() - 1).setActionCommand("file");
		    menus.add(new Menu(toolKit.get(toolKit.size() - 1)));
	    toolKit.add(new ToolButton(GraphicDrawer.REDO));
		toolKit.get(toolKit.size() - 1).addActionListener(this);
		toolKit.get(toolKit.size() - 1).setActionCommand("redo-menu");
	    toolKit.add(new ToolButton(GraphicDrawer.TEXT));
		toolKit.get(toolKit.size() - 1).addActionListener(this);
		toolKit.get(toolKit.size() - 1).setActionCommand("text");
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
	
	for (core.Menu menu : menus)
	    getContentPane().add(menu);
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
	    toolKit.get(i).setLocation
	(getContentPane().getWidth() - gap, gap * (1 + i));
	    toolKit.get(i).setSize(gap, gap);
	    toolKit.get(i).updateUI();
	}
	
	for(int i = 0; i < menus.size(); i++){
	    menus.get(i).updateUI();
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
