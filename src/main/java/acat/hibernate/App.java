package acat.hibernate;

import java.util.List;

import acat.hibernate.dao.PersonDao;
import acat.hibernate.dependency.DependencyRegistry;
import acat.hibernate.dto.PersonDto;
import acat.hibernate.entity.Person;

public class App {
	
	public static void main( String[] args ) {
		DependencyRegistry dependency = DependencyRegistry.getInstance(); //Dependency
		
		Person person1 = dependency.createPerson(); //Dependency Injected
		person1.setName("The Rock");
		person1.setEmail("dwaynejohnson@gmail.com");
		person1.setPhNo("+1549724440");
		
		Person person2 = dependency.createPerson(); //Dependency Injected
		person2.setName("Xender Cage");
		person2.setEmail("xxx@gmail.com");
		person2.setPhNo("+1384596020");
		
		PersonDao personDao = dependency.createPersonDao(); //Dependency Injected
		personDao.save(person1);
		personDao.save(person2);
		
		List<Person> people = personDao.findAll();
		for (Person prn: people) {
			//String is immutable in java.
			StringBuilder sb=dependency.createStringBuilder(); //To reduce heap size
			sb.append("Person [id="+prn.getId());
			sb.append(", name="+prn.getName());
			sb.append(", email="+prn.getEmail());
			sb.append(", phNo="+prn.getPhNo()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
		
		Person person = personDao.findOne(1);
		//System.out.println(person.getName()); //This can result in LazyInitializationException
		PersonDto personDto = dependency.createPersonDto(person); //To avoid LazyInitializationException, use DTO.
		System.out.println(personDto.getName());
		if (person!=null) {
			personDao.delete(person);
		}
    }
}
