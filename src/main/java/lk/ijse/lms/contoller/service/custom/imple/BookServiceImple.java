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
    public void update(BookDTO bookDTO) throws Exception{
        openSession(session);
        repo.update(modelMapper.map(bookDTO, Book.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) throws Exception{
        openSession(session);
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(BookDTO bookDTO) throws Exception{
        openSession(session);
        repo.add(modelMapper.map(bookDTO, Book.class), session);
        closeSession(session);
    }

    @Override
    public BookDTO search(String id) throws Exception{
        openSession(session);
        BookDTO map = modelMapper.map(repo.search(id, session), BookDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<BookDTO> getAll() {
        openSession(session);
        List from_catogery = session.createQuery("from Book").setCacheable(true).getResultList();
        closeSession(session);
        return from_catogery;
    }

}
