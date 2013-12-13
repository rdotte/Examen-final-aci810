package com.example.aci570_db.model;

import java.io.Serializable;

public class Team implements Serializable {
	
	public static final long serialVersionUID = 7526472295622776177L;
	
	private long id;
	private String nombrePais;
	private String ranking;
	private String mundialesGanados;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getMundialesGanados() {
		return mundialesGanados;
	}
	public void setMundialesGanados(String mundialesGanados) {
		this.mundialesGanados = mundialesGanados;
	}
	
	public String toString(){
		
		return this.nombrePais;
	}

}
