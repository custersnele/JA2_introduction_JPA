package be.pxl.ja2.jpa.model.school;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(@NamedQuery(name="schoolByName", query = "SELECT s FROM School s WHERE s.name = :name"))
public class School {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
	private List<Student> students = new ArrayList<>();

	public School() {
		// JPA only
	}

	public School(String name) {
		this.name = name;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public List<Student> getStudents() {
		return students;
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
}
