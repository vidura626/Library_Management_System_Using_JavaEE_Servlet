package lk.ijse.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO implements SuperDTO {
    private String m_id;
    private String name;
    private String address;
    private String contact;
}
