package db;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(schema = "Oblig3")
public class Ansatt {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int brukerid;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDateTime ansettelsesdato;
	private String stilling;
	private BigDecimal maanedslonn;
	
	public Ansatt() {
		
	}
	public Ansatt(int brukerid, String brukernavn, String fornavn, String etternavn, LocalDateTime ansettelsesdato, String stilling, BigDecimal maanedslonn ) {
		this.brukerid = brukerid;
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;	
	}
	public int getBrukerid() {
		return brukerid;
	}
	public void setBrukerid(int brukerid) {
		this.brukerid = brukerid;
	}
	public String getBrukernavn() {
		return brukernavn;
	}
	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}
	public String getFornavn() {
		return fornavn;
	}
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public LocalDateTime getAnsettelsesdato() {
		return ansettelsesdato;
	}
	public void setAnsettelsesdato(LocalDateTime ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
	}
	public String getStilling() {
		return stilling;
	}
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	public BigDecimal getMaanedslonn() {
		return maanedslonn;
	}
	public void setMaanedslonn(BigDecimal maanedslonn) {
		this.maanedslonn = maanedslonn;
	}
	@Override
	public String toString() {
		return "Ansatt [brukerid=" + brukerid + ", brukernavn=" + brukernavn + ", fornavn=" + fornavn + ", etternavn="
				+ etternavn + ", ansettelsesdato=" + ansettelsesdato + ", stilling=" + stilling + ", maanedslonn="
				+ maanedslonn + "]";
	}
	
	
	
}
