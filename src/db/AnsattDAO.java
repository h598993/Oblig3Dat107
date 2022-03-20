package db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AnsattDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");

	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			Ansatt a1 = em.find(Ansatt.class, id);
			return a1;
		} finally {
			em.close();
		}
	}

	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukernavn LIKE :brukernavn ",
					Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			return query.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("NoResultException: Kunne ikke finne noen med det brukernavnent i databasen ");

		} finally {
			em.close();
		}
		return null;

	}

	public List<Ansatt> finnAlleAnsatte() {

		EntityManager em = emf.createEntityManager();

		try {

			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
			return query.getResultList();

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public boolean endreStilling(int id, String stilling) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt a1 = em.find(Ansatt.class, id);
			a1.setStilling(stilling);

			tx.commit();
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

		return false;
	}

	public boolean endreLonn(int id, BigDecimal lonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt a1 = em.find(Ansatt.class, id);
			a1.setMaanedslonn(lonn);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {

		}

		return true;
	}

	public Ansatt leggTilAnsatt(String brukernavn, String fornavn, String etternavn,
			LocalDateTime ansettelsesdato, String stilling, BigDecimal maanedslonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt a1 = new Ansatt();
			a1.setBrukernavn(brukernavn);
			a1.setFornavn(fornavn);
			a1.setEtternavn(etternavn);
			a1.setAnsettelsesdato(ansettelsesdato);
			a1.setStilling(stilling);
			a1.setMaanedslonn(maanedslonn);
			em.persist(a1);
			tx.commit();
			return a1;
			
		} catch (Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

		return null;
	}

}
