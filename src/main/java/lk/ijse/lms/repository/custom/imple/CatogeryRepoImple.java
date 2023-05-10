package lk.ijse.lms.repository.custom.imple;

import lk.ijse.lms.entity.Catogery;
import lk.ijse.lms.repository.custom.CatogeryRepo;
import org.hibernate.Session;

import java.util.List;

public class CatogeryRepoImple implements CatogeryRepo {
    @Override
    public void add(Catogery catogery, Session session)  {
        session.save(catogery);
    }

    @Override
    public void update(Catogery catogery, Session session) {
        session.update(catogery);
    }

    @Override
    public Catogery search(String s, Session session) {
        return session.get(Catogery.class, s);
    }

    @Override
    public void delete(String s, Session session) {
        session.delete(session.load(Catogery.class, s));
    }

    @Override
    public List<Catogery> getAll(Session session) {
        return session.createQuery("from Catogery").setCacheable(true).getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        String lastId = (String) session.createQuery("select b.id from Catogery b order by b.id desc").setCacheable(true).getSingleResult();
        if (lastId == null) {
            return "Cat-001";
        } else {
            int i = Integer.parseInt(lastId.replace("Cat-", "")) + 1;
            return String.format("Cat-%03d", i);
        }
    }
}
