# Introduction to Hibernate (Entity Creation, Without Relationship)
## Abstract
> You have to use **Hibernate** when we want to make CRUD operations in relational database server using Java code.

## About
- [X] CRUD with Hibernate
- [X] Insert data using Hibernate
- [X] Fetch data using Hibernate
- [X] Update data using Hibernate
- [X] Delete data using Hibernate
- [ ] ON DELETE SET NULL
- [ ] ON DELETE CASCADE

## What is Hibernate?
- [About Hibernate ORM](https://hibernate.org/orm/)
- Hibernate ORM (Hibernate in short) is an object-relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational databases like Oracle, MySQL, MS SQL, etc. Hibernate also provides data query and retrieval facilities.
- Hibernate provides transparent persistence for Plain Old Java Objects (POJOs). The only strict requirement for a persistent class is a no-argument constructor, though not necessarily (public).

## Diagrams
- ER Diagram
<img src="images/erd.png" alt="Person with 3 attributes, ER Diagram">
- Relational Schema
<img src="images/relational.png" alt="Person with 3 attributes, Relational Schema">
- SQL Query
<pre>
create table person(
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(50),
	email VARCHAR(50),
	PRIMARY KEY (id)
);
</pre>

## Explanation of an Entity
- The following code generates a table in relational database server.
- You need your entities to be **Serializable** if you need to transfer them over-the-wire (serialize them to some other representation), store them in http session (which is in turn serialized to hard disk by the servlet container), etc. Just for _the sake of persistence_, **Serializable** is not needed, at least with Hibernate. But it is a best practice to make them Serializable.
> __Entity & Table Annotation__
```
@Entity
@Table(name = "person", uniqueConstraints = {
		@UniqueConstraint(
				columnNames = {"id", "email", "ph_no"}
		)
})
public class Person {
	//attributes, getters and setters
}
```
   - **@Entity** = this class is an **Entity**. 
   - **@Table(name = "custom_name")** = set the table name as **custom_name**
   - **@UniqueConstraint(columnNames = {"id", "email", "ph_no"})** = cannot have duplicate values for that columns
   - If we don't want to do that, we can add unique=(true,false) in each columns.
   
> __Id Annotation__
```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;  
```
  - **@Id**= this attribute is an id.
  - **@GeneratedValue(strategy = GenerationType.IDENTITY)** = automatically increase an id whenever the new data is inserted.
  - Use data type Long rather than Integer. because of
     - Integer = (−32,767 to +32,767) range. 16 bits in size.
     - Long = (−2,147,483,647 to +2,147,483,647) range. 32 bits in size.

> __Column Annotation__
```
@Column(name = "name", length = 50)
private String name;

@Column(name = "email", length = 50)
private String email;
	
@Column(name = "ph_no", length = 20)
private String phNo;
```
   - **@Column** = create column with attribute name
   - **@Column(name = "custom_name")** = create column with custom_name
   - **Other keys in @Column annotation**
      - unique (default=false)
      - updatable (default=true)
      - nullable (default=true)
      - length (default=255)
      - [precision](https://stackoverflow.com/questions/4078559/how-to-specify-doubles-precision-on-hibernate)
      - [columnDefinition](https://stackoverflow.com/questions/16078681/what-properties-does-column-columndefinition-make-redundant)
      - [scale](https://stackoverflow.com/questions/4078559/how-to-specify-doubles-precision-on-hibernate)

> __hibernate.cfg.xml__
- If you didn't add
```
"<property name="hibernate.hbm2ddl.auto">update</property>"
```
in hibernate.cfg.xml, Hibernate won't generate the table in your relational database server. But you can create it yourself.
- If you change its value to "create", Hibernate will drop the table and create whenever you run the program.

## Electronics Engineer-cum-J2EE Backend Developer ##
-  Created by - Aye Chan Aung Thwin
