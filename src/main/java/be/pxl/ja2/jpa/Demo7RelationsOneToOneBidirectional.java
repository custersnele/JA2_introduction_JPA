package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.usercredentials.Credentials;
import be.pxl.ja2.jpa.model.usercredentials.User;
import be.pxl.ja2.jpa.model.usercredentials.UserType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Demo7RelationsOneToOneBidirectional {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try{
			emf = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			User user = new User();
			user.setFirstName("Mickey");
			user.setLastName("Mouse");
			user.setCreationTime(LocalDateTime.now());
			user.setDateofBirth(LocalDate.of(1996, 12, 13));
			user.setUserType(UserType.ADMIN);
			user.setCredentials(new Credentials("mickeym", "password123!"));

			//User object having all the information (User and Credentials)
			entityManager.persist(user);
			transaction.commit();
		}catch(Exception e){
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}finally{
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}

}
