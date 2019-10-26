package acat.hibernate.dependency;

import acat.hibernate.dao.PersonDao;
import acat.hibernate.entity.Person;

public class DependencyRegistry {

	private static DependencyRegistry instance;
	
	private DependencyRegistry() {
		
	}
	
	public static DependencyRegistry getInstance() {
		if (instance==null) {
			synchronized (DependencyRegistry.class) {
				if (instance==null) {
					instance = new DependencyRegistry();
				}
			}
		}
		return instance;
	}
	
	private Person person;
	
	public Person createPerson() {
		if (person==null) {
			person=new Person();
		}
		return person;
	}
	
	private PersonDao personDao;
	
	public PersonDao createPersonDao() {
		if (personDao==null) {
			personDao=new PersonDao();
		}
		return personDao;
	}
	
	private StringBuilder sb;
	
	public StringBuilder createStringBuilder(boolean isNull) {
		if (isNull) {
			sb=null;
		}
		if (sb==null) {
			sb = new StringBuilder();
		}
		return sb;
	}
}
