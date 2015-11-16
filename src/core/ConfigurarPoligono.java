
package core;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author MARCO
 */
public class ConfigurarPoligono extends javax.swing.JFrame{
    private double lado;
    private int noLados;
    private Color relleno;
    private Color contorno;
    private Canvas canvas;
    private RegularPolygon p;

    public ConfigurarPoligono(Canvas c, RegularPolygon rp, Point po) {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(po);
        canvas = c;
        if(rp != null)
        {
            p = rp;
            cargarValores();
        }
        else
        {
            p = new RegularPolygon();
            p.posX = po.x;
            p.posY = po.y;
        }


    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        EscogerColorRelleno = new javax.swing.JButton();
        EscogerColorLinea = new javax.swing.JButton();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Lado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lados = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ConfigurarPentagono");

        EscogerColorRelleno.setText("EscogerColor");
        EscogerColorRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscogerColorRellenoActionPerformed(evt);
            }
        });

        EscogerColorLinea.setText("EscogerColor");
        EscogerColorLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscogerColorLineaActionPerformed(evt);
            }
        });

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

        Lado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LadoActionPerformed(evt);
            }
        });

        jLabel2.setText("Lado:");

        jLabel3.setText("Relleno:");

        jLabel4.setText("ColorLinea");

        jLabel5.setText("Número de lados");

        lados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ladosActionPerformed(evt);
            }
        });

        jLabel6.setText("px");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(Aceptar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cancelar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Lado, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel6))
                                .addComponent(EscogerColorRelleno)
                                .addComponent(lados, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EscogerColorLinea)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(30, 30, 30))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EscogerColorRelleno))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(EscogerColorLinea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Aceptar)
                    .addComponent(Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LadoActionPerformed
    }//GEN-LAST:event_LadoActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        canvas.actElements();
        try{
            lado=Double.parseDouble(this.Lado.getText());
            noLados = Integer.parseInt(this.lados.getText());
            if((lado!=0) && (noLados!=0) && (relleno != null) && (contorno != null))
            {
                p.bgColor = relleno;
                p.lnColor = contorno;
                p.setLongSide((int) lado);
                p.setNumSides(noLados);
                p.pointsX = p.getCoordsX();
                p.pointsY = p.getCoordsY();
                p.area = new Area(new java.awt.Polygon(p.pointsX, p.pointsY, p.pointsX.length));
                canvas.elements.add(p);
                p.state = Element.AVAILABLE;
                canvas.repaint();
                setVisible(false);
            } 
            else
                JOptionPane.showMessageDialog(rootPane, "Has ingresado datos erróneos, favor de revisarlos e intentarlo\n" +
"nuevamente.");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Has ingresado datos erróneos, favor de revisarlos e intentarlo\n" +
"nuevamente.");
        }
        
        
        
    }//GEN-LAST:event_AceptarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        p.state = Element.AVAILABLE;
        dispose();
    }//GEN-LAST:event_CancelarActionPerformed

    private void ladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ladosActionPerformed
    }//GEN-LAST:event_ladosActionPerformed

    private void EscogerColorLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscogerColorLineaActionPerformed
       contorno = JColorChooser.showDialog(rootPane, null, contorno);
       this.EscogerColorLinea.setBackground(contorno);
    }//GEN-LAST:event_EscogerColorLineaActionPerformed

    private void EscogerColorRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscogerColorRellenoActionPerformed
       relleno = JColorChooser.showDialog(rootPane, null, relleno);
       this.EscogerColorRelleno.setBackground(relleno);
    }//GEN-LAST:event_EscogerColorRellenoActionPerformed

    public final void cargarValores()
    {
        this.lados.setText("" + p.getNumSides());
        this.Lado.setText("" + p.getLongSide());
        relleno = p.bgColor;
        contorno = p.lnColor;
        this.EscogerColorRelleno.setBackground(relleno);
        this.EscogerColorLinea.setBackground(contorno);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton EscogerColorLinea;
    private javax.swing.JButton EscogerColorRelleno;
    private javax.swing.JTextField Lado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField lados;
    // End of variables declaration//GEN-END:variables

}
