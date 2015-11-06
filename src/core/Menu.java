package core;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author LuisArturo
 */

/*Clase que implementa los menus que se asocian a los botones
    Estos menus son:
    - Menu de archivo
    - Menu de lupa
    - Menu de deshacer/rehacer
    - */
public class Menu extends JComponent implements MouseListener{
    
    private static final int SIZE = 200;//Tamaño del menu
    private Point location;
    private Point center;
    private JButton b;//Boton al que está asociado
    private ArrayList<Area> areas;//Botones que tiene el menu
    private int areaActual;    //Area/Boton sobre la cual esta el cursor
    public Canvas canvas;
    public Menu(JButton b,Canvas c){
        this.canvas = c;
	setSize(SIZE, SIZE);
	location = new Point();
	center = new Point();
	areas = new ArrayList<>();
        areaActual = -1;//al momento de que se crea el menu, no hay ninguna area seleccionada
        this.b = b;
        
        this.setLocation(b.getLocation());//se asocia la localización del menu con la del boton que lo mando llamar
        
        //dado que cada menu es diferente, se definen diferentes areas para cada uno
        switch (b.getActionCommand()) {
            case "file":
                obtenerAreasFileMenu();
                addMouseListener(new Escucha(areas, Escucha.FILEMENU,canvas));
                break;
            case "lupa":
                obtenerAreasZoomMenu();
                addMouseListener(new Escucha(areas, Escucha.ZOOMMENU,canvas));
                break;
            case "redo-menu":
                obtenerAreasZoomMenu();
                addMouseListener(new Escucha(areas, Escucha.REDOMENU,canvas));
                break;
        }
        
            /*Este MouseMotionListener es el que detecta sobre qué botón está 
            el cursor del mouse y así poder resaltarlo*/
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent me)
            {
                int area = whichArea(me.getPoint());
                updateMenu(area);
            }
        });

        this.setVisible(false);
    }

    /*Esta funcion es un intermedio entre el MouseMotionListener y la clase actual.
    Dado que desde una clase interior no se pueden modificar atributos de la clase
    exterior, se hizo enta funcion.*/
    
    public void updateMenu(int noArea)
    {
        areaActual = noArea;
        this.repaint();
    }
    
    /*La funcion paint es llamada cada vez que la ventana se hace visible, 
    cambia de tamaño o se manda llamar a la funcion repaint*/
    @Override
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	Area s = new Area(new Ellipse2D.Double(0, 0, 200, 200));//Circulo exterior del menu
	s.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));//se le quita el circulo interior
	g2.translate(0, 0);
	g2.setColor(Util.normalColor);//se le da el color
	g2.fill(s);
	
        /*Es este if es donde se resalta el área seleccionada*/
	if(areaActual > 0){
	    g2.setColor(Color.CYAN);
	    g2.fill(areas.get(areaActual));
	}
        g2.setColor(Color.BLACK);
        
        /*Esta parte del codigo es para dibujar las lineas que separan cada uno 
        de los botones*/
        int cX=getWidth()/2;
        int cY=getHeight()/2;
        int bX=getWidth()/3;
        int bY=getHeight()/3;
        int aX=getWidth();
        int aY=getHeight();
        double si = Math.sin(Math.PI/4);
        double se = 1-si;
        int c[] = new int[8];
        c[0]=(int)(cX*se);
        c[1]=(int)(cY*se);
        c[2]=cX-1-((int)(bX*si))/2;
        c[3]=cY-1-(int)((bY*si))/2;
        c[4]=aX-(int)(cX*se);
        c[5]=aY-(int)(cY*se);
        c[6]=cX+((int)(bX*si))/2;
        c[7]=cY+(int)((bY*si))/2;
        g2.drawLine(0, cY, bX, cY);//Esta es la línea que divide la mitad
        if(b.getActionCommand().equals("file"))
        {
            //Las líneas extras que necesita el menu de Archivo
            g2.drawLine(c[0],c[1],c[2],c[3]);
            g2.drawLine(c[0], c[4], c[2], c[6]);
            g2.drawLine(c[7], c[3], c[5], c[1]);
        }
        g2.setColor(Color.black);
	g2.drawOval(SIZE/3, SIZE/3, SIZE/3, SIZE/3);//Dibuja la linea interior del menu
	g2.drawOval(0, 0, SIZE, SIZE);//Dibuja la linea del circulo interior del menu
        
        /*La clase menuDrawer es la que se encarga de dibujar todas las areas 
        y agregarles lo iconos correspondientes*/
        MenuDrawer md = new MenuDrawer();
        switch (b.getActionCommand()) {
            case "file":
                md.paint(MenuDrawer.IMAGE, g, areas.get(1));
                md.paint(MenuDrawer.NEW, g, areas.get(2));
                md.paint(MenuDrawer.OPEN, g, areas.get(3));
                md.paint(MenuDrawer.SAVE, g, areas.get(4));
                break;
            case "lupa":
                md.paint(MenuDrawer.MAS, g, areas.get(1));
                md.paint(MenuDrawer.MENOS, g, areas.get(2));
                break;
            case "redo-menu":
                md.paint(MenuDrawer.UNDO, g, areas.get(1));
                md.paint(MenuDrawer.REDO, g, areas.get(2));
                break;
        }
        
    }

    /*Se agrega un Mouse Listener y se implementan sus métodos, esto para poder saber cuando se 
    ha dado clic sobre el lienzo y por lo tanto, se debe mostrar el menu*/
    @Override
    //Esta parte también es temporal, mientras se define bien las acciones del ratón,
    public void mouseClicked(MouseEvent e) {
        switch(e.getButton())
        {
            case MouseEvent.BUTTON1 ://clic izquierdo sobre el boton asociado
                this.setVisible(true);//se muestra
                Point centroBoton = new Point(b.getX() + b.getWidth()/2, b.getY() + b.getHeight()/2);
                location.setLocation(centroBoton.getX() - SIZE/2, centroBoton.getY() - SIZE/2);
                this.setLocation(location);//se define la localizacion.
            break;
            case MouseEvent.BUTTON3 :
                //este case es por si alguna vez se necesita
            break;
                
        }
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        areaActual = -1;
    }
    
    //funcion que regresa el area sobre la cual esta el punto p,
    //si el punto no está en ninguna area, devuelve -1
    public int whichArea(Point p)
    {
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
                return i;
        return -1;
    }
    
    public final void obtenerAreasFileMenu()
    {
        Area gen = new Area(new Ellipse2D.Double(0, 0, 200, 200));//area general a partir de la cual se obtinen las demas
	gen.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));//se le resta el circulo interior
        
        //Las areas de los botones se definen mediante la intersección del area general con poligonos
        Polygon p = new Polygon();
        //AREA CENTRAL : USADA PARA SALIR
            Area s = new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3));
            areas.add(s);
        //AREA IMAGEN
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, 0);
	    p.addPoint(SIZE, 0);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA NUEVO
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, 0);
            p.addPoint(0, SIZE/2);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA ABRIR
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE/2);
	    p.addPoint(0, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA GUARDAR
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE);
	    p.addPoint(SIZE, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
    }
    
    public final void obtenerAreasZoomMenu()
    {
        Area gen = new Area(new Ellipse2D.Double(0, 0, 200, 200));
	gen.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));
        
        Polygon p;
        
        //AREA CENTRAL USADA PARA SALIR
            Area s = new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3));
            areas.add(s);
        //AREA MAS
            p = new Polygon();
            p.addPoint(0, 0);
            p.addPoint(SIZE,0);
            p.addPoint(SIZE, SIZE/2);
	    p.addPoint(0,SIZE/2);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA MENOS
            p = new Polygon();
            p.addPoint(0, SIZE/2);
            p.addPoint(0, SIZE);
            p.addPoint(SIZE, SIZE);
	    p.addPoint(SIZE, SIZE/2);
            
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
        
    }
    
    public JButton getBoton()
    {return this.b;}
}

