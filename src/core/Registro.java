/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauricio
 */
public class Registro extends javax.swing.JFrame {
    	private final UsuarioBD data;
        
    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        conexion mysql = new conexion();
	Connection con = mysql.conectar("jdbc:mysql://localhost:3306/paint", "root", "root");
	data = new UsuarioBD();
	data.setCon(con);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        labelpass2 = new javax.swing.JLabel();
        tipopass = new javax.swing.JLabel();
        apellidos = new javax.swing.JTextField();
        usuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        camposreq = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        passConf = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Usuario");

        jPanel1.setBackground(new java.awt.Color(233, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("iDraw");

        jPanel2.setBackground(new java.awt.Color(255, 255, 239));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 212, 167), 3, true));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre");

        nombre.setText(null);

        jLabel2.setText("Apellidos");

        apellidos.setText(null);

        usuario.setText(null);

        jLabel3.setText("Usuario");
        
        tipopass.setText("<html>La contraseña debe tener una longitud de 6 caracteres,<br> debe comenzar con mayúsculas, seguido de minúsculas y <br> terminar mínimo un número.</html>");
        
        tipopass.setFont(new java.awt.Font("Tahoma", 0, 10));

        jLabel4.setText("Contraseña");
        
        camposreq.setText("Todos los campos son requeridos");
        camposreq.setForeground(Color.red);
        
        labelpass2.setText("Confirmar contraseña");

        password.setText(null);
        
        passConf.setText(null);

        jLabel7.setText("Correo Electronico");

        correo.setText(null);

        btnAceptar.setBackground(new java.awt.Color(102, 255, 255));
        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptar.setText("Terminar registro");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(camposreq)
                            .addComponent(tipopass)
                            .addComponent(labelpass2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passConf, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelpass2))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(camposreq)
                    .addComponent(tipopass)
                .addGap(27, 27, 27)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(68, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel5)
                .addContainerGap(447, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(90, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        if(!(nombre.getText().equals(""))&& !(apellidos.getText().equals(""))&&!(usuario.getText().equals(""))
                &&!(password.getText().equals(""))&&!(passConf.getText().equals(""))&&!(correo.getText().equals("")))
        {
            if(validarCadenaAlfabetica(nombre.getText())==true && validarCadenaAlfabetica(apellidos.getText())==true)
            {
                if(validarCorreo(correo.getText())==true)
                {
                    if(validarPasswordCorrecta(password.getText(), passConf.getText())== 0 && validarPassword(password.getText())== true)
                    {
                        if(!(data.buscar(usuario.getText())) && !(data.buscarcorreo(correo.getText())))
                        {
                                if(data.insertar(usuario.getText(),password.getText(), nombre.getText(), apellidos.getText(), 
                                    correo.getText()))
                                {
                                    JOptionPane.showMessageDialog(null, "Registro Exitoso");
                                    this.dispose();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Registro Fallido");
                                    this.dispose();
                                }
                        }
                        else
                        {
                            if((data.buscarcorreo(correo.getText())))
                            {
                                JOptionPane.showMessageDialog(null, "El correo ya está registrado.");
                            }
                            else
                                JOptionPane.showMessageDialog(null, "El Nombre de Usuario ya Existe.");
                        }
                    }
                    else
                    {
                        if(validarPassword(password.getText())== false)
                            JOptionPane.showMessageDialog(null, "La contraseña no tiene el formato especificado");
                        else
                            JOptionPane.showMessageDialog(null, "Los campos de contraseña no coinciden. Por favor ingresa de nuevo la contraseña");
                        password.setText(null);
                        passConf.setText(null);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "El correo no tiene un formato válido");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "El nombre o apellido tiene un formato inválido.");
                
            }
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "No has llenado todos los campos obligatorios.");            
        }
        
       
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public boolean validarCadenaAlfabetica(String nombreOApellido)
    {
        boolean validacion;
        validacion = nombreOApellido.matches("([a-z]|[A-Z]|\\\\s)+");
        return validacion;
    }
    public boolean validarCorreo(String email)
    {
        boolean validacion;
        validacion = email.matches("[-\\w\\.]+@\\w+\\.\\w+");
        return validacion;
    }
    public int validarPasswordCorrecta(String pass1, String pass2)
    {
        int validacion;
        validacion = pass1.compareTo(pass2);
        return validacion;
    }
    public boolean validarPassword(String pass)
    {
        boolean validacion;
        validacion = pass.matches("[A-Z]+[a-z]+\\d+");
        if(validacion == true && pass.length()>= 6)
            return true;
        else
            return false;
                   
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel camposreq;
    private javax.swing.JLabel tipopass;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel labelpass2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField passConf;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField apellidos;
    private javax.swing.JTextField usuario;
    private javax.swing.JTextField correo;
    // End of variables declaration//GEN-END:variables
}
