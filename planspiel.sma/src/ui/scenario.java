package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

import domain.Bewertung;
import domain.Rolle;
import facade.gamecontroller;

public class Scenario {
	Bewertung bewerte = new Bewertung();
	gamecontroller game = new gamecontroller();
	int textSpeed = 25;
	Scanner scan = new Scanner(System.in);
	String[] teilszenarien;

	public void start(String unternehmensbeschreibung, String startszenario, ArrayList<Rolle> rollen, int scenarioId,
			int rundenanzahl, int textSpeed) { // Anzahl
		// teilrunden
		this.textSpeed = textSpeed;
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

	public void round(ArrayList<Rolle> rollen, int scenarioId, int rundenanzahl) {
		for (int runde = 0; runde < rundenanzahl; runde++) {
			for (int i = 0; i < rollen.size(); i++) {
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
		last(scenarioId);
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

	public void last(int id) {
		HashMap<Integer, String> map = new HashMap<>();

		map.put(0,
				"In einem solchen Falle eines Cyber-Angriffs ist es wichtig, alle betroffenen Kunden und Behörden\r\n"
						+ "schnellstmöglich zu informieren. Eine umfassende Untersuchung, bestehend aus Forensik und\r\n"
						+ "Penetrationstests, ist notwendig, um die Ursache des Angriffs zu ermitteln und eventuelle\r\n"
						+ "Schwachstellen im System zu beheben. Dieses Vorgehen ist wichtig, um sicherzustellen, dass ein\r\n"
						+ "solcher Angriff in Zukunft verhindert wird und die Datensicherheit für alle Beteiligten gewährleistet\r\n"
						+ "bleibt.\r\n" + "");
		map.put(5, "Um das Netzwerk und die Daten des Unternehmens zu schützen, ist es von größter Bedeutung,\r\n"
				+ "strenge und detaillierte Zugriffsberechtigungen zu überprüfen und festzulegen. Eine effektive\r\n"
				+ "Methode, um das Risiko zu minimieren, ist die Verwendung von Multifaktor-Authentifizierung, die\r\n"
				+ "eine zusätzliche Sicherheitsebene bereitstellt. Eine enge Kooperation mit externen\r\n"
				+ "Sicherheitsexperten kann auch dazu beitragen, dass mögliche Schwachstellen rechtzeitig erkannt\r\n"
				+ "und behoben werden. Es ist wichtig, auch die Versionskontrolle von Softwareupdates im Auge zu\r\n"
				+ "behalten, um sicherzustellen, dass alle Systeme stets auf dem neuesten Stand sind und somit vor\r\n"
				+ "potenziellen Angriffen geschützt werden.");
		map.put(10, "Um in der Zukunft solche Vorfälle zu vermeiden sollte Untersucht werden wie der Administrator\r\n"
				+ "Zugang zu den Bitcoins erlangt hat und ob das auf eine Sicherheitslücke im System zurückzuführen\r\n"
				+ "ist. Nach Abschluss der Untersuchung sollte das Ergebnis den Kunden und der Öffentlichkeit\r\n"
				+ "kommuniziert werden.");
		map.put(15, "Um in der Zukunft solche Vorfälle zu vermeiden sollte Untersucht werden wie der Administrator\r\n"
				+ "Zugang zu den Bitcoins erlangt hat und ob das auf eine Sicherheitslücke im System zurückzuführen\r\n"
				+ "ist. Nach Abschluss der Untersuchung sollte das Ergebnis den Kunden und der Öffentlichkeit\r\n"
				+ "kommuniziert werden.");
		map.put(20, "Um die Ursache eines möglichen Cyber-Sicherheitsvorfalls zu ermitteln, ist es notwendig, einen\r\n"
				+ "Forensik- und Pentest durchzuführen. Diese Tests helfen dabei, mögliche Schwachstellen im System\r\n"
				+ "aufzudecken und zu beheben. Darüber hinaus wäre es auch sinnvoll, die Mitarbeiter in einer\r\n"
				+ "Schulung zum Thema IT-Sicherheit zu unterstützen. Dadurch kann das Wissen und die Sensibilität für\r\n"
				+ "Cyber-Sicherheitsrisiken gestärkt werden, um zukünftigen Vorfällen vorzubeugen.");

		System.out.println("");
		System.out.println("");
		System.out.println("");
		print("Spiel beendet! Ihre Statisik: ");
		print("");
		print("5 ist die Höchstpunktzahl, -5 die niedrigste");
		printStats();
		String abschluss = map.get(id);
		System.out.println("");
		print(abschluss);

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
