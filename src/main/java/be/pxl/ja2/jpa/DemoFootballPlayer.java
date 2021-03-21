package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.FootballPlayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DemoFootballPlayer {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			FootballPlayer player = new FootballPlayer("Lionell Messi", "l.messi@barca.com");
			player.setShirtNumber(12);
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(player);
			Long persistedId = player.getId();
			player.setEmail("updated_email@gmail.com");
			tx.commit();
			entityManager.clear();
			player = entityManager.find(FootballPlayer.class, persistedId);
			System.out.println("Persisted football player: " + player);
			player.setEmail("updated_again@gmail.com");
			entityManager.clear();
			tx.begin();
			player = entityManager.find(FootballPlayer.class, persistedId);
			System.out.println(player.getEmail());
			entityManager.remove(player);
			tx.commit();
			entityManager.close();
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
