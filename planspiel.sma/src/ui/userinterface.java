package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import domain.Rolle;
import facade.gamecontroller;

public class userinterface {
	Scanner scan = new Scanner(System.in);
	private int textSpeed = 25;
	private int rundenanzahl = 3;
	private int spieleranzahl = 2;
	gamecontroller game = new gamecontroller();
	scenario szen = new scenario();
	private String message;
	ArrayList<Rolle> currentroles = new ArrayList<Rolle>();

	public void startGame() {
		game.createRoles();
		boolean bool = true;
		print("Willkommen !");
		while (bool) {
			System.out.println("");
			message = "1. Spiel starten\n2. Einstellungen\n3. Autoren\n4. Beenden";
			print(message);
			int wahl = validateInput(message, x -> x >= 1 && x <= 4);

			switch (wahl) {
			case 1:
				System.out.println("____________________");
				print("Spiel starten !");
				assignRoles();
				playScenario();
				break;
			case 2:
				System.out.println("____________________");
				print("Einstellungen");
				settings();
				break;
			case 3:
				authoren();
				break;
			case 4:
				System.out.println("_______________________");
				print("Spiel beenden, tschüss! ");
				bool = false;
			}
		}

	}

	public void settings() {
		print("1. Text-speed");
		print("2. Spieler anzahl");
		print("3. Runden anzahl");
		print("4. zurück");
		int wahl = Integer.parseInt(scan.nextLine());
		switch (wahl) {
		case 1:
			changeTextSpeed();
			break;
		case 2:
			changePlayerCount();
			break;
		case 3:
			changeRoundCount();
			break;
		case 4:
			break;
		}
	}

	public void changePlayerCount() {
		print("Spieler anzahl: " + spieleranzahl);
		System.out.println();
		print("Eingabe: ");
		spieleranzahl = Integer.parseInt(scan.nextLine());

	}

	public void changeRoundCount() {
		print("Runden anzahl: " + rundenanzahl);
		System.out.println();
		print("Eingabe: ");
		rundenanzahl = Integer.parseInt(scan.nextLine());

	}

	public void changeTextSpeed() {
		print("Text-Speed: " + textSpeed);
		System.out.println();
		print("Eingabe: ");
		textSpeed = Integer.parseInt(scan.nextLine());
	}

	public void playScenario() {
		message = "Sie können nun aus 5 verschiedenen Szenarien wählen\n"
				+ "=============================================\n" + "1. Daten im Darknet veröffentlicht\n"
				+ "---------------------------------------------\n" + "2. Ein Zulieferer wurde gehackt\n"
				+ "---------------------------------------------\n" + "3. Gestörte Windkraftanlagen\n"
				+ "---------------------------------------------\n" + "4. Diebstahl von 30 Bitcoin\n"
				+ "---------------------------------------------\n" + "5. CEO Fraud\n"
				+ "---------------------------------------------\n";

		print(message);

		int wahl = validateInput(message, x -> (x > 0 && x <= 5));

		switch (wahl) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			szen.start(
					"Ihre Firma die Ökostromgruppe Freiburg betreibt Anlagen zur Stromversorgung" + '\n'
							+ "auf Basis erneuerbarer Energien. Das Ziel des Unternehmens besteht darin, eine" + '\n'
							+ "umweltfreundliche und zuverlässige Energiequelle anzubieten, auf die sich die",
					"Aufgrund von Hackerangriffen und eine darauffolgende Störung des"
							+ " Sataliteninternets fallen rund 5.800 ihrer Windkraftanlagen aus.",
					currentroles, 10, rundenanzahl);
			break;
		case 4:
			break;
		case 5:
			break;
		}
	}

	public void assignRoles() {
		String[] roles = { "Geschäftsleitung", "Informationssicherheitsbeauftragter", "Datenschutzbeauftragter",
				"Head of Human Resources", "Head of Marketing" };
		List<Integer> choosen = new ArrayList<>();
		List<String> roleList = new ArrayList<>(Arrays.asList(roles));
		// message = "\nMit wie vielen Spielern möchten sie Spielen? (2 - 5)";
		// print(message);
		// int anzahl = validateInput(message, x -> x >= 2 && x <= 6);

		for (int i = 0; i < spieleranzahl; i++) {
			System.out.println("\nSpieler " + (i + 1) + ", wählen sie ihren Namen! ");
			print("Name: ");
			String name = scan.nextLine();

			message = "\nWählen Sie Ihre Rolle\n";
			for (int n = 0; n < roleList.size(); n++)
				if (!choosen.contains(n))
					message += (n + 1) + ". " + roles[n] + "\n";
			int wahl = 0;
			System.out.println(message);
			wahl = validateInput(message, x -> (x > 0 && x <= 6) && !choosen.contains(x - 1)) - 1;

			choosen.add(wahl);
			game.asignRoles(name, wahl + 1);
		}
		printRoles();
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

	private void printRoles() {
		System.out.println("");
		print("Ihre gewählten Namen und Rollen: ");
		currentroles = game.getRoles();
		for (Rolle i : currentroles) {
			if (i.getSpielername() != null) {
				System.out.println(i.getId() + ": " + i.getName() + " - " + i.getSpielername());
			}
		}
		System.out.println("");
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

	public void authoren() {
		System.out.println();
		System.out.println("Authoren:");
		print("Lukas Siegle");
		print("Carlo Bauer");
		print("Sven Schlicksupp");
		print("Vincent Leber");
		print("Mike Ullrich");
		print("Petar");
		System.out.println();
		print("Sma - Planspiel WS22/23");

	}

}
