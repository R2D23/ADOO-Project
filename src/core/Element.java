
package core;

import core.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Area;

/**La clase Element define en base a todos los elementos trabajables dentro
 * del lienzo.
 *
 * @author douxm_000
 */
public abstract class Element{
    //Coordenadas
    public int posX; 
    public int posY;
    public float incline;
    public boolean state;
    public Area area;//area de la figura
    private static int count = 0; //El número de instancias de esta clase.
    private int id; //El identificador de una instancia.
    
    //Constructor
    public Element(){
	id = getNewID();
    }
    
    //Copy
    public Element(Element e){
	this.area = new Area(e.area);	this.id = getNewID();
	this.incline = e.incline;	this.posX = e.posX;
	this.posY = e.posY;		this.state = e.state;
    }
    
    //Cuando algun elemento se selecciona se ejecuta select cambiando el estado
    public void select() {
        if(state) //Si esta disponible (true)
            state = false; //Entonces lo deja de estar (false)
    }
    
    //Este metodo actualiza el valor de la inclinación
    public void rotate(float incline) {
        this.incline = incline; //Asigna la nueva inclinacion
    }
    
    public float getRotation(){return incline;}
    
    //Este método cambia las coordenadas del Elemento
    public void move(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
    public Point getPos(){return new Point(posX, posY);}
    
    //Alternativa de movimiento.
    void move(Point point) {
	posX = point.x;
	posY = point.y;
    }
    
    public int getID(){return id;}
    
    public abstract void draw(Graphics g);
    public abstract void configure(Canvas canvas);
    
    //Proporciona un nuevo valor de identificación para un elemento.
    public static int getNewID(){count++; return count;}
}
