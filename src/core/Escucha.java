
package core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Angeles
 */
public class Escucha implements MouseListener{

    private ArrayList<Area> areas;
    private int tipoMenu;//Esto es para que este mismo Listener se pueda aplicar a todos los menus que tenemos
    public static final int FIGUREMENU = 0; //menu para crear figura
    public static final int FILEMENU = 1;//menu de archivo
    public static final int ZOOMMENU = 2; //menu de zoom
    public static final int REDOMENU = 3; //menu de hacer/deshacer
    public static final int SELECTIONMENU = 4;//menu de seleccion
    
    public Escucha(ArrayList<Area> as, int t)
    {
       areas = as;
       tipoMenu = t;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(tipoMenu)//de acuerdo con el tipo de Menu, se decide qué opciones realizar
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
                }
                return r;
            }
        return -1;
    }
    
    public void clicMenuFigura(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());//se obtiene sobre qué harea se dio clic
        switch(areaClic)
        {
            case MenuDrawer.CIRCLE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del circulo");
            break;
            case MenuDrawer.EXIT :
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.IRREGULAR :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion de figura irregular");
            break;
            case MenuDrawer.LINE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion de la linea");
            break;
            case MenuDrawer.POLIGONE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del poligono");
            break;
            case MenuDrawer.RECTANGLE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del rectángulo");
            break;
            case MenuDrawer.TRIANGLE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del triangulo");
            break;    
        }
    }
    
    public void clicMenuArchivo(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        System.out.println("AREA CLIC : " + areaClic);
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
                fcs.showSaveDialog(null);
            break;
            case MenuDrawer.OPEN :
                //JOptionPane.showMessageDialog(e.getComponent(),"Abrir Archivo");
                JFileChooser fco = new JFileChooser();
                fco.showOpenDialog(null);
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
                JOptionPane.showMessageDialog(e.getComponent(),"Deshacer ultima accion");
            break;
            case MenuDrawer.REDO :
                JOptionPane.showMessageDialog(e.getComponent(),"Rehacer ultima accion");
            break;
        }    
    }

    public void clicMenuSeleccion(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
        switch(areaClic)
        {
        
            case MenuDrawer.CONFIGURE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion de figura");
            break;
            case MenuDrawer.MOVE :
                JOptionPane.showMessageDialog(e.getComponent(),"Mover Figura");
            break;
            case MenuDrawer.ROTATE :
                JOptionPane.showMessageDialog(e.getComponent(),"Rotar Figura");
            break;
            case MenuDrawer.DISPOSE :
                JOptionPane.showMessageDialog(e.getComponent(),"Eliminar Figura");
            break;
            case 19 :
                e.getComponent().setVisible(false);
            break;        
        }
    }
    
}
