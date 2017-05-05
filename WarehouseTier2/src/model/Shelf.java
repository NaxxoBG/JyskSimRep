package model;

import java.io.Serializable;

public class Shelf implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int id;
	private int number;
	private String tower;

	public Shelf() {
		super();
	}

	public Shelf(int id, int number, String tower) {
		this.id = id;
		this.number = number;
		this.tower = tower;
	}

	@Override
	public String toString() {
		return "Shelf [id=" + id + ", number=" + number + ", tower=" + tower + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTower() {
		return tower;
	}

	public void setTower(String tower) {
		this.tower = tower;
	}
}