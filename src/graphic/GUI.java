
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
    private static FigureMenu fm; //Circular Create Figure
    private static SelectionMenu sm; //Circular Selection Menu
    private static File archivo;
    private static JScrollPane js;
    public static PanelColaboradores pc;
    public static JFrame frame;
    
    public static Dibujante dibujante;
    public static boolean permiso;//El permiso que el dibujante tiene sobre este archivo
    
    //The UI parameters
    public final static int GAP = 50;

    public static void setTitle(String s) {
	frame.setName(s);
        frame.setTitle(s);
    }

    public static String getName() {
	return frame.getName();
    }
    
    public static void initializeGUI() {
        
       
        
	Canvas.initializeCanvas();
	ActionListener al = (ActionEvent ae) -> {
	    GUI.actionPerformed(ae);
	};
        permiso = true;
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
        frame.getContentPane().add(pc);
        frame.getContentPane().add(members);
        frame.getContentPane().add(fm);
        frame.getContentPane().add(sm);
        for (graphic.Menu menu : menus)
	  frame.getContentPane().add(menu);
        
        frame.getContentPane().add(js);

        
        frame.setIconImage(new ImageIcon("./images/iconoGeneral.png").getImage());
        frame.setTitle("Lienzo en blanco - iDraw");
        
        
        
        
        

        
        //frame.getContentPane().add(panel);
        
	for(int i = 0; i < toolKit.size(); i++)
	    frame.getContentPane().add(toolKit.get(i));
	
	
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ConexionServer cs = new ConexionServer();
                cs.establecerConexion();
                String [] aux = {GUI.getFile().getName(), GUI.getFile().getOwner()};
                cs.enviarMensaje(new Mensaje(ConexionServer.cerrarSesion,GUI.dibujante.nomUsuario, aux));
                cs.cerrarConexion();
                //cs.enviarMensaje(new Mensaje(ConexionServer.cerrarSesion,dibujante.nomUsuario, ""));
            }
        });
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
        
        new Thread(new RecibidorActualizaciones()).start();
    }
    
    public static void updateGUI(){
	js.setLocation(GAP,GAP);
        js.setSize(new Dimension(frame.getContentPane().getWidth()  - GAP*2,
			  frame.getContentPane().getHeight() - GAP*2));
        
        panel.setBackground(Color.WHITE);
        panel.setLocation(0, 0);
        panel.setSize(
            new Dimension(frame.getContentPane().getWidth()  - GAP*2 - 30,
			  frame.getContentPane().getHeight() - GAP*2 - 30));
        panel.setPreferredSize(new Dimension(
		frame.getContentPane().getWidth()  - GAP*2 - 50,
		frame.getContentPane().getHeight() - GAP*2 - 50));
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
        
        
        
        Canvas.setOrigSize(panel.getPreferredSize());
        
        
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
    
    public static Cursor getCursor()
    {return frame.getCursor();}
    
    
    public static void showSelectionMenu(MouseEvent e)
    {
        sm.setCenter(e.getPoint());
        sm.obtLocation().setLocation(e.getX() - sm.SIZE/2 + GUI.GAP, e.getY() - sm.SIZE/2 + GUI.GAP);
        sm.setLocation(sm.obtLocation());
        sm.setVisible(true);
    }
    
    public static FigureMenu getFigureMenu()
    {return fm;}
    
    public static SelectionMenu getSelectionMenu()
    {return sm;}
    
    public static void setDibujante(String s)
    {dibujante.nomUsuario = s;}
    
    /*public static void main(String [] args)
    {initializeGUI();}*/
    
    public static void showFigureMenu(MouseEvent e)
    {
        fm.setCenter(new Point(e.getPoint().x,e.getPoint().y));        
        fm.obtLocation().setLocation(e.getX() - fm.SIZE/2 + GUI.GAP, e.getY() - fm.SIZE/2 + GUI.GAP);
        fm.setLocation(fm.obtLocation());
        fm.setVisible(true);
    }
    
    
    public static boolean areMenusOpened()
    {
        boolean aux = false;
        aux = aux || sm.isVisible();
        aux = aux || fm.isVisible();
        for(graphic.Menu menu : menus)
            aux = aux || menu.isVisible();
        return aux;
    }
    
    public static void closeAllMenus()
    {
        sm.setVisible(false);
        fm.setVisible(false);
        for(graphic.Menu menu : menus)
            menu.setVisible(false);
        pc.setVisible(false);
        members.setLocation(0, members.getY());
    }
    
    public static void setFile(File f)
    {archivo = f;}
    
    public static PanelColaboradores getPanelColaboradores()
    {return pc;}
    
}
