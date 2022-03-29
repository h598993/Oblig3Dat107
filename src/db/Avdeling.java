package db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3")
public class Avdeling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdelingsId;
	private String navn;
	private int leder;

	public Avdeling() {
	}

	public Avdeling(int avdelingsId, String navn, int leder) {
		this.avdelingsId = avdelingsId;
		this.navn = navn;
		this.leder = leder;
	}

	public int getAvdelingsId() {
		return avdelingsId;
	}

	public void setAvdelingsId(int avdelingsId) {
		this.avdelingsId = avdelingsId;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getLeder() {
		return leder;
	}

	public void setLeder(int leder) {
		this.leder = leder;
	}

	@Override
	public String toString() {
		return "Avdeling [avdelingsId=" + avdelingsId + ", navn=" + navn + ", leder=" + leder + "]";
	}
	
	

}
