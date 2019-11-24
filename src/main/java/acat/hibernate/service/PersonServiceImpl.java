package acat.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import acat.hibernate.dao.PersonDaoImpl;
import acat.hibernate.dto.PersonDto;
import acat.hibernate.entity.Person;
import acat.hibernate.repository.PersonRepository;

public class PersonServiceImpl implements PersonService {
	
	private final PersonRepository repo = new PersonDaoImpl();

	public PersonDto findOne(long id) {
		// TODO Auto-generated method stub
		Person person = repo.findOne(id);
		PersonDto dto = new PersonDto(person);
		return dto;
	}

	public List<PersonDto> findAll() {
		// TODO Auto-generated method stub
		List<Person> peopleListDao = repo.findAll();
		List<PersonDto> peopleListDto = null;
		for(Person person: peopleListDao) {
			if (peopleListDto==null) {
				peopleListDto = new ArrayList<PersonDto>();
			}
			PersonDto dto = new PersonDto(person);
			peopleListDto.add(dto);
		}
		return peopleListDto;
	}

	public PersonDto save(PersonDto dto) {
		// TODO Auto-generated method stub
		Person person = repo.save(dto.getEntity());
		return new PersonDto(person);
	}

	public PersonDto update(PersonDto dto) {
		// TODO Auto-generated method stub
		Person person = repo.update(dto.getEntity());
		return new PersonDto(person);
	}

	public void delete(PersonDto dto) {
		// TODO Auto-generated method stub
		repo.delete(dto.getEntity());
	}

	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repo.deleteById(entityId);
	}
}
