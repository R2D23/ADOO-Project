/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author LuisArturo
 */
public class Action {
    private int id;
    private Element e;
    private int type;
    
    public static final int CREATE = 1;
    public static final int MODIFY = 2;
    public static final int DELETE = 3;
    
    public Action(int t, Element elm){
	e = elm;
	type = t;
    }
    
    public int getID(){return id;}
    public Element getElement(){return e;}
    public int getType(){return type;}
    
    public static void undo(Canvas c, Action a){
	
    }
}
