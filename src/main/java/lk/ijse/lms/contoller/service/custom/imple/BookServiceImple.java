package lk.ijse.lms.contoller.service.custom.imple;

import lk.ijse.lms.contoller.dto.BookDTO;
import lk.ijse.lms.contoller.entity.Book;
import lk.ijse.lms.contoller.repository.RepoFactory;
import lk.ijse.lms.contoller.repository.custom.BookRepo;
import lk.ijse.lms.contoller.service.custom.BookService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;

import java.util.List;

public class BookServiceImple implements BookService {
    BookRepo repo = (BookRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOK);
    Session session = FactoryConfiguration.getInstance().operSession();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(BookDTO bookDTO) {
        openSession();
        repo.update(modelMapper.map(bookDTO, Book.class), session);
        closeSession();
    }

    @Override
    public void delete(String id) {
        openSession();
        repo.delete(id, session);
        closeSession();
    }

    @Override
    public void save(BookDTO bookDTO) {
        openSession();
        repo.add(modelMapper.map(bookDTO, Book.class), session);
        closeSession();
    }

    @Override
    public BookDTO search(String id) {
        openSession();
        BookDTO map = modelMapper.map(repo.search(id, session), BookDTO.class);
        closeSession();
        return map;
    }

    @Override
    public List<BookDTO> getAll() {
        openSession();
        List from_catogery = session.createQuery("from Book").setCacheable(true).getResultList();
        closeSession();
        return from_catogery;
    }

    @Override
    public void openSession() {
        session.beginTransaction();
    }

    @Override
    public void closeSession() {
        session.getTransaction().commit();
        session.close();
    }
}
