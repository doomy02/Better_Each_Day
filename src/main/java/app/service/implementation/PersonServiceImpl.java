package app.service.implementation;

import app.model.Badge;
import app.model.Person;
import app.model.Quest;
import app.repository.BadgeRepository;
import app.repository.PersonRepository;
import app.repository.QuestRepository;
import app.service.PersonService;
import app.service.QuestService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository = RepositorySinglePointAccess.getPersonRepository();
    private final BadgeRepository badgeRepository = RepositorySinglePointAccess.getBadgeRepository();
    private final QuestRepository questRepository = RepositorySinglePointAccess.getQuestRepository();

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
    public void addQuest(Person person, Quest quest) {
        if (person.getQuests() == null) {
            person.setQuests(new ArrayList<>());
        }

        if (quest.getId() == null || questRepository.findById(quest.getId()) == null) {
            quest = questRepository.save(quest);
        }

        person.getQuests().add(quest);

        personRepository.update(person);
    }

    @Override
    public Person login(String name, String password) {
        return personRepository.findByEmailAndPassword(name, password);
    }

    @Override
    public Person register(String name, String password, String email) {
        return personRepository.register(name, password, email);
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
}
