package packInterfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;

import com.toedter.calendar.JCalendar;

import packCalendario.Calendario;
import packManejoDB.Cita;
import packManejoDB.ManejoDB;
import packUtilidades.ComandoDos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaAgenda extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList list;
	private Calendario calendario = Calendario.getInstance();
	private JCalendar jCalendario;
	private DefaultListModel modelo;
	private JButton btnNewButton;
	private ManejoDB mdb;
	private JButton btnEliminarCita;
	private JLabel label;
	private JLabel lblAgendaCitas;
	private JTextField textField;
	private JLabel lblFecha;
	private JLabel lblNewLabel;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenu mnEditar;
	private JMenuItem mntmMenuDeTexto;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmSalir;
	private ArrayList<Cita> listaAux;
	private JMenu mnBasesDeDatos;
	private JButton btnNewButton_1;
	private JMenu mnCrear;
	private JMenuItem mntmNuevaBaseDe;
	private JMenuItem mntmNuevaNota;
	private JMenuItem mntmResumenMensual;
	private JMenu mnPuntos;
	private JMenuItem mntmPuntosOfertas;
	private final String VERSION = "V1.0";
	private final String TITULO = "Agenda - Peluqueria H2U ";

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaAgenda dialog = new VentanaAgenda();
			dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaAgenda() {

		listaAux = new ArrayList<Cita>();
		setTitle(TITULO+VERSION);
		setJMenuBar(getMenuBar_1());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mdb = ManejoDB.getInstance();
		modelo = new DefaultListModel();
		this.setIconImage(new ImageIcon(getClass().getResource(
				"/imagenes/icono.png")).getImage());
		jCalendario = calendario.getJCalendar();
		if (mdb.probarConexion()) {
			// mdb.getCitas();
			jCalendario.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					textField.setText(jCalendario.getDayChooser().getDay()
							+ "/"
							+ (jCalendario.getMonthChooser().getMonth() + 1)
							+ "/" + jCalendario.getYearChooser().getYear());
					boolean encontrado = false;
					System.out.println("Es el dia: "
							+ jCalendario.getDayChooser().getDay() + " del "
							+ ((jCalendario.getMonthChooser().getMonth()) + 1)
							+ " de " + jCalendario.getYearChooser().getYear());

					actualizarLista();

					/*
					 * for (Cita cita : mdb.getListaCitas()) { if
					 * (jCalendario.getDayChooser().getDay() == cita .getDia()
					 * && jCalendario.getMonthChooser().getMonth() == cita
					 * .getMes() && jCalendario.getYearChooser().getYear() ==
					 * cita .getAno()) { encontrado = true;
					 * modelo.addElement(cita.getNombre() + " - " +
					 * cita.getHora() + " - " + cita.getDescripcion()); } }
					 * 
					 * String[] arrayModelo = new String[modelo.size()]; for
					 * (int i = 0; i < arrayModelo.length; i++) { arrayModelo[i]
					 * = (String) modelo.get(i); }
					 * 
					 * //Actualizar horario de la lista for (int i = 0; i <
					 * arrayModelo.length; i++) { String aux; for (int j = 0; j
					 * < arrayModelo.length - 1; j++) { if (Integer.parseInt(mdb
					 * .sacarHoraDeHora(arrayModelo[j])) > Integer.parseInt(mdb
					 * .sacarHoraDeHora(arrayModelo[j + 1]))) {
					 * System.out.println
					 * ("comparando... "+arrayModelo[j]+" es mas grande que "
					 * +arrayModelo[j+1]); aux = arrayModelo[j]; arrayModelo[j]
					 * = arrayModelo[j + 1]; arrayModelo[j+1] = aux;
					 * 
					 * } } }
					 * 
					 * modelo.clear(); for (int i = 0; i < arrayModelo.length;
					 * i++) { modelo.addElement(arrayModelo[i]); }
					 */

				}
			});
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"No se ha podido establecer conexion con la base de datos\nNo obtendras ninguna cita");
		}
		jCalendario.setBounds(346, 12, 376, 262);

		getList().setFont(new Font("Corbel", Font.BOLD, 14));
		setBounds(100, 100, 739, 517);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane());
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getBtnEliminarCita());
		contentPanel.add(jCalendario);
		contentPanel.add(getLabel());
		contentPanel.add(getLblAgendaCitas());
		contentPanel.add(getTextField());
		contentPanel.add(getLblFecha());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getBtnNewButton_1());
		getLblFecha().requestFocus();
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 37, 319, 415);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}

	private JList getList() {
		if (list == null) {
			list = new JList(modelo);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JList list = (JList) e.getSource();
					if (e.getClickCount() == 2) {
						int index = list.locationToIndex(e.getPoint());
						System.out.println(index);
						// DatosCita dc = new DatosCita(modelo.get(index));
					}
				}
			});

		}

		return list;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Anadir Cita\n\n");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AnadirCita ac = new AnadirCita(jCalendario.getDayChooser()
							.getDay(),
							jCalendario.getMonthChooser().getMonth(),
							jCalendario.getYearChooser().getYear());
					ac.setVisible(true);
				}
			});
			btnNewButton.setBounds(343, 412, 112, 40);
		}
		return btnNewButton;
	}

	private JButton getBtnEliminarCita() {
		if (btnEliminarCita == null) {
			btnEliminarCita = new JButton("Eliminar Cita");
			btnEliminarCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getList().isSelectionEmpty()) {
						JOptionPane.showMessageDialog(null,
								"No has seleccionado ninguna cita");
					} else {
						int opcion = JOptionPane
								.showConfirmDialog(
										null,
										"¿Estas segura de que deseas eliminar la cita?",
										"Eliminar Cita",
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.WARNING_MESSAGE);
						if (opcion == 0) {
							mdb.eliminarCita(mdb
									.sacarNombreDeCita((String) getList()
											.getSelectedValue()), mdb
									.sacarHoraDeCita((String) getList()
											.getSelectedValue()), jCalendario
									.getDayChooser().getDay(), jCalendario
									.getMonthChooser().getMonth(), jCalendario
									.getYearChooser().getYear());
						}
					}
				}
			});
			btnEliminarCita.setBounds(467, 412, 128, 40);
		}
		return btnEliminarCita;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setBounds(374, 331, 70, 15);
		}
		return label;
	}

	private JLabel getLblAgendaCitas() {
		if (lblAgendaCitas == null) {
			lblAgendaCitas = new JLabel("Agenda - Citas");
			lblAgendaCitas.setFont(new Font("Dialog", Font.BOLD, 15));
			lblAgendaCitas.setBounds(12, 0, 155, 36);
		}
		return lblAgendaCitas;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String d = "", m = "", a = "";
						int j = 0;
						for (int i = 0; i < textField.getText().length(); i++) {
							if (textField.getText().charAt(i) == '/') {
								j++;
							} else if (j == 0) {
								d += textField.getText().charAt(i);
							} else if (j == 1) {
								m += textField.getText().charAt(i);
							} else if (j == 2) {
								a += textField.getText().charAt(i);
							}
						}
						System.out.println("Se ha seleccionado dia " + (a)
								+ " " + (m) + " " + (d));
						Calendar date = new GregorianCalendar(Integer
								.parseInt(a), (Integer.parseInt(m) - 1),
								Integer.parseInt(d));
						jCalendario.setCalendar(date);
					}
				}
			});
			textField.setBounds(220, 7, 111, 24);
			textField.setColumns(10);
		}
		return textField;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(165, 11, 70, 15);
		}
		return lblFecha;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel((new ImageIcon(getClass().getResource(
					"/imagenes/paredh2u.jpg"))));
			lblNewLabel.setBounds(349, 260, 364, 140);

		}
		return lblNewLabel;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnArchivo());
			menuBar.add(getMnEditar());
		}
		return menuBar;
	}

	private JMenu getMnArchivo() {
		if (mnArchivo == null) {
			mnArchivo = new JMenu("Archivo");
			mnArchivo.add(getMnCrear());
			mnArchivo.add(getMnBasesDeDatos());
			mnArchivo.add(getMntmSalir());
		}
		return mnArchivo;
	}

	private JMenu getMnEditar() {
		if (mnEditar == null) {
			mnEditar = new JMenu("Editar");
			mnEditar.add(getMnPuntos());
		}
		return mnEditar;
	}

	private JMenuItem getMntmMenuDeTexto() {
		if (mntmMenuDeTexto == null) {
			mntmMenuDeTexto = new JMenuItem("Cambiar Base de Datos");
			mntmMenuDeTexto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null,
							"Utilidad no disponible");
				}
			});
		}
		return mntmMenuDeTexto;
	}

	private JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem(
					"Crear Copia de seguridad de Base de Datos");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ComandoDos.setCommand("mkdir C:\\hola");
					String psw = JOptionPane.showInputDialog(null,
							"Introduce la contrasena de la base de datos");
					int pd = jCalendario.getDayChooser().getDay();
					int pm = jCalendario.getMonthChooser().getMonth() + 1;
					int pa = jCalendario.getYearChooser().getYear();

					String comando = "cd C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\ && mysqldump.exe -uroot -p"
							+ psw
							+ " -hlocalhost agenda > C:\\AgendaJava\\backup\\"
							+ pd + "-" + pm + "-" + pa + ".sql";
					JOptionPane.showMessageDialog(null, comando);
					ComandoDos.setCommand(comando);
				}
			});
		}
		return mntmNewMenuItem;
	}

	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return mntmSalir;
	}

	private void actualizarLista() {
		boolean encontrado = false;
		listaAux = mdb.getCitasDia(jCalendario.getDayChooser().getDay(),
				jCalendario.getMonthChooser().getMonth(), jCalendario
						.getYearChooser().getYear());
		modelo.clear();

		for (Cita cita : listaAux) {
			modelo.addElement(cita.getHora() + " - " + cita.getNombre() + " - "
					+ cita.getDescripcion());
			encontrado = true;
		}
		if (!encontrado) {
			modelo.addElement("No tienes ninguna cita");
		}
	}

	private JMenu getMnBasesDeDatos() {
		if (mnBasesDeDatos == null) {
			mnBasesDeDatos = new JMenu("Bases de Datos");
			mnBasesDeDatos.add(getMntmNuevaBaseDe());
			mnBasesDeDatos.add(getMntmNewMenuItem());
			mnBasesDeDatos.add(getMntmMenuDeTexto());
		}
		return mnBasesDeDatos;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Actualizar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actualizarLista();
				}
			});
			btnNewButton_1.setBounds(608, 412, 117, 40);
		}
		return btnNewButton_1;
	}

	private JMenu getMnCrear() {
		if (mnCrear == null) {
			mnCrear = new JMenu("Crear");
			mnCrear.add(getMntmResumenMensual());
			mnCrear.add(getMntmNuevaNota());
		}
		return mnCrear;
	}

	private JMenuItem getMntmNuevaBaseDe() {
		if (mntmNuevaBaseDe == null) {
			mntmNuevaBaseDe = new JMenuItem("Nueva base de datos (agenda)");
			mntmNuevaBaseDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mdb.crearBaseDatos();
				}
			});
		}
		return mntmNuevaBaseDe;
	}

	private JMenuItem getMntmNuevaNota() {
		if (mntmNuevaNota == null) {
			mntmNuevaNota = new JMenuItem("Nueva Nota");
			mntmNuevaNota.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aux = JOptionPane
							.showInputDialog("Introduce una nueva nota");
					JOptionPane.showMessageDialog(null, aux);
				}
			});
		}
		return mntmNuevaNota;
	}

	private JMenuItem getMntmResumenMensual() {
		if (mntmResumenMensual == null) {
			mntmResumenMensual = new JMenuItem("Resumen Mensual");
			mntmResumenMensual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ResumenMensual rm = new ResumenMensual();
					rm.setVisible(true);

				}
			});
		}
		return mntmResumenMensual;
	}

	private JMenu getMnPuntos() {
		if (mnPuntos == null) {
			mnPuntos = new JMenu("Puntos");
			mnPuntos.add(getMntmPuntosOfertas());
		}
		return mnPuntos;
	}

	private JMenuItem getMntmPuntosOfertas() {
		if (mntmPuntosOfertas == null) {
			mntmPuntosOfertas = new JMenuItem("Puntos Ofertas");
			mntmPuntosOfertas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					VentanaTiempo po = new VentanaTiempo();
					
					po.setVisible(true);

				}
			});
		}
		return mntmPuntosOfertas;
	}
}