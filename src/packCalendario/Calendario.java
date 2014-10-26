package packCalendario;

import java.util.ArrayList;

import packManejoDB.Cita;

import com.toedter.calendar.JCalendar;

public class Calendario {

	private static Calendario instance;
	private static JCalendar jCalendario;

	private Calendario() {
		jCalendario = new JCalendar();
	}

	public static Calendario getInstance() {
		if (null == instance) {
			instance = new Calendario();
		}
		return instance;
	}

	public static JCalendar getJCalendar() {
		return jCalendario;
	}

	
}