package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.school.School;
import be.pxl.ja2.jpa.model.school.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Demo8RelationsManyToOne {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			cleanUpSchoolTable(entityManager);
			tx.begin();
			School school = new School("PXL");
			entityManager.persist(school);
			entityManager.persist(new Student("Sheldon Cooper", school));
			entityManager.persist(new Student("Leonard Hofstadter", school));
			entityManager.persist(new Student("Howard Wolowitz", school));
			entityManager.persist(new Student("Rajesh Koothrappali", school));
			tx.commit();

			entityManager.clear();

			TypedQuery<School> query = entityManager.createNamedQuery("schoolByName", School.class);
			query.setParameter("name", "PXL");
			School result = query.getSingleResult();
			System.out.println("Number of students: " + result.getStudents().size());
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}

	private static void cleanUpSchoolTable(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query deleteQuery = entityManager.createQuery("DELETE FROM Student");
		deleteQuery.executeUpdate();
		deleteQuery = entityManager.createQuery("DELETE FROM School");
		deleteQuery.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.clear();
	}
}
