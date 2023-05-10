package lk.ijse.lms.contoller.repository.custom.imple;

import lk.ijse.lms.contoller.entity.Login;
import lk.ijse.lms.contoller.repository.custom.LoginRepo;
import org.hibernate.Session;

import java.util.List;

public class LoginARepoImple implements LoginRepo {
    @Override
    public void add(Login login, Session session) throws Exception {
        session.save(login);
    }

    @Override
    public void update(Login login, Session session) throws Exception {
        session.update(login);
    }

    @Override
    public Login search(String s, Session session) throws Exception {
        return session.get(Login.class, s);
    }

    @Override
    public void delete(String s, Session session) throws Exception {
        session.delete(session.load(Login.class, s));
    }

    @Override
    public List<Login> getAll(Session session) throws Exception {
        return session.createQuery("from Login").setCacheable(true).getResultList();
    }

    @Override
    public String generateNextId(Session session) throws Exception {
        String lastId = (String) session.createQuery("select b.id from Login b order by b.id desc").setCacheable(true).getSingleResult();
        if (lastId == null) {
            return "L-001";
        } else {
            int i = Integer.parseInt(lastId.replace("L-", "")) + 1;
            return String.format("L-%03d", i);
        }
    }
}
