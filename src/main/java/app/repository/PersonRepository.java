package app.repository;

import app.model.Person;

public interface PersonRepository extends CRUDRepository<Person, Integer> {

    Person findByName(String name);

    Person findByEmailAndPassword(String email, String password);

    Person findByEmail(String email);

    Person register(String name, String email, String password);
}
