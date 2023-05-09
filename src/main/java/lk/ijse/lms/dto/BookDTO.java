package lk.ijse.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO implements SuperDTO {
    private String id;
    private String ISBN;
    private String name;
    private String author;
    private String description;
    private int qty;
    private int rem_qty;
}
