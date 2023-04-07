package app.service;

import app.model.Car;
import app.model.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    Person update(Person person);

    List<Person> findAll();

    Person findById(Integer id);

    boolean delete(Person person);

    void addCar(Person person, Car car);

    Person login(String name, String password);
}
