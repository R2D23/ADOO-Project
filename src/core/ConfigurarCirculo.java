
package core;


import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConfigurarCirculo extends javax.swing.JFrame implements MouseListener{
private double radio;
private Color relleno;
private Color contorno;
private ArrayList<Area> areas;
private Canvas canvas;
private Circle circulo;
private int tipoMenu;

    public ConfigurarCirculo(ArrayList<Area> a, Canvas c, int tip) {
        initComponents();
        areas = a;
        canvas = c;
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        tipoMenu = tip;
        //this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textConfigurarCirculo = new javax.swing.JLabel();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        textoRadio = new javax.swing.JLabel();
        textoRelleno = new javax.swing.JLabel();
        textoColordelinea = new javax.swing.JLabel();
        Radio = new javax.swing.JTextField();
        BottonEscogerColorRelleno = new javax.swing.JButton();
        ButtonEscogerColorLinea = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textConfigurarCirculo.setText("Configurar Circulo");

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        textoRadio.setText("Radio:");

        textoRelleno.setText("Relleno:");

        textoColordelinea.setText("Color de linea:");

        Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioActionPerformed(evt);
            }
        });

        BottonEscogerColorRelleno.setText("EscogerColor");
        BottonEscogerColorRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BottonEscogerColorRellenoActionPerformed(evt);
            }
        });

        ButtonEscogerColorLinea.setText("EscogerColor");
        ButtonEscogerColorLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEscogerColorLineaActionPerformed(evt);
            }
        });

        jLabel5.setText("px");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(textConfigurarCirculo))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoRelleno)
                            .addComponent(textoRadio)
                            .addComponent(textoColordelinea))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BottonEscogerColorRelleno)
                            .addComponent(ButtonEscogerColorLinea)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(Aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelar)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textConfigurarCirculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Radio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoRadio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoRelleno)
                    .addComponent(BottonEscogerColorRelleno))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoColordelinea)
                    .addComponent(ButtonEscogerColorLinea))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Aceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
                setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_CancelarActionPerformed

    private void RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioActionPerformed

    private void BottonEscogerColorRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BottonEscogerColorRellenoActionPerformed
            relleno = JColorChooser.showDialog(rootPane, null, relleno);
            this.BottonEscogerColorRelleno.setBackground(relleno);
    }//GEN-LAST:event_BottonEscogerColorRellenoActionPerformed

    private void ButtonEscogerColorLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEscogerColorLineaActionPerformed
            contorno = JColorChooser.showDialog(rootPane, null, relleno);
            this.ButtonEscogerColorLinea.setBackground(contorno);
    }//GEN-LAST:event_ButtonEscogerColorLineaActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        canvas.actElements();
        if(Radio.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Has ingresado datos erróneos, favor de revisarlos e intentarlo\n" +
"nuevamente.");
        else
        {
            radio=Double.parseDouble(this.Radio.getText());
            if((radio!=0) && (relleno != null) && (contorno != null))
            {
                setVisible(false);
                circulo.bgColor = relleno;
                circulo.lnColor = contorno;
                circulo.setRadius(this.radio);
                circulo.area =  new Area(new Ellipse2D.Double(circulo.posX, circulo.posY, (int)radio,(int)radio));
                if(!canvas.elements.contains(circulo))
                    canvas.addElement(circulo);
                canvas.repaint();
                circulo = null;
                this.BottonEscogerColorRelleno.setBackground(Util.normalColor);
                this.ButtonEscogerColorLinea.setBackground(Util.normalColor);
                this.Radio.setText("");
            }
            else
                JOptionPane.showMessageDialog(rootPane, "Has ingresado datos erróneos, favor de revisarlos e intentarlo\n" +
"nuevamente.");
            
        }


    }//GEN-LAST:event_AceptarActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton BottonEscogerColorRelleno;
    private javax.swing.JButton ButtonEscogerColorLinea;
    private javax.swing.JButton Cancelar;
    private javax.swing.JTextField Radio;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel textConfigurarCirculo;
    private javax.swing.JLabel textoColordelinea;
    private javax.swing.JLabel textoRadio;
    private javax.swing.JLabel textoRelleno;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(this.tipoMenu)
        {
            case Escucha.FIGUREMENU:
                if(whichArea(e.getPoint()) == 1)
                {
                    e.getComponent().setVisible(false);
                    this.setLocation(e.getLocationOnScreen());
                    this.setVisible(true);
                    if(circulo == null)
                    {
                        circulo = new Circle();
                        circulo.posX = e.getXOnScreen();
                        circulo.posY = e.getYOnScreen();
                    }
                }
            break;
            case Escucha.SELECTIONMENU :
                if(whichArea(e.getPoint()) == 0)
                {
                    e.getComponent().setVisible(false);
                    try{
                        this.circulo = (Circle)((SelectionMenu)(e.getComponent())).elemento;
                        this.Radio.setText(circulo.getRadius() + "");
                        relleno = circulo.bgColor;
                        contorno = circulo.lnColor;
                        this.BottonEscogerColorRelleno.setBackground(relleno);
                        this.ButtonEscogerColorLinea.setBackground(contorno);
                        this.setVisible(true);
                    }
                    catch(Exception ex){}
                    
                }
            break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int whichArea(Point p)
    {
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
                return i;
        return -1;
    }
    
    public Element cualFigura(Point p)
    {
        for(int i = 0; i < canvas.elements.size(); i++)
            if(canvas.elements.get(i).area.contains(p))
                return canvas.elements.get(i);
        return null;
    }
}
