package lk.ijse.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookDTO implements SuperDTO {
    private String b_id;
    private String isbn;
    private String name;
    private String author;
    private String description;
    private int qty;
    private int rem_qty;
}
