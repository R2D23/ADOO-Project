
package graphic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import core.*;

//Importing Static Project Variables
import static graphic.Canvas.panel;

/**
 *
 * @author LuisArturo
 */
public class GUI {
    //The Components
    private static Members members; //The Collaborators Button
    private static ArrayList<JButton> toolKit; //The list of Tool Buttons
    private static ArrayList<Menu> menus; //Circular Menus
    public static FigureMenu fm; //Circular Create Figure
    public static SelectionMenu sm; //Circular Selection Menu
    public static File archivo;
    private static JScrollPane js;
    public static PanelColaboradores pc;
    public static JFrame frame;
    public static Dibujante dibujante;
    public static String permiso;//El permiso que el dibujante tiene sobre este archivo
    
    //The UI parameters
    public final static int GAP = 50;

    public static void setTitle(String s) {
	frame.setName(s);
    }

    public static String getName() {
	return frame.getName();
    }
    
    public static void initializeGUI() {
	Canvas.initializeCanvas();
	ActionListener al = (ActionEvent ae) -> {
	    GUI.actionPerformed(ae);
	};
        fm = new FigureMenu();
        sm = new SelectionMenu();
        archivo = new File();
        js = new JScrollPane(panel);
        pc = new PanelColaboradores();
        dibujante = new Dibujante();
        

	toolKit = new ArrayList<>();{
	    menus = new ArrayList<>();
	    
	    ToolButton file = new ToolButton(GraphicDrawer.FILE);
	    toolKit.add(file);//La constante se define en la clase GraphicDrawer y es para saber que icono dibujar
		file.addActionListener(al);//se define este JFrame como ActionListener de los botones
		file.setActionCommand("file");//se define el actionCommand para saber sobre que botón se realizó la acción
		    Menu fileM = new Menu(file);
		    menus.add(fileM);//se agrega el menú circular de archivo
                file.setMenu(fileM);
	    ToolButton action = new ToolButton(GraphicDrawer.REDO);
		toolKit.add(action);
		action.addActionListener(al);
		action.setActionCommand("redo-menu");
		    Menu actionM = new Menu(action);
                    menus.add(actionM);
                action.setMenu(actionM);
	    ToolButton arrow = new ToolButton(GraphicDrawer.ARROW);
		toolKit.add(arrow);
		arrow.addActionListener(al);
		arrow.setActionCommand("arrow");    
	    ToolButton select = new ToolButton(GraphicDrawer.SELECT);
		toolKit.add(select);
                select.addActionListener(al);
                select.setActionCommand("mano");
	    ToolButton pencil = new ToolButton(GraphicDrawer.PENCIL);
		toolKit.add(pencil);
		pencil.addActionListener(al);
		pencil.setActionCommand("lapiz");
	    ToolButton zoom = new ToolButton(GraphicDrawer.ZOOM);
		toolKit.add(zoom);
                zoom.addActionListener(al);
                zoom.setActionCommand("lupa");
                    Menu zoomM = new Menu(toolKit.get(toolKit.size() - 1));
		    menus.add(zoomM);
                zoom.setMenu(zoomM);
                //zoom.addMouseListener(zoomM);
	}
	frame = new JFrame("GUI");
	members = new Members();{
            members.addActionListener(al);
            members.setActionCommand("collaborators");
            members.setPanel(pc);
	    }
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setMinimumSize(new Dimension(800, 600));
	frame.getContentPane().setLayout(null);
        frame.getContentPane().add(fm);
        frame.getContentPane().add(sm);
        frame.getContentPane().add(js);
        frame.setIconImage(new ImageIcon("./images/iconoGeneral.png").getImage());
        frame.setTitle("Lienzo en blanco - iDraw");
        js.setPreferredSize(new Dimension(100, 100));
        //js.getViewport().setView(panel);
        //js.getViewport().setBackground(Color.red);
        
        
        for (graphic.Menu menu : menus)
	  frame.getContentPane().add(menu);

        frame.getContentPane().add(pc);
        frame.getContentPane().add(members);
        frame.getContentPane().add(panel);
        
	for(int i = 0; i < toolKit.size(); i++)
	    frame.getContentPane().add(toolKit.get(i));
	
	
	frame.addComponentListener(new ComponentListener(){

	    @Override
	    public void componentResized(ComponentEvent ce) {
		repaint();
	    }

	    @Override
	    public void componentMoved(ComponentEvent ce) {}

	    @Override
	    public void componentShown(ComponentEvent ce) {}

	    @Override
	    public void componentHidden(ComponentEvent ce) {}
	    
	});
	
	frame.pack();
	updateGUI();
	showGUI();
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
	    @Override
	    public void run(){
		showGUI();
	    }
	});
    }
    
    public static void updateGUI(){
	panel.setBackground(Color.WHITE);
        panel.setLocation(GAP, GAP);
        panel.setSize(
            new Dimension(frame.getContentPane().getWidth()  - GAP*2,
			  frame.getContentPane().getHeight() - GAP*2));
        panel.setPreferredSize(new Dimension(
		frame.getContentPane().getWidth()  - GAP*2,
		frame.getContentPane().getHeight() - GAP*2));
        panel.updateUI();
	for(int i = 0; i < toolKit.size(); i++){
	    toolKit.get(i).setLocation
	(frame.getContentPane().getWidth() - GAP, GAP * (1 + i));
	    toolKit.get(i).setSize(GAP, GAP);
	    toolKit.get(i).updateUI();
	}
	
	for(Menu m : menus){
	    m.updateUI();
	}
        fm.updateUI();
        
    }
    
    private static void showGUI(){
	frame.setVisible(true);
    }
    
    public static void repaint(){
	frame.repaint();
	updateGUI();
    }

    public static void actionPerformed(ActionEvent ae) {
	System.out.println(ae.getActionCommand());
        switch (ae.getActionCommand()) {//detecta quien dio clic y cambia el cursor al que sea necesario
            case "lapiz":
                frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./images/lapiz.png").getImage(),new Point(0,0),"lapiz"));
            break;
            case "arrow":
                frame.setCursor(Cursor.getDefaultCursor());
            break;
            case "mano":
                frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./images/mano.png").getImage(),new Point(0,0),"mano"));
            break;    
        }
    }
    
    public static File getFile()
    {return archivo;}
    
    public FigureMenu getFigureMenu()
    {return fm;}
    
    public SelectionMenu getSelectionMenu()
    {return sm;}
    
    public static void setDibujante(String s)
    {dibujante.nomUsuario = s;}
    
    public static PanelColaboradores getPanelColaboradores()
    {return pc;}
}
