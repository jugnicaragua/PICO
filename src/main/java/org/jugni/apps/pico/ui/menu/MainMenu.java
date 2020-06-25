package org.jugni.apps.pico.ui.menu;

import org.jugni.apps.pico.PicoApplication;
import org.jugni.apps.pico.ui.LoginView;
import org.jugni.apps.pico.ui.catalogue.CuentaNavegaForm;
import org.jugni.apps.pico.ui.catalogue.CuentaTipoForm;
import org.jugni.apps.pico.ui.catalogue.EmpresaView;
import org.jugni.apps.pico.ui.dialog.Acercade;
import org.jugni.apps.pico.ui.dialog.Respaldo;
import org.jugni.apps.pico.ui.reportes.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * @author Gustavo Castro - GACS Menu principal, este menu sera instanciado desde la
 *         VentanaPrincipal
 */
@SuppressWarnings("serial")
public class MainMenu extends JMenuBar implements ActionListener {

  private final MenuAction action;
  private final PicoApplication application;

  public MainMenu(PicoApplication application, MenuAction action) {
    this.application = application;
    this.action = action;
    initMenu();
  }

  /**
   * Inicializa las propiedades del MenuPrincipal
   *
   */
  private void initMenu() {
    setName("MenuPrincipal");

    { // Menu Archivo
      JMenu mArchivo = new JMenu("Archivo");
      mArchivo.setMnemonic('A');
      add(mArchivo);

      // Sub-menu
      addSubMenu(mArchivo, "Iniciar Session", "Inicia Session de usuario", 'I', v -> {
        try {
          showInternalView(LoginView.getInstance(application));
        } catch (Exception e) {
          e.printStackTrace();
        }
      });

      addSubMenu(mArchivo, "Datos Empresa", "Configurar los datos de la Empresa", ' ', v -> {
        try {
          showInternalView(EmpresaView.getInstance(application));
        } catch (Exception e) {
          e.printStackTrace();
        }
      });

      addSubMenu(mArchivo, "Ciclo Fiscal", "Establece el Periodo Fiscal", 'C', this);

      addSubMenu(mArchivo, "Parametros Generales", "Administra las configuraciones del Sistema",
          'P', this);

      mArchivo.addSeparator();

      addSubMenu(mArchivo, "Salir", "Sale del Sistema", 'S', v -> System.exit(0));
    }

    { // Menu Catalogo agrupa todos los sub menu de catalos
      JMenu mCatalogo = new JMenu("Catalogo");
      mCatalogo.setMnemonic('C');
      add(mCatalogo);

      // Sub-menu
      addSubMenu(mCatalogo, "Catalogo tipo de Cuentas", "Administra catalogo de tipo de cuenta",
          'T', v -> {
            try {
              showInternalView(CuentaTipoForm.getInstance(application));
            } catch (Exception e) {
              e.printStackTrace();
            }
          });

      addSubMenu(mCatalogo, "Catalogo Contable", "Administra el catalogo de cuentas", 'c', v -> {
        try {
          showInternalView(CuentaNavegaForm.getInstance(application));
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }

    { // Menu Captacion Agrupa los sub menu con movimiento periodico (comprobantes, cxp,cxc)
      JMenu mCaptacion = new JMenu("Captacion");
      mCaptacion.setMnemonic('p');
      add(mCaptacion);

      // Sub-menu
      addSubMenu(mCaptacion, "Comprobante de diario", "Administrador de comprobante diario", 'd',
          this);
      addSubMenu(mCaptacion, "Comprobante de ajuste", "Administrador de comprobante Ajuste", 'a',
          this);
    }

    { // Menu de Informe Agrupa los sub menu que generan informes
      JMenu mInforme = new JMenu("Informes");
      mInforme.setMnemonic('I');
      add(mInforme);

      // Sub-menu
      addSubMenu(mInforme, "Balance General", "Balance general de la empresa", 'b', v -> {
        try {
          Utils.showReporte("BalanceGeneral.jrxml");
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      });
    }

    { // Menu de Herramientas agrupa los menus de herramientas
      JMenu mHerramienta = new JMenu("Herramientas");
      mHerramienta.setMnemonic('H');
      add(mHerramienta);

      // Sub-menu
      addSubMenu(mHerramienta, "Respaldo", "Respalda la base de datos", 'R', v -> {
        Respaldo respaldo = new Respaldo();
        respaldo.setVisible(true);
      });
    }

    { // Agrupa los menus de ayuda y acerca de
      JMenu mAyuda = new JMenu("Ayuda");
      mAyuda.setMnemonic('y');
      add(mAyuda);

      // Sub-menu
      addSubMenu(mAyuda, "Manual en lina", "Presenta un manual del sistema", 'M', this);
      addSubMenu(mAyuda, "Ir al Foro", "Los traslada al foro de los desarrolladores", 'I', this);

      mAyuda.addSeparator();

      addSubMenu(mAyuda, "Acerca de ", "Acerca de Pico", 'A', v -> {
        Acercade about = new Acercade();
        about.setVisible(true);
      });
    }

    /*
     * mntmSalir = new ItemMenuUtils("Salir", "Sale del Sistema", 'S', KeyEvent.VK_Q,
     * InputEvent.CTRL_MASK,
     * "/org/tango-project/tango-icon-theme/16x16/actions/system-log-out.png");
     */
  }

  private void showInternalView(JInternalFrame view) {
    try {
      if (!view.isVisible()) {
        this.action.addView(view);
      } else {
        view.moveToFront();

        if (!view.isSelected()) {
          view.setSelected(true);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addSubMenu(JMenu parent, String text, String toolTip, char mnemonic,
      ActionListener actionListener) {

    JMenuItem item = new JMenuItem(text, mnemonic);
    item.setToolTipText(toolTip);
    item.addActionListener(actionListener);

    parent.add(item);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "En construcción", "Aviso",
        JOptionPane.INFORMATION_MESSAGE);

    application.getRootView().setCurrentStatus("Info: Opción en construcción");
  }

  public interface MenuAction {
    void addView(JInternalFrame view);
  }

}
