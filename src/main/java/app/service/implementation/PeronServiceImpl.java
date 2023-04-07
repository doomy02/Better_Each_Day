package app.service.implementation;

import app.model.Car;
import app.model.Person;
import app.repository.CarRepository;
import app.repository.PersonRepository;
import app.service.PersonService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.ArrayList;
import java.util.List;

public class PeronServiceImpl implements PersonService {

    private PersonRepository personRepository = RepositorySinglePointAccess.getPersonRepository();
    private CarRepository carRepository = RepositorySinglePointAccess.getCarRepository();

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.update(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public boolean delete(Person person) {
        return personRepository.delete(person);
    }

    @Override
    public void addCar(Person person, Car car) {
        if (person.getCars() == null) {
            person.setCars(new ArrayList<>());
        }

        if (car.getId() == null || carRepository.findById(car.getId()) == null) {
            car = carRepository.save(car);
        }

        person.getCars().add(car);

        personRepository.update(person);

    }

    @Override
    public Person login(String name, String password) {
        return personRepository.findByNameAndPassword(name, password);
    }
}
