package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Badge;
import app.repository.BadgeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class BadgeRepositoryImpl implements BadgeRepository {
    @Override
    public Badge save(Badge entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnCarSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnCarSaved);
    }

    @Override
    public Badge update(Badge entity) {

        return null;
    }

    @Override
    public Badge findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Badge> query = session.getNamedQuery("findCarById");
        query.setParameter("id", id);

        Badge badge;
        try {
            badge = query.getSingleResult();
        } catch (NoResultException e) {
            badge = null;
        }

        transaction.commit();
        session.close();

        return badge;
    }

    @Override
    public List<Badge> findAll() {

        return null;
    }

    @Override
    public boolean delete(Badge entity) {

        return false;
    }
}
