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
import javax.swing.JFileChooser;

/**
 *
 * @author LuisAguilar
 */
public class File {
    String name;
    Canvas canvas;
    
    public void saveFile(){
        JFileChooser fcs = new JFileChooser();
        int opc = fcs.showSaveDialog(null);
        if(opc == JFileChooser.APPROVE_OPTION)
        {
            name = fcs.getSelectedFile().getName();
            try {
                ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(name+".lz"));
                salida.writeObject(canvas.elements);
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void readFile(){
        JFileChooser fco = new JFileChooser();
        int opc = fco.showOpenDialog(null);
        if(opc == JFileChooser.APPROVE_OPTION){
            try {
              ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fco.getSelectedFile().getPath()));
              canvas.elements = (ArrayList<Element>)entrada.readObject();
              for(int i = 0; i < canvas.elements.size(); i++)
                canvas.elements.get(i).getArea();
              canvas.repaint();
              name = fco.getSelectedFile().getName();
            } catch (Exception ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
            } 

           name = fco.getSelectedFile().getName();
        }
    }
    
    public void setName(String s)
    {name = s;}
    
    public String getName()
    {return name;}
}