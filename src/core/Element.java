/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author douxm_000
 */
public abstract class Element {
    //Coordenadas
    public int posX; 
    public int posY;
    public float incline;
    public boolean state;
    
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
    
    public abstract void draw(Canvas canvas);
    public abstract void configure(Canvas canvas);
}
