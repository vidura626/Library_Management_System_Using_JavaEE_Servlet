package lk.ijse.lms.repository.custom.imple;

import lk.ijse.lms.entity.Member;
import lk.ijse.lms.repository.custom.MemberRepo;
import org.hibernate.Session;

import java.util.List;

public class MemberRepoImple implements MemberRepo {
    @Override
    public void add(Member member, Session session) {
session.save(member);
    }

    @Override
    public void update(Member member, Session session) {
session.update(member);
    }

    @Override
    public Member search(String s, Session session) {
        return session.get(Member.class, s);
    }

    @Override
    public void delete(String s, Session session) {
session.delete(session.load(Member.class,s));
    }

    @Override
    public List<Member> getAll(Session session) {
        return session.createQuery("from Member").setCacheable(true).getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        String lastId = (String) session.createQuery("select b.id from Member b order by b.id desc").setCacheable(true).getSingleResult();
        if (lastId == null) {
            return "B-001";
        } else {
            int i = Integer.parseInt(lastId.replace("B-", ""))+1;
            return String.format("B-%03d",i);
        }
    }
}
