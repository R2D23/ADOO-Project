/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Angeles
 */
public class Escucha implements MouseListener{

    private ArrayList<Area> areas;
    
    public Escucha(ArrayList<Area> as)
    {
        
       //areas = new ArrayList<>();
       areas = as;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int areaActual = whichArea(e.getPoint());
        String s = "MENU DE CONFIGURACION \n";
        switch (areaActual)
        {
            case MenuDrawer.CIRCLE :
                s = s + "circulo";
                JOptionPane.showInputDialog(s);
            break;
            case MenuDrawer.IRREGULAR :
                s = s + "irregular";
                
            break;
        }
        System.out.println(s);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public int whichArea(Point p)
    {
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
                return i;
        return -1;
    }
    
}
