
package graphic;
import javax.swing.*;
/**
 *
 * @author LuisArturo
 */
public abstract class CustomButton extends JButton{
    public void paintRules(){
	if(model.isRollover())
	    setBackground(Util.rollOverColor);
	if(model.isPressed())
	    setBackground(Util.pressedColor);
	if(!model.isRollover() && !model.isPressed())
	    setBackground(Util.normalColor);
    }
}
