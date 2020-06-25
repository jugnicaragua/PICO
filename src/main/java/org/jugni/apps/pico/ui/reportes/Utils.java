package org.jugni.apps.pico.ui.reportes;

import java.awt.Dialog;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Utils {
	static JasperPrint jp;
	public static Logger logger = Logger.getLogger(Utils.class.getName());

	private static InputStream loadReport(URL url) throws Exception {
		FileInputStream is = readFile(url);
		if (is == null) {
			throw new Exception("Plantilla de reporte no encontrada. " + url);
		}
		return is;
	}

	private static FileInputStream readFile(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

	private static FileInputStream readFile(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			out.close();
		}
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	/**
	 * Muestra el reporte en el visor de reportes de JasperReports
	 * 
	 * @param url
	 */
	static public void showReporte(String url)throws Exception {
		try {
			//demo
			Collection<String> articulos = new ArrayList<String>();
			articulos.add("omar");

			/*
			* Maven establece el directorio resources como el destino para ficheros externos
			* en dicho directorio, creo uno llamado reportes y copio los archivos de reportes.
			* Luego, la url puede quedar como: reportes/nombre_del_reporte.jrxml
			 */
            InputStream is = Utils.class.getClassLoader().getResourceAsStream("reportes/" + url);
			JasperDesign design = JRXmlLoader.load(is);
			JasperReport report = JasperCompileManager.compileReport(design);
			
			//Parametros para los Reportes
			 HashMap<String,Object> parametros = new HashMap<String,Object>();
			 parametros.put("Dir", Utils.class.getResource(".")  );
			 /*
			 *como la imagen se encuentra en resources, puedo unicamente escribir su nombre
			 * utilizar classLoader para que busque desde la raiz
            */
             URL urlLogo = Utils.class.getClassLoader().getResource( "cherry.jpg") ;
			 //con la url obtenida la paso de parametro al metodo read
			 BufferedImage urlImage = ImageIO.read(urlLogo);
			 parametros.put("logo", urlImage);
			 
			//llenando
			//jp = JasperFillManager.fillReport(report,  );
			//jp = JasperFillManager.fillReport(report, parametros ,new JRBeanCollectionDataSource(articulos));
			jp = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());

			
			JasperViewer jasperViewer = new JasperViewer(jp, false);
			//JasperViewer jasperViewer = new JasperViewer(is, true);
			
			jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
			//jasperViewer.setTitle("Etiquetas");
			jasperViewer.setZoomRatio((float) 1.25);
			jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
			jasperViewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
			jasperViewer.requestFocus();
			jasperViewer.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al caragar"+ e.getMessage() );
		}
	}

	public JasperReport compileReport(String jrxmlFileName) throws JRException, IOException {
		JasperReport jasperReport = null;

		InputStream jrxmlInput = JRLoader.getResourceInputStream(jrxmlFileName);

		if (jrxmlInput != null) {
			JasperDesign design;
			try {
				design = JRXmlLoader.load(jrxmlInput);
			} finally {
				jrxmlInput.close();
			}
			jasperReport = JasperCompileManager.compileReport(design);
		}

		return jasperReport;
	}

	private static JasperPrint createJasperPrint(InputStream reportFile) throws JRException, IOException {
		JasperDesign jasperDesign = JRXmlLoader.load(reportFile);

		JasperPrint jasperPrint = null;

		return jasperPrint;
	}

}
