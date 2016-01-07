package graphic;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Angeles
 */
public class PanelConfig
{
    public static final int RECTANGLE = 0;
    public static final int CIRCLE = 1;
    public static final int TRIANGLE = 2;
    public static final int RPOLYGON = 3;
    public static final int IPOLYGON = 4;
    public static final int LINE = 5;
    public static final int TEXT = 6;
    public static final int IMAGE = 7;
    
    private JPanel panel;
    
    private JLabel lColorLinea; /* Color de linea en figuras y de letra para texto */
    private JLabel lColorFondo; /* Color de fondo para figuras*/ 
    private JLabel lDato1; /* radio, tamLados, largoRect, base, largoLinea,tamTexto*/
    private JLabel lDato2; /* numLados, anchoRect, alturaTrian, grosorLinea */
    private JLabel lTipoTrian;
    private JLabel lMensaje;
    
    private JTextField vDato1;
    private JTextField vDato2;
    private JTextField vMensaje;
    private JComboBox vTipoTrian;
    private JButton vColorLinea;
    private JButton vColorFondo;
    private Color colorLinea;
    private Color colorFondo;
    
    public PanelConfig(int type)
    {
        panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));
        
        vColorLinea = new JButton("Escoger Color");
        vColorLinea.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    colorLinea = javax.swing.JColorChooser.showDialog(panel, null, colorLinea);
                    vColorLinea.setBackground(colorLinea);
                }
            }  
        );
        vColorFondo = new JButton("Escoger Color");
        vColorFondo.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    colorFondo = javax.swing.JColorChooser.showDialog(panel, null, colorFondo);
                    vColorFondo.setBackground(colorFondo);
                }
            }  
        );
        
        switch(type)
        {
            case RECTANGLE :
                obtenerVistaRectangulo();
            break;
            case TRIANGLE :
                obtenerVistaTriangulo();
            break;
            case RPOLYGON :
                obtenerVistaPoligonoR();
            break;
            case CIRCLE :
                obtenerVistaCirculo();
            break;
            case LINE :
                obtenerVistaLinea();
            break;
            case TEXT :
                obtenerVistaTexto();
            break;
            case IPOLYGON :
                obtenerVistaPoligonoI();
            break;
            case IMAGE :
                obtenerVistaImagen();
            break;
        }
        
        
    }
    
    private void obtenerVistaRectangulo()
    {
        lColorLinea = new JLabel("Color de Linea : ");
        lColorFondo = new JLabel("Color de Fondo : ");
        lDato1 = new JLabel("Largo : ");
        lDato2 = new JLabel("Ancho : ");
        
        vDato1 = new JTextField();
        vDato2 = new JTextField();
        
        panel.add(lDato1);
        panel.add(vDato1);
        panel.add(lDato2);
        panel.add(vDato2);
        panel.add(lColorLinea);
        panel.add(vColorLinea);
        panel.add(lColorFondo);
        panel.add(vColorFondo);
        panel.setBackground(Util.normalColor);
    }
    
    private void obtenerVistaTriangulo()
    {

        lColorLinea = new JLabel("Color de Linea : ");
        lColorFondo = new JLabel("Color de Fondo : ");
        lDato1 = new JLabel("Base : ");
        lDato2 = new JLabel("Altura : ");
        lTipoTrian = new JLabel("Tipo triangulo : ");
        
        vDato1 = new JTextField();
        vDato2 = new JTextField();
        String tipos [] = {"Equilatero", "Rectángulo","Isosceles"};
        vTipoTrian = new JComboBox(tipos);
        
        
        panel.add(lDato1);
        panel.add(vDato1);
        panel.add(lDato2);
        panel.add(vDato2);
        panel.add(lTipoTrian);
        panel.add(vTipoTrian);
        panel.add(lColorLinea);
        panel.add(vColorLinea);
        panel.add(lColorFondo);
        panel.add(vColorFondo);
        panel.setBackground(Util.normalColor);
    }
    
    private void obtenerVistaPoligonoR()
    {
        lColorLinea = new JLabel("Color de Linea : ");
        lColorFondo = new JLabel("Color de Fondo : ");
        lDato1 = new JLabel("Tamaño de lados : ");
        lDato2 = new JLabel("Número de lados : ");
        
        vDato1 = new JTextField();
        vDato2 = new JTextField();
        
        
        panel.add(lDato1);
        panel.add(vDato1);
        panel.add(lDato2);
        panel.add(vDato2);
        panel.add(lColorLinea);
        panel.add(vColorLinea);
        panel.add(lColorFondo);
        panel.add(vColorFondo);
        panel.setBackground(Util.normalColor);
    }
    
    private void obtenerVistaCirculo()
    {
        lColorLinea = new JLabel("Color de Linea : ");
        lColorFondo = new JLabel("Color de Fondo : ");
        lDato1 = new JLabel("Radio : ");
        
        vDato1 = new JTextField();
        
        panel.add(lDato1);
        panel.add(vDato1);
        panel.add(lColorLinea);
        panel.add(vColorLinea);
        panel.add(lColorFondo);
        panel.add(vColorFondo);
        panel.setBackground(Util.normalColor);
    }
    
    private void obtenerVistaLinea()
    {
        lColorLinea = new JLabel("Color de Linea : ");
        lDato1 = new JLabel("Largo : ");
        lDato2 = new JLabel("Grosor : ");
        
        vDato1 = new JTextField();
        vDato2 = new JTextField();
        
        
        panel.add(lDato1);
        panel.add(vDato1);
        panel.add(lDato2);
        panel.add(vDato2);
        panel.add(lColorLinea);
        panel.add(vColorLinea);
        panel.setBackground(Util.normalColor);
    }
    
    private void obtenerVistaTexto()
    {
        lColorLinea = new JLabel("Color de Texto : ");
        lDato1 = new JLabel("Tamaño : ");
        lMensaje = new JLabel("Texto : ");
        
        vDato1 = new JTextField();
        vMensaje = new JTextField();
        
        panel.add(lMensaje);
        panel.add(vMensaje);
        panel.add(lDato1);
        panel.add(vDato1);
        panel.add(lColorLinea);
        panel.add(vColorLinea);
        panel.setBackground(Util.normalColor);
    }
    
    private void obtenerVistaPoligonoI()
    {
        lColorLinea = new JLabel("Color de Linea : ");
        lColorFondo = new JLabel("Color de Fondo : ");
        
        panel.add(lColorLinea);
        panel.add(vColorLinea);
        panel.add(lColorFondo);
        panel.add(vColorFondo);
        panel.setBackground(Util.normalColor);
    
    }
    
    private void obtenerVistaImagen()
    {
        lDato1 = new JLabel("Largo : ");
        lDato2 = new JLabel("Ancho : ");
        
        vDato1 = new JTextField();
        vDato2 = new JTextField();
        
        panel.add(lDato1);
        panel.add(vDato1);
        panel.add(lDato2);
        panel.add(vDato2);
        panel.setBackground(Util.normalColor);
    }
    
    public JPanel getPanel()
    {return panel;}
    
    public void setColores()
    {
        vColorLinea.setBackground(colorLinea);
        vColorFondo.setBackground(colorFondo);
    }
    
    public Object [] getValoresRectangulo()
    {
        /* regresa : largo, ancho, colorLinea, colorFondo */
        Object [] datos = new Object[4];
        datos[0] = Integer.parseInt(vDato1.getText());
        datos[1] = Integer.parseInt(vDato2.getText());
        datos[2] = colorLinea;
        datos[3] = colorFondo;
        return datos;
    }
    
    public Object [] getValoresTriangulo()
    {
        /* regresa : base, altura, tipo, colorLinea, colorFondo */
        Object [] datos = new Object[5];
        datos[0] = Integer.parseInt(vDato1.getText());
        datos[1] = Integer.parseInt(vDato2.getText());
        datos[2] = vTipoTrian.getSelectedIndex() + 1;
        datos[3] = colorLinea;
        datos[4] = colorFondo;
        return datos;
    }
    
    public Object [] getValoresCirculo()
    {
        /* regresa : radio, colorLinea, colorFondo */
        Object [] datos = new Object[3];
        datos[0] = Double.parseDouble(vDato1.getText());
        datos[1] = colorLinea;
        datos[2] = colorFondo;
        return datos;
    }
    
    public Object [] getValoresPoligonoR()
    {
        /* regresa : tamLados, numLados, colorLinea, colorFondo */
        Object [] datos = new Object[4];
        datos[0] = Integer.parseInt(vDato1.getText());
        datos[1] = Integer.parseInt(vDato2.getText());
        datos[2] = colorLinea;
        datos[3] = colorFondo;
        return datos;
    }
    
    public Object [] getValoresLinea()
    {
        /* regresa : largo, grueso , colorLinea */
        Object [] datos = new Object[3];
        datos[0] = Double.parseDouble(vDato1.getText());
        datos[1] = Float.parseFloat(vDato2.getText());
        datos[2] = colorLinea;
        return datos;
    }
    
    public Object [] getValoresText()
    {
        /* regresa : tamaño, mensaje , color */
        Object [] datos = new Object[3];
        datos[0] = Integer.parseInt(vDato1.getText());
        datos[1] = vMensaje.getText();
        datos[2] = colorLinea;
        return datos;
    }
    
    public Object [] getValoresPoligonoI()
    {
        /* regresa : colorLinea, colorFondo */
        Object [] datos = new Object[2];
        datos[0] = colorLinea;
        datos[1] = colorFondo;
        return datos;
    }
    
    public Object [] getValoresImagen()
    {
        /* regresa : width, heigth */
        Object [] datos = new Object[2];
        datos[0] = Integer.parseInt(vDato1.getText());
        datos[1] = Integer.parseInt(vDato2.getText());
        return datos;
    }
    
    public void setValoresRectangulo(Object [] valores)
    {
        /* recibe : largo, ancho, colorLinea, colorFondo */
        vDato1.setText(valores[0].toString());
        vDato2.setText(valores[1].toString());
        colorLinea = (Color)valores[2];
        colorFondo = (Color)valores[3];
        setColores();
    }
    
    public void setValoresTriangulo(Object [] valores)
    {
        /* recibe : base, altura, tipoTrian, colorLinea, colorFondo */
        vDato1.setText(valores[0].toString());
        vDato2.setText(valores[1].toString());
        vTipoTrian.setSelectedIndex(((int)valores[2]) - 1);
        colorLinea = (Color)valores[3];
        colorFondo = (Color)valores[4];
        setColores();
    }
    
    public void setValoresPoligonoR(Object [] valores)
    {
         /* recibe : tamLados, numLados, colorLinea, colorFondo */
        vDato1.setText(valores[0].toString());
        vDato2.setText(valores[1].toString());
        colorLinea = (Color)valores[2];
        colorFondo = (Color)valores[3];
        setColores();
    }
    
    public void setValoresCirculo(Object [] valores)
    {
        /* recibe : radio, colorLinea, colorFondo */
        vDato1.setText(valores[0].toString());
        colorLinea = (Color)valores[1];
        colorFondo = (Color)valores[2];
        setColores();
    }
    
    public void setValoresLinea(Object [] valores)
    {
        /* recibe : largo, grueso, colorLinea */
        vDato1.setText(valores[0].toString());
        vDato2.setText(valores[1].toString());
        colorLinea = (Color)valores[2];
        vColorLinea.setBackground(colorLinea);
    }
    
    public void setValoresText(Object [] valores)
    {
        /* recibe : tamaño, mensaje , color */
        vDato1.setText(valores[0].toString());
        vMensaje.setText(valores[1].toString());
        colorLinea = (Color)valores[2];
        vColorLinea.setBackground(colorLinea);
    }
    
    public void setValoresPoligonoI(Object [] valores)
    {
        /* recibe : colorLinea, colorFondo */
        colorLinea = (Color)valores[0];
        colorFondo = (Color)valores[1];
        setColores();
    }
    
    public void setValoresImagen(Object [] valores)
    {
        /* recibe : largo, ancho*/
        vDato1.setText(valores[0].toString());
        vDato2.setText(valores[1].toString());
        
    }
    
    
}
    
    

