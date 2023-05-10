package lk.ijse.lms.contoller.repository.custom.imple;

import lk.ijse.lms.contoller.entity.Issue;
import lk.ijse.lms.contoller.repository.custom.IssueRepo;
import org.hibernate.Session;

import java.util.List;

public class IssueRepoImple implements IssueRepo {
    @Override
    public void add(Issue issue, Session session) throws Exception {
        session.save(issue);
    }

    @Override
    public void update(Issue issue, Session session) throws Exception {
        session.update(issue);
    }

    @Override
    public Issue search(String s, Session session) throws Exception {
        return session.get(Issue.class, s);
    }

    @Override
    public void delete(String s, Session session) throws Exception {
        session.delete(session.load(Issue.class, s));
    }

    @Override
    public List<Issue> getAll(Session session) throws Exception {
        return session.createQuery("from Issue").setCacheable(true).getResultList();
    }

    @Override
    public String generateNextId(Session session) throws Exception {
        return null;
    }
}
