/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LuisAguilar
 */
public class File {
    String name;
    Canvas canvas;
    File(Canvas c){
        name = null;
        this.canvas = c;
    }
    
    public void saveFile(String name){
        try {
            ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(name+".lz"));
            salida.writeObject(canvas.elements);
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Element> readFile(String dir){
        //ArrayList<Element> file=null;
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(dir));
            canvas.elements = (ArrayList<Element>)entrada.readObject();
        } catch (Exception ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        for(int i = 0; i < canvas.elements.size(); i++)
            canvas.elements.get(i).area = canvas.elements.get(i).getArea();
        return canvas.elements;
    }
    
    public void setName(String s)
    {name = s;}
    
    public String getName()
    {return name;}
    
    
    
}
