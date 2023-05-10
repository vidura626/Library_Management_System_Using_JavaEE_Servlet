package lk.ijse.lms.service.custom;

import lk.ijse.lms.dto.LoginDTO;
import lk.ijse.lms.service.SubServices;

public interface LoginService extends SubServices<LoginDTO> {
    boolean isExist(String id);
}
