package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import domain.Rolle;
import facade.Gamecontroller;

public class Userinterface {
	Scanner scan = new Scanner(System.in);
	private int textSpeed = 25;
	private int rundenanzahl = 3;
	private int spieleranzahl = 3;
	Gamecontroller game = new Gamecontroller();
	Scenario szen = new Scenario();
	private String message;
	ArrayList<Rolle> currentroles = new ArrayList<Rolle>();
	boolean bool = true;

	public void startGame() {
		loadSettings();
		print("Willkommen !");
		while (bool) {
			System.out.println("");
			message = "1. Spiel starten\n2. Einstellungen\n3. Autoren\n4. Hilfe\n5. Beenden";
			print(message);
			int wahl = validateInput(message, x -> x >= 1 && x <= 5);
			switch (wahl) {
			case 1:
				System.out.println("____________________");
				print("Spiel starten !");
				game.createRoles();
				assignRoles();
				playScenario();
				newGame();
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
				hilfe();
				break;
			case 5:
				System.out.println("_______________________");
				print("Spiel beenden, tschüss! ");
				bool = false;
			}
		}

	}

	public void loadSettings() {
		try (BufferedReader br = new BufferedReader(new FileReader("./ordner/settings.csv"))) {
			String line = br.readLine();
			String[] values = line.split(",");
			this.textSpeed = Integer.parseInt(values[0].trim());
			this.rundenanzahl = Integer.parseInt(values[1].trim());
			this.spieleranzahl = Integer.parseInt(values[2].trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeSettings(int textSpeed, int rundenanzahl, int spieleranzahl) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("./ordner/settings.csv"));
			writer.write(textSpeed + "," + rundenanzahl + "," + spieleranzahl);
			writer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben der Einstellungen: " + e.getMessage());
		}
	}

	public void newGame() {
		System.out.println("");
		print("1. Weiter spielen");
		print("2. Spiel Beenden");
		int wahl = validateInput(message, x -> x >= 1 && x <= 2);
		switch (wahl) {
		case 1:
			game.createRoles();
			currentroles = new ArrayList<Rolle>();
			game = new Gamecontroller();
			szen = new Scenario();
			break;
		case 2:
			bool = false;
			break;
		}

	}

	public void settings() {
		print("1. Text-speed");
		print("2. Spieler anzahl");
		print("3. Runden anzahl");
		print("4. zurück");
		int wahl = validateInput("settings", x -> x >= 1 && x <= 4);
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
		writeSettings(textSpeed, rundenanzahl, spieleranzahl);

	}

	public void changeRoundCount() {
		print("Runden anzahl: " + rundenanzahl);
		System.out.println();
		print("Eingabe: ");
		rundenanzahl = Integer.parseInt(scan.nextLine());
		writeSettings(textSpeed, rundenanzahl, spieleranzahl);

	}

	public void changeTextSpeed() {
		print("Text-Speed: " + textSpeed);
		System.out.println();
		print("Eingabe: ");
		textSpeed = Integer.parseInt(scan.nextLine());
		writeSettings(textSpeed, rundenanzahl, spieleranzahl);
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
			szen.start(
					"Ihr Arbeitgeber hat sich auf den Betrieb von Supermärkten und Lebensmittelgeschäften spezialisiert.\r\n"
							+ "Es werden Filialen in ganz Deutschland betrieben und ihr Unternehmen ist vor allem durch sein\r\n"
							+ "breites Angebot an Bio-Lebensmitteln bekannt. Das Unternehmen legt großen Wert auf\r\n"
							+ "Nachhaltigkeit und setzt sich für den Schutz der Umwelt und den fairen Handel mit Produzenten ein.\r\n"
							+ "",
					"Nach einem Cyber-Angriff sind gestohlene Daten von einigen ihrer Kunden im Darknet aufgetaucht.\r\n"
							+ "Die Daten stammen aus Marktumfragen und enthalten Name, Anschrift sowie Einkaufspräferenzen\r\n"
							+ "der Kunden. Die unbekannten Täter sorgen für Verunsicherung bei ihren Kunden, Mitarbeitenden\r\n"
							+ "und Lieferanten.",
					currentroles, 0, rundenanzahl, textSpeed);
			break;
		case 2:
			szen.start(
					"SolarWinds ist ein US-amerikanisches Unternehmen, das Software für Unternehmen entwickelt, um\r\n"
							+ "ihre Netzwerke, Systeme und Informationstechnologie-Infrastruktur zu verwalten. Es hat seinen\r\n"
							+ "Hauptsitz in Austin, Texas. Das Unternehmen war von Mai 2009 bis Ende 2015 und erneut von\r\n"
							+ "Oktober 2018 an der Börse gelistet. Ende 2020 hatte es etwa 300.000 Kunden, 2021 einen Umsatz\r\n"
							+ "von 718 Millionen USD und ca. 900 Mitarbeiter.\r\n" + "",
					"Der Hersteller einer von uns eingesetzten Software wurde gehackt.\r\n"
							+ "Der Hersteller betreibt die Software in seinem eigenen Rechenzentrum, in dem ein\r\n"
							+ "entsprechender Sicherheitsvorfall stattgefunden hat.\r\n" + "",
					currentroles, 5, rundenanzahl, textSpeed);
			break;
		case 3:
			szen.start(
					"Ihre Firma die “Ökostromgruppe Freiburg” betreibt Anlagen zur Stromversorgung\r\n"
							+ "auf Basis erneuerbarer Energien. Das Ziel des Unternehmens besteht darin, eine\r\n"
							+ "umweltfreundliche und zuverlässige Energiequelle anzubieten, auf die sich die\r\n"
							+ "Kunden verlassen können.\r\n" + "",
					"Aufgrund von Hackerangriffen und eine darauffolgende Störung des"
							+ " Sataliteninternets fallen rund 5.800 ihrer Windkraftanlagen aus.",
					currentroles, 10, rundenanzahl, textSpeed);
			break;
		case 4:
			szen.start(
					"Sie sind Mitarbeiter bei einem Unternehmen, das unter anderem Server und Software für den Handel\r\n"
							+ "mit Kryptowährungen anbietet. Da ihre Branche in der Vergangenheit häufig mit negativen\r\n"
							+ "Schlagzeilen rund um Hackerangriffe zu tun hatte, ist es für ihr Unternehmen besonders wichtig,\r\n"
							+ "einfach auszuführende, aber sichere Dienste anzubieten.\r\n" + "",
					"Es wurde eine unrechtmäßige Transaktion über 29,6 Bitcoins bemerkt. Die Bitcoins wurden von dem\r\n"
							+ "Konto des Unternehmens auf eine unbekannte Adresse überwiesen. Es herrscht aktuell große\r\n"
							+ "Verunsicherung darüber, wer diese Transaktion veranlasst hat und mit welchen Mitteln. Die erste\r\n"
							+ "Vermutung fällt auf einen ehemaligen IT-Administrator.\r\n" + "",
					currentroles, 15, rundenanzahl, textSpeed);
			break;
		case 5:
			szen.start("Der Geschäftsbereich ihrer Firma „Ubiquiti Networks Inc.“ ist die Herstellung von\r\n"
					+ "Netzwerkkomponenten. Neben dem ursprünglichen Produkte WLAN-Adapter, umfasst ihr Sortiment\r\n"
					+ "seit 2007 nun auch weitere Netzwerkkomponenten darunter WLAN- Router, Accesspoints und vieles\r\n"
					+ "mehr. ",
					"Ihr Unternehmen wird Opfer einer Cyberattacke bei dem sich Angreifer als CEO ausgeben. In den\r\n"
							+ "Folgen des Angriffs wurden Überweisung von Geldern in Höhe von 46,7 Millionen Dollar\r\n"
							+ "durchgeführt. ",
					currentroles, 20, rundenanzahl, textSpeed);
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

	public void hilfe() {
		message = "1. Rollenbeschreibungen\n2. zurück";
		print(message);
		int wahl = validateInput(message, x -> x >= 1 && x <= 2);
		switch (wahl) {
		case 1:
			System.out.println("");
			System.out.println("Die Rolle des Geschäftsführers\r\n" + "");
			System.out.println("-----------------------------------------");
			System.out.println(
					"Du bist Geschäftsführer und hast eine beeindruckende Karriere aufgebaut. Mit Abschluss vieler\r\n"
							+ "Fortbildungen und Führungsjobs verfügst Du über umfassende Fachkenntnisse und\r\n"
							+ "Führungserfahrung.");
			System.out.println("");
			System.out.println("");
			System.out.println("Die Rolle des Informationssicherheitsbeauftragten\r\n" + "");
			System.out.println("-----------------------------------------");
			System.out.println(
					"Du bist Informationssicherheitsbeauftragte und hast eine umfangreiche Karriere im Bereich der\r\n"
							+ "Informationssicherheit aufgebaut. Mit durch deinen Hintergrund, verfügst Du über tiefgreifendes\r\n"
							+ "Wissen und Erfahrung in der Umsetzung von Informationssicherheitsstrategien und -maßnahmen.\r\n"
							+ "");
			System.out.println("");
			System.out.println("");
			System.out.println("Die Rolle des Datenschutzbeauftragten\r\n" + "");
			System.out.println("-----------------------------------------");
			System.out.println(
					"Du bist ein Datenschutzbeauftragter mit absolviertem Studium der Rechtswissenschaften mit\r\n"
							+ "Schwerpunkt Datenschutzrecht. Nach einigen Jahren praktischer Erfahrung in einer Kanzlei, die sich\r\n"
							+ "auf Datenschutzrecht spezialisiert hat, wechselte Du in die Industrie und übernimmst dort die Rolle\r\n"
							+ "des Datenschutzbeauftragten in einem großen Unternehmen.");
			System.out.println("");
			System.out.println("");
			System.out.println("Die Rolle des Leiters der Personalabteilung\r\n" + "");
			System.out.println("-----------------------------------------");
			System.out.println(
					"Du bist der Head of Human Resources verfügst über umfangreiche Erfahrungen im Personalwesen\r\n"
							+ "und in der Führung von Teams. Mit einem Abschluss in BWL, hat er sich auf die Entwicklung und\r\n"
							+ "Umsetzung von Personalstrategien spezialisiert.\r\n" + "");
			System.out.println("");
			System.out.println("");
			System.out.println("Die Rolle des Leiters der Marketingabteilung\r\n" + "");
			System.out.println("-----------------------------------------");
			System.out
					.println("Du bist der Head of Marketing und Du besitzt breite Erfahrungen in der Gestaltung und\r\n"
							+ "Umsetzungen von erfolgreichen Marketingstrategien. Mit deinem Abschluss in Marketing hast Du\r\n"
							+ "ein tiefes Verständnis für die Bedürfnisse des Marktes und die wichtigsten Trends in der Branche.\r\n"
							+ "");
			System.out.println("");
			System.out.println("");

			break;
		case 2:
			break;
		}
	}

}
