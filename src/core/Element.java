
package core;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.geom.Area;
import java.io.Serializable;

/**La clase Element define en base a todos los elementos trabajables dentro
 * del lienzo.
 *
 * @author douxm_000
 */
public abstract class Element implements Cloneable,Serializable{
    //Coordenadas
    private static final long serialVersionUID = 1113799434508676095L;
    public int posX; 
    public int posY;
    public double incline;
    public int state;
    transient public Area area;//area de la figura
    public static final int AVAILABLE = 0;
    public static final int BUSY = 1;
    public static final int MOVING = 2;
    public static final int ROTATING = 3;
    public static final int GETTINGPOINTS = 4;
    
    public Element(){
	posX = 0;   posY = 0;	incline = 0;
	state = AVAILABLE;
	area = null;
    }
    
    public Element(Element e){
	posX = e.posX;
	posY = e.posY;
	incline = e.incline;
	state = e.state;
	area = new Area(e.area);
    }
    
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
    
    
    //Cuando algun elemento se selecciona se ejecuta select cambiando el estado
    public void select() {
        state = BUSY; //Entonces lo deja de estar (1)
    }
    
    //Este metodo actualiza el valor de la inclinación
    public void rotate(Point p) {
    }
    
    public void setIncline(double i){incline = i;}
    
    //Este método cambia las coordenadas del Elemento
    public void move(int posX, int posY) {
	this.posX = posX;
	this.posY = posY;
	getArea();
    }
    
    public abstract void draw(Graphics g);
    
    
    public abstract void getArea();
    public void doZoom(float escala)
    {
        posX *= (1 + escala);
        posY *= (1 + escala);
    }
    public double getInclination() 
    {return incline;}

    public Point getPos() {
	return new Point(posX, posY);
    }

    void move(Point point) {
	posX = point.x; posY = point.y;
	getArea();
    }
    
    public void setFirst(Point p){}
    public void setLast(Point p){}
    
    public Point getLast(){return new Point();}
}
