

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
    public static final int IMAGE = 7;
    public static final int NEW = 8;
    public static final int OPEN = 9;
    public static final int SAVE = 10;
    private Color color = Color.black;
    
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
                FileInputStream img = new FileInputStream("triangulo.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/3, y + alto/3,ancho/2,alto/2, null);
               
	    }
	    break;
            case CIRCLE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("circulo.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/7, y + alto/4,ancho/2,alto/2, null);
                
	    }    
            break;
            case POLIGONE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("poligono.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/4,ancho/2,alto/2, null);
	    }
            break;
            case RECTANGLE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("cuadrado.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/2,ancho/2,alto/2, null);
	    }
            break;
            case LINE:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("line.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/7,ancho/2,alto/2, null);
	    }
            break;                
            case IRREGULAR:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("doodle.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/3, y + alto/7,ancho/2,alto/2, null);
	    }
            break;                        
            case EXIT:
	    {
		g2 = (Graphics2D) g;
                FileInputStream img = new FileInputStream("salir.png");
                BufferedImage in = ImageIO.read(img);
                g2.drawImage(in, x + ancho/4, y + alto/7,ancho/2,alto/2, null);
	    }
            break;                    
	    
	}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void setColor(Color color){
	this.color = color;
    }

    
}

    

