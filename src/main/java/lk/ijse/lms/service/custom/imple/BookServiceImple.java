package lk.ijse.lms.service.custom.imple;

import lk.ijse.lms.dto.BookDTO;
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
    Session session = FactoryConfiguration.getInstance().operSession();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(BookDTO bookDTO){
        openSession(session);
        repo.update(modelMapper.map(bookDTO, Book.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id){
        openSession(session);
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(BookDTO bookDTO){
        openSession(session);
        repo.add(modelMapper.map(bookDTO, Book.class), session);
        closeSession(session);
    }

    @Override
    public BookDTO search(String id){
        openSession(session);
        BookDTO map = modelMapper.map(repo.search(id, session), BookDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<BookDTO> getAll() {
        openSession(session);
        List from_book = session.createQuery("from Book").setCacheable(true).getResultList();
        Object map = modelMapper.map(from_book, new TypeToken<List<BookDTO>>() {
        }.getType());
        closeSession(session);
        return (List<BookDTO>) map;
    }

}
