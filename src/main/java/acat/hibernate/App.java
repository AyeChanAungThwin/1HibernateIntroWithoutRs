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
		person1.setName("Dwayne Johnson");
		person1.setEmail("therock@gmail.com");
		person1.setPhNo("+1549724440");
		
		Person person2 = dependency.createPerson(); //Dependency Injected
		person2.setName("Vin Diesel");
		person2.setEmail("xendercage@gmail.com");
		person2.setPhNo("+1384596020");
		
		Person person3 = dependency.createPerson();
		person3.setName("Jason Statham");
		person3.setEmail("transporter@gmail.com");
		person3.setPhNo("+1484020249");
		
		//Insert
		PersonDao personDao = dependency.createPersonDao(); //Dependency Injected
		personDao.save(person1);
		personDao.save(person2);
		personDao.save(person3);
		
		//Fetch
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
		
		//Update
		Person prn1 = personDao.findOne(1);
		//System.out.println(prn1.getName()); //This can result in LazyInitializationException
		PersonDto personDto1 = dependency.createPersonDto(prn1); //To avoid LazyInitializationException, use DTO.
		System.out.println(personDto1.getName());
		
		if (prn1!=null) {
			prn1.setName("Aye Chan Aung Thwin");
			personDao.update(prn1);
		}
		
		//Delete
		Person prn2 = personDao.findOne(2);
		PersonDto personDto2 = dependency.createPersonDto(prn2);
		System.out.println(personDto2.getName());
		
		if (prn2!=null) {
			personDao.delete(prn2);
		}
		
		//Wrong update!
		Person prn3 = personDao.findOne(3);
		PersonDto personDto3 = dependency.createPersonDto(prn3);
		System.out.println(personDto3.getName());
		
		if (prn3!=null) {
			prn3.setId(null); //updating without id will insert a new one when we use Hibernate
			prn3.setName("Aye Chan Aung Thwin");
			personDao.update(prn3);
		}
    }
}
