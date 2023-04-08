package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Person;
import app.repository.PersonRepository;
import app.service.PersonService;
import app.single_point_access.ServiceSinglePointAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public Person save(Person entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnPersonSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnPersonSaved);
    }

    @Override
    public Person update(Person entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public List<Person> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Person> query = session.getNamedQuery("findAllPersons");
        List<Person> persons = query.getResultList();

        transaction.commit();
        session.close();

        return persons;
    }

    @Override
    public Person findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Person> query = session.getNamedQuery("findPersonById");
        query.setParameter("id", id);

        Person person;
        try {
            person = query.getSingleResult();
        } catch (NoResultException e) {
            person = null;
        }

        transaction.commit();
        session.close();

        return person;
    }


    @Override
    public boolean delete(Person entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public Person findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Person> query = session.getNamedQuery("findPersonByName");
        query.setParameter("name", name);
        Person person;
        try {
            person = query.getSingleResult();
        } catch (NoResultException e) {
            person = null;
        }


        transaction.commit();
        session.close();

        return person;
    }

    @Override
    public Person findByEmailAndPassword(String email, String password) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.getNamedQuery("findPersonByEmailAndPassword");
        query.setParameter("email", email);
        query.setParameter("password", password);

        Person person;
        try {
            person = (Person) query.getSingleResult();
        } catch (NoResultException e) {
            person = null;
        }

        transaction.commit();
        session.close();

        return person;
    }

    @Override
    public Person findByEmail(String email) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Person> query = session.getNamedQuery("findPersonByEmail");
        query.setParameter("email", email);

        Person person;
        try {
            person = query.getSingleResult();
        } catch (NoResultException e) {
            person = null;
        }

        transaction.commit();
        session.close();

        return person;
    }

    @Override
    public Person register(String name, String password, String email)
    {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Person user = new Person();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        PersonService personService = ServiceSinglePointAccess.getPersonService();
        Person savedPerson = personService.save(user);

        transaction.commit();
        session.close();

        return savedPerson;
    }
}
