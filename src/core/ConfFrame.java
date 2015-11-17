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
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JColorChooser;

import static core.Canvas.seleccionado;
import javax.swing.JComboBox;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author LuisArturo
 */
public class ConfFrame extends JFrame{
    private static ConfFrame frame;
    private static boolean isCreating;
    
    public static final int RECTANGLE = 0;
    public static final int CIRCLE = 1;
    public static final int TRIANGLE = 2;
    public static final int RPOLYGON = 3;
    public static final int IPOLYGON = 4;
    public static final int LINE = 5;
    
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
	public static final int SQUARE = 9;
	
	private int type;
	private FlowLayout layout;
	private JLabel name;
	private JLabel aux1;
	private JLabel aux2;
	private JTextField field1;
	private JTextField field2;
	private Color bc;
	private JButton button;
	private JComboBox list;
	
	public Property(int type){
	    this.type = type;
	    
	    layout = new FlowLayout(FlowLayout.CENTER, 5, 5);
	    getContentPane().setLayout(layout);
	    
	    name = new JLabel();
	    field1 = new JTextField("0");
	    field2 = new JTextField("0");
		field1.setPreferredSize(new Dimension(45, 30));
		field2.setPreferredSize(new Dimension(45, 30));
	    aux1 = new JLabel();
	    aux2 = new JLabel();
	    button = new JButton("OK");
	    button.setPreferredSize(new Dimension(60, 30));
	    
	    list = new JComboBox();
	    
	    ActionListener action = (ActionEvent ae) -> {
		submit();
	    };
	    
	    ActionListener doClick = (ActionEvent ae) -> {
		button.doClick();
	    };
	    	    
	    field1.addActionListener(doClick);
	    field2.addActionListener(doClick);
	    button.addActionListener(action);
	    list.addActionListener(action);
	    	    	    
	    switch (type){
		case POSITION:
		    name.setText("Posición: ");
		    aux1.setText("X: ");    aux2.setText("Y: ");
		    field1.setText(String.valueOf(seleccionado.posX));
		    field2.setText(String.valueOf(seleccionado.posY));
		    
		    add(name);	add(aux1);  add(field1);
		    add(aux2);	add(field2);
		    break;
		case ANGLE:
		    name.setText("Ángulo: ");
		    field1.setText(String.valueOf(seleccionado.incline));
		    
		    add(name); add(field1);
		    break;
		case BCOLOR:
		    name.setText("Color de Fondo: ");
		    bc = new Color(((Figure) seleccionado).getBgColor().getRGB()
			    & 0x00FFFFFF);
		    button.setBackground(bc);
		    
		    add(name);	add(button);
		    break;
		case LCOLOR:
		    name.setText("Color de Línea: ");
		    bc = new Color(((Figure) seleccionado).getLnColor().getRGB()
			    & 0x00FFFFFF);
		    button.setBackground(bc);
		    
		    add(name);	add(button);
		    break;
		case RADIUS:
		    name.setText("Radio del Círculo: ");
		    field1.setText(String.valueOf(((Circle) seleccionado).getRadius()));
		    
		    add(name);	add(field1); add(button);
		    break;
		case TRIANGLE_TYPE:
		    name.setText("Típo de Triángulo: ");
		    list.addItem("Equilátero");
		    list.addItem("Rectángulo");
		    list.addItem("Isóceles");
		    
		    switch (((Triangle) seleccionado).getType()){
			case Triangle.EQUILATERAL_TRIANGLE:
			    list.setSelectedIndex(0);
			    break;
			case Triangle.RIGHT_TRIANGLE:
			    list.setSelectedIndex(1);
			    break;
			case Triangle.ISOSCELES_TRIANGLE:
			    list.setSelectedIndex(2);
			    break;
		    }
		    
		    add(name);	add(list);
		    break;
		case SQUARE:
		    name.setText("Base: ");
		    field1.setText(String.valueOf(((Rectangle) seleccionado).getWidth()));
		    aux1.setText("Altura: ");
		    field2.setText(String.valueOf(((Rectangle) seleccionado).getHeight()));
		    
		    add(name);	add(field1);	add(aux1);  add(field2);
		    add(button);
		    break;
		case FACES:
		    name.setText("Número de Lados: ");
		    field1.setText(String.valueOf(((RegularPolygon) seleccionado).getNumSides()));
		    
		    add(name);	add(field1);	add(button);
		    break;
		case FACE_SIZE:
		    name.setText("Tamaño de cada Lado: ");
		    field1.setText(String.valueOf(((RegularPolygon) seleccionado).getLongSide()));
		    
		    add(name);	add(field1);	add(button);
		    break;
	    }
	    pack();
	}
	
	public void submit(){
	    switch(type){
		case POSITION:{
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
			field1.setText(String.valueOf(seleccionado.getPos().x));
			field2.setText(String.valueOf(seleccionado.getPos().y));
		    }
		} break;
		case ANGLE:{
		    double angle;
		    
		    try{
			angle = Double.parseDouble(field1.getText());
			setAngle(angle);
		    } catch(NumberFormatException nfe){
			
		    } finally {
			field1.setText(String.valueOf(seleccionado.incline));
		    }
		} break;
		case BCOLOR:{
		    Color c;
		    c = JColorChooser.showDialog(this, null,
			    ((Figure) seleccionado).getBgColor());
		    if(c != null){
			setBGColor(c);
			c = new Color(((Figure) seleccionado).getBgColor().getRGB()
				& 0x00FFFFFF);
			button.setBackground(c);
		    }
		} break;
		case LCOLOR: {
		    Color c;
		    c = JColorChooser.showDialog(this, null, 
			    ((Figure) seleccionado).getLnColor());
		    if(c != null){
			setLnColor(c);
			c = new Color(((Figure) seleccionado).getLnColor().getRGB()
				& 0x00FFFFFF);
			button.setBackground(c);
		    }
		} break;
		case RADIUS:{
		    double r;
		    try {
			r = Double.parseDouble(field1.getText());
			if (r < 0) {
			    throw new NumberFormatException();
			}
			setRadius(r);
		    }catch(NumberFormatException nfe){
			
		    } finally {
			field1.setText(String.valueOf(((Circle) seleccionado).getRadius()));
		    }
		} break;
		case TRIANGLE_TYPE:{
		    if(((String)list.getSelectedItem()).matches("Equilátero"))
			setTriangleType(Triangle.EQUILATERAL_TRIANGLE);
		    if(((String)list.getSelectedItem()).matches("Rectángulo"))
			setTriangleType(Triangle.RIGHT_TRIANGLE);
		    if(((String)list.getSelectedItem()).matches("Isóceles"))
			setTriangleType(Triangle.ISOSCELES_TRIANGLE);
		} break;
		case SQUARE:{
		    int x1;
		    int y1;
		    try {
			x1 = Integer.parseInt(field1.getText());
			y1 = Integer.parseInt(field2.getText());
			if (x1 < 0 || y1 < 0) {
			    throw new NumberFormatException();
			}
			setSquare(x1, y1);
		    }catch(NumberFormatException nfe){
			
		    } finally {
			field1.setText(String.valueOf(((Rectangle) seleccionado).getWidth()));
			field2.setText(String.valueOf(((Rectangle) seleccionado).getHeight()));
		    }
		} break;
		case FACES:{
		    int n;
		    try{
			n = Integer.parseInt(field1.getText());
			if(n < 0)
			    throw new NumberFormatException();
			setFaces(n);
		    } catch (NumberFormatException nfe){
			
		    } finally {
			field1.setText(String.valueOf(((RegularPolygon) seleccionado).getNumSides()));
		    }
		} break;
		case FACE_SIZE:{
		    int s;
		    try{
			s = Integer.parseInt(field1.getText());
			if(s < 0)
			    throw new NumberFormatException();
			setFaceSize(s);
		    } catch (Exception e) {
			
		    } finally {
			field1.setText(String.valueOf(((RegularPolygon) seleccionado).getLongSide()));
		    }
		}
	    }
	    Canvas.repaint();
	}
    }
    
    private ConfFrame(){
	this.setResizable(false);
	this.setUndecorated(true);
	Box box = Box.createVerticalBox();
	
	if(Element.class.isAssignableFrom(seleccionado.getClass())){
	    //Property position = new Property(Property.POSITION);
	    //Property rotation = new Property(Property.ANGLE);
	    
	    //box.add(position);  box.add(rotation);  
	} if (Figure.class.isAssignableFrom(seleccionado.getClass())){
	    Property bgColor = new Property(Property.BCOLOR);
	    Property lnColor = new Property(Property.LCOLOR);
	    
	    box.add(bgColor);	box.add(lnColor);
	} if (Circle.class.isAssignableFrom(seleccionado.getClass())){
	    Property radius = new Property(Property.RADIUS);
	    
	    box.add(radius);
	} if (Triangle.class.isAssignableFrom(seleccionado.getClass())){
	    Property tType = new Property(Property.TRIANGLE_TYPE);
	    
	    box.add(tType);
	} if (Rectangle.class.isAssignableFrom(seleccionado.getClass())){
	    Property square = new Property(Property.SQUARE);
	    
	    box.add(square);
	} if (RegularPolygon.class.isAssignableFrom(seleccionado.getClass())){
	    Property noSides = new Property(Property.FACES);
	    Property side = new Property(Property.FACE_SIZE);
	    
	    box.add(noSides);	box.add(side);
	}
	
	JButton close = new JButton("Terminar");
	ActionListener closeA = (ActionEvent ae) -> {
	    closeFrame();
	};
	close.addActionListener(closeA);
	box.add(close);
	
	if(isCreating){
	    close.setText("Cancelar");
	    JButton done = new JButton("Crear");
	    ActionListener doneA = (ActionEvent ae) -> {
		finnishCreate();
	    };
	    done.addActionListener(doneA);
	    box.add(done);
	}
	
	add(box);
	pack();
    }
    
    public static void showFrame(){
	frame = new ConfFrame();
	frame.setVisible(true);
    }
    
    public static void showFrame(Point p){
	showFrame();
	frame.setLocation(p);
    }
    
    public static void create(int type, Point p){
	isCreating = true;
	switch(type){
	    case CIRCLE:
	    seleccionado = new Circle();
	    break;
	    case RECTANGLE:
	    seleccionado = new Rectangle();
	    break;
	    case LINE:
	    seleccionado = new Line();
	    break;
	    case RPOLYGON:
	    seleccionado = new RegularPolygon();
	    break;
	    case TRIANGLE:
	    seleccionado = new Triangle();
	    break;
	}
	seleccionado.move(p);
	showFrame(p);
    }
    
    private static void finnishCreate(){
	Canvas.addElement(seleccionado);
	closeFrame();
    }
    
    private static void closeFrame(){
	frame.setVisible(false);
	frame = null;
	System.gc();
	isCreating = false;
    }
    
    public static void setPosition(Point p){
	if(!isCreating)
	Action.createAction(Action.ELEMENT_MOVE, Canvas.elements.indexOf(seleccionado), p);
	seleccionado.move(p.x, p.y);
    }
    
    public static void setAngle(double f){
	if(!isCreating)
	Action.createAction(Action.ELEMENT_ROTATE, Canvas.elements.indexOf(seleccionado), f);
	seleccionado.setIncline(f);
    }
    
    public static void setBGColor(Color c){
	if(!isCreating)
	Action.createAction(Action.FIGURE_BCOLOR, Canvas.elements.indexOf(seleccionado), c);
	((Figure) seleccionado).setBgColor(c);
    }
    
    public static void setLnColor(Color c){
	if(!isCreating)
	Action.createAction(Action.FIGURE_LCOLOR, Canvas.elements.indexOf(seleccionado), c);
	((Figure) seleccionado).setLnColor(c);
    }
    
    public static void setRadius(double r){
	if(!isCreating)
	Action.createAction(Action.CIRCLE_RADIUS, Canvas.elements.indexOf(seleccionado), r);
	((Circle) seleccionado).setRadius(r);
    }
    
    public static void setTriangleType(int i){
	if(!isCreating)
	Action.createAction(Action.TRIANGLE_TYPE, Canvas.elements.indexOf(seleccionado), i);
	((Triangle) seleccionado).setType(i);
    }
    
    public static void setSquare(int x, int y){
	if(!isCreating)
	Action.createAction(Action.SQUARE, Canvas.elements.indexOf(seleccionado), new Point(x, y));
	((Rectangle) seleccionado).setWidth(x);
	((Rectangle) seleccionado).setWidth(y);
    }
    
    public static void setFaces(int n){
	if(!isCreating)
	Action.createAction(Action.NO_SIDES, Canvas.elements.indexOf(seleccionado), n);
	((RegularPolygon) seleccionado).setNumSides(n);
    }
    
    public static void setFaceSize(int d){
	if(!isCreating)
	Action.createAction(Action.SIDE_SIZE, Canvas.elements.indexOf(seleccionado), d);
	((RegularPolygon) seleccionado).setLongSide(d);
    }
}
