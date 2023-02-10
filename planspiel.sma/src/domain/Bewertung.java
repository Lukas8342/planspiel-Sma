package domain;

public class Bewertung {
	public int getVertraulichkeit() {
		return vertraulichkeit;
	}

	public void setVertraulichkeit(int vertraulichkeit) {
		this.vertraulichkeit = vertraulichkeit;
	}

	public int getIntegrität() {
		return integrität;
	}

	public void setIntegrität(int integrität) {
		this.integrität = integrität;
	}

	public int getVerfügbarkeit() {
		return verfügbarkeit;
	}

	public void setVerfügbarkeit(int verfügbarkeit) {
		this.verfügbarkeit = verfügbarkeit;
	}

	public int getFinanzen() {
		return finanzen;
	}

	public void setFinanzen(int finanzen) {
		this.finanzen = finanzen;
	}

	public int getWahrnehmung() {
		return wahrnehmung;
	}

	public void setWahrnehmung(int wahrnehmung) {
		this.wahrnehmung = wahrnehmung;
	}

	int vertraulichkeit = 0;
	int integrität = 0;
	int verfügbarkeit = 0;
	int finanzen = 0;
	int wahrnehmung = 0;

	int[][] c1szen = { { 1, 1, 0, 0, 0, 0, -1, -1 }, { 1, 1, 0, 0, 0, 1, 0, -1 }, { -1, -1, -1, 0, 1, 0, -1, 0 }, // Geschäftsführer
			{ 1, 1, 0, 0, 0, 1, 1, -1 }, { 0, 0, 1, 0, 1, 1, 0, -1 }, { 1, -1, 0, 0, 0, 0, -1, -1 },
			{ 1, -1, 0, 0, 0, -1, 0, -1 }, { -1, -1, -1, 0, 1, -1, -1, 0 }, { 1, 1, 0, 0, 0, 0, 1, -1 },
			{ 0, 0, 1, 0, 1, 0, 1, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 1, 0, -1 },
			{ -1, -1, -1, 0, 0, 1, -1, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 1, -1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, -1, -1, 0, 0, 0, -1, 0 },
			{ 0, 1, 0, 0, 0, 1, 0, -1 }, { 0, 0, 1, 0, 1, 0, 1, -1 }, { 1, 1, 0, 0, 0, 1, 0, -1 },
			{ 0, 0, 0, 0, 0, -1, 0, 0 }, { -1, -1, 0, 0, 1, 0, -1, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 1, 0 } };

	int[][] c2szen = { { 1, 0, 1, 0, 0, -1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1 }, // ISMS
			{ 1, 1, 1, 1, 0, 0, 1, 1, 0 }, { 1, 1, 1, 1, 0, -1, 1, 1, -1 }, { 0, 0, 1, 1, 0, 0, 1, 1, -1 },
			{ 0, 0, 1, 0, 0, 0, 1, 0, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, -1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { 0, 0, 0, 0, 0, -1, 0, 0, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, -1 }, { 0, 0, 0, 0, 0, -1, 0, 0, -1 },
			{ 0, 0, 0, 1, -1, -1, 0, 1, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, 0, 0, 0, 0, -1, -1, 1 },
			{ 1, 1, 0, 0, 0, 0, 1, 1, -1 }, { 1, 1, 0, 0, 0, 0, 1, 0, -1 }, { 1, 0, 1, 1, 1, 0, 1, 1, -1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, 0, 0, -1, 0, 0, -1, 0, 1 }, { 1, 0, 1, 1, 1, 0, 1, 1, -1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, -1 } };

	int[][] c3szen = { { -1, -1, 1, 1, 1, -1 }, { 0, -1, 0, 0, 0, 0 }, { 0, -1, -1, 0, 0, 0 }, // Datenschutzbeauftragter
			{ 0, -1, 1, 1, 0, -1 }, { -1, -1, 1, 1, 0, -1 }, { 0, 1, 1, 1, -1, 0 }, { 0, 0, 0, 0, -1, 0 },
			{ 0, -1, -1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0 }, { 1, 0, 1, 1, -1, 0 }, { 0, 0, 1, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 0 }, { 0, -1, -1, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0 }, { 1, -1, 1, 0, -1, 0 },
			{ 0, 1, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0, 0 }, { 0, -1, -1, 0, 0, 0 }, { 0, 1, 1, 1, 1, -1 },
			{ 1, 1, -1, -1, -1, -1 }, { 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 0 }, { 0, -1, -1, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0 }, { 1, 1, 0, 0, 0, -1 } };

	int[][] c4szen = { { 0, 0, 0, 1, 1, 0, -1 }, { 1, -1, -1, -1, -1, -1, 0 }, { -1, 1, 1, 0, 0, -1, 0 }, // HOHR
			{ 0, 0, 0, 0, 0, 0, 0 }, { 1, -1, -1, 1, 1, 1, -1 }, { 0, 0, 0, 1, 1, 0, -1 }, { 1, -1, -1, -1, -1, -1, 0 },
			{ -1, 1, 1, 0, 0, -1, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 1, -1, -1, 1, 1, 1, -1 }, { 0, 0, 0, 1, 1, 0, -1 },
			{ 1, -1, -1, -1, -1, -1, 0 }, { -1, 1, 1, 0, 0, -1, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
			{ 1, -1, -1, 1, 1, 1, -1 }, { 0, 0, 0, 1, 1, 0, -1 }, { 1, -1, -1, -1, -1, -1, 0 },
			{ -1, 1, 1, 0, 0, -1, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 1, -1, -1, 1, 1, 1, -1 }, { 0, 0, 0, 1, 1, 0, -1 },
			{ 1, -1, -1, -1, -1, -1, 0 }, { -1, 1, 1, 0, 0, -1, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
			{ 1, -1, -1, 1, 1, 1, -1 } };

	int[][] c5szen = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, -1, -1, -1, -1, -1 }, // HOM
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, 1, -1, 1, -1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1, -1, -1, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ -1, 1, 0, 0, -1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, -1, -1, -1, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, 1, 0, 0, -1, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, -1, 0, 0, -1, -1, -1, -1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, 1, 0, 0, -1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, -1, -1 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0 }

	};

	public int[][] getC1szen() {
		return c1szen;
	}

	public int[][] getC2szen() {
		return c2szen;
	}

	public int[][] getC3szen() {
		return c3szen;
	}

	public int[][] getC4szen() {
		return c4szen;
	}

	public int[][] getC5szen() {
		return c5szen;
	}
}
