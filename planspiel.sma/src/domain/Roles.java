package domain;

import java.util.ArrayList;

public class Roles {

	ArrayList<Rolle> allRoles = new ArrayList<Rolle>();

	public void asign(int a, String name) {
		for (Rolle rolle : allRoles) {
			if (a == rolle.getId()) {
				rolle.setSpielername(name);
			}
		}
	}

	public ArrayList<Rolle> getRoles() {
		return allRoles;
	}

	public void createAllRoles() {
		allRoles.add(createRoleGeschäftsleitung());
		allRoles.add(createRoleInformationssicherheitsbeauftragter());
		allRoles.add(createRoleDatenschutzbeauftragter());
		allRoles.add(createRoleHoHR());
		allRoles.add(createRoleHoM());
	}

	public Rolle createRoleGeschäftsleitung() {
		ArrayList<String> antwortenGeschäftsleitung = new ArrayList<String>();
		antwortenGeschäftsleitung.add(
				"In IT-Sicherheitsmaßnahmen sowie Mitarbeiter Schulungen investieren, damit die Eintrittswahrscheinlichkeit für einen Vorfall in Zukunft sinkt. ");
		antwortenGeschäftsleitung
				.add("IT-Forensik-Firma beauftragen, die die Ursachen des Angriffs untersucht und Beweise sammelt. ");
		antwortenGeschäftsleitung.add("Schadenersatz zahlen. ");
		antwortenGeschäftsleitung.add("Meldung an die Aufsichtsbehörden sowie die betroffenen Kunden. ");
		antwortenGeschäftsleitung
				.add("Pressemitteilung herausgeben, die das Unternehmen in ein positives Licht rückt. ");
		antwortenGeschäftsleitung.add("Einen Krisenplan aktivieren. ");
		antwortenGeschäftsleitung.add("Eine Belohnung für Hinweise auf die Täter aussetzen.");
		antwortenGeschäftsleitung.add("Keine Handlung unternehmen.");
		return new Rolle("Geschäftsleitung", antwortenGeschäftsleitung, 1);
	}

	public Rolle createRoleInformationssicherheitsbeauftragter() {
		ArrayList<String> antwortenInformationssicherheitsbeauftragter = new ArrayList<String>();
		antwortenInformationssicherheitsbeauftragter.add("Mitarbeiterschulungen");
		antwortenInformationssicherheitsbeauftragter.add("Simulierte Angriffe");
		antwortenInformationssicherheitsbeauftragter.add("Überprüfung von Zugriffs- und Authentifizierungsprotokollen");
		antwortenInformationssicherheitsbeauftragter.add(
				"Beauftragen der Einführung einer weiteren Kontrollinstanz, wie z.B. ein Mehraugenprinzip bei Finanztransaktionen.");
		antwortenInformationssicherheitsbeauftragter.add(
				"Verschärfte Einschränkungen bei Mitarbeitern, wenn es um Finanztransaktionen geht, um sicher zu gehen, dass nur autorisierte Personen solche tätigen können.");
		antwortenInformationssicherheitsbeauftragter.add(
				"Überwachung von E-Mail-Kommunikation und Verhinderung unautorisierter Überweisungen (Datenschutztechnisch bedenklich!)");
		antwortenInformationssicherheitsbeauftragter.add(
				"Implementierung von Technologien wie Multi-Faktor-Authentifizierung und Verschlüsselung, um die Sicherheit von Finanztransaktionen zu erhöhen.");
		antwortenInformationssicherheitsbeauftragter.add(
				"Überwachung und Überprüfung von Drittanbietern, die Zugang zu sensiblen Daten und Systemen haben.");
		antwortenInformationssicherheitsbeauftragter.add("Keine Handlung unternehmen.");

		return new Rolle("Informationssicherheitsbeauftragter", antwortenInformationssicherheitsbeauftragter, 2);
	}

	public Rolle createRoleDatenschutzbeauftragter() {
		ArrayList<String> antwortenDatenschutzbeauftragter = new ArrayList<String>();
		antwortenDatenschutzbeauftragter
				.add("Eine Mitteilung an die Öffentlichkeit geben, dass die Kundendaten nicht gefährdet sind. ");
		antwortenDatenschutzbeauftragter
				.add("Eine Schulung für Mitarbeiter zum Thema Datenschutz durchführen (Kosten: 5.000 Euro).");
		antwortenDatenschutzbeauftragter.add(
				"Das gesamte Datenbanksystem auf Sicherheitslücken und Schwachstellen testen lassen (Penetrationstest) (Kosten: 25.000 Euro). ");
		antwortenDatenschutzbeauftragter.add(
				"Ein Sicherheits Audits durchführen, um potenzielle Bedrohungen frühzeitig zu erkennen und zu beheben.");
		antwortenDatenschutzbeauftragter.add("Eine neue Datenschutzrichtlinie erstellen und implementieren. ");
		antwortenDatenschutzbeauftragter.add("Keine Aktion. ");
		return new Rolle("Datenschutzbeauftragter", antwortenDatenschutzbeauftragter, 3);
	}

	public Rolle createRoleHoHR() {
		ArrayList<String> antwortenHoHR = new ArrayList<String>();
		antwortenHoHR.add("Neuen Manager einstellen. ");
		antwortenHoHR.add("Manager feuern. ");
		antwortenHoHR.add("Verantwortlichen feuern. ");
		antwortenHoHR.add("Security-Awarness-Schulung halten ");
		antwortenHoHR.add("Schulung zu Arbeitsschutzrichtlinien halten. ");
		antwortenHoHR.add("Arbeitsschutzmaßnahmen umsetzen. ");
		antwortenHoHR.add("Keine Handlung unternehmen. ");
		return new Rolle("Head of Human Resources", antwortenHoHR, 4);
	}

	public Rolle createRoleHoM() {
		ArrayList<String> antwortenHoM = new ArrayList<String>();
		antwortenHoM.add("Keine Aktion durchführen.");
		antwortenHoM.add("Abgabe einer öffentlichen offiziellen Erklärung zum Cyberangriff.");
		antwortenHoM.add("Öffentliche Positionierung gegen die Erfüllung von Forderungen der Angreifer.");
		antwortenHoM.add("Öffentliche Positionierung zur Erfüllung von Forderungen der Angreifer.");
		antwortenHoM.add("Informierung der Kunden über Cyberschwachstellen.");
		antwortenHoM.add("Besänftigung der Kunden.");
		antwortenHoM.add("Überwachung der öffentlichen Meinung im Zusammenhang mit dem Vorfall.");
		antwortenHoM.add("Überwachung von langfristigen Auswirkungen im Kundenvertrauen.");
		return new Rolle("Head of Marketing", antwortenHoM, 5);
	}

}
