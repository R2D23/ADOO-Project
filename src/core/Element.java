
package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.Serializable;
import static graphic.Canvas.zoom;
import graphic.Canvas;

/**La clase Element define en base a todos los elementos trabajables dentro
 * del lienzo.
 *
 * @author douxm_000
 */
public abstract class Element implements Cloneable,Serializable{
    //Coordenadas
    //private static final long serialVersionUID = 1113799434508676095L;
    private int posX; 
    private int posY;
    private double incline;
    private int state;
    transient private Area area;//area de la figura
    private static double recentIncline = 0;
    
    public static final int AVAILABLE = 0;
    public static final int BUSY = 1;
    public static final int MOVING = 2;
    public static final int ROTATING = 3;
    public static final int GETTINGPOINTS = 4;
    public static final int BLOCKED = 5;
    public static final int OWNED = 6;
    
    public Element(){
	posX = 0;   posY = 0;	incline = recentIncline;
	state = AVAILABLE;
    }
    
    public Element(Element e){
	posX = e.posX;
	posY = e.posY;
	incline = e.incline;
	state = e.state;
    }
    
    public Object clone(){
        Element e = null;
        try{
            e = (Element)super.clone();
        }catch(CloneNotSupportedException ex){
        }
        return e;
    }
    
    
    //Cuando algun elemento se selecciona se ejecuta select cambiando el estado
    public final void select() {
        state = BUSY; //Entonces lo deja de estar (1)
    }
    
    //Este metodo actualiza el valor de la inclinación
    public final void rotate(Point p) {
        incline = Math.atan2(p.x - area.getBounds2D().getCenterX(), 
                -(p.y - area.getBounds2D().getCenterY()));
        recentIncline = incline;
        repaint();
    }
    
    public void move(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
        repaint();
    }
    
    public abstract void paint(Graphics g);
    
    public final Area getArea(){return area;}
    
    public final void setArea(Area a){area = a;}
    
    public final double getInclination(){return incline;}
    
    public final void setIncline(Double i){incline = i;}
    
    
    public Point getPos(){return new Point(posX,posY);}
    
    public final void move(Point p){move(p.x,p.y);}
    
    public final int getState(){return state;}
    
    public final void setState(int s){state = s;}
    
    public void setFirst(Point p){}
    
    public void setLast(Point p){}
    
    public Point getLast(){return new Point();}
    
    public abstract void refreshArea();
    
    public abstract void configure();
    
    public void transformArea()
    {
        rotateArea();
        zoomArea();
    }
    
    private void rotateArea()
    {
        AffineTransform rot = new AffineTransform();
        //rot.setToRotation(incline, area.getBounds2D().getCenterX(), area.getBounds2D().getCenterY());
        rot.setToRotation(incline,posX,posY);
        area.transform(rot);
    }
    
    public void zoomArea()
    {
        AffineTransform rot = new AffineTransform();
        rot.scale(zoom, zoom);
        area.transform(rot);
    }
    
    public void repaint()
    {paint(Canvas.panel.getGraphics());}
    
}
