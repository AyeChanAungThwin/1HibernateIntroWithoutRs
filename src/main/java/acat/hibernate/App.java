package acat.hibernate;

import java.util.List;

import acat.hibernate.controller.PersonController;
import acat.hibernate.dao.PersonDaoImpl;
import acat.hibernate.dependency.DependencyRegistry;
import acat.hibernate.dto.PersonDto;
import acat.hibernate.entity.Person;

public class App {
	
	public static void main( String[] args ) {
		//Recommended for beginners();
		//easy();
		
		//Recommended for working with team! We use these layers in actual world!
		hard();
    }
	
	public static void easy() {
		Person person1 = new Person();
		person1.setName("Dwayne Johnson");
		person1.setEmail("therock@gmail.com");
		person1.setPhNo("+1549724440");
		
		Person person2 = new Person();
		person2.setName("Vin Diesel");
		person2.setEmail("xendercage@gmail.com");
		person2.setPhNo("+1384596020");
		
		Person person3 = new Person();
		person3.setName("Jason Statham");
		person3.setEmail("transporter@gmail.com");
		person3.setPhNo("+1484020249");
		
		//Insert or Create
		PersonDaoImpl personDao = new PersonDaoImpl();
		personDao.save(person1);
		personDao.save(person2);
		personDao.save(person3);
		
		//Fetch or Retrieve
		List<Person> people = personDao.findAll();
		for (Person prn: people) {
			//String is immutable in java.
			StringBuilder sb=new StringBuilder(); //To reduce heap size
			sb.append("Person [id="+prn.getId());
			sb.append(", name="+prn.getName());
			sb.append(", email="+prn.getEmail());
			sb.append(", phNo="+prn.getPhNo()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
		
		//Update
		Person prn1 = personDao.findOne(1);
		//System.out.println(prn1.getName()); //This can result in LazyInitializationException
		
		/*To avoid LazyInitializationException, use DTO.
		 * DTO must be used inside Service Layer!
		 * In here, the code is working but WRONG LOGIC!
		 */
		PersonDto personDto1 = new PersonDto(prn1);
		System.out.println(personDto1.getName());
		
		if (prn1!=null) {
			prn1.setName("Aye Chan Aung Thwin");
			personDao.update(prn1);
		}
		
		//Delete
		Person prn2 = personDao.findOne(2);
		PersonDto personDto2 = new PersonDto(prn2);
		System.out.println(personDto2.getName());
		
		if (prn2!=null) {
			personDao.delete(prn2);
		}
		
		//Wrong Update!
		Person prn3 = personDao.findOne(3);
		PersonDto personDto3 = new PersonDto(prn3);
		System.out.println(personDto3.getName());
		
		if (prn3!=null) {
			//Hibernate will auto generate id when id is null.
			prn3.setId(null); //updating without id will insert a new one when we use Hibernate
			prn3.setName("Aye Chan Aung Thwin");
			personDao.update(prn3);
		}
	}
	
	public static void hard() {
		/* This is the Presentation Layer!
		 * Here, we used MVC design pattern.
		 * LazyInitializationException is handled by the Service Layer!
		 * Controller only works between model and view.
		 * This configuration will help you when you moved to Spring or SpringBoot Framework for J2EE!
		 */
		
		DependencyRegistry dependency = DependencyRegistry.getInstance(); //Dependency
		
		PersonController controller = (PersonController) dependency.getInstance(PersonController.class);
		
		Person person1 = (Person) dependency.getInstance(Person.class); //Same as Person person1 = new Person();
		person1.setName("Dwayne Johnson");
		person1.setEmail("therock@gmail.com");
		person1.setPhNo("+1549724440");
		
		Person person2 = (Person) dependency.getInstance(Person.class);
		person2.setName("Vin Diesel");
		person2.setEmail("xendercage@gmail.com");
		person2.setPhNo("+1384596020");
		
		Person person3 = (Person) dependency.getInstance(Person.class);
		person3.setName("Jason Statham");
		person3.setEmail("transporter@gmail.com");
		person3.setPhNo("+1484020249");
		
		//Insert or Create
		controller.save(person1);
		controller.save(person2);
		controller.save(person3);
		
		//Fetch or Retrieve
		List<Person> people = controller.getAllPeople();
		controller.printResult(people);
		
		//Update
		Person prn1 = controller.getById(1);
		controller.printResult(prn1);
		
		if (prn1!=null) {
			prn1.setName("Aye Chan Aung Thwin");
			controller.update(prn1);
		}
		
		//Delete
		Person prn2 = controller.getById(2);
		controller.printResult(prn2);
		
		if (prn2!=null) {
			controller.delete(prn2);
		}
		
		//Wrong Update!
		Person prn3 = controller.getById(3);
		controller.printResult(prn3);
		
		if (prn3!=null) {
			//Hibernate will auto generate id when id is null.
			prn3.setId(null); //updating without id will insert a new one when we use Hibernate.
			prn3.setName("Aye Chan Aung Thwin");
			controller.update(prn3);
		}
	}
}
