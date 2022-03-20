package db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Klient {

	public static void main(String[] args) {
		AnsattDAO db = new AnsattDAO();
		
		Ansatt a1 = db.finnAnsattMedId(2);
		System.out.println(a1);
		
		Ansatt a2 = db.finnAnsattMedBrukernavn("");
		System.out.println(a2);
		
		skrivUtAlleAnsatte();
		
		// Endre Stilling
		System.out.println(db.endreStilling(1, "Tømrer"));
		Ansatt a3 = db.finnAnsattMedBrukernavn("sl");
		System.out.println(a3);
		
		// Endre lønn
		System.out.println("*****************************************");
		System.out.println(db.endreLonn(2,new BigDecimal(350000) ));
		skrivUtAlleAnsatte();
		
		System.out.println("**********************************");
		// legge til en ansatt
		System.out.println(db.leggTilAnsatt("bs", "Brannmann", "Sam", LocalDateTime.now(), "Brannmann", new BigDecimal(400000)));
		skrivUtAlleAnsatte();
		
		
		
		
		
		
		
	
		
	}
	
	

	public static void hentAnsatt(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager em = emf.createEntityManager();

		try {
			Ansatt a1 = em.find(Ansatt.class, id);
			System.out.println(a1);
		} catch (Throwable e) {
			System.out.println("Error: ");
			e.printStackTrace();

		} finally {
			em.close();
		}
	}

	public static void hentAlleAnsatte() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager em = emf.createEntityManager();

		try {
		TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a ", Ansatt.class);
			List<Ansatt> ansattliste = query.getResultList();
			
			for(int i = 0; i < ansattliste.size(); i++) {
				System.out.println(ansattliste.get(i));
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void skrivUtAlleAnsatte() {
		AnsattDAO db = new AnsattDAO();
		List<Ansatt> alleAnsatte = db.finnAlleAnsatte();
		for(int i = 0; i < alleAnsatte.size();i++) {
			System.out.println(alleAnsatte.get(i));
		}
		
	}
}
