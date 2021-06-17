package com.uba.fi.bdd.cityapp.model;

/**
 * Automatic Teller Machine
 */
public class ATM {

	private int id;
	private String bankName;
	private String bankNetwork;
	private int terminalQty;
	private String streetName;
	private int streetHeight;
	private String neighbourhoodName;
	private int communeId;

	public ATM() {}

	public ATM(int id, String bankName, String bankNetwork, int terminalQty,
			   String streetName, int streetHeight, String neighbourhoodName, int communeId) {
		this.id = id;
		this.bankName = bankName;
		this.bankNetwork = bankNetwork;
		this.terminalQty = terminalQty;
		this.streetName = streetName;
		this.streetHeight = streetHeight;
		this.neighbourhoodName = neighbourhoodName;
		this.communeId = communeId;
	}

	public int getId() {
		return id;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankNetwork() {
		return bankNetwork;
	}

	public int getTerminalQty() {
		return terminalQty;
	}

	public String getStreetName() {
		return streetName;
	}

	public int getStreetHeight() {
		return streetHeight;
	}

	public String getNeighbourhoodName() {
		return neighbourhoodName;
	}

	public int getCommuneId() {
		return communeId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBankNetwork(String bankNetwork) {
		this.bankNetwork = bankNetwork;
	}

	public void setTerminalQty(int terminalQty) {
		this.terminalQty = terminalQty;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setStreetHeight(int streetHeight) {
		this.streetHeight = streetHeight;
	}

	public void setNeighbourhoodName(String neighbourhoodName) {
		this.neighbourhoodName = neighbourhoodName;
	}

	public void setCommuneId(int communeId) {
		this.communeId = communeId;
	}

}
