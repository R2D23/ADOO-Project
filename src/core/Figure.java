/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.Element;
import java.awt.Color;
import java.awt.geom.Area;

/**
 *
 * @author douxm_000
 */
public abstract class Figure extends core.Element {
    public Color bgColor; //Color del area de la figura
    public Color lnColor; //Color de las lineas de la figura
    
    public Figure(){
	super();
	bgColor = null;
	lnColor = null;
    }
    
    public Figure(Figure f){
	super(f);
	bgColor = new Color(f.bgColor.getRGB());
	lnColor = new Color(f.lnColor.getRGB());
    }
    
    public void setBgColor(Color c){
	bgColor = new Color(c.getRGB());
    }
    
    public void setLnColor(Color c){
	lnColor = new Color(c.getRGB());
    }
    
    public Color getBgColor(){return bgColor;}
    public Color getLgColor(){return lnColor;}
}
