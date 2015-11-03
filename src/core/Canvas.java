
package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author douxm_000
 */
public class Canvas extends JPanel implements Serializable {
    public ArrayList<Element> elements;
    FigureMenu fm;
    SelectionMenu sm;
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
     
      public void returnPast(){
        if(pastelements!=null){
            futureelements=cloneList(elements);
            elements=cloneList(pastelements);
            pastelements=null;
        }else{
            JOptionPane.showMessageDialog(null,"No hay pasos atras");
        }
    }
    
    public void toFuture(){
        if(futureelements!=null){
            pastelements=cloneList(elements);
            elements=cloneList(futureelements);
            futureelements=null;
        }else{
             JOptionPane.showMessageDialog(null,"No hay pasos hacia adelante");
        }
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
