package packUtilidades;

import java.io.*;

import javax.swing.JOptionPane;

public class ComandoDos {

	public static void main(String args[]) {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c ver");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

		} catch (IOException e1) {
		} catch (InterruptedException e2) {
		}

		System.out.println("Done");
	}
	
	public static void setCommand(String comando){
		try {
			Process p = Runtime.getRuntime().exec("cmd /c "+comando);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			JOptionPane.showMessageDialog(null, "Se ha ejecutado el comando");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error de E/S");

		} catch (InterruptedException e2) {
			JOptionPane.showMessageDialog(null, "Interrupcion no prevista");

		}

	}
}
