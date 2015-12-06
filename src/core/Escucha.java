
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
import static core.Action.*;

//Importing Static Project Variables
import static core.GUI.archivo;

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
    
    public Escucha(ArrayList<Area> as, int t)
    {
       areas = as;
       tipoMenu = t;
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
            case FILEMENU :
                clicMenuArchivo(e);
            break;
            case ZOOMMENU : 
                clicMenuZoom(e);
            break;
            case REDOMENU :
                clicMenuRedo(e);
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
                    Canvas.addElement(im);
                    Canvas.repaint();
                    e.getComponent().setVisible(false);
                }
                catch(Exception ex){}
            break;
            case MenuDrawer.NEW :
                if(GUI.getName() == null)
                {
                    int op = JOptionPane.showConfirmDialog(null, "Estas a punto de cerrar un lienzo sin guardar ¿Continuar?", "Cerrar",YES_NO_OPTION);
                    if(op == YES_OPTION)
                    {
                        GUI.setTitle( "Lienzo en blanco - iDraw");
                        Canvas.elements.clear();
                        Canvas.repaint();
                    }
                e.getComponent().setVisible(false);    
                }
                            
            break;
            case MenuDrawer.SAVE :
                //System.out.println((((Menu)e.getComponent()).getBoton().getTopLevelAncestor()).getName());
                JFileChooser fcs = new JFileChooser();
                opc=fcs.showSaveDialog(null);
                if(opc == JFileChooser.APPROVE_OPTION){
//                    archivo.saveFile(fcs.getSelectedFile().getPath());

                e.getComponent().setVisible(false);    
                }
                
            break;
            case MenuDrawer.OPEN :
                //JOptionPane.showMessageDialog(e.getComponent(),"Abrir Archivo");
                JFileChooser fco = new JFileChooser();
                opc=fco.showOpenDialog(null);
                if(opc == JFileChooser.APPROVE_OPTION){
                    //File file = new File(canvas.elements, canvas);
                   //canvas.elements=archivo.readFile(fco.getSelectedFile().getPath());
                   Canvas.repaint();
                   archivo.setName(fco.getSelectedFile().getName());
                   GUI.setTitle(fco.getSelectedFile().getName() + " - iDraw");
                   GUI.repaint();
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
//               canvas.doZoom(0.1f);
            break;
            case MenuDrawer.MENOS :
  //             canvas.doZoom(-0.1f);
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
                Canvas.repaint();
            break;
            case MenuDrawer.REDO :
                //JOptionPane.showMessageDialog(e.getComponent(),"Rehacer ultima accion");
                if(redoStack.isEmpty())
		    System.out.println("There are no action to be Redone");
		else
		    redo();
                Canvas.repaint();
            break;
        }    
    }

    
    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Elemento drag : " + seleccionado);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    
    }
    

}
