
package core;

import graphic.Canvas;
import graphic.GUI;
import graphic.PanelConfig;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.Dimension;
import javax.swing.JOptionPane;
/**
 *
 * @author douxm_000
 */
public class Rectangle extends core.Figure {

    int width;
    int height;

    public Rectangle(){}
    
    /*public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }*/

    public Rectangle(int w, int h)
    {width = w; height = h;}
    
    public void setWidth(int b)
    {width = b; repaint();}
    
    public int getWidth()
    {return width;}
    
    public void setHeight(int h)
    {height = h; repaint();}
    
    public int getHeight()
    {return height;}
    
    public void setSize(Point p)
    {
        width = p.x;
        height = p.y;
    }
    
    public Dimension getSize()
    {return new Dimension(width, height);}
    
    @Override
    public void refreshArea()
    {
        setArea(new Area(new java.awt.Rectangle(getPos().x, getPos().y, width, height)));
        transformArea();
    }
    
    public static void create(Point p)
    {
        PanelConfig pc = new PanelConfig(PanelConfig.RECTANGLE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Crear","Cancelar"};
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Crear Rectangulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        
        /* Si se dio aceptar */
        if(op == 0)
        {
            Object [] datos = pc.getValoresRectangulo();
            Rectangle r = new Rectangle((int)datos[0],(int)datos[1]);
            r.setLnColor((java.awt.Color)datos[2]);
            r.setBgColor((java.awt.Color)datos[3]);
            r.move(p.x, p.y);
            Canvas.addElement(r);
            Canvas.repaint();
        }
            
    }
    
    @Override
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.RECTANGLE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = new Object[4];
        valores[0] = this.width;
        valores[1] = this.height;
        valores[2] = lnColor;
        valores[3] = bgColor;
        pc.setValoresRectangulo(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Circulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresRectangulo();
            width = (int)datos[0];
            height = (int)datos[1];
            lnColor = (java.awt.Color)datos[2];
            bgColor = (java.awt.Color)datos[3];
        }
    }
    
    /*public void configure(Canvas canvas) {
        //new ConfigurarCuadrado(canvas,this,new Point(this.posX, this.posY)).setVisible(true);
        
    }
    public void getArea()
    {
        area = new Area(new java.awt.Rectangle(posX, posY, (int)width, (int)height));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
    }
    
    public void doZoom(float escala)
    {
        this.height *= (1+escala);  
        this.width *= (1+escala);
        getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = (posY + height/2) - e.getY();
        double X = (posX + width/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }
    
    public void setWidth(int b){
	this.width = b;
	getArea();
    }
    
    public double getWidth(){
	return width;
    }
    
    public void setHeight(int h){
	this.height = h;
	getArea();
    }
    
    public double getHeight(){
	return height;
    }
    
    public void setSize(Point p){
	width = p.x;
    }
    
    public Point getSize(){
	return new Point(width, height);
    }*/
}
