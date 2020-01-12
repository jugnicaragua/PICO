package org.jugni.apps.pico.vista.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import org.jugni.apps.pico.vista.utils.CerrarButton;
import org.jugni.apps.pico.vista.utils.GuardarButton;

/**
 * <strong> org.jugni.apps.pico.vista.swing </strong>
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 *
 * Clase CuentaTipoForm : Formulario para los tipos de cuenta
 */
public class CuentaTipoForm extends JInternalFrame {

     private static CuentaTipoForm INSTANCE;
     private JTextField txtCuentaTipo;

     // El constructor privado no permite que se genere un constructor por defecto.
     private CuentaTipoForm() {
          INSTANCE=this;
          initCuentaTipoForm();
     }

     /**
      * SingleTon de CuentaTipoForm.
      *
      * @return
      */
     public static CuentaTipoForm getInstancia() {
          return (INSTANCE==null) ? new CuentaTipoForm() : INSTANCE;
     }

     protected void cerrar() {
          this.dispose();
          INSTANCE=null;
     }

     private void initCuentaTipoForm() {
          //Caracteristicas del formulario
          setToolTipText("Formulario de tipo de cuenta");
          setOpaque(true);
          setBorder(new LineBorder(new Color(0, 0, 0)));
          setIconifiable(false);
          setTitle("Tipo de cuenta");
          setClosable(true);
          setName("Tipo_De_Cuenta");

          //se crear una etiqueta para titulo del formulario
          JLabel lblTitle = new JLabel("Tipos de cuenta");
          lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
          lblTitle.setPreferredSize(new Dimension(40, 40));

          //Instanciar objetos contenidos en el formulario
          JLabel lblCuentaTipo = new JLabel("Tipo de cuenta:");
          JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
          txtCuentaTipo = new JTextField(30);
          CerrarButton btnCerrar = new CerrarButton();
          GuardarButton btnGuardar = new GuardarButton();
          JPanel panel_button = new JPanel();

          //se define caracteristicas de objesto del formulario
          lblCuentaTipo.setToolTipText("Descripción de tipo de cuenta");
          txtCuentaTipo.setToolTipText("Descripción de tipo de cuenta");
          txtCuentaTipo.addKeyListener(new java.awt.event.KeyAdapter() { 
               //Habilita el boton guardar cuando digitamos algun caracter en el textfield
               @Override
               public void keyPressed(java.awt.event.KeyEvent evt) {
                    if(!btnGuardar.isEnabled())  btnGuardar.setEnabled(true);
               }
          });
          panel.add(lblCuentaTipo);
          panel.add(txtCuentaTipo);
          panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.black));

          btnCerrar.addActionListener((ActionEvent arg0) -> {
               cerrar();
          });
          btnGuardar.addActionListener((ActionEvent arg0) -> {
               guardar();
               cerrar();
          });

          //Agrega los componentes al formulario
          panel_button.add(btnCerrar);
          panel_button.add(btnGuardar);
          getContentPane().add(lblTitle, BorderLayout.NORTH);
          getContentPane().add(panel, BorderLayout.CENTER);
          getContentPane().add(panel_button, BorderLayout.SOUTH);
          pack();
     }
     
     private void guardar(){
     }
     
}
