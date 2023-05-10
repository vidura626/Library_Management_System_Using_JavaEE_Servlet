package lk.ijse.lms.contoller.entity;

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
public class Issue implements SuperEntity {
    @Id
    @Column(insertable = false, updatable = false)
    private String memberId;
    @Id
    @Column(insertable = false, updatable = false)
    private String bookId;
    @Id
    @Column(insertable = false, updatable = false)
    private String bookISBN;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued_date;

    @ManyToOne
    @JoinColumn(name = "memberId", referencedColumnName = "m_id", nullable = false)
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumns(
            {
                    @JoinColumn(name = "bookId", referencedColumnName = "b_Id", nullable = false),
                    @JoinColumn(name = "bookISBN", referencedColumnName = "ISBN", nullable = false)
            }
    )
    private Book book;

}
