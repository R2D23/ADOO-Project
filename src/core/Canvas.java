
package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author douxm_000
 */
public class Canvas extends JPanel {
    public ArrayList<Element> elements;
    FigureMenu fm;
    SelectionMenu sm;
    public Canvas() {
        elements = new ArrayList<>();
        addMouseMotionListener(new Escucha(this));
    }
    
    //Agrega un elemento a la lista de Elementos sobre el Lienzo.
    public void addElement(Element e) {
        elements.add(e);
    }
    
    //Elimina un elemento siempre y cuando lo contenga
    public void deleteElement(Element e) {
        if(elements.contains(e))
            elements.remove(e);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for(int i = 0; i < elements.size(); i++)
        {
            elements.get(i).draw(g);
        }
        
    }
}
