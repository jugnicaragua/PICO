package org.jugni.apps.pico.vista.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import org.jfree.layout.CenterLayout;
import org.jugni.apps.pico.modelos.Cuenta;
import org.jugni.apps.pico.modelos.CuentaTipo;
import org.jugni.apps.pico.modelos.Estado;
import org.jugni.apps.pico.vista.utils.ButtonContructor;
import org.jugni.apps.pico.vista.utils.CabezeraRenderer;
import org.jugni.apps.pico.vista.utils.CerrarButton;
import org.jugni.apps.pico.vista.utils.ColorCeldaRenderer;

/**
 * <strong> org.jugni.apps.pico.vista.swing </strong>
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 *
 * Clase CuentaTipoForm : Formulario para navegar en los registros de catalogo de cuentas
 */
public class CuentaNavegaForm extends JInternalFrame {

     private static CuentaNavegaForm INSTANCE;
     private static List<Cuenta> cuentas;
     private CuentaTableModel cuentaTableModel;
     private JTable table;

     private CuentaNavegaForm() {
          INSTANCE = this;
          initCuentaNavegaForm();
     }

     public static CuentaNavegaForm getInstancia() {
          if (INSTANCE == null) {
               new CuentaNavegaForm();
          }
          if((cuentas==null || cuentas.size()<1) && JOptionPane.showConfirmDialog(INSTANCE,
                  "No se encontraron registro en la base de datos, ¿Desea agregar un nuevo registro?",
                  INSTANCE.getTitle(),
                  JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
               JOptionPane.showMessageDialog(INSTANCE,"El navegador se cerrara hasta que agregue registro");
               return null;
          }

          return INSTANCE;
     }

     private void initCuentaNavegaForm() {
          cuentaTableModel = new CuentaTableModel();
          table = new JTable(cuentaTableModel);
          var pnlTop = new JPanel(new CenterLayout());
          var pnlCentral = new JPanel(new CenterLayout());
          var pnlButton = new JPanel();
          var btnCerrar = new CerrarButton();
          var btnNuevo = ButtonContructor.createButtonDialogo("Nuevo", "/org/tango-project/tango-icon-theme/32x32/actions/document-new.png");
          var btnBorrar = ButtonContructor.createButtonDialogo("Nuevo", "/org/tango-project/tango-icon-theme/32x32/actions/edit-delete.png");
          var btnAyuda = ButtonContructor.createButtonDialogo("Ayuda", "/org/tango-project/tango-icon-theme/32x32/apps/help-browser.png");
          var scrollPane = new JScrollPane(table);

          btnCerrar.addActionListener((ActionEvent arg0) -> {
               cerrar();
          });
          btnNuevo.addActionListener((ActionEvent arg0) -> {
               MenuPrincipalAcciones.mostrarNuevoVentanaCuenta();
          });
          //TODO: prueba de tabla
/*          cuentas.add(new Cuenta("01-000-001", "Caja", 1, 1, "Deudora", new CuentaTipo("Activo"), new Estado("Activa")));
          cuentas.add(new Cuenta("01-000-002", "Banco", 1, 1, "Deudora", new CuentaTipo("Activo"), new Estado("Activa")));
          cuentas.add(new Cuenta("01-000-003", "Edificio", 1, 1, "Deudora", new CuentaTipo("Activo"), new Estado("Activa")));
          cuentas.add(new Cuenta("01-000-004", "Terreno", 1, 1, "Deudora", new CuentaTipo("Activo"), new Estado("Activa")));
          cuentas.add(new Cuenta("01-000-005", "documento", 1, 1, "Deudora", new CuentaTipo("Activo"), new Estado("Activa")));*/
          cuentaTableModel.fireTableDataChanged();

          //Especifica el ancho de las columnas
          renderizarColumna(0, 140, SwingConstants.LEFT);
          renderizarColumna(1, 280, SwingConstants.LEFT);
          renderizarColumna(2, 120, SwingConstants.LEFT);
          renderizarColumna(3, 120, SwingConstants.LEFT);
          renderizarColumna(4, 170, SwingConstants.RIGHT);
          renderizarColumna(5, 170, SwingConstants.RIGHT);
          renderizarColumna(6, 170, SwingConstants.RIGHT);

          table.setPreferredScrollableViewportSize(new Dimension(1180, 120));
          table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
          table.setAutoscrolls(true);
          //Enumera todas las columnas que se encuentra en la tabla
          Enumeration<TableColumn> en = table.getColumnModel().getColumns();
          while (en.hasMoreElements()) {//ciclo mientras exista mas elemento de la enumeracion
               TableColumn tc = en.nextElement(); //instancia el elemento de la enumeracion en tc
               tc.setHeaderRenderer(new CabezeraRenderer()); //Aplica renderizado a la TableColumn de la enumeracion
          }
          pnlCentral.add(scrollPane);

          pnlButton.add(btnBorrar);
          pnlButton.add(Box.createRigidArea(new Dimension(100, 10)));
          pnlButton.add(btnAyuda);
          pnlButton.add(Box.createRigidArea(new Dimension(60, 10)));
          pnlButton.add(btnNuevo);
          pnlButton.add(btnCerrar);
          getContentPane().add(pnlTop, BorderLayout.NORTH);
          getContentPane().add(pnlCentral, BorderLayout.CENTER);
          getContentPane().add(pnlButton, BorderLayout.SOUTH);
          this.setToolTipText("Navegador de registro  Catalogo de cuentas");
          this.setOpaque(true);
          this.setBorder(new LineBorder(new Color(0, 0, 0)));
          this.setIconifiable(false);
          this.setTitle("Navegar Catalogo de cuentas");
          this.setClosable(true);
          this.setName("Nave_De_Cuenta");
//          getContentPane().setPreferredSize(new Dimension(800, 280));
          this.setPreferredSize(new Dimension(1180, 280));
          pack();
     }

     protected void cerrar() {
          this.dispose();
          INSTANCE = null;
     }

     //Renderiza las columnas dando tamaño y orientacion a las columna, aplica color a las filas  
     private void renderizarColumna(int columna, int ancho, int Alineacion) {
          table.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
          table.getColumnModel().getColumn(columna).setResizable(false);
          table.getColumnModel().getColumn(columna).setCellRenderer(new ColorCeldaRenderer(Alineacion));
     }

     /**
      * Clase tablemodel de la tabla cuenta
      */
     public class CuentaTableModel extends AbstractTableModel {

          String[] cabezera = {"Codigo", "Nombre de la cuenta", "Tipo", "Naturaleza", "Debe", "Haber", "Saldo"};

          public CuentaTableModel() {
               cuentas = new ArrayList<>();
          }

          @Override
          public String getColumnName(int column) {
               return cabezera[column];
          }

          @Override
          public int getRowCount() {
               return cuentas.size();
          }

          @Override
          public int getColumnCount() {
               return cabezera.length;
          }

          @Override
          public Object getValueAt(int fila, int col) {
               Object obj;
               switch (col) {
                    case 0: //Columna codigo
                         obj = cuentas.get(fila).getId();
                         break;
                    case 1: //Columna Nombre de la cuenta
                         obj = cuentas.get(fila).getDescripcion();
                         break;
                    case 2: //Columna tipo de cuenta
                         obj = "discrip";
                         break;
                    case 3: //Columna Naturaleza
                         obj = cuentas.get(fila).getNaturaleza();
                         break;
                    case 4: //Columna debe
                         obj = "1,500.00";
                         break;
                    case 5: //Columna haber
                         obj = "1,500.00";
                         break;
                    case 6: //Columna Saldo
                         obj = "0.00";
                         break;
                    default:
                         obj = null;
               }
               return obj;
          }

          @Override
          public boolean isCellEditable(int rowIndex, int columnIndex) {
               return false; //Los registro de solo lectura
          }

     }

}
