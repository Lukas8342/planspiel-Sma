package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import domain.Bewertung;
import domain.Rolle;
import facade.gamecontroller;

public class scenario {
	Bewertung bewerte = new Bewertung();
	gamecontroller game = new gamecontroller();
	int textSpeed = 25;
	Scanner scan = new Scanner(System.in);
	String[] teilszenarien;

	public void start(String unternehmensbeschreibung, String startszenario, ArrayList<Rolle> rollen, int scenarioId,
			int rundenanzahl) { // Anzahl
		// teilrunden
		// ?
		teilszenarien = game.getScenario(scenarioId);
		System.out.println("Ihr Unternehmen: -");
		System.out.println("----------------------------");
		print(unternehmensbeschreibung);
		System.out.println("============================");
		System.out.println("Runde 1:");
		System.out.println("Szenario -");
		System.out.println("----------------------------");
		print(startszenario);
		System.out.println("============================");
		System.out.println("");
		round(rollen, scenarioId, rundenanzahl);
	}

	int validate = 0;

	public void loadSettings() {
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Lukas\\eclipse-workspace\\planspiel.sma\\src\\ui\\settings.csv"))) {
			String line = br.readLine();
			String[] values = line.split(",");
			this.textSpeed = Integer.parseInt(values[0].trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void round(ArrayList<Rolle> rollen, int scenarioId, int rundenanzahl) {
		for (int runde = 0; runde < rundenanzahl; runde++) {
			for (int i = 1; i < rollen.size(); i++) { // Role Array fängt bei 1 an (Id 1-6)
				validate = i;
				if (rollen.get(i).getSpielername() != null) {
					print(rollen.get(i).getSpielername() + "(" + rollen.get(i).getName() + ") "
							+ "welche der folgenden Aktionen möchten sie durchführen? ");
					System.out.println("---------------------------------------------------------------------");
					for (int n = 0; n < rollen.get(i).getAnwortliste().size(); n++) {
						System.out.println((n + 1) + ". " + rollen.get(i).getAnwortliste().get(n));
					}

					int auswahl = validateInput("", x -> x >= 1 && x <= rollen.get(validate).getAnwortliste().size())
							- 1;

					bewertung(auswahl, rollen.get(i).getId(), scenarioId);
					rollen.get(validate).getAnwortliste().remove(auswahl);
				}
			}
			if (runde + 1 < rundenanzahl) {
				print("Runde " + (runde + 2) + ": ");
				pickNextScen(scenarioId);
				System.out.println("");
			}
		}
		last();
	}

	public void bewertung(int antwort, int rolle, int scenarioId) {

		switch (rolle) {
		case 1:
			int[][] j = bewerte.getC1szen(); // Geschäftsführer
			bewerte.setVertraulichkeit(bewerte.getVertraulichkeit() + j[scenarioId][antwort]);
			bewerte.setVerfügbarkeit(bewerte.getVerfügbarkeit() + j[scenarioId + 1][antwort]);
			bewerte.setFinanzen(bewerte.getFinanzen() + j[scenarioId + 2][antwort]);
			bewerte.setIntegrität(bewerte.getIntegrität() + j[scenarioId + 3][antwort]);
			bewerte.setWahrnehmung(bewerte.getWahrnehmung() + j[scenarioId + 4][antwort]);
			break;
		case 2:
			int[][] i = bewerte.getC2szen(); // ISMS
			bewerte.setVertraulichkeit(bewerte.getVertraulichkeit() + i[scenarioId][antwort]);
			bewerte.setVerfügbarkeit(bewerte.getVerfügbarkeit() + i[scenarioId + 1][antwort]);
			bewerte.setFinanzen(bewerte.getFinanzen() + i[scenarioId + 2][antwort]);
			bewerte.setIntegrität(bewerte.getIntegrität() + i[scenarioId + 3][antwort]);
			bewerte.setWahrnehmung(bewerte.getWahrnehmung() + i[scenarioId + 4][antwort]);
			break;
		case 3:
			int[][] n = bewerte.getC3szen(); // Datenschutzbeauftragter
			bewerte.setVertraulichkeit(bewerte.getVertraulichkeit() + n[scenarioId][antwort]);
			bewerte.setVerfügbarkeit(bewerte.getVerfügbarkeit() + n[scenarioId + 1][antwort]);
			bewerte.setFinanzen(bewerte.getFinanzen() + n[scenarioId + 2][antwort]);
			bewerte.setIntegrität(bewerte.getIntegrität() + n[scenarioId + 3][antwort]);
			bewerte.setWahrnehmung(bewerte.getWahrnehmung() + n[scenarioId + 4][antwort]);
			break;
		case 4:
			int[][] a = bewerte.getC4szen(); // HOHR
			bewerte.setVertraulichkeit(bewerte.getVertraulichkeit() + a[scenarioId][antwort]);
			bewerte.setVerfügbarkeit(bewerte.getVerfügbarkeit() + a[scenarioId + 1][antwort]);
			bewerte.setFinanzen(bewerte.getFinanzen() + a[scenarioId + 2][antwort]);
			bewerte.setIntegrität(bewerte.getIntegrität() + a[scenarioId + 3][antwort]);
			bewerte.setWahrnehmung(bewerte.getWahrnehmung() + a[scenarioId + 4][antwort]);
			break;
		case 5:
			int[][] b = bewerte.getC5szen(); // HOM
			bewerte.setVertraulichkeit(bewerte.getVertraulichkeit() + b[scenarioId][antwort]);
			bewerte.setVerfügbarkeit(bewerte.getVerfügbarkeit() + b[scenarioId + 1][antwort]);
			bewerte.setFinanzen(bewerte.getFinanzen() + b[scenarioId + 2][antwort]);
			bewerte.setIntegrität(bewerte.getIntegrität() + b[scenarioId + 3][antwort]);
			bewerte.setWahrnehmung(bewerte.getWahrnehmung() + b[scenarioId + 4][antwort]);
			break;
		}
	}

	public void pickNextScen(int id) {

		int[] stats = { bewerte.getVertraulichkeit(), bewerte.getVerfügbarkeit(), bewerte.getFinanzen(),
				bewerte.getIntegrität(), bewerte.getWahrnehmung() };

		int lowest = 10;
		int idx = 0;
		for (int i = 0; i < stats.length; i++) {
			if (teilszenarien[i] != null) {
				if (stats[i] < lowest) {
					lowest = stats[i];
					idx = i;
				}
			}
		}
		System.out.println("Szenario -");
		System.out.println("----------------------------");
		print(teilszenarien[idx]);
		System.out.println("============================");
		teilszenarien[idx] = null;

	}

	public void printStats() {
		System.out.println("");
		System.out.println("Vertraulichkeit: " + bewerte.getVertraulichkeit());
		System.out.println("Verfügbarkeit: " + bewerte.getVerfügbarkeit());
		System.out.println("Finanzen: " + bewerte.getFinanzen());
		System.out.println("Integrität: " + bewerte.getIntegrität());
		System.out.println("Wahrnehmung: " + bewerte.getWahrnehmung());
		System.out.println("");

	}

	public void last() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		print("Spiel beendet! Ihre Statisik: ");
		print("");
		print("5 ist die Höchstpunktzahl, -5 die niedrigste");
		printStats();
	}

	private void print(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
			try {
				Thread.sleep(textSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("");
	}

	private int validateInput(String messageToRepeat, Predicate<Integer> condition) {
		boolean valid = true;

		int value = -1;

		do {
			valid = true;
			try {
				System.out.print("\nEingabe: ");
				value = Integer.parseInt(scan.nextLine());
				if (!condition.test(value)) {
					System.out.println("\nBitte die Auswahl beachten!\n" + messageToRepeat);
					valid = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("Falsches Format!\n\n" + messageToRepeat);
				valid = false;
			}

		} while (!valid);
		return value;
	}

}
