

package core;

/**
 *
 * @author Angeles
 */

import java.awt.*;
import java.awt.geom.Area;
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
    public static final int IMAGE = 7;
    public static final int NEW = 8;
    public static final int OPEN = 9;
    public static final int SAVE = 10;
    public static final int MAS = 11;
    public static final int MENOS = 12;
    public static final int UNDO = 13;
    public static final int REDO = 14;
    public static final int CONFIGURE = 15;
    public static final int MOVE = 16;
    public static final int ROTATE = 17;
    public static final int DISPOSE = 18;
    public static final int EXIT2 = 19;
    public static final int TEXT = 20;
    
    /*
    * @author LuisArturo
    */
    public void paint(int id, Graphics g, Area a){
	Graphics2D g2;
        int x = a.getBounds().x;
        int y = a.getBounds().y;
        int ancho = a.getBounds().width;
        int alto = a.getBounds().height;
	try{
        switch(id){
	    case TRIANGLE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("./images/triangulo.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/3, y + alto/3,ancho/2,alto/2, null);
               
	    }
	    break;
            case CIRCLE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("./images/circulo.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/7, y + alto/4,ancho/2,alto/2, null);
                
	    }    
            break;
            case POLIGONE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("./images/poligono.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/4,ancho/2,alto/2, null);
	    }
            break;
            case RECTANGLE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("./images/cuadrado.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/2,ancho/2,alto/2, null);
	    }
            break;
            case LINE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("./images/line.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/7,ancho/2,alto/2, null);
	    }
            break;                
            case IRREGULAR:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("./images/doodle.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/2, y + alto/6,ancho/2,alto/2, null);
	    }
            break;                        
            case EXIT:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("./images/salir.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/7,ancho/2,alto/2, null);
	    }
            break;    
                case IMAGE : 
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/image.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/3,ancho/3,alto/2, null);
                }
                break;
                case NEW :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/new.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/3,ancho/2,alto/2, null);
                }
                break;    
                case SAVE :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/save.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/5, y + alto/7,ancho/2,alto*2/3, null);
                }
                break;
                case OPEN :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/open.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/7,ancho/2,alto/2, null);
                }
                break;
                case MAS :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/plus.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/5,ancho/3,alto/2, null);
                }
                break;
                case MENOS :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/menos.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/3,ancho/3,alto/2, null);
                }
                break;
                case UNDO :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/undo.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/5,ancho/3,alto/2, null);
                }
                break;
                case REDO :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/redo.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/3,ancho/3,alto/2, null);
                }
                break;
                case CONFIGURE :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/configure.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/3,ancho/2,alto/2, null);
                }
                break;
                case MOVE :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/move.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/3, y + alto/3,ancho/2,alto/2, null);
                }
                break;    
                case ROTATE :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/rotate.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/4, y + alto/5,ancho/2,alto/2, null);
                }
                break;
                case DISPOSE :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/trash.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/8, y + alto/4,ancho/2,alto/2, null);
                }
                break;    
                case TEXT :
                {
                    g2 =(Graphics2D)g;
                    FileInputStream img = new FileInputStream("./images/text.png");
                    BufferedImage in = ImageIO.read(img);
                    g2.drawImage(in, x + ancho/8, y + alto/4,ancho/2,alto/2, null);
                }
                break;
	    
	}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

    

