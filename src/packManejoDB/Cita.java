package packManejoDB;

public class Cita {

	private int mes;
	private int dia;
	private int ano;
	private String nombre;
	private String apellido;
	private String descripcion;
	private String hora;
	private String puntos;

	public Cita() {
		this(0, 0, 0, "No tiene nombre", "No tine apellido",
				"No hay descripcion", "No tiene hora");
	}
	
	public Cita(int pDia, int pMes, int pAno, String pNombre,
			String pDescripcion, String pHora){
		this(pDia, pMes, pAno, pNombre, "DEFAULT", pDescripcion, pHora);
	}

	public Cita(int pDia, int pMes, int pAno, String pNombre, String pApe,
			String pDescripcion, String pHora) {
		this.dia = pDia;
		this.mes = pMes;
		this.ano = pAno;
		this.nombre = pNombre;
		this.apellido = pApe;
		this.descripcion = pDescripcion;
		this.hora = pHora;
	}

	public int getDia() {
		return this.dia;
	}

	public String getPuntos(){
		return this.puntos;
	}
	public int getMes() {
		return this.mes;
	}

	public int getAno() {
		return this.ano;
	}

	public String getHora(){
		return this.hora;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido(){
		return this.apellido;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
}
