package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AvdelingDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");

	public Avdeling finnAvdelingMedId(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Avdeling.class, id);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public Ansatt finnLeder(int avdId) {
		EntityManager em = emf.createEntityManager();

		Avdeling a1 = finnAvdelingMedId(avdId);

		try {
			return em.find(Ansatt.class, a1.getLeder());
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return null;

	}

	public List<Ansatt> avdeling(int avdId) {
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.avdeling = :avdId ",
					Ansatt.class);
			query.setParameter("avdId", avdId);
			return query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public Avdeling leggTilNyAvdeling(String navn, int leder) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		if (erIkkeLeder(leder)) {
			try {
				tx.begin();

				Avdeling a1 = new Avdeling();
				a1.setNavn(navn);
				a1.setLeder(leder);
				em.persist(a1);
				tx.commit();

				tx.begin();
				Ansatt avdelingsleder = em.find(Ansatt.class, leder);
				avdelingsleder.setAvdeling(a1.getAvdelingsId());
				tx.commit();
				return a1;
			} catch (Throwable e) {
				e.printStackTrace();
				if (tx.isActive()) {
					tx.rollback();
				}
			} finally {
				em.close();
			}
		} else {
			System.out.println("Den ansatte er allerde leder ved en annen avdeling");

		}
		return null;
	}

	private boolean erIkkeLeder(int ansattId) {
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Avdeling> query = em.createQuery("SELECT a FROM Avdeling a WHERE a.leder = :ansattId",
					Avdeling.class);
			query.setParameter("ansattId", ansattId);
			List<Avdeling> avdeling = query.getResultList();
			return avdeling.isEmpty();

		} catch (Throwable e) {
			e.printStackTrace();

		} finally {
			em.close();
		}

		return false;
	}
}
