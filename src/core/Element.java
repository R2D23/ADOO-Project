
package core;

import java.awt.Graphics;
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
    public double incline;
    public int state;
    public Area area;//area de la figura
    public static final int AVAILABLE = 0;
    public static final int BUSY = 1;
    public static final int MOVING = 2;
    public static final int ROTATING = 3;
    
    //Cuando algun elemento se selecciona se ejecuta select cambiando el estado
    public void select() {
        if(state==AVAILABLE) //Si esta disponible (0)
            state = BUSY; //Entonces lo deja de estar (1)
    }
    
    //Este metodo actualiza el valor de la inclinación
    public void rotate(double incline) {
        this.incline = incline; //Asigna la nueva inclinacion
    }
    
    //Este método cambia las coordenadas del Elemento
    public void move(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
    public abstract void draw(Graphics g);
    public abstract void configure(Canvas canvas);
}
