package lk.ijse.lms.service.custom.imple;

import lk.ijse.lms.dto.BookDTO;
import lk.ijse.lms.dto.Book_PK;
import lk.ijse.lms.entity.Book;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.repository.custom.BookRepo;
import lk.ijse.lms.service.custom.BookService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class BookServiceImple implements BookService {
    BookRepo repo = (BookRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOK);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(BookDTO bookDTO) {
        Session session = openSession();
        repo.update(modelMapper.map(bookDTO, Book.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) {
        throw new RuntimeException("Delete service which is parameter has String id is not allowed for BookService");
    }

    @Override
    public void save(BookDTO bookDTO) {
        Session session = openSession();
        repo.add(modelMapper.map(bookDTO, Book.class), session);
        closeSession(session);
    }

    @Override
    public BookDTO search(String id) {
        throw new RuntimeException("Search service which is parameter has String id is not allowed for BookService");
    }

    @Override
    public List<BookDTO> getAll() {
        Session session = openSession();
        List from_book = session.createQuery("from Book").setCacheable(true).getResultList();
        Object map = modelMapper.map(from_book, new TypeToken<List<BookDTO>>() {
        }.getType());
        closeSession(session);
        return (List<BookDTO>) map;
    }

    @Override
    public BookDTO search(Book_PK book_pk) {
        Session session = openSession();
        Book book = session.get(Book.class, modelMapper.map(book_pk, lk.ijse.lms.entity.Book_PK.class));
        closeSession(session);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public void delete(Book_PK book_pk) {
        Session session = openSession();
        Book load = session.load(Book.class, modelMapper.map(book_pk, lk.ijse.lms.entity.Book_PK.class));
        session.delete(load);
        closeSession(session);
    }

    @Override
    public boolean isExist(Book_PK book_pk) {
        Session session = openSession();
        Book book = session.get(Book.class, modelMapper.map(book_pk, lk.ijse.lms.entity.Book_PK.class));
        closeSession(session);
        return book != null;
    }
}
