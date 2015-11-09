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
    public Canvas canvas;
    private final Members members;
    private final ArrayList<JButton> toolKit;
    private final ArrayList<Menu> menus;
    private final FigureMenu fm;
    private final SelectionMenu sm;
    File archivo;
    private final JScrollPane js;
    
    //The UI parameters
    public final static int GAP = 50;
    
    public GUI(){
        fm = new FigureMenu();
        sm = new SelectionMenu();
        canvas = new Canvas();
        archivo = new File(canvas);
        js = new JScrollPane(canvas);

	toolKit = new ArrayList<>();{
	    menus = new ArrayList<>();
	    
	    ToolButton file = new ToolButton(GraphicDrawer.FILE);
	    toolKit.add(file);//La constante se define en la clase GraphicDrawer y es para saber que icono dibujar
		file.addActionListener(this);//se define este JFrame como ActionListener de los botones
		file.setActionCommand("file");//se define el actionCommand para saber sobre que botón se realizó la acción
		    Menu fileM = new Menu(file,canvas);
		    menus.add(fileM);//se agrega el menú circular de archivo
                file.setMenu(fileM);
                //file.addMouseListener(fileM);//se agrega el menu circualar de archivo como MouseListener
	    ToolButton action = new ToolButton(GraphicDrawer.REDO);
		toolKit.add(action);
		action.addActionListener(this);
		action.setActionCommand("redo-menu");
		    Menu actionM = new Menu(action,canvas);
                    menus.add(actionM);
                action.setMenu(actionM);
                //action.addMouseListener(actionM);
	    /*ToolButton text = new ToolButton(GraphicDrawer.TEXT);
		toolKit.add(text);
		text.addActionListener(this);
		text.setActionCommand("text");*/
	    ToolButton arrow = new ToolButton(GraphicDrawer.ARROW);
		toolKit.add(arrow);
		arrow.addActionListener(this);
		arrow.setActionCommand("arrow");    
	    ToolButton select = new ToolButton(GraphicDrawer.SELECT);
		toolKit.add(select);
                select.addActionListener(this);
                select.setActionCommand("mano");
	    ToolButton pencil = new ToolButton(GraphicDrawer.PENCIL);
		toolKit.add(pencil);
		pencil.addActionListener(this);
		pencil.setActionCommand("lapiz");
	    ToolButton zoom = new ToolButton(GraphicDrawer.ZOOM);
		toolKit.add(zoom);
                zoom.addActionListener(this);
                zoom.setActionCommand("lupa");
                    Menu zoomM = new Menu(toolKit.get(toolKit.size() - 1),canvas);
		    menus.add(zoomM);
                zoom.setMenu(zoomM);
                //zoom.addMouseListener(zoomM);
	}
	
        canvas.addMouseListener(canvas);
        canvas.addMouseMotionListener(canvas);
        canvas.setName("Canvas"); //Damos un nombre
        this.setName("GUI");
        
        
        
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
        this.getContentPane().add(fm);
        this.getContentPane().add(sm);
        this.getContentPane().add(js);
        this.setIconImage(new ImageIcon("./images/iconoGeneral.png").getImage());
        this.setTitle("Lienzo en blanco - iDraw");
        this.canvas.setMenus();
        js.setPreferredSize(new Dimension(100, 100));
        js.getViewport().setView(canvas);
        js.getViewport().setBackground(Color.red);
        sm.setCanvas(canvas);
        
        
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
        canvas.setLocation(GAP, GAP);
        canvas.setSize(
            new Dimension(getContentPane().getWidth()  - GAP*2,
			  getContentPane().getHeight() - GAP*2));
        canvas.setPreferredSize(new Dimension(getContentPane().getWidth()  - GAP*2,
			  getContentPane().getHeight() - GAP*2));
        canvas.updateUI();
        canvas.setOriginals();
	for(int i = 0; i < toolKit.size(); i++){
	    toolKit.get(i).setLocation
	(getContentPane().getWidth() - GAP, GAP * (1 + i));
	    toolKit.get(i).setSize(GAP, GAP);
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
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./images/lapiz.png").getImage(),new Point(0,0),"lapiz"));
            break;
            case "arrow":
                setCursor(Cursor.getDefaultCursor());
            break;
            case "mano":
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./images/mano.png").getImage(),new Point(0,0),"mano"));
            break;    
        }
    }
    
    public File getFile()
    {return archivo;}
    
    public FigureMenu getFigureMenu()
    {return fm;}
    
    public SelectionMenu getSelectionMenu()
    {return sm;}
    
    public Canvas getCanvas()
    {return this.canvas;}
}
