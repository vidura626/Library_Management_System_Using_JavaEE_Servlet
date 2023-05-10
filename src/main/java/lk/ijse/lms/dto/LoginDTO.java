package lk.ijse.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO implements SuperDTO {
    private String l_id;
    private String name;
    private String address;
    private String username;
    private String password;
}
