package acat.hibernate.view;

import java.util.List;

import acat.hibernate.entity.Person;

public class PersonView {
	
	public void printPersonDetails(Person person) {
		StringBuilder sb = new StringBuilder();
		sb.append("<Person Output>[id="+person.getId());
		sb.append(", name="+person.getEmail());
		sb.append(", email="+person.getEmail());
		sb.append(", phNo="+person.getPhNo()+"]<Person Output>\n*****");
		System.err.println(sb.toString());
	}
	
	public void printPersonDetails(List<Person> people) {
		for (Person person: people) {
			printPersonDetails(person);
		}
	}
}
