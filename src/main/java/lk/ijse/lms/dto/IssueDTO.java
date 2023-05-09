package lk.ijse.lms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueDTO implements SuperDTO {
    private String memberId;
    private String bookId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued_date;
    private MemberDTO member;
    private BookDTO bookDTO;
}
