package acat.hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import acat.hibernate.dto.PersonDto;
import acat.hibernate.entity.Person;
import acat.hibernate.service.PersonService;
import acat.hibernate.service.PersonServiceImpl;
import acat.hibernate.view.PersonView;

public class PersonController {
	
	private Person model;
	private PersonView view;
	
	private final PersonService personService = new PersonServiceImpl();
	
	public PersonController() {
		model = new Person();
		view = new PersonView();
	}

	public PersonController(Person model, PersonView view) {
		this.model = model;
		this.view = view;
	}
	
	public Person getModel() {
		return model;
	}

	public void setModel(Person model) {
		this.model = model;
	}

	public PersonView getView() {
		return view;
	}

	public void setView(PersonView view) {
		this.view = view;
	}
	
	public Person getById(long id) {
		PersonDto dto = personService.findOne(id);
		this.model = dto.getEntity();
		return this.model;
	}
	
	public List<Person> getAllPeople() {
		List<PersonDto> personListDto = personService.findAll();
		List<Person> modelList = null;
		for (PersonDto dto: personListDto) {
			if (modelList==null) {
				modelList = new ArrayList<Person>();
			}
			modelList.add(dto.getEntity());
		}
		return modelList;
	}
	
	public Person save(Person model) {
		this.model = model;
		PersonDto dto = personService.save(new PersonDto(model));
		return dto.getEntity();
	}
	
	public Person update(Person model) {
		this.model = model;
		PersonDto dto = personService.update(new PersonDto(model));
		return dto.getEntity();
	}
	
	public void delete(Person model) {
		this.model = model;
		personService.delete(new PersonDto(model));
	}
	
	public void deleteById(long id) {
		personService.deleteById(id);
	}
	
	public void printPersonDetails(Person model) {
		view.printPersonDetails(model);
	}
	
	public void printPersonDetails(List<Person> people) {
		view.printPersonDetails(people);
	}
}