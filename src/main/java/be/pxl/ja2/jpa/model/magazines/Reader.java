package be.pxl.ja2.jpa.model.magazines;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String subscriberNumber;
	private String name;

	@ManyToMany
	private List<Magazine> magazineList = new ArrayList<>();

	public Reader() {
		// JPA only
	}

	public Reader(String subscriberNumber, String name) {
		this.subscriberNumber = subscriberNumber;
		this.name = name;
	}

	public Reader(String name) {
		this.name = name;
	}

	public void addMagazine(Magazine magazine) {
		magazineList.add(magazine);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(name).append(" READS ").append("\n");
		magazineList.stream().forEach(m -> result.append(m).append("\n"));
		return result.toString();
	}
}
