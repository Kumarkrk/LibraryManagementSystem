
package com.te.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.te.model.User;
import com.te.util.HibernateUtil;

public class UserDao {

    public User save(User user) {
        Transaction tx = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.persist(user);

            tx.commit();
            return user;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public long countByUsername(String username) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                "SELECT COUNT(u) FROM User u " +
                "WHERE LOWER(u.username) = :username",
                Long.class
            ).setParameter("username", normalize(username))
             .uniqueResult();

            return count == null ? 0 : count;
        }
    }

    public User findByUsername(String username) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM User u WHERE LOWER(u.username) = :username",
                User.class
            ).setParameter("username", normalize(username))
             .uniqueResult();
        }
    }

    private String normalize(String username) {
        return username.trim().toLowerCase(java.util.Locale.ROOT);
    }
}
