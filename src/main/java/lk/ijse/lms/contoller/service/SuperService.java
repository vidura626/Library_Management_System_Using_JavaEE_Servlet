package lk.ijse.lms.contoller.service;

import lk.ijse.lms.contoller.dto.SuperDTO;

import java.util.List;

public interface SuperService<T extends SuperDTO> {
    void save(T t);
    T search(String id);
    List<T> getAll();
    void openSession();
    void closeSession();
}
