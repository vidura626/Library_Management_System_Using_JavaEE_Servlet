package lk.ijse.lms.service.custom;

import lk.ijse.lms.dto.BookDTO;
import lk.ijse.lms.dto.Book_PK;
import lk.ijse.lms.service.SubServices;

public interface BookService extends SubServices<BookDTO> {
    BookDTO search(Book_PK book_pk);
    boolean isExist(Book_PK book_pk);
}
