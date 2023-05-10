package lk.ijse.lms.contoller.servlets;

import lk.ijse.lms.contoller.entity.Book;
import lk.ijse.lms.contoller.entity.Book_PK;
import lk.ijse.lms.contoller.entity.Catogery;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/book")
public class BookController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        Session session = FactoryConfiguration.getInstance().operSession();
        session.beginTransaction();
        session.save(new Book("Cat-01","wefw","ewf","ewfewf","efewf",23,3));
//        Book_PK book_pk = new Book_PK("C1", "wefw");
        session.getTransaction().commit();
        session.close();
        System.out.println("Saved");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
