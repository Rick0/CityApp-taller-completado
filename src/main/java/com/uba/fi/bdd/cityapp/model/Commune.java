package com.uba.fi.bdd.cityapp.model;

public class Commune {

	private Integer id;
	private Double area;
	private Double perimeter;
	private String neighbourhoodsList;

	public Commune() {}

	public Commune(int id, double area, double perimeter, String neighbourhoodsList) {
		this.id = id;
		this.area = area;
		this.perimeter = perimeter;
		this.neighbourhoodsList = neighbourhoodsList;
	}

	public Integer getId() {
		return id;
	}

	public Double getArea() {
		return area;
	}

	public Double getPerimeter() {
		return perimeter;
	}

	public String getNeighbourhoodsList() {
		return neighbourhoodsList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}

	public void setNeighbourhoodsList(String neighbourhoodsList) {
		this.neighbourhoodsList = neighbourhoodsList;
	}

}
