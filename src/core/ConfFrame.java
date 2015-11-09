/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JColorChooser;

/**
 *
 * @author LuisArturo
 */
public class ConfFrame extends JFrame{    
    private static Element e;
    private static ConfFrame frame;
    
    public static void main(String args[]){
	//Simulador
	{
	    Canvas c;
	    c = new Canvas();
	    c.addElement(new Circle(15));
	    e = Canvas.elements.get(0);
	}
	
	java.awt.EventQueue.invokeLater(() -> {
	    new ConfFrame().setVisible(true);
	});
    }
    
    private class Property extends JPanel{
	public static final int POSITION = 0;
	public static final int ANGLE = 1;
	public static final int BCOLOR = 2;
	public static final int LCOLOR = 21;
	public static final int FACES = 3;
	public static final int SIZE = 4;
	public static final int TRIANGLE_TYPE = 5;
	public static final int FACE_SIZE = 6;
	public static final int LENGTH = 7;
	public static final int RADIUS = 8;
	
	public ActionListener action;
		
	private FlowLayout layout;
	private JLabel name;
	private JLabel aux1;
	private JLabel aux2;
	private JTextField field1;
	private JTextField field2;
	private Color bc;
	private JButton button;
	
	public Property(int type){
	    Property thisP = this;
	    layout = new FlowLayout(FlowLayout.CENTER, 5, 5);
	    getContentPane().setLayout(layout);
	    
	    
	    name = new JLabel();
	    field1 = new JTextField("0");
	    field2 = new JTextField("0");
		field1.setPreferredSize(new Dimension(45, 30));
		field2.setPreferredSize(new Dimension(45, 30));
	    aux1 = new JLabel();
	    aux2 = new JLabel();
	    
	    action = (ActionEvent ae) -> {
		if (ae.getActionCommand().equals("Pos")) {
		    int x1;
		    int y1;
		    try {
			x1 = Integer.parseInt(field1.getText());
			y1 = Integer.parseInt(field2.getText());
			if (x1 < 0 || y1 < 0) {
			    throw new NumberFormatException();
			}
			setPosition(new Point(x1, y1));
		    }catch(NumberFormatException nfe){
			
		    } finally {
			field1.setText(String.valueOf(e.getPos().x));
			field2.setText(String.valueOf(e.getPos().y));
		    }
		}
		if(ae.getActionCommand().equals("Angle")){
		    double angle;
		    
		    try{
			angle = Double.parseDouble(field1.getText());
			setAngle(angle);
		    } catch(NumberFormatException nfe){
			
		    } finally {
			field1.setText(String.valueOf(e.incline));
		    }
		}
		if(ae.getActionCommand().equals("BColor")){
		    Color c;
		    c = JColorChooser.showDialog(thisP, null, ((Figure) e).getBgColor());
		    if(c != null){
			setBGColor(c);
			c = new Color(((Figure) e).getBgColor().getRGB()
				& 0x00FFFFFF);
			button.setBackground(c);
		    }
		}
		if(ae.getActionCommand().equals("LColor")){
		    Color c;
		    c = JColorChooser.showDialog(thisP, null, ((Figure) e).getLnColor());
		    if(c != null){
			setLnColor(c);
			c = new Color(((Figure) e).getLnColor().getRGB()
				& 0x00FFFFFF);
			button.setBackground(c);
		    }
		} if(ae.getActionCommand().equals("Radius")){
		    double r;
		    try {
			r = Double.parseDouble(field1.getText());
			if (r < 0) {
			    throw new NumberFormatException();
			}
			setRadius(r);
		    }catch(NumberFormatException nfe){
			
		    } finally {
			field1.setText(String.valueOf(((Circle) e).getRadius()));
		    }
		}
		Canvas.canvas.repaint();
	    };
	    
	    button = new JButton("OK");
	    button.setPreferredSize(new Dimension(60, 30));
	    button.addActionListener(action);
	    	    
	    switch (type){
		case POSITION:
		    name.setText("Posición: ");
		    aux1.setText("X: ");    aux2.setText("Y: ");
		    field1.setText(String.valueOf(e.posX));
		    field2.setText(String.valueOf(e.posY));
		    button.setActionCommand("Pos");
		    
		    add(name);	add(aux1);  add(field1);
		    add(aux2);	add(field2); add(button);
		    break;
		case ANGLE:
		    name.setText("Ángulo: ");
		    field1.setText(String.valueOf(e.incline));
		    button.setActionCommand("Angle");
		    
		    add(name); add(field1); add(button);
		    break;
		case BCOLOR:
		    name.setText("Color de Fondo: ");
		    bc = new Color(((Figure) e).getBgColor().getRGB()
			    & 0x00FFFFFF);
		    button.setBackground(bc);
		    button.setActionCommand("BColor");
		    
		    add(name);	add(button);
		    break;
		case LCOLOR:
		    name.setText("Color de Línea: ");
		    bc = new Color(((Figure) e).getLnColor().getRGB()
			    & 0x00FFFFFF);
		    button.setBackground(bc);
		    button.setActionCommand("LColor");
		    
		    add(name);	add(button);
		    break;
		case RADIUS:
		    name.setText("Radio del Círculo: ");
		    field1.setText(String.valueOf(((Circle) e).getRadius()));
		    button.setActionCommand("Radius");
		    
		    add(name);	add(field1);	add(button);
		    break;
	    }
	    pack();
	}
    }
    
    private ConfFrame(){
	this.setResizable(false);
	this.setUndecorated(true);
	Box box = Box.createVerticalBox();
	
	if(Element.class.isAssignableFrom(e.getClass())){
	    Property position = new Property(Property.POSITION);
	    Property rotation = new Property(Property.ANGLE);
	    
	    box.add(position);  box.add(rotation);  
	} if (Figure.class.isAssignableFrom(e.getClass())){
	    Property bgColor = new Property(Property.BCOLOR);
	    Property lnColor = new Property(Property.LCOLOR);
	    
	    box.add(bgColor);	box.add(lnColor);
	} if (Circle.class.isAssignableFrom(e.getClass())){
	    Property radius = new Property(Property.RADIUS);
	    
	    box.add(radius);
	}
	
	JButton close = new JButton("Terminar");
	ActionListener closeA = (ActionEvent ae) -> {
	    closeFrame();
	};
	close.addActionListener(closeA);
	box.add(close);
	
	add(box);
	pack();
    }
    
    public static void showFrame(Element elm){
	if(elm == null)
	    return;
	e = elm;
	frame = new ConfFrame();
	frame.setVisible(true);
    }
    
    private static void closeFrame(){
	frame.setVisible(false);
	frame = null;
	System.gc();
    }
    
    public static void setPosition(Point p){
	Action.createAction(Action.ELEMENT_MOVE, Canvas.elements.indexOf(e), p);
	e.move(p.x, p.y);
    }
    
    public static void setAngle(double f){
	Action.createAction(Action.ELEMENT_ROTATE, Canvas.elements.indexOf(e), f);
	e.setIncline(f);
    }
    
    public static void setBGColor(Color c){
	Action.createAction(Action.FIGURE_BCOLOR, Canvas.elements.indexOf(e), c);
	((Figure) e).setBgColor(c);
    }
    
    public static void setLnColor(Color c){
	Action.createAction(Action.FIGURE_LCOLOR, Canvas.elements.indexOf(e), c);
	((Figure) e).setLnColor(c);
    }
    
    public static void setRadius(double r){
	Action.createAction(Action.CIRCLE_RADIUS, Canvas.elements.indexOf(e), r);
	((Circle) e).setRadius(r);
    }
}
