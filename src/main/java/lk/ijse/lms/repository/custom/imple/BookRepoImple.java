package lk.ijse.lms.repository.custom.imple;

import lk.ijse.lms.entity.Book;
import lk.ijse.lms.repository.custom.BookRepo;
import org.hibernate.Session;

import java.util.List;

public class BookRepoImple implements BookRepo {
    @Override
    public void add(Book book, Session session) {
        session.save(book);
    }

    @Override
    public void update(Book book, Session session) {
        session.update(book);
    }

    @Override
    public Book search(String s, Session session) {
        return session.get(Book.class, s);
    }

    @Override
    public void delete(String s, Session session) {
        session.delete(session.load(Book.class,s));
    }

    @Override
    public List<Book> getAll(Session session) {
        return session.createQuery("from Book").setCacheable(true).getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        String lastId = (String) session.createQuery("select b.id from Book b order by b.b_id desc").setCacheable(true).getSingleResult();
        if (lastId == null) {
            return "B-001";
        } else {
            int i = Integer.parseInt(lastId.replace("B-", ""))+1;
            return String.format("B-%03d",i);
        }
    }
}
