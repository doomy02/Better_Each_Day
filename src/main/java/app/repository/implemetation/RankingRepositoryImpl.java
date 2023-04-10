package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Ranking;
import app.repository.RankingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class RankingRepositoryImpl implements RankingRepository {

    @Override
    public Ranking findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Ranking> query = session.getNamedQuery("findRankingById");
        query.setParameter("id", id);

        Ranking r;
        try {
            r = query.getSingleResult();
        } catch (NoResultException e) {
            r = null;
        }

        transaction.commit();
        session.close();

        return r;
    }

    @Override
    public List<Ranking> findAll() {
        return null;
    }

    @Override
    public boolean delete(Ranking entity) {
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
    public Ranking save(Ranking r) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnRankingSaved = (Integer) session.save(r);

        transaction.commit();
        session.close();

        return findById(idOnRankingSaved);
    }

    @Override
    public Ranking update(Ranking r) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = r.getId();
        session.saveOrUpdate(r);

        transaction.commit();
        session.close();

        return findById(id);
    }
}
