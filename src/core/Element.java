
package core;

import core.Canvas;
import java.awt.Component;
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
    public float incline;
    public boolean state;
    public Area area;//area de la figura
    
    //Cuando algun elemento se selecciona se ejecuta select cambiando el estado
    public void select() {
        if(state) //Si esta disponible (true)
            state = false; //Entonces lo deja de estar (false)
    }
    
    //Este metodo actualiza el valor de la inclinación
    public void rotate(float incline) {
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
