
package com.te.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.te.dao.IssueDao;
import com.te.model.Book;
import com.te.model.Issue;
import com.te.model.IssueStatus;
import com.te.model.Member;
import com.te.util.HibernateUtil;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class IssueService {

    private final IssueDao issueDao = new IssueDao();

    private final Validator validator =
        Validation.buildDefaultValidatorFactory().getValidator();

    private void validate(Issue issue) {
        Set<ConstraintViolation<Issue>> errors =
            validator.validate(issue);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(
                errors.iterator().next().getMessage());
        }
    }

    public Issue issueBook(int memberId, int bookId) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                Member member =
                    issueDao.findMemberForUpdate(session, memberId);

                if (member == null) {
                    throw new IllegalArgumentException(
                        "Member not found");
                }

                Book book =
                    issueDao.findBookForUpdate(session, bookId);

                if (book == null) {
                    throw new IllegalArgumentException(
                        "Book not found");
                }

                if (book.getQuantity() == null ||
                    book.getQuantity() <= 0) {
                    throw new IllegalStateException(
                        "No copies are available");
                }

                Issue issue = new Issue();
                issue.setMember(member);
                issue.setBook(book);
                issue.setIssueDate(LocalDate.now());
                issue.setStatus(IssueStatus.ISSUED);

                validate(issue);

                book.setQuantity(book.getQuantity() - 1);
                issueDao.save(session, issue);

                tx.commit();
                return issue;

            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }

    public Issue returnBook(int issueId) {
        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                Issue issue =
                    issueDao.findIssueForUpdate(session, issueId);

                if (issue == null) {
                    throw new IllegalArgumentException(
                        "Issue not found");
                }

                if (issue.getStatus() != IssueStatus.ISSUED) {
                    throw new IllegalStateException(
                        "Book has already been returned");
                }

                Book book = issueDao.findBookForUpdate(
                    session, issue.getBook().getId().intValue());

                if (book == null) {
                    throw new IllegalStateException("Book not found");
                }

                issue.setReturnDate(LocalDate.now());
                issue.setStatus(IssueStatus.RETURNED);
                book.setQuantity(book.getQuantity() + 1);

                tx.commit();
                return issue;

            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }

    public Issue getIssueById(Long id) {
        return issueDao.findById(id);
    }

    public List<Issue> getAllIssues() {
        return issueDao.findAll();
    }

    public List<Issue> getActiveIssues() {
        return issueDao.findActiveIssues();
    }

    public List<Issue> getIssuesByMember(Long memberId) {
        return issueDao.findByMember(memberId);
    }

    public List<Issue> getIssuesByBook(Long bookId) {
        return issueDao.findByBook(bookId);
    }
}
