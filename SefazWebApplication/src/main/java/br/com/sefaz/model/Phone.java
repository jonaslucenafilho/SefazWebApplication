package br.com.sefaz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ddd")
	private int ddd;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "type")
	private String type;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private User user;
	
	public Phone () {
		
	}

	public Phone(Long id, int ddd, String number, String type, User user) {
		this.id = id;
		this.ddd = ddd;
		this.number = number;
		this.type = type;
		this.user = user;
	}

	public Phone(int ddd, String number, String type, User user) {
		this.ddd = ddd;
		this.number = number;
		this.type = type;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
