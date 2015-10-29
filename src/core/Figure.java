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
    public Color bgcolor; //Color del area de la figura
    public Color lncolor; //Color de las lineas de la figura
    
    public Figure(){
	super();
    }
}
