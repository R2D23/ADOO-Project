
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
    Canvas canvas;
    
    public Escucha(ArrayList<Area> as, int t, Canvas canvas)
    {
       areas = as;
       tipoMenu = t;
       this.canvas = canvas;
    }

    Escucha(ArrayList<Area> as, int t) {
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
        canvas.drawAll();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.drawAll();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        canvas.drawAll();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        canvas.drawAll();
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
        Element fig = null;
        switch(areaClic)
        {
            case MenuDrawer.CIRCLE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del circulo");
                fig = new Circle(); //Definimos que la figura es un Circulo
            break;
            case MenuDrawer.EXIT :
                e.getComponent().setVisible(false);
            break;
            case MenuDrawer.IRREGULAR :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion de figura irregular");
            break;
            case MenuDrawer.LINE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion de la linea");
                fig = new Line();
            break;
            case MenuDrawer.POLIGONE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del poligono");
                fig = new RegularPolygon();
            break;
            case MenuDrawer.RECTANGLE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del rectángulo");
                fig = new Rectangle(); //Definimos que la figura es un Rectangulo
            break;
            case MenuDrawer.TRIANGLE :
                JOptionPane.showMessageDialog(e.getComponent(),"Configuracion del triangulo");
                fig = new Triangle();    
            break;
        }
        if(fig!=null) { //Comprobamos que se haya instanciado
            fig.posX = e.getComponent().getLocation().x;
            fig.posY = e.getComponent().getLocation().y;
            fig.configure(canvas); //Abrimos el menu de configuracion de la figura
            fig.draw(canvas); //Dibujamos la figura
            canvas.drawAll(); //Dibujamos todo otra vez
        }
    }
    
    public void clicMenuArchivo(MouseEvent e)
    {
        
    }
    
    
}
