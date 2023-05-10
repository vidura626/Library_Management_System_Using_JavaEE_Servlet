package lk.ijse.lms.repository.custom.imple;

import lk.ijse.lms.entity.Return;
import lk.ijse.lms.repository.custom.ReturnRepo;
import org.hibernate.Session;

import java.util.List;

public class ReturnRepoImple implements ReturnRepo {
    @Override
    public void add(Return aReturn, Session session) {
        session.save(aReturn);
    }

    @Override
    public void update(Return aReturn, Session session) {
        session.update(aReturn);
    }

    @Override
    public Return search(String s, Session session) {
        return session.get(Return.class, s);
    }

    @Override
    public void delete(String s, Session session) {
        session.delete(session.load(Return.class, s));
    }

    @Override
    public List<Return> getAll(Session session) {
        return session.createQuery("from Return").setCacheable(true).getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        String lastId = (String) session.createQuery("select b.id from Return b order by b.id desc").setCacheable(true).getSingleResult();
        if (lastId == null) {
            return "Ret-001";
        } else {
            int i = Integer.parseInt(lastId.replace("Ret-", "")) + 1;
            return String.format("Ret-%03d", i);
        }
    }
}
