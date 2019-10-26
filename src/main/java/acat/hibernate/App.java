package acat.hibernate;

import java.util.List;

import acat.hibernate.dao.PersonDao;
import acat.hibernate.dependency.DependencyRegistry;
import acat.hibernate.entity.Person;

public class App {
	
	public static void main( String[] args ) {
		DependencyRegistry dependency = DependencyRegistry.getInstance(); //Dependency
		
		Person person = dependency.createPerson(); //Dependency Injected
		person.setName("The Rock");
		person.setEmail("dwaynejohnson@gmail.com");
		person.setPhNo("+1549724440");
		
		PersonDao personDao = dependency.createPersonDao(); //Dependency Injected
		personDao.save(person);
		
		List<Person> people = personDao.findAll();
		for (Person prn: people) {
			//String is immutable in java.
			StringBuilder sb=dependency.createStringBuilder(true); //To reduce heap size
			sb.append("Person [id="+prn.getId());
			sb.append(", name="+prn.getName());
			sb.append(", email="+prn.getEmail());
			sb.append(", phNo="+prn.getPhNo()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
    }
}
