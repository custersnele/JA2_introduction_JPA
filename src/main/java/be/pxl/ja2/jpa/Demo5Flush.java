package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.participant.Participant;
import be.pxl.ja2.jpa.util.Helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class Demo5Flush {

	public static void main(String[] args) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicdb_pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		String uid = UUID.randomUUID().toString();
		String email = uid + " @gmail.com";
		Participant participant = new Participant(uid, "Participant 1", email);
		em.persist(participant);
		//em.flush();

		//TypedQuery<Participant> query = em.createQuery("SELECT p FROM Participant p WHERE p.email = :email", Participant.class);
		//query.setParameter("email", email);
		//Participant result = query.getSingleResult();
		//System.out.println(result);

		Helper.checkData("Participant");
		em.getTransaction().rollback();

		em.close();
		emf.close();

		Helper.checkData("Participant");
	}

}
