package be.pxl.ja2.jpa.model.patient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 40, nullable = false)
	private String name;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MedicalRecord medicalRecord;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MedicalRecord getMedicalFile() {
		return medicalRecord;
	}

	public void setMedicalFile(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
}
