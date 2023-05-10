package lk.ijse.lms.contoller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.lms.contoller.entity.Book;
import lk.ijse.lms.contoller.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueDTO implements SuperDTO {
    private String memberId;
    private String bookId;
    private String bookISBN;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued_date;
    private Member member;
    private Book book;
}
