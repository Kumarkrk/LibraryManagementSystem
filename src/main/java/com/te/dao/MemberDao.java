
package com.te.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.te.model.Member;
import com.te.util.HibernateUtil;

public class MemberDao {

    public Member save(Member member) {
        Transaction tx = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.persist(member);

            tx.commit();
            return member;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public Member findById(Long id) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Member.class, id);
        }
    }

    public List<Member> findAll() {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM Member m ORDER BY m.id",
                Member.class
            ).getResultList();
        }
    }

    public List<Member> searchMembers(String keyword) {
        String q = keyword == null ? "" :
            keyword.trim().toLowerCase();

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM Member m WHERE " +
                "LOWER(m.name) LIKE :q OR " +
                "LOWER(m.email) LIKE :q OR " +
                "m.phone LIKE :q OR " +
                "STR(m.id) LIKE :q " +
                "ORDER BY m.id",
                Member.class
            ).setParameter("q", "%" + q + "%")
             .getResultList();
        }
    }

    public long countByEmail(String email) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                "SELECT COUNT(m) FROM Member m " +
                "WHERE LOWER(m.email) = :email",
                Long.class
            ).setParameter("email", email.trim().toLowerCase())
             .uniqueResult();

            return count == null ? 0 : count;
        }
    }

    public long countByEmailExcludingId(String email, Long id) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                "SELECT COUNT(m) FROM Member m " +
                "WHERE LOWER(m.email) = :email AND m.id <> :id",
                Long.class
            ).setParameter("email", email.trim().toLowerCase())
             .setParameter("id", id)
             .uniqueResult();

            return count == null ? 0 : count;
        }
    }

    public Member update(Member member) {
        Transaction tx = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Member updatedMember = session.merge(member);

            tx.commit();
            return updatedMember;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public boolean hasIssueHistory(Long memberId) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                "SELECT COUNT(i) FROM Issue i " +
                "WHERE i.member.id = :id",
                Long.class
            ).setParameter("id", memberId)
             .uniqueResult();

            return count != null && count > 0;
        }
    }

    public boolean deleteMember(int id) {
        Transaction tx = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Member member = session.get(Member.class, id);

            if (member == null) {
                tx.commit();
                return false;
            }

            Long count = session.createQuery(
                "SELECT COUNT(i) FROM Issue i " +
                "WHERE i.member.id = :id",
                Long.class
            ).setParameter("id", id)
             .uniqueResult();

            if (count != null && count > 0) {
                throw new IllegalStateException(
                    "Cannot delete a member with issue history");
            }

            session.remove(member);

            tx.commit();
            return true;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
}
