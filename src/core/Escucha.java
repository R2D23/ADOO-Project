
package core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Angeles
 */
public class Escucha implements MouseListener{

    private ArrayList<Area> areas;
    private int tipoMenu;
    public static final int FIGUREMENU = 0;
    public static final int FILEMENU = 1;
    
    public Escucha(ArrayList<Area> as, int t)
    {
       areas = as;
       tipoMenu = t;
    }
    
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
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
                return i;
        return -1;
    }
    
    public void clicMenuFigura(MouseEvent e)
    {
        int areaClic = whichArea(e.getPoint());
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
        
    }
    
    
}
