package lk.ijse.lms.entity;

import lk.ijse.lms.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Login implements SuperEntity {
    @Id
    private String l_id;
    private String name;
    private String address;
    private String username;
    private String password;
}
