package lk.ijse.lms.contoller.repository;

import lk.ijse.lms.contoller.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface Crud<T extends SuperEntity,ID extends Serializable> extends SuperRepo {
    void add(T t, Session session) throws Exception;
    void update(T t, Session session) throws Exception;
    T search(ID id, Session session) throws Exception;
    void delete(ID id, Session session) throws Exception;
    List<T> getAll(Session session) throws Exception;
    ID generateNextId(Session session) throws Exception;
}
