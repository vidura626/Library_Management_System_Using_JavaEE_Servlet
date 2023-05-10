package lk.ijse.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatogeryDTO implements SuperDTO {
    String cat_id;
    String cato_name;
}