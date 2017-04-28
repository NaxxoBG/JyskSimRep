package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the good database table.
 * 
 */
@Entity
@NamedQuery(name="Good.findAll", query="SELECT g FROM Good g")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer palletid;

	private String manufacturer;

	private String name;

	public Good(Integer palletid, String manufacturer, String name) {
		super();
		this.palletid = palletid;
		this.manufacturer = manufacturer;
		this.name = name;
	}

	public Good() {
	}

	public Integer getPalletid() {
		return this.palletid;
	}

	public void setPalletid(Integer palletid) {
		this.palletid = palletid;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}