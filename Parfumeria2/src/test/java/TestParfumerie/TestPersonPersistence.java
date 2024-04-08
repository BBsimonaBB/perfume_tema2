package TestParfumerie;


import com.example.parfumeria2.Model.Persistence.CreateDBTables;
import com.example.parfumeria2.Model.Persistence.PersonPersistence;
import com.example.parfumeria2.Model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestPersonPersistence {

    public int populatePersonTable() {
        PersonPersistence personPersistence = new PersonPersistence();
        String[] id = {"u01", "u02", "u03", "u04"};
        String[] names = {"John", "Jane", "Bob", "Alice"};
        String[] surnames = {"Smith", "Joe", "Dylan", "Gold"};
        String[] emails = {"johnsmith@gmail.com", "janejoe@hotmail.uk.co", "bobdylan@gmail.com", "alicegold@gmail.com"};
        String[] passwords = {"12345", "24680", "abcde", "fghij"};
        Person.Job[] jobs = {Person.Job.Employee, Person.Job.Employee, Person.Job.Manager, Person.Job.Admin};
        for (int i = 0; i < names.length; i++) {
            Person u = new Person(id[i], names[i], surnames[i],emails[i],passwords[i],jobs[i]);
            personPersistence.createPerson(u);
        }
    return 4; //persoane adaugate

    }
    @Test
    public void testCreatePerson() {
        CreateDBTables.createDatabase();
        CreateDBTables.createPersonTable();
        PersonPersistence personPersistence = new PersonPersistence();
        personPersistence.deletePerson("u0test");
        Person p = new Person("u0test", "John", "Smith", "johnsmith@example.com", "password", Person.Job.Employee);
        //Act
        boolean result1 = personPersistence.createPerson(p);
        //Assert
        assertTrue(result1);

    }
    @Test
    public void testReadPerson() {
        CreateDBTables.createDatabase();
        CreateDBTables.createPersonTable();
        PersonPersistence personPersistence = new PersonPersistence();
        //Act
        Person pp = new Person("u0test", "John", "Smith", "johnsmith@example.com", "password", Person.Job.Employee);
        personPersistence.createPerson(pp);
        Person p = personPersistence.readPerson("u0test");
        //Assert
        int f = 0;
        if(pp.getID().equals(p.getID()) && pp.getName().equals(p.getName()) && pp.getSurname().equals(p.getSurname())
            && pp.getEmail().equals(p.getEmail()) && pp.getPassword().equals(p.getPassword()) && pp.getJob().equals(p.getJob()))
            f = 1;
         Assertions.assertEquals(f, 1);

    }
    @Test
    public void testUpdatePerson() {
        CreateDBTables.createDatabase();
        CreateDBTables.createPersonTable();
        PersonPersistence personPersistence = new PersonPersistence();
        //Act
        Person p = new Person("u0test", "John", "Smith", "johnsmith@example.com", "password", Person.Job.Employee);
        personPersistence.createPerson(p);
        boolean result1 = personPersistence.updatePersonField("u0test","name","Johnny");
        //Assert
        assertTrue(result1);
    }
    @Test
    public void testSearchPerson(){
        PersonPersistence personPersistence = new PersonPersistence();
        CreateDBTables.createDatabase();
        CreateDBTables.createPersonTable();
        Person p = new Person("u0test", "John", "Smith", "johnsmith@example.com", "password", Person.Job.Employee);
        //Act
        boolean result1 = personPersistence.createPerson(p);
        Person pp = personPersistence.searchPersonByName("John");
        Assertions.assertNotNull(pp);

        personPersistence.deletePerson("u0test");
    }
    @Test
    public void testDeletePerson(){
        CreateDBTables.createDatabase();
        CreateDBTables.createPersonTable();
        PersonPersistence personPersistence = new PersonPersistence();
        Person p = new Person("u0test", "John", "Smith", "johnsmith@example.com", "password", Person.Job.Employee);
        //Act
        personPersistence.createPerson(p);
        boolean result = personPersistence.deletePerson("u0test");
        assertTrue(result);
    }
    @Test
    public void testgetAllPersons(){
        CreateDBTables.createDatabase();
        CreateDBTables.createPersonTable();
        PersonPersistence personPersistence = new PersonPersistence();
        populatePersonTable();
        ArrayList<Person> persons = personPersistence.getAllPersons();
        Assertions.assertEquals(persons.size(),4);
    }

}