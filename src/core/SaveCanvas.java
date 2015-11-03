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
public class SaveCanvas {
    ArrayList<Element> lista;
    SaveCanvas(ArrayList<Element> lista){
        this.lista = lista;
    }
    
    public void saveFile(String name){
        try {
            ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(name+".lz"));
            salida.writeObject(lista);
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Element> readFile(String dir){
        ArrayList<Element> file=null;
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(dir));
            file=(ArrayList<Element>)entrada.readObject();
        } catch (Exception ex) {
            Logger.getLogger(SaveCanvas.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return file;
    }
    
    
    
    
    
    
}
