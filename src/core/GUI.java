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
    private FigureMenu fm;
    private SelectionMenu sm;
    
    //The UI parameters
    private final static int gap = 50;
    
    public GUI(){
	canvas = new JPanel();
        fm = new FigureMenu();
        sm = new SelectionMenu();
        
	toolKit = new ArrayList<>();{
	    menus = new ArrayList<>();
	   //se crean las instancias de los botones de herramientas
	    toolKit.add(new ToolButton(GraphicDrawer.FILE));//La constante se define en la clase GraphicDrawer y es para saber que icono dibujar
		toolKit.get(toolKit.size() - 1).addActionListener(this);//se define este JFrame como ActionListener de los botones
		toolKit.get(toolKit.size() - 1).setActionCommand("file");//se define el actionCommand para saber sobre que botón se realizó la acción
		    menus.add(new Menu(toolKit.get(toolKit.size() - 1)));//se agrega el menú circular de archivo
                toolKit.get(toolKit.size()-1).addMouseListener(menus.get(menus.size()-1));//se agrega el menu circualar de archivo como MouseListener
	    toolKit.add(new ToolButton(GraphicDrawer.REDO));
		toolKit.get(toolKit.size() - 1).addActionListener(this);
		toolKit.get(toolKit.size() - 1).setActionCommand("redo-menu");
                    menus.add(new Menu(toolKit.get(toolKit.size() - 1)));
                toolKit.get(toolKit.size()-1).addMouseListener(menus.get(menus.size()-1));
	    toolKit.add(new ToolButton(GraphicDrawer.TEXT));
		toolKit.get(toolKit.size() - 1).addActionListener(this);
		toolKit.get(toolKit.size() - 1).setActionCommand("text");
            toolKit.add(new ToolButton(GraphicDrawer.ARROW));
		toolKit.get(toolKit.size() - 1).addActionListener(this);
		toolKit.get(toolKit.size() - 1).setActionCommand("arrow");    
            toolKit.add(new ToolButton(GraphicDrawer.SELECT));
                toolKit.get(toolKit.size()-1).addActionListener(this);
                toolKit.get(toolKit.size()-1).setActionCommand("mano");
            toolKit.add(new ToolButton(GraphicDrawer.PENCIL));
		toolKit.get(toolKit.size() - 1).addActionListener(this);
		toolKit.get(toolKit.size() - 1).setActionCommand("lapiz");
            toolKit.add(new ToolButton(GraphicDrawer.ZOOM));
                toolKit.get(toolKit.size()-1).addActionListener(this);
                toolKit.get(toolKit.size()-1).setActionCommand("lupa");
                    menus.add(new Menu(toolKit.get(toolKit.size() - 1)));
                toolKit.get(toolKit.size()-1).addMouseListener(menus.get(menus.size()-1));
                    
	}
	
        canvas.addMouseListener(fm);//al lienzo se agrega el menu de figuras como MouseListener
        canvas.addMouseListener(sm);//al lienzo se agrega el menu de seleccion como MouseListener, esto es provisional mientras se agregan las figuras
        
        members = new Members();{//se crean la instancia del boton colaboradores
	members.addActionListener(this);
	members.setActionCommand("collaborators");
	}
    }
    
    @Override
    public void run() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setMinimumSize(new Dimension(800, 600));
	this.getContentPane().setLayout(null);

        this.getContentPane().add(fm);//se agrega a este JFrame el menu de figura
        this.getContentPane().add(sm);//se agrega el menu de seleccion
	
        for (core.Menu menu : menus) //se agregan todos los menus
	  getContentPane().add(menu);

        this.getContentPane().add(members);//se agrega el boton miembros
	this.getContentPane().add(canvas);
	for(int i = 0; i < toolKit.size(); i++)//Se agregan los botones de herramientas
	    getContentPane().add(toolKit.get(i));
	
	pack();
	updateGUI();
	showGUI();
    }
    
    public void updateGUI(){
        canvas.setBackground(Color.WHITE);
        
	canvas.setLocation(gap, gap);//se coloca el lienzo
	canvas.setSize(
		new Dimension(	getContentPane().getWidth()  - gap*2,
				getContentPane().getHeight() - gap*2));
	canvas.updateUI();//metodo que actualiza los componentes graficos del elemento
	
	for(int i = 0; i < toolKit.size(); i++){//se define la pocision de los botones
	    toolKit.get(i).setLocation
	(getContentPane().getWidth() - gap, gap * (1 + i));
	    toolKit.get(i).setSize(gap, gap);
	    toolKit.get(i).updateUI();
	}
	
	for(int i = 0; i < menus.size(); i++){
	    menus.get(i).updateUI();
	}
	
	fm.updateUI();
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
        switch (ae.getActionCommand()) {//detecta quien dio clic y cambia el cursor al que sea necesario
            case "lapiz":
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("lapiz.png").getImage(),new Point(0,0),"lapiz"));
            break;
            case "arrow":
                setCursor(Cursor.getDefaultCursor());
            break;
            case "mano":
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("mano.png").getImage(),new Point(0,0),"mano"));
            break;    
            case "text":
                setCursor(new Cursor(Cursor.TEXT_CURSOR));
                
            break;
                
        }
    }
    
    
}
