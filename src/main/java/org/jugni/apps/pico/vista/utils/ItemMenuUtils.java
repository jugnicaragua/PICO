/**
 *Clase ItemMenu
 * @author Gustavo Castro -GACS
 * Con esta se evita repetir algunas sentencia y se le cargan al contructor
 */
package org.jugni.apps.pico.vista.utils;

public class ItemMenuUtils extends javax.swing.JMenuItem {
    private static final long serialVersionUID = 1L;
        /**
         * Contructor de item menu
         * @param titulo Titulo del menu
         * @param cToolTip Descripcion del menu
         * @param cMnemonic Caracter para ejecucion del menu "Alt+caracter"
         * @param tecla Combinacion de tecla asignado al menu
         * @param teclamodi  Tecla de control (Ctrl y Alt)combinada con el parametro tecla
         */
        public ItemMenuUtils(String titulo, String cToolTip,char cMnemonic,int tecla,int teclamodi){
            super(titulo, cMnemonic);
            setToolTipText(cToolTip);
            setAccelerator(javax.swing.KeyStroke.getKeyStroke(tecla, teclamodi));
    }  
        
     /**
      * 
      * Contructor de item menu
      * @param titulo Titulo del menu
      * @param cToolTip Descripcion del menu
      * @param cMnemonic Caracter para ejecucion del menu "Alt+caracter"
      */   
    public ItemMenuUtils(String titulo, String cToolTip,char cMnemonic){
        setText(titulo);
        setToolTipText(cToolTip);
        setMnemonic(cMnemonic);
    }    
    
     /**
     * Metodo para establecer el icono que se visulisara en el itemmenu 
     * @param strPath Direccion relativa o estatica  donde se almacena la imagen para icon del itemnenu
     */
   public void setIcon(String strPath){
             setIcon(new javax.swing.ImageIcon(getClass().getResource(strPath)));
    }
    
}
