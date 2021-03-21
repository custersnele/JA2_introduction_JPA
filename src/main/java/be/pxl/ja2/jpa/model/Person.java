package be.pxl.ja2.jpa.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="PERSONS")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 40, nullable = false)
	private String firstName;
	@Column(length = 40, nullable = false)
	private String lastName;
	private LocalDate birthday;
	@Enumerated(value = EnumType.STRING)
	private Gender gender;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] picture;
	@Column(name = "bio")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String comment;
	private boolean married;
	@Transient
	private String token;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
