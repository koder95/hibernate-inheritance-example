package core.basesyntax.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.function.Function;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao {
    protected final SessionFactory sessionFactory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <T> T saveEntity(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Cannot save: " + entity, ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }

    protected <T> List<T> findEntities(Function<CriteriaBuilder, CriteriaQuery<T>> queryFactory) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            return session.createQuery(queryFactory.apply(builder)).getResultList();
        } catch (Exception e) {
            throw new HibernateException("Entity not found", e);
        }
    }
}
