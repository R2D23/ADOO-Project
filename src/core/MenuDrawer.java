

package core;

/**
 *
 * @author Angeles
 */

import java.awt.*;
import java.awt.geom.Area;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class MenuDrawer{
    public static final int TRIANGLE = 0;
    public static final int CIRCLE = 1;
    public static final int POLIGONE= 2;
    public static final int RECTANGLE = 3;
    public static final int LINE = 4;
    public static final int IRREGULAR = 5;
    public static final int EXIT = 6;
    private Color color = Color.black;
    
    /**
    * <p>The GraphicDrawer.paint() Function allows the user to draw pre-defined graphics
    * into a given Graphics Object. The user may give the rectangle properties
    * which defines at best the desired area to draw with a Int id which
    * requests the desired graphic, whenever it's vectorial or raster graphics</p>
    * 
    * @author LuisArturo
    */
    public void paint(int id, Graphics g, Area a){
	Graphics2D g2;
        int x = a.getBounds().x;
        int y = a.getBounds().y;
        int ancho = a.getBounds().width;
        int alto = a.getBounds().height;
	switch(id){
	    case TRIANGLE:
	    {
		g2 = (Graphics2D) g;
                try
                {
                    FileInputStream img = new FileInputStream("triangulo.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/3, y + alto/3,ancho/2,alto/2, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }
	    break;
            case CIRCLE:
	    {
		g2 = (Graphics2D) g;
                try
                {
                    FileInputStream img = new FileInputStream("circulo.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/7, y + alto/4,ancho/2,alto/2, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }    
            break;
            case POLIGONE:
	    {
		g2 = (Graphics2D) g;
                try
                {
                    FileInputStream img = new FileInputStream("poligono.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/4,ancho/2,alto/2, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }
            break;
            case RECTANGLE:
	    {
		g2 = (Graphics2D) g;
                try
                {
                    FileInputStream img = new FileInputStream("cuadrado.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/2,ancho/2,alto/2, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }
            break;
            case LINE:
	    {
		g2 = (Graphics2D) g;
                try
                {
                    FileInputStream img = new FileInputStream("line.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/7,ancho/2,alto/2, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }
            break;                
            case IRREGULAR:
	    {
		g2 = (Graphics2D) g;
                try
                {
                    FileInputStream img = new FileInputStream("doodle.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/3, y + alto/7,ancho/2,alto/2, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }
            break;                        
            case EXIT:
	    {
		g2 = (Graphics2D) g;
                try
                {
                    FileInputStream img = new FileInputStream("salir.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/7,ancho/2,alto/2, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }
            break;                    
	    /*case REDO:
	    {
		g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fillArc(r.width*2/10, r.height*2/10, r.width*6/10, r.height*6/10, 270, 225);
		Polygon p = new Polygon();
		p.addPoint(r.width*2/10, r.height*2/10);
		p.addPoint(r.width*2/10, r.height*5/10);
		p.addPoint(r.width*5/10, r.height*5/10);
		g2.fillPolygon(p);
		g2.setColor(g2.getBackground());
		g2.fillArc(r.width*3/10, r.height*3/10, r.width*4/10, r.height*4/10, 270, 225);
	    }
	    break;
	    case TEXT:
	    {
		g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, r.height*6/10));
		g2.drawChars("A".toCharArray(), 0, 1, r.width*3/10, r.height*7/10);
	    }
	    break;
            case SELECT:
            {
                g2 = (Graphics2D)g;
                try
                {
                    FileInputStream img = new FileInputStream("mano.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, r.width*1/10, r.height*1/10,r.width*2/3,r.height*2/3, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            break;
            case PENCIL:
            {
                g2 = (Graphics2D)g;
                try
                {
                    FileInputStream img = new FileInputStream("lapiz.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, r.width*2/10, r.height*2/10,r.width*2/3,r.height*2/3, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            break;
                
            case ARROW:
            {
                g2 = (Graphics2D)g;
                try
                {
                    FileInputStream img = new FileInputStream("arrow.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, r.width*2/10, r.height*2/10,r.width*2/3,r.height*2/3, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            break;
            case ZOOM:
            {
                g2 = (Graphics2D)g;
                try
                {
                    FileInputStream img = new FileInputStream("zoom.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, r.width*2/10, r.height*2/10,r.width*2/3,r.height*2/3, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            break;*/
	}
    }
    
    public void setColor(Color color){
	this.color = color;
    }

    
}

    

