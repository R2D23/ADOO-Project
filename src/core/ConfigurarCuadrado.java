
package core;

import core.Barradecolores;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author MARCO
 */
public class ConfigurarCuadrado extends javax.swing.JFrame implements MouseListener {
    private Color contorno;
    private Color relleno;
    private double base;
    private double Altura;
    private ArrayList<Area> areas;
    private Canvas canvas;
    private Rectangle r;
    private int tipoMenu;
    
    public ConfigurarCuadrado(ArrayList<Area> a, Canvas c, int t) {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        areas = a;
        canvas = c;
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
        Base = new javax.swing.JTextField();
        EscogerColorRelleno = new javax.swing.JButton();
        EscogerColorLinea = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        altura = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ConfigurarCuadrado");

        jLabel2.setText("Base del Cuadrado:");

        jLabel3.setText("Relleno:");

        jLabel4.setText("ColorLinea:");

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

        Base.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaseActionPerformed(evt);
            }
        });

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

        jLabel5.setText("px");

        jLabel6.setText("Altura del Cuadrado:");

        jScrollPane1.setViewportView(altura);

        jLabel7.setText("px");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(Aceptar)
                        .addGap(48, 48, 48)
                        .addComponent(Cancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EscogerColorLinea)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(Base, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EscogerColorRelleno)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EscogerColorRelleno)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(EscogerColorLinea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Aceptar)
                    .addComponent(Cancelar))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_CancelarActionPerformed

    private void EscogerColorRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscogerColorRellenoActionPerformed
        relleno = JColorChooser.showDialog(rootPane, null, relleno);
        this.EscogerColorRelleno.setBackground(relleno);
    }//GEN-LAST:event_EscogerColorRellenoActionPerformed

    private void EscogerColorLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscogerColorLineaActionPerformed
       contorno = JColorChooser.showDialog(rootPane, null, relleno);
       this.EscogerColorLinea.setBackground(contorno);
    }//GEN-LAST:event_EscogerColorLineaActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        canvas.actElements();
        try
        {
            base=Double.parseDouble(this.Base.getText());
            Altura = Double.parseDouble(this.altura.getText());
            if((base!=0)&&(Altura != 0) && (relleno != null) && (contorno != null))
            {
                r.bgColor = relleno;
                r.lnColor = contorno;
                r.height = Altura;
                r.width = base;
                r.area = new Area(new java.awt.Rectangle(r.posX, r.posY, (int)base, (int)Altura));
                r.incline = 0;
                canvas.elements.add(r);
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
           



// TODO add your handling code here:
    }//GEN-LAST:event_AceptarActionPerformed

    private void BaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BaseActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JTextField Base;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton EscogerColorLinea;
    private javax.swing.JButton EscogerColorRelleno;
    private javax.swing.JTextPane altura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (tipoMenu)
        {
            case Escucha.FIGUREMENU :
                if(whichArea(e.getPoint()) == 3)
                {
                    e.getComponent().setVisible(false);
                    this.setLocation(e.getLocationOnScreen());
                    this.setVisible(true);
                    r = new Rectangle();
                    r.posX = e.getXOnScreen();
                    r.posY = e.getYOnScreen();
                    r.incline = 0;
                }
            break;
            case Escucha.SELECTIONMENU :
                if(whichArea(e.getPoint()) == 0)
                {
                    e.getComponent().setVisible(false);
                    try{
                        this.r = (Rectangle)((SelectionMenu)(e.getComponent())).elemento;
                        this.altura.setText("" + r.height);
                        this.Base.setText("" + r.width);
                        relleno = r.bgColor;
                        contorno = r.lnColor;
                        this.EscogerColorRelleno.setBackground(relleno);
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
