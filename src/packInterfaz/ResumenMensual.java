package packInterfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

public class ResumenMensual extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblServicios;
	private JLabel lblFototerapia;
	private JLabel lblProductos;
	private JLabel lblResumenMensual;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNeto;
	private JLabel lblIva;
	private JLabel lblTotal;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel serviciosTotal;
	private JLabel fototerapiaTotal;
	private JLabel productosTotal;
	private JLabel lblTotal_1;
	private JLabel totalNeto;
	private JLabel totalIVA;
	private JLabel totalTotal;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JButton btnGenerarDoc;
	private JButton btnVolver;
	private JButton btnActualizar;
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	private double serv_neto;
	private double serv_iva;
	private double serv_total;

	private double foto_neto;
	private double foto_iva;
	private double foto_total;

	private double prod_neto;
	private double prod_iva;
	private double prod_total;

	private DecimalFormat df;
	private String rutaPlantilla;
	private String rutaArchivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResumenMensual dialog = new ResumenMensual();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResumenMensual() {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df = new DecimalFormat("0.00", simbolos);
		setBounds(100, 100, 739, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblServicios());
		contentPanel.add(getLblFototerapia());
		contentPanel.add(getLblProductos());
		contentPanel.add(getLblResumenMensual());
		contentPanel.add(getTextField());
		contentPanel.add(getTextField_1());
		contentPanel.add(getTextField_2());
		contentPanel.add(getLblNeto());
		contentPanel.add(getLblIva());
		contentPanel.add(getLblTotal());
		contentPanel.add(getTextField_3());
		contentPanel.add(getTextField_4());
		contentPanel.add(getTextField_5());
		contentPanel.add(getLabel());
		contentPanel.add(getLabel_1());
		contentPanel.add(getLabel_2());
		contentPanel.add(getLblTotal_1());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getLabel_3());
		contentPanel.add(getLabel_4());
		contentPanel.add(getLabel_5());
		contentPanel.add(getLabel_6());
		contentPanel.add(getLabel_7());
		contentPanel.add(getLabel_8());
		contentPanel.add(getLabel_9());
		contentPanel.add(getLabel_10());
		contentPanel.add(getBtnGenerarDoc());
		contentPanel.add(getBtnVolver());
		contentPanel.add(getBtnActualizar());
		contentPanel.add(getComboBox());
		contentPanel.add(getComboBox_1());
	}

	private JLabel getLblServicios() {
		if (lblServicios == null) {
			lblServicios = new JLabel("Servicios");
			lblServicios.setFont(new Font("Dialog", Font.BOLD, 20));
			lblServicios.setBounds(33, 155, 159, 29);
		}
		return lblServicios;
	}

	private JLabel getLblFototerapia() {
		if (lblFototerapia == null) {
			lblFototerapia = new JLabel("Fototerapia");
			lblFototerapia.setFont(new Font("Dialog", Font.BOLD, 20));
			lblFototerapia.setBounds(33, 222, 164, 32);
		}
		return lblFototerapia;
	}

	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos");
			lblProductos.setFont(new Font("Dialog", Font.BOLD, 20));
			lblProductos.setBounds(33, 299, 164, 35);
		}
		return lblProductos;
	}

	private JLabel getLblResumenMensual() {
		if (lblResumenMensual == null) {
			lblResumenMensual = new JLabel("Resumen Mensual");
			lblResumenMensual.setFont(new Font("Dialog", Font.BOLD, 25));
			lblResumenMensual.setBounds(33, 12, 280, 44);
		}
		return lblResumenMensual;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText("0,00");
			textField.setBounds(210, 304, 114, 29);
			textField.setColumns(10);
		}
		return textField;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setText("0,00");
			textField_1.setBounds(210, 157, 114, 29);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setText("0,00");
			textField_2.setBounds(210, 226, 114, 29);
			textField_2.setColumns(10);
		}
		return textField_2;
	}

	private JLabel getLblNeto() {
		if (lblNeto == null) {
			lblNeto = new JLabel("Neto");
			lblNeto.setFont(new Font("Dialog", Font.BOLD, 20));
			lblNeto.setBounds(210, 86, 93, 29);
		}
		return lblNeto;
	}

	private JLabel getLblIva() {
		if (lblIva == null) {
			lblIva = new JLabel("IVA");
			lblIva.setFont(new Font("Dialog", Font.BOLD, 20));
			lblIva.setBounds(389, 86, 93, 29);
		}
		return lblIva;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total");
			lblTotal.setFont(new Font("Dialog", Font.BOLD, 20));
			lblTotal.setBounds(546, 86, 70, 24);
		}
		return lblTotal;
	}

	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setText("21");
			textField_3.setBounds(390, 157, 40, 29);
			textField_3.setColumns(10);
		}
		return textField_3;
	}

	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setText("21");
			textField_4.setBounds(389, 226, 41, 29);
			textField_4.setColumns(10);
		}
		return textField_4;
	}

	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setText("21");
			textField_5.setBounds(389, 304, 40, 29);
			textField_5.setColumns(10);
		}
		return textField_5;
	}

	private JLabel getLabel() {
		if (serviciosTotal == null) {
			serviciosTotal = new JLabel("0,00");
			serviciosTotal.setFont(new Font("Dialog", Font.BOLD, 19));
			serviciosTotal.setBounds(535, 155, 178, 29);
		}
		return serviciosTotal;
	}

	private JLabel getLabel_1() {
		if (fototerapiaTotal == null) {
			fototerapiaTotal = new JLabel("0,00");
			fototerapiaTotal.setFont(new Font("Dialog", Font.BOLD, 19));
			fototerapiaTotal.setBounds(535, 228, 178, 21);
		}
		return fototerapiaTotal;
	}

	private JLabel getLabel_2() {
		if (productosTotal == null) {
			productosTotal = new JLabel("0,00");
			productosTotal.setFont(new Font("Dialog", Font.BOLD, 19));
			productosTotal.setBounds(535, 303, 178, 27);
		}
		return productosTotal;
	}

	private JLabel getLblTotal_1() {
		if (lblTotal_1 == null) {
			lblTotal_1 = new JLabel("Total");
			lblTotal_1.setFont(new Font("Dialog", Font.BOLD, 20));
			lblTotal_1.setBounds(40, 387, 81, 29);
		}
		return lblTotal_1;
	}

	private JLabel getLblNewLabel() {
		if (totalNeto == null) {
			totalNeto = new JLabel("0,00");
			totalNeto.setFont(new Font("Dialog", Font.BOLD, 19));
			totalNeto.setBounds(210, 394, 114, 15);
		}
		return totalNeto;
	}

	private JLabel getLabel_3() {
		if (totalIVA == null) {
			totalIVA = new JLabel("0,00");
			totalIVA.setFont(new Font("Dialog", Font.BOLD, 19));
			totalIVA.setBounds(367, 394, 156, 15);
		}
		return totalIVA;
	}

	private JLabel getLabel_4() {
		if (totalTotal == null) {
			totalTotal = new JLabel("0,00");
			totalTotal.setFont(new Font("Dialog", Font.BOLD, 19));
			totalTotal.setBounds(535, 394, 178, 15);
		}
		return totalTotal;
	}

	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("%");
			label_5.setFont(new Font("Dialog", Font.BOLD, 19));
			label_5.setBounds(441, 155, 41, 29);
		}
		return label_5;
	}

	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("%");
			label_6.setFont(new Font("Dialog", Font.BOLD, 19));
			label_6.setBounds(441, 222, 41, 29);
		}
		return label_6;
	}

	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("%");
			label_7.setFont(new Font("Dialog", Font.BOLD, 19));
			label_7.setBounds(441, 302, 41, 29);
		}
		return label_7;
	}

	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("\u20AC");
			label_8.setFont(new Font("Dialog", Font.BOLD, 19));
			label_8.setBounds(330, 155, 41, 29);
		}
		return label_8;
	}

	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("\u20AC");
			label_9.setFont(new Font("Dialog", Font.BOLD, 19));
			label_9.setBounds(330, 224, 41, 29);
		}
		return label_9;
	}

	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("\u20AC");
			label_10.setFont(new Font("Dialog", Font.BOLD, 19));
			label_10.setBounds(330, 302, 41, 29);
		}
		return label_10;
	}

	private JButton getBtnGenerarDoc() {
		if (btnGenerarDoc == null) {
			btnGenerarDoc = new JButton("Generar Doc");
			btnGenerarDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int opc = JOptionPane.showConfirmDialog(null,
							"Has actualizado los valores?");
					generarDocumento();

				}
			});
			btnGenerarDoc.setBounds(459, 451, 153, 25);
		}
		return btnGenerarDoc;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnVolver.setBounds(330, 451, 117, 25);
		}
		return btnVolver;
	}

	private JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Parte servicios
					String serv_net = getTextField_1().getText().replaceAll(
							",", ".");
					String serv_iv = getTextField_3().getText().replaceAll(
							", ", ".");
					serv_neto = redondear(Double.parseDouble(serv_net));
					serv_iva = redondear(serv_neto
							* ((double) Integer.parseInt(serv_iv) / (double) 100));
					serviciosTotal.setText(String.valueOf(df
							.format(redondear(serv_neto + serv_iva))));

					// Parte fototerapia
					String foto_net = getTextField_2().getText().replaceAll(
							",", ".");
					String foto_iv = getTextField_4().getText().replaceAll(
							", ", ".");
					foto_neto = redondear(Double.parseDouble(foto_net));
					foto_iva = redondear(foto_neto
							* ((double) Integer.parseInt(foto_iv) / (double) 100));
					fototerapiaTotal.setText(String.valueOf(df
							.format(redondear(foto_neto + foto_iva))));

					// Parte productos
					String prod_net = getTextField().getText().replaceAll(",",
							".");
					String prod_iv = getTextField_5().getText().replaceAll(
							", ", ".");
					prod_neto = redondear(Double.parseDouble(prod_net));
					prod_iva = redondear(prod_neto
							* ((double) Integer.parseInt(prod_iv) / (double) 100));
					productosTotal.setText(String.valueOf(df
							.format(redondear(prod_neto + prod_iva))));

					// Parte total
					double total_serv = Double.parseDouble(serviciosTotal
							.getText().replaceAll(",", "."));
					double total_foto = Double.parseDouble(fototerapiaTotal
							.getText().replaceAll(",", "."));
					double total_prod = Double.parseDouble(productosTotal
							.getText().replaceAll(",", "."));

					double total_total = (serv_neto + foto_neto + prod_neto)
							+ ((serv_neto + foto_neto + prod_neto) * 0.21);
					totalNeto.setText(String.valueOf(df.format(serv_neto
							+ foto_neto + prod_neto)));
					// totalIVA.setText(String.valueOf(serv_iva + foto_iva
					// + prod_iva));
					totalIVA.setText(String.valueOf(df.format((serv_neto
							+ foto_neto + prod_neto) * 0.21)));
					totalTotal.setText(String.valueOf(df.format(total_total)));

				}
			});
			btnActualizar.setBounds(123, 451, 117, 25);
		}
		return btnActualizar;
	}

	public double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void generarDocumento() {

		DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
		try {
			rutaPlantilla = "C:\\AgendaJava\\resumen_mensual\\plantilla.odt";
			DocumentTemplate template = documentTemplateFactory
					.getTemplate(new File(rutaPlantilla));
			// DocumentTemplate template = documentTemplateFactory
			// .getTemplate(new File(
			// "/home/iocio/Escritorio/plantilla.odt"));
			Map data = new HashMap();
			// Servicios
			data.put("servicios_neto", df.format(Double.parseDouble(textField_1
					.getText().replaceAll(",", "."))));
			data.put("servicios_iva", textField_3.getText());
			data.put("servicios_iva_precio", df.format(serv_iva));
			data.put("servicios_total", serviciosTotal.getText());
			// Fototerapia

			data.put("fotote_neto", df.format(Double.parseDouble(textField_2
					.getText().replaceAll(",", "."))));
			data.put("fotote_iva", textField_4.getText());
			data.put("fotote_iva_precio", df.format(foto_iva));
			data.put("fotote_total", fototerapiaTotal.getText());
			// Productos

			data.put("product_neto", df.format(Double.parseDouble(textField
					.getText().replaceAll(",", "."))));
			data.put("product_iva", textField_5.getText());
			data.put("product_iva_precio", df.format(prod_iva));
			data.put("product_total", productosTotal.getText());
			// Totales
			data.put("total_neto", totalNeto.getText());
			data.put("total_iva", totalIVA.getText());
			data.put("total_total", totalTotal.getText());

			// Fecha
			data.put("mes", (String) getComboBox().getSelectedItem());
			data.put("ano", (String) getComboBox_1().getSelectedItem());

			// ...
			rutaArchivo = "C:\\AgendaJava\\resumen_mensual\\Resumen";
			String rutaResumen = "C:\\AgendaJava\\resumen_mensual\\";
			template.createDocument(data, new FileOutputStream(rutaArchivo+" "
					+ (String) getComboBox().getSelectedItem() + " de "
					+ (String) getComboBox_1().getSelectedItem() + ".odt"));

			// template.createDocument(data, new FileOutputStream(
			// "/home/iocio/Escritorio/ "
			// + (String) getComboBox().getSelectedItem() + " de "
			// + (String) getComboBox_1().getSelectedItem()
			// + ".odt"));
			JOptionPane.showMessageDialog(null, "Resumen Mensual completado en: "+rutaArchivo);
			Process p = new ProcessBuilder("explorer.exe", "/select,"+rutaResumen).start();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR\n-Plantilla no encontrada en "+rutaPlantilla+"\n-El archivo que se intenta generar en "+rutaArchivo+" esta siendo utilizado por otro programa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error de Entrada/Salida");
		} catch (DocumentTemplateException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Problema con plantilla");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Excepcion General");
		}
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			String[] meses = new String[] { "Enero", "Febrero", "Marzo",
					"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
					"Octubre", "Noviembre", "Diciembre" };
			comboBox = new JComboBox(meses);
			comboBox.setBounds(346, 26, 133, 24);
		}
		return comboBox;
	}

	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			String[] anos = new String[51];
			int j = 0;
			for (int i = 2013; i < 2064; i++) {
				anos[j] = String.valueOf(i);
				j++;
			}
			comboBox_1 = new JComboBox(anos);
			comboBox_1.setBounds(546, 26, 93, 24);
		}
		return comboBox_1;
	}
}
