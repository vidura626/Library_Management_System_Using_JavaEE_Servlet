package lk.ijse.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Member implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String address;
    private String contact;
}
