
package core;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Angeles
 */
public class Escucha implements MouseInputListener {

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
        //seleccionado = null;
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
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion de figura irregular");
            break;
        }
    }
    
    public void clicMenuArchivo(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        int opc=0;
        switch(areaClic)
        {
            case 6 : //esta es el area central que se usa para salir
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.IMAGE :
                try{
                    JFileChooser fci = new JFileChooser();
                    opc=fci.showOpenDialog(null);
                    Imagen im = new Imagen(fci.getSelectedFile().getPath());
                    canvas.addElement(im);
                    canvas.repaint();
                    e.getComponent().setVisible(false);
                }
                catch(Exception ex){}
            break;
            case MenuDrawer.NEW :
                if(((GUI)(((Menu)e.getComponent()).getBoton().getTopLevelAncestor())).getFile().getName() == null)
                {
                    int op = JOptionPane.showConfirmDialog(null, "Estas a punto de cerrar un lienzo sin guardar ¿Continuar?", "Cerrar",YES_NO_OPTION);
                    if(op == YES_OPTION)
                    {
                        ((GUI)(((Menu)e.getComponent()).getBoton().getTopLevelAncestor())).setTitle( "Lienzo en blanco - iDraw");
                        canvas.elements.clear();
                        canvas.repaint();
                    }
                e.getComponent().setVisible(false);    
                }
                            
            break;
            case MenuDrawer.SAVE :
                //System.out.println((((Menu)e.getComponent()).getBoton().getTopLevelAncestor()).getName());
                File archivo = ((GUI)(((Menu)e.getComponent()).getBoton().getTopLevelAncestor())).getFile();
                JFileChooser fcs = new JFileChooser();
                opc=fcs.showSaveDialog(null);
                if(opc == JFileChooser.APPROVE_OPTION){
                    archivo.saveFile(fcs.getSelectedFile().getPath());

                e.getComponent().setVisible(false);    
                }
                
            break;
            case MenuDrawer.OPEN :
                //JOptionPane.showMessageDialog(e.getComponent(),"Abrir Archivo");
                archivo = ((GUI)(((Menu)e.getComponent()).getBoton().getTopLevelAncestor())).getFile();
                JFileChooser fco = new JFileChooser();
                opc=fco.showOpenDialog(null);
                if(opc == JFileChooser.APPROVE_OPTION){
                    //File file = new File(canvas.elements, canvas);
                   canvas.elements=archivo.readFile(fco.getSelectedFile().getPath());
                   canvas.repaint();
                   archivo.setName(fco.getSelectedFile().getName());
                   ((GUI)(((Menu)e.getComponent()).getBoton().getTopLevelAncestor())).setTitle(fco.getSelectedFile().getName() + " - iDraw");
                   ((GUI)(((Menu)e.getComponent()).getBoton().getTopLevelAncestor())).repaint();
                   e.getComponent().setVisible(false);
                }
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
               canvas.doZoom(0.1f);
            break;
            case MenuDrawer.MENOS :
                canvas.doZoom(-0.1f);
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
                canvas.returnPast();
                canvas.repaint();
            break;
            case MenuDrawer.REDO :
                //JOptionPane.showMessageDialog(e.getComponent(),"Rehacer ultima accion");
                canvas.toFuture();
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
                canvas.actElements();
                canvas.seleccionado.state = Element.MOVING;
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.ROTATE :
                canvas.actElements();
                canvas.seleccionado.state = Element.ROTATING;
                for(Element el : canvas.elements)
                    System.out.println(el);
                System.out.println();
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.DISPOSE :
                canvas.actElements();
                canvas.deleteElement(canvas.seleccionado);
                //JOptionPane.showMessageDialog(e.getComponent(),"Eliminar Figura");
                e.getComponent().setVisible(false);
                canvas.repaint();
            break;
            case MenuDrawer.EXIT2 :
                canvas.seleccionado.state = Element.AVAILABLE;
                e.getComponent().setVisible(false);
            break;                   
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Elemento drag : " + seleccionado);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    //System.out.println("se mueve" + this.seleccionado);

        if(canvas.seleccionado != null)
        {
            switch(canvas.seleccionado.state)
            {
                case Element.MOVING:
                    canvas.seleccionado.move(e.getX(), e.getY());                    
                break;
                case Element.ROTATING:
                    canvas.seleccionado.rotate(e.getPoint());
                break;
            }
            
            canvas.repaint();
        }
        
        
        
        /*if(!canvas.elements.isEmpty()) {
            for(int i=0; i<canvas.elements.size(); i++) {
                if(canvas.elements.get(i).state==Element.MOVING) {
                   canvas.elements.get(i).move(e.getX(), e.getY());
                   canvas.elements.get(i).configure(canvas);
                } else if(canvas.elements.get(i).state==Element.ROTATING) {
                    canvas.elements.get(i).rotate(0.0, e.getPoint());
                    System.out.println("rotando");
                    /*double pendiente, newIncline;
                    if(e.getX()-canvas.elements.get(i).posX!=0) {
                        //Element el = canvas.elements.get(i);
                        //Point centro = new Point(el.posX + el.)
                        int Y = e.getY()-canvas.elements.get(i).posY;
                        int X = e.getX()-canvas.elements.get(i).posX;
                        pendiente = Y/X;
                        //System.out.println("" + (e.getY()-canvas.elements.get(i).posY));
                        //System.out.println("" + (e.getX()-canvas.elements.get(i).posX));
                        System.out.println("xM : " + e.getX() + "\tyM : " + e.getY());
                        System.out.println("xE : " + canvas.elements.get(i).posX + "\tyE : " + canvas.elements.get(i).posY);
                        System.out.println("X = " + X + "  Y = "+ Y);
                        System.out.println("" + pendiente);
                        newIncline = Math.atan(pendiente);
                    } else {
                        pendiente = (e.getY()-canvas.elements.get(i).posY)/0.001;
                        newIncline = Math.atan(pendiente);
                    }
                    //System.out.println("Inclinacion: "+Math.toDegrees(newIncline)+" °");
                    canvas.elements.get(i).rotate(newIncline,e.getPoint());
                    canvas.elements.get(i).configure(canvas);
                }
                
            }
            
            //canvas.repaint();
        }*/
    }
    

}
