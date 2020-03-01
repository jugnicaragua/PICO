package org.jugni.apps.pico.vista.utils;

import javax.swing.*;
import java.awt.*;

public class ButtonContructor {
    /**
     * Crea Botonos para los formulario
     * @return
     * @param title
     */
    public static JButton createButtonDialogo(String title){
        var btn=new JButton(title);
        btn.setPreferredSize(new Dimension(120, 50));
        btn.setFocusable(false);
        return btn;
    }
    /**
     * Crea Botonos para los formulario
     * @return
     * @param title
     * @param pathResource
     */
    public static JButton createButtonDialogo(String title,String pathResource){
        var btn=createButtonDialogo(title);
        btn.setIcon(new ImageIcon(ButtonContructor.class.getClass().getResource("/org/tango-project/tango-icon-theme/32x32/actions/process-stop.png")));
        return btn;
    }

}
