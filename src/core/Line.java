
package core;

import graphic.Canvas;
import static graphic.Canvas.seleccionadoTemporal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import graphic.GUI;
import graphic.PanelConfig;
import graphic.Util;
import java.awt.geom.Area;
import javax.swing.JOptionPane;

/**
 *
 * @author douxm_000
 */
public class Line extends Element {

    double length;
    Color color;
    float grosor;
    Point [] puntos;
    
    public Line(double length, float angle, Color color) {
        this.length = length;
        this.color = color;
    }
    
    public Line(int finX, int finY, Color color) { //Contructor alternativo a traves de coordenadas
        length = Math.sqrt(Math.pow(finY-getPos().y, 2)+Math.pow(finX-getPos().x, 2)); //Distancia entre dos puntos
        setIncline(Math.atan2(finX-getPos().x,finY - getPos().y));
        this.color = color;
    }
    
    public Line(){
	puntos = new Point[2];
        grosor = 5; 
        color = Color.black;
    }

    @Override
    public void paint(Graphics g) {
        refreshArea();
        Graphics2D g2 = (Graphics2D) g.create();
        Stroke s = new BasicStroke(grosor);
        g2.setStroke(s);
        if(getState() != GETTINGPOINTS) {
            if(getState() != AVAILABLE)
                g2.setColor(Util.selectedColor);
            else
                g2.setColor(color);
            g2.draw(getArea());
            g2.fill(getArea());
            
            //g2.drawLine(puntos[0].x,puntos[0].y, puntos[1].x,puntos[1].y);
            
        } 
    }
    
    @Override
    public void setLast(Point p)
    {
        Point nuevoPunto = new Point((int)p.getX(),(int)( p.getY() + GUI.GAP/2));
        puntos[1] = nuevoPunto;
        setState(Element.AVAILABLE);
        
        double Y = puntos[1].y - puntos[0].y;
        double X = puntos[1].x - puntos[0].x;
        setIncline(Math.atan2(Y,X));
        move(puntos[0].x, puntos[0].y);

        PanelConfig pc = new PanelConfig(PanelConfig.LINE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] valores = {length,grosor, color};
        pc.setValoresLinea(valores);
        Object [] options = {"Crear","Cancelar"};
        
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Crear Línea", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op != 0)
            Canvas.deleteElementTemporal(seleccionadoTemporal);
        else if(op == 0)
        {
            Object [] datos = pc.getValoresLinea();
            length = (double)datos[0];
            grosor = (float)datos[1];
            color = (Color)datos[2];
            resetLastPoint();
        }
        Canvas.seleccionadoTemporal = null;
        
    }
    
    private void resetLastPoint()
    {
        puntos[1].x = puntos[0].x + (int)(length * Math.cos(getInclination()));
        puntos[1].y = puntos[0].y + (int)(length * Math.sin(getInclination()));
    }
    
    @Override
    public void setFirst(Point p)
    {puntos[0] = new Point(p);}
    
    @Override
    public Point getLast()
    {return puntos[0];}
    
    
    @Override
    public void refreshArea()
    {
        try{length = puntos[0].distance(puntos[1]);}catch(Exception e){}
        java.awt.Rectangle r2d = new java.awt.Rectangle(getPos().x, getPos().y, (int)(length),(int)(grosor));
        setArea(new Area(r2d));
        transformArea();
    }
    
    @Override
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.LINE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = {length, grosor,color};//new Object[3];
        /*valores[0] = length;
        valores[1] = grosor;
        valores[2] = color;*/
        pc.setValoresLinea(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Linea", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresLinea();
            length = (double)datos[0];
            grosor = (float)datos[1];
            color = (Color)datos[2];
            resetLastPoint();
        }
    }
}
