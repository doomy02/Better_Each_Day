package app.single_point_access;

import app.repository.CarRepository;
import app.repository.PersonRepository;
import app.repository.implemetation.CarRepositoryImpl;
import app.repository.implemetation.PersonRepositoryImpl;

public class RepositorySinglePointAccess {

    private static PersonRepository personRepository;
    private static CarRepository carRepository;

    static {

        personRepository = new PersonRepositoryImpl();
        carRepository = new CarRepositoryImpl();
    }

    public static PersonRepository getPersonRepository() {
        return personRepository;
    }

    public static CarRepository getCarRepository() {
        return carRepository;
    }
}
