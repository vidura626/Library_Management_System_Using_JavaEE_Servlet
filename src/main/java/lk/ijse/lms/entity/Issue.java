package lk.ijse.lms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(Issue_PK.class)
public class Issue implements SuperEntity{
    @Id
    private String memberId;
    @Id
    private String bookId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued_date;

    @ManyToOne
    @JoinColumn(name = "memberId",referencedColumnName ="id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookId",referencedColumnName ="id", nullable = false)
    private Book book;
}
