
package org.jugni.apps.pico.ui.util;

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
public class CerrarButton extends JButton{
     public CerrarButton(){
          setText("Cerrar");
          setIcon(new ImageIcon(getClass().getResource("/org/tango-project/tango-icon-theme/32x32/actions/process-stop.png")));
          setPreferredSize(new Dimension(120, 50));
          setFocusable(false);
          setToolTipText("Cierra el Formulario");
     }
}
