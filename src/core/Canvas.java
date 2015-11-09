
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
import javax.swing.Scrollable;

/**
 *
 * @author douxm_000
 */
public class Canvas extends JPanel implements Serializable, MouseListener, MouseMotionListener {
    public ArrayList<Element> elements;
    FigureMenu fm;
    SelectionMenu sm;
    public ArrayList<Element> pastelements;
    public ArrayList<Element> futureelements;
    Element seleccionado;
    private int originalWidth;
    private int originalHeight;
    private int indiceZoom;
    
    
    public Canvas() {
        elements = new ArrayList<>();
        //addMouseMotionListener(new Escucha(this));
        pastelements=null;
        futureelements=null;
        seleccionado = null;
        indiceZoom = 100;
        //sm = ((GUI)getTopLevelAncestor()
    //).getSelectionMenu();
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
            elements.get(i).draw(g);
        
    }
    
    public void doZoom(int escala)
    {
        System.out.println("escala : " + (indiceZoom + escala));
        if(indiceZoom + escala <= 0)
            JOptionPane.showMessageDialog(null, "No se puede hacer mas zoom");
        else
        {
            indiceZoom += escala;
            this.setSize((int)(originalWidth*indiceZoom/100), (int)(originalHeight*indiceZoom/100));
            for(Element e : elements)
               e.doZoom(escala/100);
            this.repaint();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            switch(getCursor().getName())//if((this.getCursor().getName().equals("mano")) && (e.getButton() == MouseEvent.BUTTON1))
            {
                case "mano":
                    if(seleccionado == null)
                    {
                        int sel = sm.cualFigura(e.getPoint());
                        if(sel >= 0)
                        {   
                            seleccionado = elements.get(sel);
                            seleccionado.state = Element.BUSY;
                            elements.remove(seleccionado);//Esto es para que lo traiga al frente
                            elements.add(seleccionado);
                            sm.setCenter(e.getPoint());
                            sm.obtLocation().setLocation(e.getX() - sm.SIZE/2 + GUI.GAP, e.getY() - sm.SIZE/2 + GUI.GAP);
                            sm.setLocation(sm.obtLocation());
                            repaint();
                            sm.setVisible(!sm.isVisible());
                        }
                    }
                    else
                    {
                        seleccionado.state = Element.AVAILABLE;
                        seleccionado.draw(this.getGraphics());
                        seleccionado = null;
                    }
                       
                break;
                case "lapiz" :
                    if((seleccionado != null) && (seleccionado.state == Element.GETTINGPOINTS))
                    {
                        Irregular fig = (Irregular)seleccionado;
                        Point nuevoPunto = new Point(e.getX(), e.getY() + GUI.GAP/2);
                        if(fig.getFirst().distance(nuevoPunto) > 10)
                            fig.newPoint(nuevoPunto);
                        else
                        {
                            fig.getArea();
                            fig.state = Element.AVAILABLE;
                            seleccionado = null;
                        }
                        repaint();
                    }
                    else
                    {
                        fm.setSize(fm.SIZE, fm.SIZE);
                        fm.obtLocation().setLocation(e.getX() - fm.SIZE/2 + GUI.GAP, e.getY() - fm.SIZE/2 + GUI.GAP);
                        fm.setCenter(new Point(e.getPoint().x,e.getPoint().y));
                        fm.setLocation(fm.obtLocation());
                        fm.setVisible(!fm.isVisible());
                    }
                break;
            }
//        else
//            this.setVisible(false);
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
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        if(seleccionado != null)
        {
            switch(seleccionado.state)
            {
                case Element.MOVING:
                    seleccionado.move(e.getX(), e.getY());                    
                break;
                case Element.ROTATING:
                    seleccionado.rotate(e.getPoint());
                break;
                case Element.GETTINGPOINTS:
                    Point lastPoint = ((Irregular)seleccionado).getLast();
                    Graphics g = getGraphics();
                    g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY()+30);
                    repaint();
                break;
            }
            repaint();
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {}
      
    public void setMenus()
    {
        fm = ((GUI)getTopLevelAncestor()).getFigureMenu();
        sm = ((GUI)getTopLevelAncestor()).getSelectionMenu();
    }
    
    public void setOriginals()
    {
        originalWidth = this.getWidth();
        originalHeight = this.getHeight();
        System.out.println("w : " + originalWidth + " h : " + originalHeight );
    }
    
    public int getIndiceZoom()
    {return indiceZoom;}
  
}
