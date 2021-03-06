package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TTTesttt {
	
	@JsonProperty("alfa")
	private String alfa;
	
	@JsonProperty("bravo")
	private String bravo;
	
	@JsonProperty("charlie")
	private int charlie;
	
	public TTTesttt() {
	}
	
	public TTTesttt(String alfa, String bravo, int charlie) {
		super();
		this.alfa = alfa;
		this.bravo = bravo;
		this.charlie = charlie;
	}
	
	public String getAlfa() {
		return alfa;
	}
	public void setAlfa(String alfa) {
		this.alfa = alfa;
	}
	public String getBravo() {
		return bravo;
	}
	public void setBravo(String bravo) {
		this.bravo = bravo;
	}
	public int getCharlie() {
		return charlie;
	}
	public void setCharlie(int charlie) {
		this.charlie = charlie;
	}
	
	@Override
	public String toString() {
		return "TTTesttt [alfa=" + alfa + ", bravo=" + bravo + ", charlie=" + charlie + "]";
	}

}
