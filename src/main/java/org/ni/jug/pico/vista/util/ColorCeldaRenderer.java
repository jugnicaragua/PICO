package org.ni.jug.pico.vista.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * <strong> org.jugni.apps.pico.vista.utils </strong>
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 *
 *
 * Clase ColorCeldaRenderer :Aplica color a las celdas impar y a fila selecionadas
 */
public class ColorCeldaRenderer extends DefaultTableCellRenderer {

     private final Color backgroundPar = new Color(255, 245, 238);
     private final Color backgroundImpar = new Color(191, 219, 255);
     private final Color backgroundSelect = new Color(198, 239, 206);

     private final Color foregroundTable = Color.BLACK;
     private final Color foregroundSelect = Color.BLUE;

     public ColorCeldaRenderer(int aliniacion) {
          setHorizontalAlignment(aliniacion);
     }

     @Override
     public Component getTableCellRendererComponent(JTable table,
             Object value,
             boolean isSelected,
             boolean hasFocus,
             int row,
             int column) {
          Color background, fground = foregroundTable;
          setBackground(null);
          super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

          background = (row % 2 == 0) ? backgroundPar : backgroundImpar;

          if (isSelected) {
               background = backgroundSelect;
               fground = foregroundSelect;
          }
          this.setBackground(background);
          this.setForeground(fground);
          return this;
     }
}
