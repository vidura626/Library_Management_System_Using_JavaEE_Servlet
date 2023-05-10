package lk.ijse.lms.contoller.service;

import lk.ijse.lms.contoller.dto.SuperDTO;

public interface SubServices<T extends SuperDTO> extends SuperService<T> {
    void update(T t);
    void delete(String id);
}
