package lk.ijse.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(Book_PK.class)
public class Book implements SuperEntity {
    @Id
    private String id;
    @Id
    private String ISBN;
    private String name;
    private String author;
    private String description;
    private int qty;
    private int rem_qty;
}
