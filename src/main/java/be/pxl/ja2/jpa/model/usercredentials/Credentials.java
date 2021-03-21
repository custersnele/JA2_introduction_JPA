package be.pxl.ja2.jpa.model.usercredentials;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Credentials {

	@Id
	@GeneratedValue
	private Long credentialId;
	private String userName;
	private String password;
	@OneToOne(mappedBy = "credentials")
	private User user;

	public Credentials() {
		// JPA only
	}

	public Credentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
