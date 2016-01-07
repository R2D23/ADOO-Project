/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class GraphicDrawer{
    public static final int FILE = 0;
    public static final int REDO = 1;
    public static final int TEXT = 2;
    public static final int ARROW = 3;
    public static final int SELECT = 4;
    public static final int PENCIL = 5;
    public static final int ZOOM = 6;
    private Color color = Color.black;
    
    /**
    * <p>The GraphicDrawer.paint() Function allows the user to draw pre-defined graphics
    * into a given Graphics Object. The user may give the rectangle properties
    * which defines at best the desired area to draw with a Int id which
    * requests the desired graphic, whenever it's vectorial or raster graphics</p>
    * 
    * @author LuisArturo
    */
    public void paint(int id, Dimension r, Graphics g){
	Graphics2D g2;
	switch(id){
	    case FILE:
	    {
		g2 = (Graphics2D) g;
		try
                {
                    FileInputStream img = new FileInputStream("./images/archivo.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, r.width*2/10, r.height*2/10,r.width*2/3,r.height*2/3, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	    }
	    break;
	    case REDO:
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
                    FileInputStream img = new FileInputStream("./images/mano.png");
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
                    FileInputStream img = new FileInputStream("./images/lapiz.png");
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
                    FileInputStream img = new FileInputStream("./images/cursorBase.png");
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
                    FileInputStream img = new FileInputStream("./images/zoom.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, r.width*2/10, r.height*2/10,r.width*2/3,r.height*2/3, null);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            break;
	}
    }
    
    public void setColor(Color color){
	this.color = color;
    }

    
}
