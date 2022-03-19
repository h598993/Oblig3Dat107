package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Klient {

	public static void main(String[] args) {
		hentAnsatt(1);
		hentAlleAnsatte();
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

}
