package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.magazines.Magazine;
import be.pxl.ja2.jpa.model.magazines.Reader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Demo9RelationsManyToMany {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			cleanUpMagazineAndReaderTable(entityManager);
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			Magazine mag1 = new Magazine("Story");
			Magazine mag2 = new Magazine("Flair");
			Magazine mag3 = new Magazine("EOS");
			entityManager.persist(mag1);
			entityManager.persist(mag2);
			entityManager.persist(mag3);

			Reader reader1 = new Reader("A001", "Sheldon Cooper");
			Reader reader2 = new Reader("A002", "Penny");
			reader1.addMagazine(mag2);
			reader1.addMagazine(mag3);
			reader2.addMagazine(mag1);
			reader2.addMagazine(mag2);

			entityManager.persist(reader1);
			entityManager.persist(reader2);
			tx.commit();

			entityManager.clear();

			TypedQuery<Reader> query = entityManager.createQuery("SELECT r FROM Reader r", Reader.class);
			List<Reader> result = query.getResultList();
			result.forEach(System.out::println);
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}

	private static void cleanUpMagazineAndReaderTable(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query deleteQuery = entityManager.createQuery("DELETE FROM Reader");
		deleteQuery.executeUpdate();
		deleteQuery = entityManager.createQuery("DELETE FROM Magazine");
		deleteQuery.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.clear();
	}
}
