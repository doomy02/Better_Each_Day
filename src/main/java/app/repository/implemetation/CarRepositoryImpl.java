package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import app.repository.CarRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    @Override
    public Car save(Car entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnCarSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnCarSaved);
    }

    @Override
    public Car update(Car entity) {
        // TO DO
        return null;
    }

    @Override
    public Car findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Car> query = session.getNamedQuery("findCarById");
        query.setParameter("id", id);

        Car car;
        try {
            car = query.getSingleResult();
        } catch (NoResultException e) {
            car = null;
        }

        transaction.commit();
        session.close();

        return car;
    }

    @Override
    public List<Car> findAll() {
        // TO DO
        return null;
    }

    @Override
    public boolean delete(Car entity) {
        // TO DO
        return false;
    }
}
