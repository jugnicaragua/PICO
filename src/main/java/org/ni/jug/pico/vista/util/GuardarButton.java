
package org.ni.jug.pico.vista.util;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *<strong> org.jugni.apps.pico.vista.utils </strong>
 *  @author  :Gustavo Castro <gacsnic75@gmail.com>
 *  @version : 1.0.0
 *  @license : GPLv3
 *
 *   Clase CerrarButton : clase util hereda de jbutton
 */
public class GuardarButton extends JButton{
     public GuardarButton(){
          setText("Guardar");
          setIcon(new ImageIcon(getClass().getResource("/org/tango-project/tango-icon-theme/32x32/actions/document-save.png")));
          setPreferredSize(new Dimension(120, 50));
          setFocusable(false);
          setEnabled(false);
          setToolTipText("Guarda los cambios de los campos del Formulario");
     }
}
