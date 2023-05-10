package lk.ijse.lms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Return implements SuperEntity{
    @Id
    private String r_id;
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnedDate;
}
