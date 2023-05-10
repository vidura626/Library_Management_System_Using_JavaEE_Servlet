package lk.ijse.lms.entity;

import com.sun.istack.NotNull;
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
    @NotNull
    private String b_id;
    @Id
    @NotNull
    private String ISBN;
    @NotNull
    private String name;
    @NotNull
    private String author;
    @NotNull
    private String description;
    @NotNull
    private int qty;
    @NotNull
    private int rem_qty;
}
