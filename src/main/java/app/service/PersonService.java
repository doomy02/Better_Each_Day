package app.service;

import app.model.Badge;
import app.model.Person;
import app.model.Quest;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    Person update(Person person);

    List<Person> findAll();

    Person findById(Integer id);

    boolean delete(Person person);

    void addBadge(Person person, Badge badge);

    void addQuest(Person person, Quest quest);

    Person login(String name, String password);

    Person register(String name, String password, String email);

    Person findByEmail(String email);
}
