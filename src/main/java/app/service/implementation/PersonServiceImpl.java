package app.service.implementation;

import app.model.Badge;
import app.model.Person;
import app.repository.BadgeRepository;
import app.repository.PersonRepository;
import app.service.PersonService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository = RepositorySinglePointAccess.getPersonRepository();
    private BadgeRepository badgeRepository= RepositorySinglePointAccess.getCarRepository();

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
    public void addBadge(Person person, Badge badge) {
        if (person.getBadges() == null) {
            person.setBadges(new ArrayList<>());
        }

        if (badge.getId() == null || badgeRepository.findById(badge.getId()) == null) {
            badge = badgeRepository.save(badge);
        }

        person.getBadges().add(badge);

        personRepository.update(person);

    }

    @Override
    public Person login(String name, String password) {
        return personRepository.findByEmailAndPassword(name, password);
    }
}
