/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author LuisArturo
 */
public abstract class CustomButton extends JButton{
    public void paintRules(){
	if(model.isRollover())
	    setBackground(Util.rollOverColor);
	if(model.isPressed())
	    setBackground(Util.pressedColor);
	if(!model.isRollover() && !model.isPressed())
	    setBackground(Util.normalColor);
    }
}
