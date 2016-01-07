
package core;

import graphic.Canvas;
import static graphic.Canvas.zoom;
import graphic.GUI;
import graphic.PanelConfig;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Angeles
 */

public class Imagen extends Element implements Serializable{
    int width;
    int height;
    String src;
    ImageIcon img;
    public Imagen(String s)
    {
        super();
        src = s;
        img = new ImageIcon(src);
        width = img.getIconWidth();
        height = img.getIconHeight();
        //this.posX = 0;
        //this.posY = 0;
        //this.incline = 0;
    }
    
    @Override
    public void paint(Graphics g)
    {
        refreshArea();
        Graphics2D g2 = (Graphics2D)(g.create()); 
        g2.rotate(getInclination(), getPos().x + width/2,getPos().y + height/2);
        g2.scale(zoom, zoom);
        g2.drawImage(img.getImage(), getPos().x, getPos().y, width, height,null);
    }
    
    
    @Override
    public void refreshArea()
    {
        setArea(new Area(new java.awt.Rectangle(getPos().x, getPos().y, (int)width, (int)height)));
        transformArea();
    }
    
   
    
    @Override
    public void configure()
    {
        PanelConfig pc = new PanelConfig(PanelConfig.IMAGE);
        javax.swing.JPanel pn = pc.getPanel();
        Object [] options = {"Modificar", "Cancelar"};
        Object [] valores = new Object[2];
        valores[0] = this.width;
        valores[1] = this.height;
        pc.setValoresImagen(valores);
        int op = JOptionPane.showOptionDialog(GUI.frame,pn, "Configurar Circulo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(op == 0)
        {
            Object [] datos = pc.getValoresImagen();
            width = (int)datos[0];
            height = (int)datos[1];
        }
        Canvas.repaint();
    }
    
}