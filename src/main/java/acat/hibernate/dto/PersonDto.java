package acat.hibernate.dto;

import java.io.Serializable;

import acat.hibernate.entity.Person;

public class PersonDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915317817868710134L;

	private Long id;
	private String name;
	private String email;
	private String phNo;
	
	public PersonDto(Person person) {
		this.id=person.getId();
		this.name=person.getName();
		this.email=person.getEmail();
		this.phNo=person.getPhNo();
	}
	
	public Person getEntity() {
		Person person = new Person();
		person.setId(this.id);
		person.setName(this.name);
		person.setEmail(this.email);
		person.setPhNo(this.phNo);
		
		return person;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
}
