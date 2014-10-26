package packManejoDB;

public class Tiempo {

	private int mes;
	private int dia;
	private int ano;
	private String tiempo;

	public Tiempo() {
		this(0, 0, 0, "No se han insertado minutos");
	}

	public Tiempo(int pDia, int pMes, int pAno, String pTiempo) {
		this.dia = pDia;
		this.mes = pMes;
		this.ano = pAno;
		this.tiempo = pTiempo;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
}
