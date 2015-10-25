/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import javax.swing.JPanel;
import java.util.ArrayList;

/**
 *
 * @author douxm_000
 */
public class Canvas extends JPanel{
    public ArrayList<Element> elements;
    
    public Canvas() {
        elements = new ArrayList<>();
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
    
    
    public void drawAll() {
        //Vamos a dibujar todos los elementos que se encuentren en la lista del lienzo
        this.elements.stream().forEach((element) -> {
            element.draw(this);
        });
    }

}
