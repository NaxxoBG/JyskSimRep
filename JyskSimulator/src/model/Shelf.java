package model;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;


/**
 * The persistent class for the shelf database table.
 * 
 */
@Entity
@NamedQuery(name="Shelf.findAll", query="SELECT s FROM Shelf s")
@Table(schema = "jysksim")
public class Shelf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer id;

	private Integer number;

	private String tower;

	public Shelf() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTower() {
		return this.tower;
	}

	public void setTower(String tower) {
		this.tower = tower;
	}

}