
package com.te.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.te.model.Book;
import com.te.util.HibernateUtil;

public class BookDao {

    public Book save(Book book) {
        Transaction tx = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.persist(book);

            tx.commit();
            return book;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public Book findById(Long id) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Book.class, id);
        }
    }

    public List<Book> findAll() {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM Book b ORDER BY b.id", Book.class
            ).getResultList();
        }
    }

    public List<Book> searchBooks(
            String isbn, String title,
            String author, String category) {

        StringBuilder hql = new StringBuilder("FROM Book b WHERE 1=1");

        if (hasText(isbn)) {
            hql.append(" AND b.isbn = :isbn");
        }

        if (hasText(title)) {
            hql.append(" AND LOWER(b.title) LIKE :title");
        }

        if (hasText(author)) {
            hql.append(" AND LOWER(b.author) LIKE :author");
        }

        if (hasCategory(category)) {
            hql.append(" AND b.category = :category");
        }

        hql.append(" ORDER BY b.id");

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Query<Book> query =
                session.createQuery(hql.toString(), Book.class);

            if (hasText(isbn)) {
                query.setParameter("isbn", isbn.trim());
            }

            if (hasText(title)) {
                query.setParameter("title",
                    "%" + title.trim().toLowerCase() + "%");
            }

            if (hasText(author)) {
                query.setParameter("author",
                    "%" + author.trim().toLowerCase() + "%");
            }

            if (hasCategory(category)) {
                query.setParameter(
                    "category",
                    com.te.model.Category.valueOf(
                        category.trim().toUpperCase()
                    )
                );
            }

            return query.getResultList();
        }
    }

    public long countByIsbn(String isbn) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                    "SELECT COUNT(b) FROM Book b WHERE b.isbn = :isbn",
                    Long.class)
                .setParameter("isbn", isbn)
                .uniqueResult();

            return count == null ? 0L : count;
        }
    }

    public long countByIsbnExcludingId(String isbn, Long id) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                "SELECT COUNT(b) FROM Book b " +
                "WHERE b.isbn = :isbn AND b.id <> :id",
                Long.class
            ).setParameter("isbn", isbn)
             .setParameter("id", id)
             .uniqueResult();

            return count == null ? 0 : count;
        }
    }

    public Book update(Book book) {
        Transaction tx = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Book updatedBook = session.merge(book);

            tx.commit();
            return updatedBook;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public boolean hasIssueHistory(Long bookId) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                "SELECT COUNT(i) FROM Issue i WHERE i.book.id = :id",
                Long.class
            ).setParameter("id", bookId).uniqueResult();

            return count != null && count > 0;
        }
    }

    public boolean delete(Long id) {
        Transaction tx = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Book book = session.get(Book.class, id);

            if (book == null) {
                tx.commit();
                return false;
            }

            session.remove(book);

            tx.commit();
            return true;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean hasCategory(String category) {
        return hasText(category)
            && !category.trim().equalsIgnoreCase("All Categories")
            && !category.trim().equalsIgnoreCase("All category");
    }
}
