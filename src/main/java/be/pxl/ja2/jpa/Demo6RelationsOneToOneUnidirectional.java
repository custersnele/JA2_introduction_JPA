package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.patient.MedicalRecord;
import be.pxl.ja2.jpa.model.patient.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Demo6RelationsOneToOneUnidirectional {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			cleanUpPatientTable(entityManager);
			Patient patient = new Patient();
			patient.setName("Sheldon Cooper");
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setHeight(186);
			medicalRecord.setWeight(65.7);
			patient.setMedicalFile(medicalRecord);
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(patient);
			tx.commit();
			entityManager.clear();

			TypedQuery<Patient> query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.name = :name", Patient.class);
			tx.begin();
			query.setParameter("name", "Sheldon Cooper");
			Patient result = query.getSingleResult();
			System.out.println(patient.getMedicalFile().getHeight());
			result.getMedicalFile().setWeight(65.9);
			tx.commit();
		}
		finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}

	private static void cleanUpPatientTable(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query deleteFromPerson = entityManager.createQuery("DELETE FROM Patient");
		deleteFromPerson.executeUpdate();
		Query deleteFromMedicalRecord = entityManager.createQuery("DELETE FROM MedicalRecord");
		deleteFromMedicalRecord.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.clear();
	}

}
