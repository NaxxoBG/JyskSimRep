package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pallet database table.
 * 
 */
@Entity
@NamedQuery(name="Pallet.findAll", query="SELECT p FROM Pallet p")
public class Pallet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private Integer count;

	@Column(name="good_id")
	private Integer goodId;

	//bi-directional one-to-one association to Good
	@OneToOne(mappedBy="pallet")
	private Good good;

	public Pallet() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getGoodId() {
		return this.goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Good getGood() {
		return this.good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

}