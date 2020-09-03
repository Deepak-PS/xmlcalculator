package com.calculator.application.model;

public class City {
	
	private String name;
	private double population;
	private double area;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPopulation() {
		return population;
	}
	public void setPopulation(double population) {
		this.population = population;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public String toString() {
		return "City [Name: "+name+", Population: "+population+", Area: "+area +"]";
	}
}
