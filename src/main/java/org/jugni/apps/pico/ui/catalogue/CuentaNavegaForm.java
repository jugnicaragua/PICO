package org.jugni.apps.pico.ui.catalogue;

import org.jugni.apps.pico.data.model.Cuenta;
import org.jugni.apps.pico.ui.util.ButtonContructor;
import org.jugni.apps.pico.ui.util.CabezeraRenderer;
import org.jugni.apps.pico.ui.util.CerrarButton;
import org.jugni.apps.pico.ui.util.ColorCeldaRenderer;
import org.jugni.apps.pico.ui.util.MenuPrincipalAcciones;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <strong> org.jugni.apps.pico.vista.swing </strong>
 *
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @version : 0.1.0
 * @license : GPLv3
 *          <p>
 *          Clase CuentaTipoForm : Formulario para navegar en los registros de catalogo de cuentas
 */
public class CuentaNavegaForm extends JInternalFrame {

  private GridBagConstraints constraints;
  private GridBagLayout grid;
  private static CuentaNavegaForm INSTANCE;
  private static List<Cuenta> cuentas;
  private CuentaTableModel cuentaTableModel;
  private JCheckBox chkActivo, chkInactivo, chkBalance;
  private JTable table;
  private JPanel pnlTop;
  private final JTextField txtNombre = new JTextField();
  private final JTextField txtCodigo = new JTextField();
  private final JComboBox cmbCategoria = new JComboBox();

  private CuentaNavegaForm() {
    INSTANCE = this;
    initCuentaNavegaForm();
  }

  public static CuentaNavegaForm getInstancia() {
    if (INSTANCE == null) {
      new CuentaNavegaForm();
    }
    if ((cuentas == null || cuentas.size() < 1) && JOptionPane.showConfirmDialog(INSTANCE,
        "No se encontraron registro en la base de datos, ¿Desea agregar un nuevo registro?",
        INSTANCE.getTitle(), JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
      JOptionPane.showMessageDialog(INSTANCE, "El navegador se cerrara hasta que agregue registro");
      return null;
    }

    return INSTANCE;
  }

  private void initCuentaNavegaForm() {
    cuentaTableModel = new CuentaTableModel();
    table = new JTable(cuentaTableModel);
    chkActivo = new JCheckBox();
    chkInactivo = new JCheckBox();
    chkBalance = new JCheckBox();
    var pnlTop = new JPanel(new GridLayout(2, 2, 5, 5));
    var pnlButton = new JPanel();
    var btnCerrar = new CerrarButton();
    var btnNuevo = ButtonContructor.createButtonDialogo("Nuevo",
        "/org/tango-project/tango-icon-theme/32x32/actions/document-new.png");
    var btnBorrar = ButtonContructor.createButtonDialogo("Nuevo",
        "/org/tango-project/tango-icon-theme/32x32/actions/edit-delete.png");
    var btnAyuda = ButtonContructor.createButtonDialogo("Ayuda",
        "/org/tango-project/tango-icon-theme/32x32/apps/help-browser.png");
    var scrollPane = new JScrollPane(table);

    // Contenedores de los componentes de filtrado
    var hbxNombre = Box.createHorizontalBox();
    var hbxCodigo = Box.createHorizontalBox();
    var hbxCategoria = Box.createHorizontalBox();
    var hbxActivo = Box.createHorizontalBox();

    // ******** Casracterisiticas de los objetos que contiene el formulario**********
    // se crear una etiqueta para titulo del formulario
    pnlTop.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar",
        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.black));
    txtNombre.setMaximumSize(new Dimension(460, 36));
    txtCodigo.setMaximumSize(new Dimension(460, 36));
    cmbCategoria.setMaximumSize(new Dimension(460, 36));
    txtNombre.setToolTipText("Ingrese el nombre de la cuenta");
    txtCodigo.setToolTipText("Ingrese el codigo de la cuenta");
    cmbCategoria.setToolTipText("Seleccione la categoria");

    // se agrega caracteristicas para los checkbox
    chkActivo.setText("Activos");
    chkInactivo.setText("Inactivas");
    chkBalance.setText("Saldo = 0");
    chkActivo.setToolTipText("Presenta las cuentas activas");
    chkActivo.setToolTipText("Presenta las cuentas Inactivas");
    chkBalance.setToolTipText("Presenta las cuenta con saldo cero");
    chkActivo.addActionListener(a -> {
      enableActivoInactivo();
    });
    chkInactivo.addActionListener(a -> {
      enableActivoInactivo();
    });

    btnCerrar.addActionListener((ActionEvent arg0) -> {
      cerrar();
    });
    btnNuevo.addActionListener((ActionEvent arg0) -> {
      MenuPrincipalAcciones.mostrarNuevoVentanaCuenta();
    });
    // Caracteristica del panel superior

    // TODO: prueba de tabla
    /*
     * cuentas.add(new Cuenta("01-000-001", "Caja", 1, 1, "Deudora", new CuentaTipo("Activo"), new
     * Estado("Activa"))); cuentas.add(new Cuenta("01-000-002", "Banco", 1, 1, "Deudora", new
     * CuentaTipo("Activo"), new Estado("Activa"))); cuentas.add(new Cuenta("01-000-003",
     * "Edificio", 1, 1, "Deudora", new CuentaTipo("Activo"), new Estado("Activa")));
     * cuentas.add(new Cuenta("01-000-004", "Terreno", 1, 1, "Deudora", new CuentaTipo("Activo"),
     * new Estado("Activa"))); cuentas.add(new Cuenta("01-000-005", "documento", 1, 1, "Deudora",
     * new CuentaTipo("Activo"), new Estado("Activa")));
     */
    cuentaTableModel.fireTableDataChanged();

    // Especifica el ancho de las columnas
    renderizarColumna(0, 140, SwingConstants.LEFT);
    renderizarColumna(1, 280, SwingConstants.LEFT);
    renderizarColumna(2, 120, SwingConstants.LEFT);
    renderizarColumna(3, 120, SwingConstants.LEFT);
    renderizarColumna(4, 170, SwingConstants.RIGHT);
    renderizarColumna(5, 170, SwingConstants.RIGHT);
    renderizarColumna(6, 170, SwingConstants.RIGHT);

    // table.setPreferredScrollableViewportSize(new Dimension(1180, 220));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setAutoscrolls(true);
    // Enumera todas las columnas que se encuentra en la tabla
    Enumeration<TableColumn> en = table.getColumnModel().getColumns();
    while (en.hasMoreElements()) {// ciclo mientras exista mas elemento de la enumeracion
      TableColumn tc = en.nextElement(); // instancia el elemento de la enumeracion en tc
      tc.setHeaderRenderer(new CabezeraRenderer()); // Aplica renderizado a la TableColumn de la
                                                    // enumeracion
    }
    hbxNombre.add(new JLabel("Nombre : "));
    hbxNombre.add(txtNombre);
    hbxCodigo.add(new JLabel("Codigo : "));
    hbxCodigo.add(txtCodigo);
    hbxCategoria.add(new JLabel("Categoria : "));
    hbxCategoria.add(cmbCategoria);

    // Se agregan los componentes del contenedor hbxactivo
    hbxActivo.add(chkActivo);
    hbxActivo.add(Box.createHorizontalStrut(4));
    hbxActivo.add(chkInactivo);
    hbxActivo.add(Box.createHorizontalStrut(10));
    hbxActivo.add(chkBalance);

    pnlTop.add(hbxNombre);
    pnlTop.add(hbxCategoria);
    pnlTop.add(hbxCodigo);
    pnlTop.add(hbxActivo);

    pnlButton.add(btnBorrar);
    pnlButton.add(Box.createRigidArea(new Dimension(100, 10)));
    pnlButton.add(btnAyuda);
    pnlButton.add(Box.createRigidArea(new Dimension(60, 10)));
    pnlButton.add(btnNuevo);
    pnlButton.add(btnCerrar);
    getContentPane().add(pnlTop, BorderLayout.NORTH);
    getContentPane().add(scrollPane, BorderLayout.CENTER);

    // getContentPane().add(pnlCentral, BorderLayout.CENTER);
    getContentPane().add(pnlButton, BorderLayout.SOUTH);
    this.setToolTipText("Navegador de registro  Catalogo de cuentas");
    this.setOpaque(true);
    this.setBorder(new LineBorder(new Color(0, 0, 0)));
    this.setIconifiable(false);
    this.setTitle("Navegar Catalogo de cuentas");
    this.setClosable(true);
    this.setName("Nave_De_Cuenta");
    // getContentPane().setPreferredSize(new Dimension(800, 280));
    this.setPreferredSize(new Dimension(1150, 450));
    pack();
  }

  protected void cerrar() {
    this.dispose();
    INSTANCE = null;
  }

  // Renderiza las columnas dando tamaño y orientacion a las columna, aplica color a las filas
  private void renderizarColumna(int columna, int ancho, int Alineacion) {
    table.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
    table.getColumnModel().getColumn(columna).setResizable(false);
    table.getColumnModel().getColumn(columna).setCellRenderer(new ColorCeldaRenderer(Alineacion));
  }

  private void enableActivoInactivo() {
    chkActivo.setEnabled(!chkInactivo.isSelected());
    chkInactivo.setEnabled(!chkActivo.isSelected());
  }

  /**
   * Clase tablemodel de la tabla cuenta
   */
  class CuentaTableModel extends AbstractTableModel {

    String[] cabezera =
        {"Codigo", "Nombre de la cuenta", "Tipo", "Naturaleza", "Debe", "Haber", "Saldo"};
    List<Cuenta> registrosCuentas;

    public CuentaTableModel() {
      cuentas = new ArrayList<>();
    }

    protected void update() {
      registrosCuentas = new ArrayList<>(cuentas);
      if (txtNombre.getText().trim().isEmpty()) {
        registrosCuentas.removeIf(c -> !c.getDescripcion().contains(txtNombre.getText().trim()));
      }
      if (txtCodigo.getText().trim().isEmpty()) {
        registrosCuentas.removeIf(c -> !c.getId().equals(txtCodigo.getText().trim()));
      }
      if (cmbCategoria.getSelectedIndex() > -1) {
        registrosCuentas.removeIf(c -> !c.getCuentaTipo().getDescripcion()
            .contains(cmbCategoria.getSelectedItem().toString()));
      }
      if (chkActivo.isSelected() && !chkInactivo.isSelected()) {
        // TODO registrosCuentas.removeIf( e -> e.getEstado());
      }
    }

    @Override
    public String getColumnName(int column) {
      return cabezera[column];
    }

    @Override
    public int getRowCount() {
      return registrosCuentas.size();
    }

    @Override
    public int getColumnCount() {
      return cabezera.length;
    }

    @Override
    public Object getValueAt(int fila, int col) {
      Object obj;
      switch (col) {
        case 0: // Columna codigo
          obj = registrosCuentas.get(fila).getId();
          break;
        case 1: // Columna Nombre de la cuenta
          obj = registrosCuentas.get(fila).getDescripcion();
          break;
        case 2: // Columna tipo de cuenta
          obj = "discrip";
          break;
        case 3: // Columna Naturaleza
          obj = registrosCuentas.get(fila).getNaturaleza();
          break;
        case 4: // Columna debe
          obj = "1,500.00";
          break;
        case 5: // Columna haber
          obj = "1,500.00";
          break;
        case 6: // Columna Saldo
          obj = "0.00";
          break;
        default:
          obj = null;
      }
      return obj;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false; // Los registro de solo lectura
    }

  }

}
