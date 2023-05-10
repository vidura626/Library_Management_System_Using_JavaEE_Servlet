package lk.ijse.lms.service.custom;

import lk.ijse.lms.dto.Book_PK;
import lk.ijse.lms.dto.MemberDTO;
import lk.ijse.lms.service.SubServices;

public interface MemberService extends SubServices<MemberDTO> {
    boolean isExist(String id);
}
