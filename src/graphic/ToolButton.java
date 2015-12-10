
package graphic;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author LuisArturo
 */
public class ToolButton extends CustomButton{
    private GraphicDrawer gd;
    private int id;
    private Menu menu = null;
    public ToolButton(int id){
	this.setSize(100, 100);
	gd = new GraphicDrawer();
	this.id = id;
	setContentAreaFilled(false);
        this.addMouseListener(new MouseAdapter()
        {
           @Override
           public void mouseClicked(MouseEvent me) 
           {
                if(menu != null)
                {   
                    mostrarMenu();
                }
           }
        });
    }
    
    @Override
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	paintRules();
	g2.setBackground(getBackground());
	g.setColor(this.getParent().getBackground());
	g.setColor(getBackground());
	g.fillOval(0, 0, getWidth(), getHeight());
        
	g.setColor(Color.black);
	//g.drawOval(0, 0, getWidth(), getHeight());
	gd.paint(id, getSize(), g);
    }
    
    public void setMenu(Menu m)
    {menu = m;}
    
    private void mostrarMenu()
    {
        if(!getCursor().getName().equals(Cursor.getDefaultCursor().getName()))
            return;
        
        Point centroBoton = new Point(getX() + getWidth()/2, getY() + getHeight()/2);
        menu.setLocation((int)centroBoton.getX() - Menu.SIZE/2, (int)centroBoton.getY() - Menu.SIZE/2);
        menu.setVisible(true);}
}
