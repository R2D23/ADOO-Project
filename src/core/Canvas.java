
package core;

import static core.Action.*;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author douxm_000
 */
public class Canvas extends JPanel implements Serializable {
    public static ArrayList<Element> elements;
    private static FigureMenu fm;
    private static SelectionMenu sm;
    public ArrayList<Element> pastelements;
    public ArrayList<Element> futureelements;
    public Canvas() {
        elements = new ArrayList<>();
        addMouseMotionListener(new Escucha(this));
        pastelements=null;
        futureelements=null;
    }
    
     public void actElements(){
        pastelements=cloneList(elements);
    }
    
    ArrayList<Element> cloneList(ArrayList<Element> origen){
        ArrayList<Element> copia=new ArrayList<Element>();
        for(int i=0;i<origen.size();i++){
            copia.add((Element)origen.get(i).clone());
        }
        return copia;
    }
    
    //Agrega un elemento a la lista de Elementos sobre el Lienzo.
    public void addElement(Element e) {
	new Action(Action.CREATE, elements.size(), e);
        elements.add(e);
    }
    
    //Elimina un elemento siempre y cuando lo contenga
    public void deleteElement(Element e) {
        if(elements.contains(e))
            deleteElement(elements.indexOf(e));
    }
    
    public void deleteElement(int i){
	new Action(Action.DELETE, elements.size(), null);
	elements.remove(i);
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
