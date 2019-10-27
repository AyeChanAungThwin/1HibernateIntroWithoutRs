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

	public Person createPerson() {
		return new Person();
	}
	
	public PersonDao createPersonDao() {
		return new PersonDao();
	}
	
	public StringBuilder createStringBuilder() {
		return new StringBuilder();
	}
}
