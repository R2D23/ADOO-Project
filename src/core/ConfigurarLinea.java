
package core;

import core.Barradecolores;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JColorChooser;


public class ConfigurarLinea extends javax.swing.JFrame implements MouseListener{

private double largo;
//private float angulo;
private float grueso;
private Color contorno;
private ArrayList<Area> areas;
private Canvas canvas;
private Line l;
private int tipoMenu;
    
    

    public ConfigurarLinea(ArrayList<Area> a, Canvas c, int t) {
        initComponents();
        areas = a;
        canvas = c;
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        tipoMenu = t;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        EscogerColorLinea = new javax.swing.JButton();
        Largo = new javax.swing.JTextField();
        Grosor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ConfigurarLinea");

        jLabel2.setText("Largo:");

        jLabel3.setText("Grosor");

        jLabel4.setText("ColorLinea");

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

        EscogerColorLinea.setText("EscogerColor");
        EscogerColorLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscogerColorLineaActionPerformed(evt);
            }
        });

        Largo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Largo(evt);
            }
        });

        Grosor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrosorActionPerformed(evt);
            }
        });

        jLabel5.setText("px");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Grosor, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Largo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EscogerColorLinea)
                            .addComponent(jLabel1))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(Aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelar)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(Largo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Grosor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EscogerColorLinea)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Aceptar)
                    .addComponent(Cancelar))
                .addGap(0, 28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        canvas.actElements();
        try
        {
            largo=Double.parseDouble(this.Largo.getText());
            //angulo = (float)this.inclinacion.getValue();
            grueso = Float.parseFloat(this.Grosor.getText());
            if((largo!=0) && (contorno != null))
            {
                //l.angle = angulo;
                l.color = contorno;
                l.length = largo;
                l.grosor = grueso;
                //java.awt.Rectangle r2d = new java.awt.Rectangle(l.posX, l.posY, (int)(l.length*Math.cos(l.angle)),(int)(l.length*Math.sin(l.angle)));
                java.awt.Rectangle r2d = new java.awt.Rectangle(l.posX-(int)(l.grosor/2), l.posY-(int)(l.grosor/2), (int)(l.length + l.grosor), (int)l.grosor);
                AffineTransform atr = new AffineTransform();
                atr.setToRotation(l.incline);
                l.area = new Area(r2d);
                //l.area.transform(atr);
                canvas.elements.add(l);
                canvas.repaint();
                setVisible(false);
            }    
        }
        catch(Exception e)
        {
            
        }

    }//GEN-LAST:event_AceptarActionPerformed

    private void EscogerColorLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscogerColorLineaActionPerformed
       contorno = JColorChooser.showDialog(rootPane, null, contorno);
       this.EscogerColorLinea.setBackground(contorno);
    }//GEN-LAST:event_EscogerColorLineaActionPerformed

    private void GrosorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrosorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GrosorActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_CancelarActionPerformed

    private void Largo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Largo
    }//GEN-LAST:event_Largo

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton EscogerColorLinea;
    private javax.swing.JTextField Grosor;
    private javax.swing.JTextField Largo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(tipoMenu)
        {
            case Escucha.FIGUREMENU :
                if(whichArea(e.getPoint()) == 4)
                {
                    e.getComponent().setVisible(false);
                    this.setLocation(e.getLocationOnScreen());
                    this.setVisible(true);
                    l = new Line();
                    l.posX = e.getXOnScreen();
                    l.posY = e.getYOnScreen();
                }
            break;
            case Escucha.SELECTIONMENU :
                if(whichArea(e.getPoint()) == 0)
                {
                    e.getComponent().setVisible(false);
                    try{
                        this.l = (Line)canvas.seleccionado;
                        this.Largo.setText(l.length + "");
                        this.Grosor.setText(l.grosor + "");
                        //this.inclinacion.setValue((int)l.angle);
                        contorno = l.color;
                        this.EscogerColorLinea.setBackground(contorno);
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
}
