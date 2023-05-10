package lk.ijse.lms.repository.custom.imple;

import lk.ijse.lms.entity.Issue;
import lk.ijse.lms.repository.custom.IssueRepo;
import org.hibernate.Session;

import java.util.List;

public class IssueRepoImple implements IssueRepo {
    @Override
    public void add(Issue issue, Session session) {
        session.save(issue);
    }

    @Override
    public void update(Issue issue, Session session) {
        session.update(issue);
    }

    @Override
    public Issue search(String s, Session session) {
        return session.get(Issue.class, s);
    }

    @Override
    public void delete(String s, Session session) {
        session.delete(session.load(Issue.class, s));
    }

    @Override
    public List<Issue> getAll(Session session) {
        return session.createQuery("from Issue").setCacheable(true).getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        return null;
    }
}
