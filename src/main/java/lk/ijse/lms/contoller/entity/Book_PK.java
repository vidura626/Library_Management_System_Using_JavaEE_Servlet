package lk.ijse.lms.contoller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book_PK implements Serializable {
    private String b_id;
    private String ISBN;
}
