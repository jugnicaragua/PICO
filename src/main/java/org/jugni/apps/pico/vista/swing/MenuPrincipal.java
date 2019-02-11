/**
 * @author gacs
 * Menu principal, este menu sera instanciado desde la VentanaPrincipal
 */
package org.jugni.apps.pico.vista.swing;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.jugni.apps.pico.clasesherramientas.ItemMenus;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuPrincipal extends JMenuBar {

    private static final long serialVersionUID = 1L;
    
    private JMenu	mnArchivo,          //Menu Archivo
    				mnCatalogo,          //Menu Catalogo agrupa todos los sub menu de catalos
    				mnCaptacion,        //Menu Captacion Agrupa los sub menu con movimiento periodico( comprobantes, cxp,cxc)
    				mnInforme,			// Menu de Informe Agrupa los sub menu que generan informes ;
    				mnHerramienta,		//Menu de Herramientas agrupa los menus de herramientas
    				mnAyuda;			// Agrupa los menus de ayuda y acerca de
    
    private ItemMenus   mntmIniciarSession,			//Sub menu que llama a formulario de inicio de session
    					mntmParametrosGenerales,	//Sub menu que llama al formulario de configuracion
    					mntmCicloFiscal,			//Sub menu que llama al formulario  de configuracion de siclo fiscal
    					mntmSalir,					//Sub menu que cierra la aplicacion
    					mntmCatalogoTipoCuentas,	//Sub Menu que llama al formulario de Tipos de cuenta
    					mntmCatalogoContable,		//Sub Menu que llama al formulario de catalogo de cuenta
                        mntmManualEnLina, 			//Sub menu que llama al manual que se encuentra en linea
                        mntmIrAlForo,  				//sub menu que lo traslada a un foro de los desarrolladores
                        mntmAcercaDe; 				//Sub menu que muestra un dialogo acerca de
            
                                        
           
    public MenuPrincipal() {
            initMenuPrincipal();
    }	
	
    /**
     * Inicializa las propiedades del MenuPrincipal
     * 
     */
    private void initMenuPrincipal() {
            
        // Inicia la declaracion de menus
        mnArchivo            = new JMenu("Archivo");
        mnCatalogo         = new JMenu("Catalogo");
        mnCaptacion        = new JMenu("Captacion");
        mnInforme            = new JMenu("Informes");
        mnHerramienta     = new JMenu("Herramientas");
        mnAyuda                = new JMenu("Ayuda");
        
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
         
        //**Se instancia los sub menu del menu mnCatalogo
        mntmCatalogoTipoCuentas  = new ItemMenus("Catalogo tipo de Cuentas","Administra catalogo de tipo de cuenta",'T');
        mntmCatalogoContable     = new ItemMenus("Catalogo Contable","Administra el catalogo de cuentas",'c');
        //**Se agrega los sub menu al menu mnCatalogo
        mnCatalogo.add(mntmCatalogoTipoCuentas);
        mnCatalogo.add(mntmCatalogoContable);
        
         mntmManualEnLina   = new ItemMenus("Manual en lina","Presenta un manual del sistema",'M');
         mntmIrAlForo               = new ItemMenus("Ir al Foro","Los traslada al foro de los desarrolladores",'I');		
         mntmAcercaDe           = new ItemMenus("Acerca de ","Acerca de Pico",'A');
         mnAyuda.add(mntmManualEnLina);
         mnAyuda.add(mntmIrAlForo);
         mnAyuda.addSeparator();
         mnAyuda.add(mntmAcercaDe);

                
    }
}
