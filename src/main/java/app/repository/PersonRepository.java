package app.repository;

import app.model.Person;

public interface PersonRepository extends CRUDRepository<Person, Integer> {

    Person findByName(String name);

    Person findByNameAndPassword(String name, String password);
}
