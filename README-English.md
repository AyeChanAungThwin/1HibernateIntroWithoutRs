# Hibernate (One to One Relationship)
## Details
> [About Hibernate ORM](https://hibernate.org/orm/)
> There's only one entities; **Person**.
> It's about how to use Object/Relation Mapping using Hibernate.
> You have to use Hibernate when we want to make CRUD operations in relational database server using Java code.

## About
- [X] Insert data using Hibernate
- [X] Fetch data using Hibernate
- [X] Update data using Hibernate
- [X] Delete data using Hibernate
- [ ] ON DELETE SET NULL
- [ ] ON DELETE CASCADE

## Explanation
###### Person Entity ######
- The following code generates a table in relational database server.
- You need your entities to be **Serializable** if you need to transfer them over-the-wire (serialize them to some other representation), store them in http session (which is in turn serialized to hard disk by the servlet container), etc. Just for _the sake of persistence_, **Serializable** is not needed, at least with Hibernate. But it is a best practice to make them Serializable.
   - ###### Java Code ######
```
@Entity
@Table(name = "person")
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915317817868710134L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "ph_no")
	private String phNo;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
}
```
- If you didn't add 
```
"<property name="hibernate.hbm2ddl.auto">update</property>"
```
in hibernate.cfg.xml, Hibernate won't generate the table in your relational database server. But you can create it yourself.
- If you change its value to create, it will create a table using "DROP TABLE IF EXISTS table_name."
   - ###### MySQL Query ######
```
CREATE TABLE person (
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(255),
	email VARCHAR(255),
	ph_no VARCHAR(255),
	PRIMARY KEY (id)
);
```
## Electronics Engineer-cum-J2EE Backend Developer ##
-  Created by - Aye Chan Aung Thwin
