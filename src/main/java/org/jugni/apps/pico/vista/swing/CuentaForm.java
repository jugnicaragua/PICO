package org.jugni.apps.pico.vista.swing;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
* <strong> org.jugni.apps.pico.vista.swing </strong>
*
* @author :Gustavo Castro <gacsnic75@gmail.com>
* @version : 0.1.0
* @license : GPLv3
*

* Clase CuentaForm : Formulario para los catalogo de cuentas
*/
public class CuentaForm extends JInternalFrame {
    private static CuentaForm INSTANCE;

    private CuentaForm(){
        INSTANCE = this;
        initCuentaForm();
    }

    public static CuentaForm getInstancia(){
        return (INSTANCE == null) ? new CuentaForm() : INSTANCE;
    }

    private void initCuentaForm(){
        //Caracteristicas del formulario
        setToolTipText("Formulario Catalogo de cuentas");
        setOpaque(true);
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setIconifiable(false);
        setTitle("Catalogo de cuentas");
        setClosable(true);
        setName("Tipo_De_Cuenta");

        //se crear una etiqueta para titulo del formulario
        JLabel lblTitle = new JLabel("Catalogo de cuenta");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setPreferredSize(new Dimension(40, 40));

    }
}
