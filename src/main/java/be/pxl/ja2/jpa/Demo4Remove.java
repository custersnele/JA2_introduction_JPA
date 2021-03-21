package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Demo4Remove {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			TypedQuery<Contact> query = entityManager.createQuery("SELECT c FROM Contact c WHERE c.name = :name", Contact.class);

			query.setParameter("name", "Sophie");
			try {
				Contact result = query.getSingleResult();
				System.out.println(result);
				EntityTransaction tx = entityManager.getTransaction();
				tx.begin();
				entityManager.remove(result);
				tx.commit();
			} catch (NoResultException e) {
				System.out.println("No contact found with name <Sophie>");
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}
}
