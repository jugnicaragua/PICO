package org.ni.jug.pico.ui.core;

import org.ni.jug.pico.ui.util.JDialogoUtil;
import org.ni.jug.pico.ui.util.Utilities;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Formulario para respaldo
 * se uiliza el grouplayout
 * @author gacs
 *
 */
public class Respaldo extends JDialogoUtil {
	private JLabel lblTitle; 
	private JLabel lblDestino; 
	private JLabel lblFile; 
	private JPanel pnlButton;
	private JPanel contentPanel;
	private JPanel pnlTitulo;
	private JTextField txtDestino;	//Guarda la direccion donde se almacenara el respaldo de la BD
	private JTextField txtFile;		//Guarda el nombre del archivo de respaldo 
	private JButton cmdOk,cmdCancel,cmdDestino;
	private static final String SEPARADOR=System.getProperty("file.separator");
	private static final String ORINGENBD=System.getProperty("user.dir")+SEPARADOR+"var"+SEPARADOR+"bd"+SEPARADOR+"pico.db"; //direccion origen de la base de datos

	/**
	 * Crontuctor Acercade
	 * @param parent recibe el frame del cual es jdiogo es hijo
	 * 
	 */
	public Respaldo() {
		initRespaldo();
		pack();
		setLocationRelativeTo(null);
	}


	public static void main(String[] args) {
		try {
			Respaldo dialog = new Respaldo();
			dialog.setModal(false);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void initRespaldo() {
		String strDestino=System.getProperty("user.dir")+SEPARADOR+"var"+SEPARADOR+"respaldo"; //Direccion destino donde se almacenara el respaldo
		String nombreArchivo="Respaldo"+ new SimpleDateFormat("yyyy-mm-dd-HH:mm:ss").
				format(Calendar.getInstance().getTime()); //Nombre recomendado para el archivo de respaldo
		if(!exists(strDestino)) {
			new File(strDestino).mkdirs();
		}
		//Inicializa el Jdialogo
		{
			setTitle("Respaldo");
			setName("Respaldo");
			getContentPane().setLayout(new BorderLayout());
		}
		//Se instanciasn los componentes del jdialogo
		{
			lblTitle		=	new JLabel("Respaldo..");
			lblDestino		=	new JLabel("Destino :");
			lblFile			=	new JLabel("Archivo :");
			contentPanel 	= 	new JPanel();
			pnlButton 		=	new JPanel();
			pnlTitulo 		=	new JPanel();
			txtDestino		=	new JTextField();
			txtFile			=	new JTextField();
			cmdOk			= 	new JButton("Aceptar");
			cmdCancel		= 	new JButton("Cancelar");
			cmdDestino 		=	new JButton("...");
		}
		//Se inicisilizan los componentes
		{
			lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
			txtDestino.setText(strDestino);
			txtFile.setText(nombreArchivo);
			cmdDestino.addActionListener(a ->  {	
				String strDirectorio=getDirectorio(txtDestino.getText());
				if(!strDirectorio.isEmpty())
					txtDestino.setText(strDirectorio);
			});
			cmdOk.addActionListener(a ->  {				
				if(!respaldo())
					return;
					
				frmClose();
			});
			cmdCancel.addActionListener(a ->  {	
				frmClose();
			});
		}
		//inicializa los paneles
		{
			GroupLayout gl = new GroupLayout(contentPanel);
			contentPanel.setLayout(gl);
			//Se instancia la agrupacion horizontal del GroupLayout (gl) 
			GroupLayout.SequentialGroup hGroup = gl.createSequentialGroup();
			//Se instancia la agrupacion vertical del GroupLayout (gl) 
			GroupLayout.SequentialGroup vGroup = gl.createSequentialGroup();
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			//se agregan los componente al grupo horizontal
			hGroup.addGroup(
					gl.createParallelGroup().
					addComponent(lblDestino).
					addComponent(lblFile)
					);
			hGroup.addGroup(
					gl.createParallelGroup().
					addComponent(txtDestino,240, GroupLayout.DEFAULT_SIZE, 241).
					addComponent(txtFile)
					);
			hGroup.addComponent(cmdDestino);

			//Se agrega los componente al grupo vertical
			vGroup.addGroup(
					gl.createParallelGroup(Alignment.BASELINE).
					addComponent(lblDestino).
					addComponent(txtDestino).
					addComponent(cmdDestino)
					);
			vGroup.addGroup(
					gl.createParallelGroup(Alignment.BASELINE).
					addComponent(lblFile).
					addComponent(txtFile)
					);
		//Se agregan los grupos al GroupLayout (gl)
			gl.setHorizontalGroup(hGroup);
			gl.setVerticalGroup(vGroup);
//		    gl.linkSize(SwingConstants.HORIZONTAL, txtDestino, txtFile);
		    
			//			contentPanel.setLayout(new BorderLayout());
			pnlButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pnlButton.add(cmdOk);
			pnlTitulo.setLayout(new FlowLayout(FlowLayout.LEFT));
			pnlTitulo.add(lblTitle);
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			getContentPane().add(pnlButton,BorderLayout.SOUTH);
			getContentPane().add(pnlTitulo,BorderLayout.NORTH);
			pnlButton.add(cmdCancel);
			pnlButton.add(cmdOk);

		}
	}
	
	private String getDirectorio(String strDirectorioPredeterminado) {
		String strdestino=null;
		JFileChooser jfc = new JFileChooser(strDirectorioPredeterminado);
		jfc.setDialogTitle("Seleccione el directorio: ");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setDialogType(JFileChooser.CUSTOM_DIALOG);

		int returnValue = jfc.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (jfc.getSelectedFile().isDirectory()) {
				strdestino= jfc.getSelectedFile().getAbsolutePath();
			}
		}	
		return strdestino;
	}
	/**
	 * Verifica si existe una deteminada direccion
	 * @param direccion a verificar
	 * @return devuelve verdadero si la direccion existe
	 */
	private boolean exists (String direccion) {
		File file =new File(direccion);
		return file.exists();
	}
	/**
	 * Respalda la base de datos, verifica que el nombre del archivo y la direccion esten correcta 
	 * @return devuelve verdadero si el respaldo se efectuo correctamente
	 */
	private boolean respaldo(){
		String strDirectorio=txtDestino.getText(); //Gurada el contenido del objeto txtDestino
		String strDestino;
		boolean blnreturn=true;
		try {
			
			if(txtFile.getText().isEmpty()){
				txtFile.requestFocus();
				blnreturn=false;
				throw new IOException("Nombre de archivo no puede ser en blanco");
			}
			//Verifica que el directorio exista
			if(strDirectorio.isEmpty() && exists(strDirectorio)){
				blnreturn=false;
				txtDestino.requestFocus();
				throw new IOException("Direccion Incorrecta");
			}//Verifica que la direccion del directorio termine en el separador de archivo
			else if(!SEPARADOR.equals(strDirectorio.charAt(strDirectorio.length()-1))){
				strDirectorio=strDirectorio+SEPARADOR;
				txtDestino.setText(strDirectorio);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		strDestino=strDirectorio+txtFile.getText();
			if(blnreturn) {
				try {
					Utilities.cp(ORINGENBD, strDestino);
				} catch (IOException e) {
					blnreturn=false;
					e.printStackTrace();
				}
			}
			return blnreturn;
		}
	}
