package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Journey;
import app.repository.JourneyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class JourneyRepositoryImpl implements JourneyRepository {
    @Override
    public Journey save(Journey entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnJourneySaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnJourneySaved);
    }

    @Override
    public Journey update(Journey entity) {
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
    public Journey findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Journey> query = session.getNamedQuery("findJourneyById");
        query.setParameter("id", id);

        Journey j;
        try {
            j = query.getSingleResult();
        } catch (NoResultException e) {
            j = null;
        }

        transaction.commit();
        session.close();

        return j;
    }

    @Override
    public List<Journey> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Journey> query = session.getNamedQuery("findAllJourneys");
        List<Journey> j = query.getResultList();

        transaction.commit();
        session.close();

        return j;
    }

    @Override
    public boolean delete(Journey entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
}
