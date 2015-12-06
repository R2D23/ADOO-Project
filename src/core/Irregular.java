/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static core.Canvas.seleccionado;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 *
 * @author douxm_000
 */
public class Irregular extends Figure {

    public ArrayList<Point> vertices;
    public Point first;
    
    public static final int DISTANCE_TRESHOLD = 10;
    
    public Irregular(){
	super();
        vertices = new ArrayList<Point>();
        this.bgColor = Color.BLUE;
        this.lnColor = Color.CYAN;
    }
    
    public void newPoint(Point p) {
        vertices.add(p);
    }
    
   
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(getState()!=GETTINGPOINTS) {
            super.paint(g);
        } else {
            for(int i=0; i<vertices.size()-1; i++) {
                g2.drawLine(vertices.get(i).x, vertices.get(i).y, vertices.get(i+1).x, vertices.get(i+1).y);
                g2.fillOval(vertices.get(i).x-5, vertices.get(i).y-5, 10, 10);
                g2.fillOval(vertices.get(i+1).x-5, vertices.get(i+1).y-5, 10, 10);
            }
        }
    }

    private int[] getCoordsX() {
        int[] coord = new int [vertices.size()];
        for(int i=0; i<vertices.size(); i++)
            coord[i] = vertices.get(i).x;
        return coord;
    }

    private int[] getCoordsY() {
        int[] coord = new int [vertices.size()];
        for(int i=0; i<vertices.size(); i++)
            coord[i] = vertices.get(i).y;
        return coord;
    }
    
    public void setFirst(Point p){
        first = p;
        vertices.add(first);
    }
    
    public Point getFirst()
    {return first;}
    
    public Point getLast()
    {return vertices.get(vertices.size() - 1);}
        
    //Este metodo decide si es el ultimo punto
    @Override
    public void setLast(Point p){
        if(first.distance(p) > DISTANCE_TRESHOLD)//si no es el ultimo, solo agrega el punto a la lista
            newPoint(p);
	else {
            getArea();
            setState(Element.AVAILABLE);
            seleccionado = null;
        }
    }

    @Override
    public void refreshArea() {
	Area area = new Area(new java.awt.Polygon(
		getCoordsX(), getCoordsY(), getCoordsX().length));
	setArea(area);
        transformArea();
    }
}
