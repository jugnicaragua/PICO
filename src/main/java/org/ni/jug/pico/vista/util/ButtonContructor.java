package org.ni.jug.pico.vista.util;

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
        btn.setIcon(new ImageIcon(ButtonContructor.class.getResource(pathResource)));
        return btn;
    }

}
