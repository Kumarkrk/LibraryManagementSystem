
package com.te.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.te.model.Book;
import com.te.model.Issue;
import com.te.model.IssueStatus;
import com.te.model.Member;

public class IssueDao {

    public Member findMemberForUpdate(Session session, int memberId) {
        return session.find(
            Member.class, memberId, LockMode.PESSIMISTIC_WRITE);
    }

    public Book findBookForUpdate(Session session, int bookId) {
        return session.find(
            Book.class, bookId, LockMode.PESSIMISTIC_WRITE);
    }

    public Issue findIssueForUpdate(Session session, int issueId) {
        return session.find(
            Issue.class, issueId, LockMode.PESSIMISTIC_WRITE);
    }

    public Issue findById(Long id) {
        try (Session session = sessionFactory().openSession()) {
            return session.get(Issue.class, id);
        }
    }

    public List<Issue> findAll() {
        try (Session session = sessionFactory().openSession()) {
            return session.createQuery(
                "FROM Issue i ORDER BY i.issueDate DESC",
                Issue.class
            ).getResultList();
        }
    }

    public List<Issue> findActiveIssues() {
        try (Session session = sessionFactory().openSession()) {

            return session.createQuery(
                "SELECT i FROM Issue i " +
                "JOIN FETCH i.member " +
                "JOIN FETCH i.book " +
                "WHERE i.status = :status " +
                "ORDER BY i.issueDate DESC",
                Issue.class
            )
            .setParameter("status", IssueStatus.ISSUED)
            .getResultList();
        }
    }

    public List<Issue> findByMember(Long memberId) {
        try (Session session = sessionFactory().openSession()) {
            return session.createQuery(
                "FROM Issue i WHERE i.member.id = :id " +
                "ORDER BY i.issueDate DESC",
                Issue.class
            ).setParameter("id", memberId)
             .getResultList();
        }
    }

    public List<Issue> findByBook(Long bookId) {
        try (Session session = sessionFactory().openSession()) {
            return session.createQuery(
                "FROM Issue i WHERE i.book.id = :id " +
                "ORDER BY i.issueDate DESC",
                Issue.class
            ).setParameter("id", bookId)
             .getResultList();
        }
    }

    public void save(Session session, Issue issue) {
        session.persist(issue);
    }

    public void flush(Session session) {
        session.flush();
    }

    private org.hibernate.SessionFactory sessionFactory() {
        return com.te.util.HibernateUtil.getSessionFactory();
    }
}
