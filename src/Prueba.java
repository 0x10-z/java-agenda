import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;


public class Prueba extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Prueba dialog = new Prueba();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Prueba() {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		{
			textField = new JTextField();
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			getContentPane().add(textField_2);
			textField_2.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			getContentPane().add(textField_1);
			textField_1.setColumns(10);
		}
	}

}
