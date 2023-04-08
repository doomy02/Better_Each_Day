package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Person;
import app.repository.PersonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        // TO DO
        // Same logic - extract it somehow
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

        // Native SQL - not preferred
        // Query query = session.createSQLQuery("select * from person");

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

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Person where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Person> query = session.getNamedQuery("findPersonById");
        query.setParameter("id", id);

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

        // Used a Named Query - best solution
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

        // TO DO
        // Same logic - extract it somehow
        TypedQuery<Person> query = session.getNamedQuery("findPersonByEmailAndPassword");
        query.setParameter("email", email);
        query.setParameter("password", password);

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
}
