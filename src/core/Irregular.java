
package core;

import graphic.Canvas;
import static graphic.Canvas.seleccionadoTemporal;
import graphic.GUI;
import graphic.PanelConfig;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author douxm_000
 */
public class Irregular extends Figure {

    public ArrayList<Point> vertices;
    public Point first;
    
    public static final int DISTANCE_THRESHOLD = 10;
    
    public Irregular() {
        super();
        vertices = new ArrayList<>();
        this.bgColor = Color.BLUE;
        this.lnColor = Color.CYAN;
    }
    
    public void newPoint(Point p) {
        vertices.add(p);
    }
    
   
    @Override
    public void paint(Graphics g) {
        refreshArea();
        Graphics2D g2 = (Graphics2D) g;
        if(getState() != GETTINGPOINTS) {
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
    
    

    /*@Override
    public void getArea() {
        int[] pointsX;
        int[] pointsY;
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, first.x, first.y);
        area.transform(rot);
    }*/
    
    @Override
    public void setFirst(Point p)
    {
        first = new Point(p.x,p.y);
        vertices.add(first);
    }
    
    public Point getFirst()
    {return first;}
    
    @Override
    public Point getLast()
    {return vertices.get(vertices.size() - 1);}
    
    /*@Override
    public void move(int posX, int posY)
    {
        int dx = posX - first.x;
        System.out.println("POSX : " + posX + "  POSY : " + posY);
        int dy = posY - first.y;
        System.out.println("dX : " + dx + "  dY : " + dy);
        for (Point vertice : vertices) 
        {
            System.out.println("xantes : " + vertice.x + " yantes : " + vertice.y);
            vertice.translate(dx, dy);
            System.out.println("xdespues : " + vertice.x + " ydespues : " + vertice.y);
            
        }
        
        getArea();
    }
    
    @Override
    public void rotate(Point p)
    {
        double Y = first.y - p.getY();
        double X = first.x- p.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }*/
    
    @Override
    public void setLast(Point p)//Este metodo decide si es el ultimo punto
    {
        if(first.distance(p) > DISTANCE_THRESHOLD)//si no es el ultimo, solo agrega el punto a la lista
            newPoint(p);
        
        else
        {
            refreshArea();
            PanelConfig pc = new PanelConfig(PanelConfig.IPOLYGON);
            javax.swing.JPanel pn = pc.getPanel();
            Object [] options = {"Crear","Cancelar"};
            int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Crear Poligono Irregular", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
            setState(Element.AVAILABLE);
            if(op != 0)
                Canvas.deleteElementTemporal(seleccionadoTemporal);
            Object [] valores = pc.getValoresPoligonoI();
            lnColor = (Color)valores[0];
            bgColor = (Color)valores[1];
            seleccionadoTemporal = null;
            
        }
    }
    
    @Override
    public void refreshArea()
    {
        Area area = new Area(new java.awt.Polygon(getCoordsX(),getCoordsY(),getCoordsX().length));
        setArea(area);
        transformArea();
    }
    
    @Override
    public void move(int posX, int posY)
    {
        int dx = posX - first.x;
        int dy = posY - first.y;
        for (Point vertice : vertices) 
            vertice.translate(dx, dy);
    }
    
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.IPOLYGON);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = new Object[2];
        valores[0] = lnColor;
        valores[1] = bgColor;
        pc.setValoresPoligonoI(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Polígono Irregular", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresPoligonoI();
            lnColor = (java.awt.Color)datos[0];
            bgColor = (java.awt.Color)datos[1];
        }
    }
            
}
