package lk.ijse.lms.contoller.service;

import lk.ijse.lms.contoller.dto.SuperDTO;
import org.hibernate.Session;

import java.util.List;

public interface SuperService<T extends SuperDTO> {
    void save(T t) throws Exception;
    T search(String id) throws Exception;
    List<T> getAll() throws Exception;
    default void openSession(Session session){
        session.beginTransaction();
    }
    default void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    }
}
