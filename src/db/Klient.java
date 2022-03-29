package db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Klient {

	public static void main(String[] args) {

		kjoer();

	}

	private static void kjoer() {
		AnsattDAO db = new AnsattDAO();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("************************************************************************************");
			System.out.println("Hva vil du gjøre?");
			System.out.println(
					"(V)is alle Ansatte, (F)inn ansatt, (L)egg til ansatt, (P)rint ansatte ved avdeling , (A)vslutt");
			char respons = Character.toUpperCase(scanner.nextLine().charAt(0));

			switch (respons) {

			case 'V':
				skrivUtAlleAnsatte();
				break;

			case 'F':
				System.out.println("Skriv brukernavn på ansatt");
				String respons2 = scanner.nextLine();
				System.out.println(db.finnAnsattMedBrukernavn(respons2));
				break;

			case 'L':
				System.out.println("Skriv brukernavn");
				String brukernavn = scanner.nextLine();
				System.out.println("Skriv fornavn");
				String fornavn = scanner.nextLine();
				System.out.println("Skriv etternavn");
				String etternavn = scanner.nextLine();
				System.out.println("Skriv stilling");
				String stilling = scanner.nextLine();
				System.out.println("Velg lønn");
				BigDecimal lonn = new BigDecimal(scanner.nextLine());
				System.out.println("Skriv inn avdelingskode");
				int avdkode = Integer.parseInt(scanner.nextLine());

				db.leggTilAnsatt(brukernavn, fornavn, etternavn, LocalDateTime.now(), stilling, lonn, avdkode);
				break;

			case 'P':
				System.out.println("Skriv inn avdelingskode");
				printAnsatteVedAvdeling(Integer.parseInt(scanner.nextLine()));
				break;

			case 'A':
				scanner.close();
				avslutt();
				break;

			default:
				System.out.println("Feil! Prøv igjen");
				break;
			}

		}
	}

	private static void avslutt() {
		System.out.println("Program avsluttet");
		System.exit(0);
	}

	public static void printAnsatteVedAvdeling(int avdId) {
		
		AvdelingDAO adb = new AvdelingDAO();

		Ansatt leder = adb.finnLeder(avdId);
		if (leder == null) {
			System.out.println("Denne avdelingen finnes ikke");
			return;
		}

		List<Ansatt> avdAnsatte = adb.avdeling(avdId);

		int lederIndx = -1;
		boolean funnet = false;

		for (int i = 0; i < avdAnsatte.size() && !funnet; i++) {
			if (avdAnsatte.get(i).getBrukerid() == leder.getBrukerid()) {
				lederIndx = i;
				funnet = true;
			}
		}
		avdAnsatte.remove(lederIndx); // fyller inn indeks
		avdAnsatte.add(0, leder);

		for (int i = 0; i < avdAnsatte.size(); i++) {

			if (leder.getBrukerid() == avdAnsatte.get(i).getBrukerid()) {

				System.out.println(leder.toString().toUpperCase());
			} else {
				System.out.println(avdAnsatte.get(i));
			}

		}

	}

	public static boolean endreAvdeling(int ansattid, int avdKode) {

		AnsattDAO db = new AnsattDAO();
		AvdelingDAO adb = new AvdelingDAO();
		Ansatt ansatt = db.finnAnsattMedId(ansattid);
		Ansatt leder = adb.finnLeder(ansatt.getAvdeling());

		if (ansatt.equals(leder)) {
			return false;
		}

		return db.endreAvdeling(ansattid, avdKode);
	}

	public static void skrivUtAlleAnsatte() {
		AnsattDAO db = new AnsattDAO();
		List<Ansatt> alleAnsatte = db.finnAlleAnsatte();
		for (int i = 0; i < alleAnsatte.size(); i++) {
			System.out.println(alleAnsatte.get(i));
		}

	}
}
