# Hibernateကိုစတင်လေ့လာခြင်း (Relationshipမပါဘဲ Entityဆောက်ခြင်း)
## အတိုချုပ်
> **Hibernate**ကိုသုံးလိုရင် Database Serverတွေမှာလိုတဲ့ CRUD(Create, Retrieve, Update, Delete) operationsတွေကို Java Codeကနေ တိုက်ရိုက်ခိုင်းလို့ရပါတယ်။
> **Javaနဲ့ MySQL Server**ကြားမှာ _တခါမှ_ connectionချိတ်ပြီး မစမ်းဖူးသေးရင်တော့ [ဒါကို](https://www.javatpoint.com/example-to-connect-to-the-mysql-database) အရင်လေ့လာစေချင်ပါတယ်။

## လေ့လာရမယ့်အကြောင်း
- [X] CRUD with Hibernate
- [X] Insert data using Hibernate
- [X] Fetch data using Hibernate
- [X] Update data using Hibernate
- [X] Delete data using Hibernate
- [ ] ON DELETE SET NULL
- [ ] ON DELETE CASCADE

## Hibernateဆိုတာဘာလဲ?
- [Hibernate ORMအကြောင်း](https://hibernate.org/orm/)
- Hibernate ORMဆိုတာ Javaအတွက် Objectနဲ့ Relationalတွေကို mappingချိတ်ထားတဲ့ Frameworkတစ်ခုပါပဲ။ Java Objectလေးဆောက်ပြီး Relational database serverတွေြဖစ်တဲ့ Oracle, MySQL, MS SQLတွေကို ချိတ်ပြီး CRUDလုပ်လိုက်လို့ရတယ်။
- Ruleတစ်ခုပဲရှိတယ် အဲ့ဒါက argumentsတွေပါတဲ့ constructorတွေဆောက်မိရင် public typeပါတဲ့ no-argument constructorလည်းဆောက်ပေးဖို့လိုတယ်။ တကယ်တမ်းအဲ့လို ဆောက်ကြတာက မှားတယ်။ Entityဆိုတာ Entityအလုပ်ကိုပဲလုပ်ရမယ်။ Argumentsတွေပါတဲ့ constructorဆောက်ပြီး အလုပ်လုပ်တဲ့ DTOရဲ့အလုပ်က ရှိပြီးမို့လို့ပဲ။
- Hibernateမှာ HQL queryဆိုတာရှိတယ်။ သူက ဘာတစ်ခုကောင်းလဲဆိုတော့ ဘယ်database serverပဲသုံးသုံး ဒီQueryပဲသုံးပြီး ခိုင်းလို့ရတယ်။ မသုံးချင်ရင်တော့ database serverတစ်ခုပြောင်းတိုင်း Native Queryအသစ်ရေးပေါ့။ Native Queryကို လုံးဝမသုံးရဘူးလို့ ဆိုလိုတာမဟုတ်ဘူးနော်။ သူနေ့ရာနဲ့သူတော့ သုံးသင့်တဲ့နေရာတွေလည်းရှိတယ်။

## DAOဆိုတာဘာလဲ?
- DAO(Data Access Object)ဆိုတာက MySQLကနေလာတဲ့ Dataမှန်သမျှကို Objectအဖြစ်ပြောင်းတဲ့အလုပ်ကို လုပ်ရတယ်။ CRUDအလုပ်တွေပေါ့။

## DTOဆိုတာဘာလဲ?
- DAOကနေ Objectရဲ့ methodကိုလှမ်းခေါ်တဲ့အခါ LazyInitializationExceptionဆိုတဲ့ Errorတတ်နိုင်တယ်။ Errorတက်ရခြင်းအကြောင်းက Objectရှိပေမယ့် methodကိုခေါ်လိုက်တဲ့အချိန်မှာ Hibernateရဲ့ Sessionက ပိတ်သွားလို့ပဲ။ အဲ့ဒါကြောင့် တစ်နေရာမှာ Objectကိုပြန်သိမ်းပြီးမှ Dataကိုသယ်မှ အဆင်ပြေတော့မှာလေ။ အဲ့ဒါကြောင့် DTOဆိုတဲ့ Objectဆောက်ပြီး dataကို သိမ်းရတယ်။ DTO(Data Transfer Object)ဆိုတာ DAOကနေ ထွက်လာတဲ့ Objectတွေကို သိမ်းတဲ့အလုပ်ကို လုပ်ရတယ်။

## Projectဆောက်ခြင်း
- Alt+Shift+Nကိုနှိပ်ပြီး _Maven Project_ကိုဆောက်ပါ။
- pom.xmlမှာ အောက်က Dependencyတွေကိုထည့်ပါ။
```
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.4.7.Final</version>
</dependency>
	
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.18</version>
</dependency>
```
- [hibernate.cfg.xml](https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/session-configuration.html)ဖိုင်ကို src/main/javaအောက်မှာ ထည့်ပါ။
- အောက်အတိုင်း hibernate.cfg.xmlမှာ မထည့်ခဲ့ရင်
```
"<property name="hibernate.hbm2ddl.auto">update</property>"
```
Hibernateက Databaseထဲမှာ tableဆောက်မှာမဟုတ်ဘူး။ ကိုယ့်ဘာကိုယ်တော့ ရေးပြီးဆောက်လို့ရတယ်။
- သူ့valueကို "create"လို့ပြောင်းလိုက်ရင် Hibernateကို runတိုင်းမှာ ရှိပြီးသား tableကို ဖျက်ချပြီး အသစ်ပြန်ဆောက်လိမ့်မယ်။
- HibernateUtilsကနေ Database Serverနဲ့Javaကို connectionချိတ်တယ်။

## Diagramများ
- ER Diagram
<img src="images/erd.png" alt="Person with 3 attributes, ER Diagram">
- Relational Schema
<img src="images/relational.png" alt="Person with 3 attributes, Relational Schema">
- SQL Query
<pre>
CREATE TABLE person(
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(50),
	email VARCHAR(50),
	ph_no VARCHAR(20),
	PRIMARY KEY (id)
);
</pre>

## အပေါ်ကပုံအတွက် Java POJO
- အောက်က POJOအတိုင်းရေးလိုက်ရုံနဲ့ Hibernateက tableဆောက်ပေးတယ်။ အရမ်းလွယ်သွားလား?
```
public class Person {
	private Long id;
	private String name;
	private String email;
	private String ph_no;
	
	//getters and setters
}
```
- ဒါပေမယ့် အဲ့လိုဆောက်ရုံနဲ့တော့ မရသေးဘူး။ Hibernateအလုပ်လုပ်ဖို့ အောက်ကလို Annotationတွေတပ်ပေးရမယ်
```
@Entity
@Table(name="person")
public class Person {
	@Id
	@GenerateValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name");
	private String name;
	
	@Column(name="email");
	private String email;
	
	@Column(name="ph_no");
	private String phNo;
}
```

## Annotationsတွေအကြောင်း
- **@Entity**
  - Entityဖြစ်ကြောင်း Hibernateကို အသိပေးတာပါ။
- **@Table(name="custom_name")**
  - Tableဖြစ်ကြောင်း Hibernateကို အသိပေးတာပါ။ **@Table**လို့ပဲရေးထားတယ်ဆိုရင်တော့ Hibernateက Java Classရဲ့ နာမည်အတိုင်း table nameယူပြီးဆောက်သွားမှာပါ၊ ဒါပေမယ့် lowercase characatersနဲ့ပဲဆောက်မှာပါ၊ "person"ဆိုပြီးတော့ပေါ့။
  - **@Table annotationအကြောင်း ပိုသိရန်**
    - **@UniqueConstraint**ဆိုတာကို columnထဲမှာထည့်မယ့် valueတွေက တူလို့မရမယ့် အခြေအနေရှိနေရင် ထည့်ရပါမယ်။ ဒီမှာမထည့်ဘဲ **@Column(unique=true)**မှာထည့်လည်းရပါတယ်။
```
@Table(name = "person", uniqueConstraints = {
		@UniqueConstraint(
				columnNames = {"id", "email", "ph_no"}
		)
})
```
- **@Id**
  - IDဖြစ်ကြောင်း Hibernateကိုအသိပေးတာပါ
  - Integerထက် Longကိုသုံးစေချင်ပါတယ်၊ ဘာကြောင့်လဲဆိုတော့...
     - Integer = (−32,767 to +32,767) range. 16 bitsပဲရှိပြီး
     - Long = (−2,147,483,647 to +2,147,483,647) range. 32 bitsရှိလို့ ပိုဆန့်ပါတယ်။
- **@GenerateValue(strategy=GenerationType.IDENTITY)**
  - ID valueကို အလိုအလေျာက်တိုးပါမယ်လို့ပြောတာပါ။
- **@Column(name="custom_name")**
  - Columnဖြစ်ကြောင်း အသိပေးတာပါ။
  - **@Column annotationအကြောင်း ပိုသိရန်**
    - unique (default=false)
    - updatable (default=true)
    - nullable (default=true)
    - length (default=255)
    - [precision](https://stackoverflow.com/questions/4078559/how-to-specify-doubles-precision-on-hibernate)
    - [columnDefinition](https://stackoverflow.com/questions/16078681/what-properties-does-column-columndefinition-make-redundant)
    - [scale](https://stackoverflow.com/questions/4078559/how-to-specify-doubles-precision-on-hibernate)

## Electronics Engineer-cum-J2EE Backend Developer ##
- တင်သူ - အေးချမ်းအောင်သွင်
