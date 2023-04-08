package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Person;
import app.model.Quest;
import app.repository.QuestRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class QuestRepositoryImpl implements QuestRepository {

    @Override
    public Quest save(Quest entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnQuestSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnQuestSaved);
    }

    @Override
    public Quest update(Quest entity) {
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
    public Quest findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Quest> query = session.getNamedQuery("findQuestById");
        query.setParameter("id", id);

        Quest q;
        try {
            q = query.getSingleResult();
        } catch (NoResultException e) {
            q = null;
        }

        transaction.commit();
        session.close();

        return q;
    }

    @Override
    public List<Quest> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Quest> query = session.getNamedQuery("findAllQuests");
        List<Quest> q = query.getResultList();

        transaction.commit();
        session.close();

        return q;
    }

    @Override
    public boolean delete(Quest entity) {
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
