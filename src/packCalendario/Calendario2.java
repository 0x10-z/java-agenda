package packCalendario;

import java.util.ArrayList;

import packManejoDB.Cita;

import com.toedter.calendar.JCalendar;

public class Calendario2 {

	private static Calendario2 instance;
	private static JCalendar jCalendario;

	private Calendario2() {
		jCalendario = new JCalendar();
	}

	public static Calendario2 getInstance() {
		if (null == instance) {
			instance = new Calendario2();
		}
		return instance;
	}

	public static JCalendar getJCalendar() {
		return jCalendario;
	}

	
}