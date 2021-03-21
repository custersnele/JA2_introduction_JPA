package be.pxl.ja2.jpa.model.magazines;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Magazine {
	@Id
	@GeneratedValue
	private Long id;
	private String title;

	public Magazine() {
		// JPA only
	}

	public Magazine(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Magazine{" +
				"id=" + id +
				", title='" + title + '\'' +
				'}';
	}
}
