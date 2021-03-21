package be.pxl.ja2.jpa.model.school;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@ManyToOne
	private School school;

	public Student() {
		// JPA only
	}

	public Student(String name, School school) {
		this.name = name;
		this.school = school;
		school.addStudent(this);
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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
