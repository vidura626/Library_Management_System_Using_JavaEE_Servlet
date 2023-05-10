package lk.ijse.lms.contoller.repository;

import lk.ijse.lms.contoller.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface Crud<T extends SuperEntity,ID extends Serializable> extends SuperRepo {
    void add(T t, Session session);
    void update(T t, Session session);
    T search(ID id, Session session);
    void delete(ID id, Session session);
    List<T> getAll(Session session);
    ID generateNextId(Session session);
}
