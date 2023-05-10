package lk.ijse.lms.contoller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Catogery implements SuperEntity{
    @Id
    String cat_id;
    String cato_name;
}