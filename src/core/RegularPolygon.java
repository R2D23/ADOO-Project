
package core;

import graphic.Canvas;
import graphic.GUI;
import graphic.PanelConfig;
import java.awt.geom.Area;
import javax.swing.JOptionPane;

/**
 * 
 * @author douxm_000
 */
public class RegularPolygon extends Figure {

    private int numSides;
    private int longSide;
    int pointsX[];
    int pointsY[];
    
    public RegularPolygon( int longSide, int numSides) {
        /*Poligono Regular de numSides lados, donde cada lado mide longSide
          numSides >= 3   */
        this.numSides = numSides;
        this.longSide = longSide;
        pointsX = new int[numSides];
        pointsY = new int[numSides];
    }
    
    public RegularPolygon() {
	longSide = 30;
	numSides = 5;
    }

    /*@Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();        
        if(state!=AVAILABLE) {
            g2.setColor(Util.negative(bgColor));
            g2.fill(area);
	    g2.setColor(Util.negative(lnColor));
	    g2.draw(area);
        } else {
            g2.setColor(bgColor);
	    g2.fill(area);
            g2.setColor(lnColor);
	    g2.draw(area);
        }
    }*/
    
    public int[] getCoordsX() {
        double littleAngle = 2*Math.PI/numSides; //Es el ángulo de uno de los sectores
        double radius = longSide/(2*Math.sin(littleAngle/2)); //Radio del Poligono
        int coordX[] = new int[numSides];
        for(int i=0; i<numSides; i++) {
            coordX[i] = getPos().x + (int)(radius * Math.cos(littleAngle*i));
        }
        return coordX;
    }

    public int[] getCoordsY() {
        double littleAngle = 2*Math.PI/numSides; //Es el ángulo de uno de los sectores
        double radius = longSide/(2*Math.sin(littleAngle/2)); //Radio del Poligono
        int coordY[] = new int[numSides];
        for(int i=0; i<numSides; i++) {
            coordY[i] = getPos().y + (int)(radius * Math.sin(littleAngle*i));
        }
        return coordY;
    }
    
    @Override
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.RPOLYGON);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = new Object[4];
        valores[0] = numSides;
        valores[1] = longSide;
        valores[2] = lnColor;
        valores[3] = bgColor;
        pc.setValoresPoligonoR(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Circulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresPoligonoR();
            numSides = (int)datos[0];
            longSide = (int)datos[1];
            lnColor = (java.awt.Color)datos[2];
            bgColor = (java.awt.Color)datos[3];
        }
    }
    
    
    public static void create(java.awt.Point p)
    {
        PanelConfig pc = new PanelConfig(PanelConfig.RPOLYGON);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Crear","Cancelar"};
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Crear Polígono Regular", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresPoligonoR();
            RegularPolygon rp  = new RegularPolygon((int)datos[0],(int)datos[1]);
            rp.setLnColor((java.awt.Color)datos[2]);
            rp.setBgColor((java.awt.Color)datos[3]);
            rp.move(p.x, p.y);
            Canvas.addElement(rp);
            Canvas.repaint();
        }
    }
    /*public void getArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        area = new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length));
        AffineTransform rot = new AffineTransform();
        rot.setToRotation(incline, posX, posY);
        area.transform(rot);
    }
    
    public void doZoom(float escala)
    {
        super.doZoom(escala);
        this.longSide *=(1+escala);
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        getArea();
    }
    
    public void rotate(java.awt.Point e) {
        double Y = posY - e.getY();
        double X = posX - e.getX();
        double pendiente = Y/X;
        this.incline = Math.atan(pendiente) + Math.PI/2;
        if(X < 0)
            this.incline += Math.PI;
        getArea();
    }*/

    public int getNumSides() {
	return numSides;
    }

    public void setNumSides(int numSides) {
	this.numSides = numSides;
	repaint();
    }

    public int getLongSide() {
	return longSide;
    }

    public void setLongSide(int longSide) {
	this.longSide = longSide;
	repaint();
    }
    
    @Override
    public void refreshArea()
    {
        pointsX = getCoordsX();
        pointsY = getCoordsY();
        setArea(new Area(new java.awt.Polygon(pointsX, pointsY, pointsX.length)));
        transformArea();
    }
}
