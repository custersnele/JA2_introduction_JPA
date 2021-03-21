package be.pxl.ja2.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class FootballPlayer {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String email;
	@Transient
	private int shirtNumber;

	public FootballPlayer() {
	}

	public FootballPlayer(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(int shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	@Override
	public String toString() {
		return "FootballPlayer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", shirtNumber=" + shirtNumber +
				'}';
	}
}
