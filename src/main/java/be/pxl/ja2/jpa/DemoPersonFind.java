package be.pxl.ja2.jpa;

import be.pxl.ja2.jpa.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class DemoPersonFind {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			Scanner input = new Scanner(System.in);
			System.out.println("Enter person id:");
			long id = input.nextLong();
			Person person = entityManager.find(Person.class, id);
			if (person != null) {
				System.out.println(person.getFirstName());
				System.out.println(person.getComment());
				System.out.println(person.getToken());
				displayImage(person.getPicture());
			} else {
				System.out.println("No person found with id <" + id + ">");
			}
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

	private static void displayImage(byte[] image) {
		ImageIcon icon=new ImageIcon(image);
		JFrame frame=new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(200,300);
		JLabel lbl=new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
