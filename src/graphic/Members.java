
package graphic;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author LuisArturo
 */
public class Members extends CustomButton{

    private PanelColaboradores pc;
    
    public Members(){
	setSize(50, 200);
	setLocation(0, 75);
        addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {   muestraPanel(); }
            }
        );
    }
    
    public void muestraPanel()
    {
        if(!getCursor().equals(Cursor.getDefaultCursor()))
            return;
        
        if(pc.isVisible())
        {
            this.setLocation(0, this.getY());
            pc.setVisible(false);
        }
        else
        {
            this.setLocation(pc.getWidth(), this.getY());
            pc.setVisible(true);
            pc.repaint();
            pc.setLocation(0, this.getY() - 50);
        }

    }
    @Override
    public void paint(Graphics g){
	paintRules();
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(getBackground());
	g2.fillRoundRect(-10, 0, getWidth() + 9, getHeight() - 1, 20, 20);
	g2.setColor(Color.black);
	g2.drawRoundRect(-10, 0, getWidth() + 9, getHeight() - 1, 20, 20);
	
	g2.rotate(Math.PI*3/2);
	g2.setColor(Color.black);
	g2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 30));
	g2.drawString("Colaboradores", -190, 35);
    }
    
    public void setPanel(PanelColaboradores aux)
    {   pc = aux;   }
}
