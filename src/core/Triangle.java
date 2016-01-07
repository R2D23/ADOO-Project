
package core;

import graphic.Canvas;
import graphic.GUI;
import graphic.PanelConfig;
import java.awt.geom.Area;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author douxm_000
 */
public class Triangle extends Rectangle{

   
    int type;
    public int pointsX[];
    public int pointsY[];
    public static final int EQUILATERAL_TRIANGLE = 1;
    public static final int RIGHT_TRIANGLE = 2;
    public static final int ISOSCELES_TRIANGLE = 3;
    
    public Triangle(int base, int height, int type) {
        this.width = base;
        this.height = height;
        this.type = type;
        pointsX = new int[3];
        pointsY = new int[3];
    }
    
    public Triangle(int base) {
        this.height = base;
        this.height = (int) Math.sqrt(Math.pow(base, 2)-Math.pow(base/2, 2)); //Pitágoras
        type = EQUILATERAL_TRIANGLE;
        pointsX = new int[3];
        pointsY = new int[3];
    }
    
    public Triangle(){
	super();
	type = Triangle.EQUILATERAL_TRIANGLE;
	width = 50;
	height = 50;
    }

    public int[] getCoordsX() {
        int coordX[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: case ISOSCELES_TRIANGLE:
                coordX[0] = getPos().x;
                coordX[1] = (int)(getPos().x+width/2);
                coordX[2] = (int)(getPos().x+width);
            break;
            case RIGHT_TRIANGLE:
                coordX[0] = getPos().x;
                coordX[1] = getPos().x;
                coordX[2] = (int)(getPos().x+width);
            break;
        }
        return coordX;
    }

    public int[] getCoordsY() {
        int coordY[] = new int[3];
        switch(type) {
            case EQUILATERAL_TRIANGLE: 
                coordY[0] = (int)(getPos().y+width*Math.sin(Math.PI/3));
                coordY[1] = getPos().y;
                coordY[2] = (int)(getPos().y+width*Math.sin(Math.PI/3));
            break;
            case ISOSCELES_TRIANGLE:
                coordY[0] = (int)(getPos().y+height);
                coordY[1] = getPos().y;
                coordY[2] = (int)(getPos().y+height);
            break;    
            case RIGHT_TRIANGLE:
                coordY[0] = getPos().y;
                coordY[1] = (int)(getPos().y+height);
                coordY[2] = (int)(getPos().y+height);
            break;
        }
        return coordY;
    }
    
    public static void create(java.awt.Point p)
    {
        PanelConfig pc = new PanelConfig(PanelConfig.TRIANGLE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Crear","Cancelar"};
        int op = JOptionPane.showOptionDialog(GUI.frame,pn,"Crear Triangulo",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresTriangulo();
            Triangle t = new Triangle((int)datos[0],(int)datos[1], (int)datos[2]);
            t.setLnColor((java.awt.Color)datos[3]);
            t.setBgColor((java.awt.Color)datos[4]);
            t.move(p.x, p.y);
            Canvas.addElement(t);
            Canvas.repaint();
        }
    }
    
    /*public void getArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX+width/2, posY+height/2);
        area.transform(rot);
    }
    
    @Override
    public void doZoom(float escala)
    {
        super.doZoom(escala);
        this.width *= (1+escala);     
        this.height *= (1+escala);
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        getArea();
    }
    
    @Override
    public void rotate(Point e) {
        double Y = (posY + height/2) - e.getY();
        double X = (posX + width/2) - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }*/
    
    public void setType(int i){
	type = i;
	repaint();
    }
    
    public int getType(){
	return type;
    }
    
    @Override
    public void refreshArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        setArea(new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length)));
        transformArea();
    }
    
    @Override
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.TRIANGLE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = new Object[5];
        valores[0] = width;
        valores[1] = height;
        valores[2] = type;
        valores[3] = lnColor;
        valores[4] = bgColor;
        pc.setValoresTriangulo(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Circulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresTriangulo();
            width = (int)datos[0];
            height = (int)datos[1];
            type = (int)datos[2];
            lnColor = (java.awt.Color)datos[3];
            bgColor = (java.awt.Color)datos[4];
        }
    }
}
