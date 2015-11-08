
package core;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static core.Action.*;
import java.awt.Graphics;

/**
 *
 * @author Angeles
 */
public class Escucha implements MouseListener, MouseMotionListener {

    private ArrayList<Area> areas;
    private int tipoMenu;
    public static final int FIGUREMENU = 0;
    public static final int FILEMENU = 1;
    public static final int ZOOMMENU = 2; //menu de zoom
    public static final int REDOMENU = 3; //menu de hacer/deshacer
    public static final int SELECTIONMENU = 4;//menu de seleccion
    Canvas canvas;
    
    public Escucha(Canvas canvas) {
        this.canvas = canvas;
    }
    
    public Escucha(ArrayList<Area> as, int t, Canvas canvas)
    {
       areas = as;
       tipoMenu = t;
       this.canvas = canvas;
    }

    /*Escucha(ArrayList<Area> as, int t) {
        areas = as;
        tipoMenu = t;
    }*/
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(tipoMenu)
        {
            case FIGUREMENU :
                clicMenuFigura(e);
            break;
            case FILEMENU :
                clicMenuArchivo(e);
            break;
            case ZOOMMENU : 
                clicMenuZoom(e);
            break;
            case REDOMENU :
                clicMenuRedo(e);
            break;
            case SELECTIONMENU :
                clicMenuSeleccion(e);
            break;
        }
        //canvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //canvas.drawAll();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //canvas.drawAll();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //canvas.drawAll();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //canvas.drawAll();
    }
    
    //Funcion para determinar en qué 
    //area del menu circular se ha hecho clic
    public int whichArea(Point p)
    {
        int r = -1;
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
            {
                switch(tipoMenu)
                {
                    case FIGUREMENU :
                        r = i;
                    break;
                    case FILEMENU :
                        r = i + MenuDrawer.EXIT; 
                        /*como las constantes de MenuDrawer se declaran incrementalmente y los indices del ArrayList
                        siempre comienzan desde cero, se tiene que sumar esta constante para obtener el valor correcto de la constante*/
                    break;
                    case ZOOMMENU :
                        r = i + MenuDrawer.SAVE;
                    break;
                    case REDOMENU :
                        r = i + MenuDrawer.MENOS;
                    break;
                    case SELECTIONMENU :
                        r = i + MenuDrawer.CONFIGURE;
                    break;
                }
                return r;
            }
        return -1;
    }
    
    public void clicMenuFigura(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        switch(areaClic)
        {
            case MenuDrawer.EXIT :
                e.getComponent().setVisible(false);
                //canvas.repaint();
            break;
            case MenuDrawer.IRREGULAR :
                //JOptionPane.showMessageDialog(e.getComponent(),"Configuracion de figura irregular");
                Irregular fig = new Irregular();
                e.getComponent().setVisible(false);
                fig.state = Element.GETTINGPOINTS;
                fig.newPoint(e.getLocationOnScreen());
                canvas.addElement(fig);
            break;
        }
    }
    
    public void clicMenuArchivo(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        System.out.println("AREA CLIC : " + areaClic);
        int opc=0;
        switch(areaClic)
        {
            case 6 : //esta es el area central que se usa para salir
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.IMAGE :
                JOptionPane.showMessageDialog(e.getComponent(),"Importar imagen");
            break;
            case MenuDrawer.NEW :
                JOptionPane.showMessageDialog(e.getComponent(),"Nuevo Archivo");
            break;
            case MenuDrawer.SAVE :
                //JOptionPane.showMessageDialog(e.getComponent(),"Guardar Archivo");
                JFileChooser fcs = new JFileChooser();
                opc=fcs.showSaveDialog(null);
                if(opc == JFileChooser.APPROVE_OPTION){
                    SaveCanvas file = new SaveCanvas(canvas.elements);
                    file.saveFile(fcs.getSelectedFile().getPath());
                }
                
            break;
            case MenuDrawer.OPEN :
                //JOptionPane.showMessageDialog(e.getComponent(),"Abrir Archivo");
                JFileChooser fco = new JFileChooser();
                opc=fco.showOpenDialog(null);
                if(opc == JFileChooser.APPROVE_OPTION){
                    SaveCanvas file = new SaveCanvas(canvas.elements);
                    canvas.elements=file.readFile(fco.getSelectedFile().getPath());
                    canvas.repaint();
                }
                fco.getName();
            break;
        }
    }
   
    public void clicMenuZoom(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        System.out.println("AREA CLIC : " + areaClic);
        switch(areaClic)
        {
            case 10 :
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.MAS :
                JOptionPane.showMessageDialog(e.getComponent(),"Hacer zoom +");
            break;
            case MenuDrawer.MENOS :
                JOptionPane.showMessageDialog(e.getComponent(),"Hacer zoom -");
            break;
        }
    }
    
    public void clicMenuRedo(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        System.out.println("AREA CLIC : " + areaClic);
        switch(areaClic)
        {
            case 12 :
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.UNDO :
                //JOptionPane.showMessageDialog(e.getComponent(),"Deshacer ultima accion");
		if(undoStack.isEmpty())
		    System.out.println("There are no action to be Undone");
		else
		    undo();
                canvas.repaint();
            break;
            case MenuDrawer.REDO :
                //JOptionPane.showMessageDialog(e.getComponent(),"Rehacer ultima accion");
                if(redoStack.isEmpty())
		    System.out.println("There are no action to be Redone");
		else
		    redo();
                canvas.repaint();
            break;
        }    
    }

    public void clicMenuSeleccion(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        switch(areaClic)
        {
            case MenuDrawer.MOVE :
                //JOptionPane.showMessageDialog(e.getComponent(),"Mover Figura");
                //System.out.println("ANTES: "+((SelectionMenu)(e.getComponent())).elemento.state);
                canvas.actElements();
                ((SelectionMenu)(e.getComponent())).elemento.state = Element.MOVING;
                //System.out.println("DESPUES: "+((SelectionMenu)(e.getComponent())).elemento.state);
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.ROTATE :
                canvas.actElements();
                //JOptionPane.showMessageDialog(e.getComponent(),"Rotar Figura");
                ((SelectionMenu)(e.getComponent())).elemento.state = Element.ROTATING;
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.DISPOSE :
                canvas.actElements();
                JOptionPane.showMessageDialog(e.getComponent(),"Eliminar Figura");
            break;
            case MenuDrawer.EXIT2 :
                e.getComponent().setVisible(false);
            break;   
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("dragg");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!canvas.elements.isEmpty()) {
            for(int i=0; i<canvas.elements.size(); i++) {
                if(canvas.elements.get(i).state==Element.MOVING) {
                   canvas.elements.get(i).move(e.getX(), e.getY());
                   canvas.elements.get(i).configure(canvas);
                } else if(canvas.elements.get(i).state==Element.ROTATING) {
                    double pendiente, newIncline;
                    if(e.getX()-canvas.elements.get(i).posX!=0) {
                        pendiente = (e.getY()-canvas.elements.get(i).posY)/(e.getX()-canvas.elements.get(i).posX);
                        newIncline = Math.atan(pendiente);
                    } else {
                        pendiente = (e.getY()-canvas.elements.get(i).posY)/0.001;
                        newIncline = Math.atan(pendiente);
                    }
                    //System.out.println("Inclinacion: "+Math.toDegrees(newIncline)+" °");
                    canvas.elements.get(i).rotate(newIncline);
                    canvas.elements.get(i).configure(canvas);
                } else if(canvas.elements.get(i).state==Element.GETTINGPOINTS) {
                    if(!((Irregular)(canvas.elements.get(i))).vertices.isEmpty()) {
                        ArrayList<Point> ps = ((Irregular)(canvas.elements.get(i))).vertices;
                        Point lastPoint = ps.get(ps.size()-1);
                        Graphics g = canvas.getGraphics();
                        g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY()+30);
                    }
                }
            }
            canvas.repaint();
        }
    }
}
