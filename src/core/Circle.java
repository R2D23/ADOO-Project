
package core;

import graphic.Canvas;
import graphic.GUI;
import graphic.PanelConfig;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JOptionPane;

/**
 *
 * @author douxm_000
 */
public class Circle extends Figure{
    
    private double radio;
    
    private static double recentRadio = 50;
    
    public Circle() {
	super();
	radio = recentRadio;
    }
    
    public Circle(double radio) {
        super();
        this.radio = Math.abs(radio);
    }
    
    public Circle(Circle c)
    {
        super(c);
        this.radio = c.radio;
    }
    
    public double getRadius()
    {return radio;}
    
    public void setRadius(Double aDouble)
    {
        radio = aDouble;
        recentRadio = radio;
        repaint();
    }
    
  
    public Object clone()
    {return new Circle(this);}
    
    @Override
    public void refreshArea()
    {
        setArea(new Area(new Ellipse2D.Double(getPos().x, getPos().y, radio*2, radio*2)));
        transformArea();
    }
    
    @Override
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.CIRCLE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = new Object[3];
        valores[0] = this.radio;
        valores[1] = this.lnColor;
        valores[2] = this.bgColor;
        pc.setValoresCirculo(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Circulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresCirculo();
            setRadius((double)datos[0]);
            setLnColor((java.awt.Color)datos[1]);
            setBgColor((java.awt.Color)datos[2]);
        }
    }
    
    public static void create(java.awt.Point p)
    {
        PanelConfig pc = new PanelConfig(PanelConfig.CIRCLE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Crear","Cancelar"};
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Crear Circulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresCirculo();
            Circle c = new Circle((Double)datos[0]);
            c.setLnColor((java.awt.Color)datos[1]);
            c.setBgColor((java.awt.Color)datos[2]);
            c.move(p.x, p.y);
            Canvas.addElement(c);
            Canvas.repaint();
        }
    }
    
}
