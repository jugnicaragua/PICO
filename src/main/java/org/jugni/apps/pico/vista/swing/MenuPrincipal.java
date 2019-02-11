/**
 * @author Gustavo Castro - GACS
 * Menu principal, este menu sera instanciado desde la VentanaPrincipal
 */
package org.jugni.apps.pico.vista.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.jugni.apps.pico.clasesherramientas.ItemMenus;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class MenuPrincipal extends JMenuBar implements ActionListener{
    private static final long serialVersionUID = 1L;
    
    private JMenu	mnArchivo,          //Menu Archivo
    				mnCatalogo,          //Menu Catalogo agrupa todos los sub menu de catalos
    				mnCaptacion,        //Menu Captacion Agrupa los sub menu con movimiento periodico( comprobantes, cxp,cxc)
    				mnInforme,			// Menu de Informe Agrupa los sub menu que generan informes ;
    				mnHerramienta,		//Menu de Herramientas agrupa los menus de herramientas
    				mnAyuda;			// Agrupa los menus de ayuda y acerca de
    
    private ItemMenus   
            mntmIniciarSession,                         //Submenu que llama a formulario de inicio de session
            mntmParametrosGenerales,            //Submenu que llama al formulario de configuracion
            mntmCicloFiscal,                                //Submenu que llama al formulario  de configuracion de siclo fiscal
            mntmSalir,                                          //Submenu que cierra la aplicacion
            mntmCatalogoTipoCuentas,	//Submenu que llama al formulario de Tipos de cuenta
            mntmCatalogoContable,                   //Submenu que llama al formulario de catalogo de cuenta
            mntmCaptacionDiario,                     //Submenu que llama al formulario de captacion de comprobante diario
            mntmCaptacionAjuste,                     //Submenu que llama al formulario de captacion de comprobante Ajuste            
            mntmManualEnLina,                           //Submenu que llama al manual que se encuentra en linea
            mntmIrAlForo,                                   //submenu que lo traslada a un foro de los desarrolladores
            mntmAcercaDe;                               //Submenu que muestra un dialogo acerca de
            
            
                                        
           
    public MenuPrincipal() {
            initMenuPrincipal();
    }	
	
    /**
     * Inicializa las propiedades del MenuPrincipal
     * 
     */
    private void initMenuPrincipal() {
           setName("MenuPrincipal");
        // Inicia la declaracion de menus
        mnArchivo            = new JMenu("Archivo");
        mnCatalogo         = new JMenu("Catalogo");
        mnCaptacion        = new JMenu("Captacion");
        mnInforme            = new JMenu("Informes");
        mnHerramienta     = new JMenu("Herramientas");
        mnAyuda                = new JMenu("Ayuda");
        mnArchivo.setMnemonic('A');
        mnCatalogo.setMnemonic('C');
        mnCaptacion.setMnemonic('p');
        mnInforme.setMnemonic('I');
        mnHerramienta.setMnemonic('H');
        mnAyuda.setMnemonic('y');
        //Se agraga los menus a la barra de menu
        add(mnArchivo);
        add(mnCatalogo);
        add(mnCaptacion);
        add(mnInforme);
        add(mnHerramienta);
        add(mnAyuda);
        //Finaliza la declaracion de menus
		
        //*Inicia declaracion de sub menu e item
        //**Se instancia los submenu de archivo
        mntmIniciarSession                  =	new ItemMenus("Iniciar Session","Inicia Session de usuario",'I');
        mntmParametrosGenerales     =	new ItemMenus("Parametros Generales","Administra las configuraciones del Sistema",'P');
        mntmCicloFiscal                         =	new ItemMenus("Ciclo Fiscal","Establece el Periodo Fiscal",'C');
        mntmSalir                                   =  	new ItemMenus("Salir","Sale del Sistema",'S',KeyEvent.VK_Q, InputEvent.CTRL_MASK);
       
        mntmSalir.setIcon("/org/tango-project/tango-icon-theme/16x16/actions/system-log-out.png");
        //**Agrega el metodo de escucha para los sub menu de mnArchivo
        mntmIniciarSession.addActionListener(this);
        mntmParametrosGenerales.addActionListener(this);
        mntmCicloFiscal.addActionListener(this);
        mntmSalir.addActionListener((ActionEvent e) -> {
            //matar todo el prceso
            System.exit(0);
        });
        //**Se agregan los sub menu al menu mnArchivo
        mnArchivo.add(mntmIniciarSession);
        mnArchivo.add(mntmCicloFiscal);
        mnArchivo.add(mntmParametrosGenerales);
        mnArchivo.addSeparator();
        mnArchivo.add(mntmSalir);
         
        //**Se instancia los submenu del menu mnCatalogo
        mntmCatalogoTipoCuentas  = new ItemMenus("Catalogo tipo de Cuentas","Administra catalogo de tipo de cuenta",'T');
        mntmCatalogoContable     = new ItemMenus("Catalogo Contable","Administra el catalogo de cuentas",'c');
        //**Agrega el metodo de escucha para los sub menu de mnCatalogo
        mntmCatalogoTipoCuentas.addActionListener(this);
        mntmCatalogoContable.addActionListener(this);
        //**Se agrega los submenu al menu mnCatalogo
        mnCatalogo.add(mntmCatalogoTipoCuentas);
        mnCatalogo.add(mntmCatalogoContable);
        
        //**Se Instancia los submenu al menu mnCaptacion
        mntmCaptacionDiario     =   new ItemMenus("Comprobante dirio", "Administrador de comprobante diario", 'd');
        mntmCaptacionAjuste    =   new ItemMenus("Comprobante de ajuste", "Administrador de comprobante Ajuste", 'a');
        //**Agrega el metodo de escucha para los sub menu de mnCaptacion
        mntmCaptacionDiario.addActionListener(this);
        mntmCaptacionAjuste.addActionListener(this);
        //**Se agrega los submenu al menu mnCaptacion
        mnCaptacion.add(mntmCaptacionDiario);
        mnCaptacion.add(mntmCaptacionAjuste);
        
        //**Se Instancia los submenu al menu mnAyuda
         mntmManualEnLina   = new ItemMenus("Manual en lina","Presenta un manual del sistema",'M');
         mntmIrAlForo               = new ItemMenus("Ir al Foro","Los traslada al foro de los desarrolladores",'I');		
         mntmAcercaDe           = new ItemMenus("Acerca de ","Acerca de Pico",'A');
        //**Agrega el metodo de escucha para los sub menu de mnAyuda
        mntmManualEnLina.addActionListener(this);
        mntmIrAlForo.addActionListener(this);
        mntmAcercaDe.addActionListener(this);
        //**Se agrega los submenu al menu mnAyuda
         mnAyuda.add(mntmManualEnLina);
         mnAyuda.add(mntmIrAlForo);
         mnAyuda.addSeparator();
         mnAyuda.add(mntmAcercaDe);

           
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame()
                ,"No Terminado " , "menu no definido", JOptionPane.ERROR_MESSAGE);
    }
}
