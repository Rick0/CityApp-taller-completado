package com.uba.fi.bdd.cityapp.model;

public class Neighbourhood {

	private String name;
	private Double area;
	private Double perimeter;
	private Integer communeId;

	public Neighbourhood() {}

	public Neighbourhood(String name, double area, double perimeter, int communeId) {
		this.name = name;
		this.area = area;
		this.perimeter = perimeter;
		this.communeId = communeId;
	}

	public String getName() {
		return name;
	}

	public Double getArea() {
		return area;
	}

	public Double getPerimeter() {
		return perimeter;
	}

	public Integer getCommuneId() {
		return communeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}

	public void setCommuneId(Integer communeId) {
		this.communeId = communeId;
	}

}
