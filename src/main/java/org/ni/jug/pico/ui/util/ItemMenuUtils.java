/**
 * Clase ItemMenu
 *
 * @author Gustavo Castro -GACS
 * Con esta se evita repetir algunas sentencia y se le cargan al contructor
 */
package org.ni.jug.pico.ui.util;

public class ItemMenuUtils extends javax.swing.JMenuItem {


    private static final long serialVersionUID = 1L;

    /**
     * Contructor de item menu
     *
     * @param titulo    Titulo del menu
     * @param cToolTip  Descripcion del menu
     * @param cMnemonic Caracter para ejecucion del menu "Alt+caracter"
     * @param tecla     Combinacion de tecla asignado al menu
     * @param teclamodi Tecla de control (Ctrl y Alt)combinada con el parametro tecla
     */
    public ItemMenuUtils(String titulo, String cToolTip, char cMnemonic, int tecla, int teclamodi) {
        super(titulo, cMnemonic);
        setToolTipText(cToolTip);
        setAccelerator(javax.swing.KeyStroke.getKeyStroke(tecla, teclamodi));
    }


    /**
     * SobreCarga del Metodo para agregar un Iconoo
     *
     * @param titulo
     * @param cToolTip
     * @param cMnemonic
     * @param tecla
     * @param teclamodi
     * @param rutaIcono
     */
    public ItemMenuUtils(String titulo, String cToolTip, char cMnemonic, int tecla, int teclamodi, String rutaIcono) {
        super(titulo, cMnemonic);
        setToolTipText(cToolTip);
        setAccelerator(javax.swing.KeyStroke.getKeyStroke(tecla, teclamodi));
        setIcon(rutaIcono);
    }


    /**
     * Contructor de item menu
     *
     * @param titulo    Titulo del menu
     * @param cToolTip  Descripcion del menu
     * @param cMnemonic Caracter para ejecucion del menu "Alt+caracter"
     */
    public ItemMenuUtils(String titulo, String cToolTip, char cMnemonic) {
        setText(titulo);
        setToolTipText(cToolTip);
        setMnemonic(cMnemonic);
    }

    /**
     * SobreCarga de metodo para solo agegar lo Básico
     *
     * @param titulo
     * @param cToolTip
     */
    public ItemMenuUtils(String titulo, String cToolTip) {
        setText(titulo);
        setToolTipText(cToolTip);
    }

    /***
     * sobreCarga del metodo, para no agregar nMonic, solo ruta de iCono.
     * @param titulo
     * @param cToolTip
     * @param rutaIcono
     */
    public ItemMenuUtils(String titulo, String cToolTip, String rutaIcono) {
        setText(titulo);
        setToolTipText(cToolTip);
        setIcon(rutaIcono);
    }

    /**
     * SobreCarga del metodo para agregar un icono.
     *
     * @param titulo
     * @param cToolTip
     * @param cMnemonic
     * @param rutaIcono
     */
    public ItemMenuUtils(String titulo, String cToolTip, char cMnemonic, String rutaIcono) {
        setText(titulo);
        setToolTipText(cToolTip);
        setMnemonic(cMnemonic);
        setIcon(rutaIcono);
    }


    /**
     * Metodo para establecer el icono que se visulisara en el itemmenu 
     * @param strPath Direccion relativa o estatica  donde se almacena la imagen para icon del itemnenu
     */
    public void setIcon(String strPath) {
        setIcon(new javax.swing.ImageIcon(getClass().getResource(strPath)));
    }

}
