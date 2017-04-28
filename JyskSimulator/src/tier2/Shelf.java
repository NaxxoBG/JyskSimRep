package tier2;

public class Shelf {
	private char tower;
	private int number;

	public Shelf(char tower, int number) {
		this.tower = tower;
		this.number = number;
	}

	public char getTower() {
		return tower;
	}

	public void setTower(char tower) {
		this.tower = tower;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Shelf [tower=" + tower + ", number=" + number + "]";
	}
}