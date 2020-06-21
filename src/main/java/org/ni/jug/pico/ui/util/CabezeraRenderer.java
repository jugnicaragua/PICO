package org.ni.jug.pico.ui.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

/**
 * <strong> org.jugni.apps.pico.vista.utils </strong>
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 *
 * Clase CabezeraRenderer : Rederiza los encabezado de las columnas
 */
public class CabezeraRenderer extends JLabel implements TableCellRenderer {

     private String toolTip;

     public CabezeraRenderer(int aliniacion) {
          this(aliniacion, null);
     }

     /**
      * Contructor para cabeza de columna con titulo centrado por defecto y sin tooltip
      *
      */
     public CabezeraRenderer() {
          this(null);
     }

     /**
      * Contructor para cabeza de columna con titulo centrado por defect
      *
      * @param toolTip
      */
     public CabezeraRenderer(String toolTip) {
          this(SwingConstants.CENTER, toolTip);
     }

     public CabezeraRenderer(int aliniacion, String toolTip) {
          setHorizontalAlignment(aliniacion);
          this.toolTip = toolTip;
          setOpaque(true);
          setBorder(LineBorder.createGrayLineBorder());
          setForeground(Color.white);
          setFont(new Font("Arial", Font.BOLD, 14));
     }

     @Override
     public Component getTableCellRendererComponent(JTable table,
             Object value,
             boolean isSelected,
             boolean hasFocus,
             int row,
             int column) {
          setBackground(new Color(0, 140, 255));
          setText(value.toString());
          setToolTipText(toolTip);
          return this;
     }

}
