package packInterfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import packManejoDB.Cita;
import packManejoDB.ManejoDB;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AnadirCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JLabel lblDescripcion;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JComboBox comboBox;
	private JCalendar calendario;
	private JButton btnAceptar;
	private JButton btnVolver;
	private ManejoDB mdb;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AnadirCita dialog = new AnadirCita(14, 04, 1992);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param k
	 * @param j
	 * @param i
	 */
	public AnadirCita(int pDia, int pMes, int pAno) {
		mdb = ManejoDB.getInstance();
		calendario = new JCalendar();

		Calendar date = new GregorianCalendar(pAno, pMes, pDia);
		calendario.setCalendar(date);

		calendario.setBounds(246, 12, 329, 199);
		setBounds(100, 100, 589, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(calendario);
		contentPanel.add(getLblNombre());
		contentPanel.add(getLblFecha());
		contentPanel.add(getLblHora());
		contentPanel.add(getLblDescripcion());
		contentPanel.add(getTextField());
		contentPanel.add(getTextField_1());
		contentPanel.add(getComboBox());
		contentPanel.add(getBtnAceptar());
		contentPanel.add(getBtnVolver());
		contentPanel.add(getScrollPane());
		calendario.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				textField.setText(String.valueOf(calendario.getDayChooser()
						.getDay()
						+ "/"
						+ (calendario.getMonthChooser().getMonth() + 1)
						+ "/"
						+ calendario.getYearChooser().getYear()));
			}
		});
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(25, 29, 70, 15);
		}
		return lblNombre;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
			lblFecha.setBounds(25, 68, 70, 15);
		}
		return lblFecha;
	}

	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora");
			lblHora.setBounds(25, 110, 70, 15);
		}
		return lblHora;
	}

	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setBounds(25, 196, 101, 15);
		}
		return lblDescripcion;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(105, 63, 96, 24);
			textField.setColumns(10);
		}
		return textField;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(113, 25, 114, 24);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			ArrayList<String> comboHoras = new ArrayList<String>();
			for (int i = 8; i <= 21; i++) {
				if (i < 10) {
					comboHoras.add("0" + i + ":00");
					comboHoras.add("0" + i + ":15");
					comboHoras.add("0" + i + ":30");
					comboHoras.add("0" + i + ":45");
				} else {
					comboHoras.add(i + ":00");
					comboHoras.add(i + ":15");
					comboHoras.add(i + ":30");
					comboHoras.add(i + ":45");
				}
			}
			comboBox = new JComboBox(new Vector(comboHoras));
			comboBox.setBounds(113, 105, 75, 24);
		}
		return comboBox;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					mdb.anadirCita(textField_1.getText(),
							 textArea.getText(),
							calendario.getDayChooser().getDay(), calendario
									.getMonthChooser().getMonth(), calendario
									.getYearChooser().getYear(),
							(String) comboBox.getSelectedItem());
					
					dispose();
				}
			});
			btnAceptar.setBounds(424, 236, 117, 25);
		}
		return btnAceptar;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolver.setBounds(424, 280, 117, 25);
		}
		return btnVolver;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 223, 387, 124);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
}