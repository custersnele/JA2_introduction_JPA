package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.usercredentials.Credentials;
import be.pxl.ja2.jpa.model.usercredentials.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Demo7OneToOneBidirectionalTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			emf = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = emf.createEntityManager();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Credentials id: ");
			long credentialsId = scanner.nextLong();
			transaction = entityManager.getTransaction();
			transaction.begin();


			Credentials credential = entityManager.find(Credentials.class, credentialsId);

			System.err.println("================= From Credentials ===================");
			System.err.println("User Name: " + credential.getUserName());
			System.err.println("Password: " + credential.getPassword());
			//In Bi-direction we can navigate to other side entity(User)
			System.err.println("First Name : " + credential.getUser().getFirstName());
			System.err.println("Last Name : " + credential.getUser().getLastName());

			User credentialUser = credential.getUser();
			credentialUser.setCredentials(new Credentials("mickey_update", "minie123!"));
			transaction.commit();

			System.out.println("Enter User id: ");
			long userId = scanner.nextLong();

			User user = entityManager.find(User.class, userId);

			System.err.println("================= From User ===================");
			System.err.println("First Name : " + user.getFirstName());
			System.err.println("Last Name : " + user.getLastName());
			//In Bi-direction we can navigate to other side entity(Credentials)
			System.err.println("User Name: " + user.getCredentials().getUserName());
			System.err.println("Password: " + user.getCredentials().getPassword());


		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}
}
