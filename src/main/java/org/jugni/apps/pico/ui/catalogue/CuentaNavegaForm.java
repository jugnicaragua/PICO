package org.jugni.apps.pico.ui.catalogue;

import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.data.model.Cuenta;
import org.jugni.apps.pico.security.annotation.Access;
import org.jugni.apps.pico.security.exception.InvalidAccessException;
import org.jugni.apps.pico.security.model.Rol;
import org.jugni.apps.pico.ui.util.BaseInternalView;
import org.jugni.apps.pico.ui.util.ButtonContructor;
import org.jugni.apps.pico.ui.util.CabezeraRenderer;
import org.jugni.apps.pico.ui.util.CerrarButton;
import org.jugni.apps.pico.ui.util.ColorCeldaRenderer;

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
@SuppressWarnings("serial")
@Access(name = "catalogo-contable", rol = Rol.ADMIN)
public class CuentaNavegaForm extends BaseInternalView {

  private static CuentaNavegaForm instance;

  public static CuentaNavegaForm getInstance(PicoApplication application)
      throws InvalidAccessException {
    if (instance == null) {
      synchronized (CuentaNavegaForm.class) {
        instance = new CuentaNavegaForm(application);
      }
    }

    return instance;
  }

  private GridBagConstraints constraints;
  private GridBagLayout grid;
  private static CuentaNavegaForm INSTANCE;
  private static List<Cuenta> cuentas;
  private CuentaTableModel cuentaTableModel;
  private JCheckBox chkActivo, chkInactivo, chkBalance;
  private JTable table;
  private JPanel pnlTop;
  private JTextField txtNombre;
  private JTextField txtCodigo;
  private JComboBox cmbCategoria;

  private CuentaNavegaForm(PicoApplication application) throws InvalidAccessException {
    super(application);
  }

  private void initCuentaNavegaForm() {
    cuentaTableModel = new CuentaTableModel();
    table = new JTable(cuentaTableModel);
    chkActivo = new JCheckBox();
    chkInactivo = new JCheckBox();
    chkBalance = new JCheckBox();
    this.txtNombre = new JTextField();
    this.txtCodigo = new JTextField();
    this.cmbCategoria = new JComboBox();

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
      try {
        getApplication().getRootView().addView(CuentaForm.getInstance(getApplication()));
      } catch (Exception ignore) {

      }
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
    List<Cuenta> cuentas;

    public CuentaTableModel() {
      cuentas = new ArrayList<>();
    }

    protected void update() {
      if (txtNombre.getText().trim().isEmpty()) {
        cuentas.removeIf(c -> !c.getDescripcion().contains(txtNombre.getText().trim()));
      }
      if (txtCodigo.getText().trim().isEmpty()) {
        cuentas.removeIf(c -> !c.getId().equals(txtCodigo.getText().trim()));
      }
      if (cmbCategoria.getSelectedIndex() > -1) {
        cuentas.removeIf(c -> !c.getCuentaTipo().getDescripcion()
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
        case 0: // Columna codigo
          obj = cuentas.get(fila).getId();
          break;
        case 1: // Columna Nombre de la cuenta
          obj = cuentas.get(fila).getDescripcion();
          break;
        case 2: // Columna tipo de cuenta
          obj = "discrip";
          break;
        case 3: // Columna Naturaleza
          obj = cuentas.get(fila).getNaturaleza();
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

  @Override
  protected void initView() {
    initCuentaNavegaForm();
  }

  @Override
  protected void close() {
    instance = null;
  }

}
