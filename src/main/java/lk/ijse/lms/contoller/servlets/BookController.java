package lk.ijse.lms.contoller.servlets;

import com.google.gson.Gson;
import lk.ijse.lms.dto.BookDTO;
import lk.ijse.lms.dto.Book_PK;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.service.ServiceFactory;
import lk.ijse.lms.service.custom.BookService;
import lk.ijse.lms.util.Response;
import org.modelmapper.ModelMapper;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/book")
public class BookController extends HttpServlet {
    BookService service = (BookService) ServiceFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOK);
    ModelMapper modelMapper = new ModelMapper();
    Gson gson = new Gson();

    JsonObject dataJsonObjectCreator(BookDTO book) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("b_id", book.getB_id());
        objectBuilder.add("isbn", book.getIsbn());
        objectBuilder.add("name", book.getName());
        objectBuilder.add("author", book.getAuthor());
        objectBuilder.add("description", book.getDescription());
        objectBuilder.add("qty", book.getQty());
        objectBuilder.add("rem_qty", book.getRem_qty());
        return objectBuilder.build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        Gson gson = new Gson();
        String option = req.getParameter("OPTION");
        Response response;
        try {
            switch (option) {
                case "SEARCH":
                    if (service.isExist(new Book_PK(req.getParameter("id"), req.getParameter("isbn")))) {
                        BookDTO search = service.search(new Book_PK(req.getParameter("id"), req.getParameter("isbn")));
                        resp.getWriter().write(gson.toJson(new Response(200, "Book found", dataJsonObjectCreator(search))));
                    } else {
                        resp.getWriter().write(gson.toJson(new Response(400, "Book not found", null)));
                    }
                    break;
                case "GETALL":
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    service.getAll().forEach(bookDTO -> arrayBuilder.add(dataJsonObjectCreator(bookDTO)));
                    JsonArray data = arrayBuilder.build();
                    resp.getWriter().write(gson.toJson(new Response(400, "Success", data)));
                    break;
                default:
                    resp.getWriter().write(gson.toJson(new Response(400, "No option found", null)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(gson.toJson(new Response(500, "Error".concat(e.getLocalizedMessage()), null)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        Gson gson = new Gson();

        try {
            String bookId = jsonObject.getString("b_id");
            String isbn = jsonObject.getString("isbn");
            String name = jsonObject.getString("name");
            String author = jsonObject.getString("author");
            String description = jsonObject.getString("description");
            int qty = jsonObject.getInt("qty");
            int remQty = jsonObject.getInt("rem_qty");

            if (service.isExist(new Book_PK(bookId, isbn))) {
                resp.getWriter().write(gson.toJson(new Response(400, "Already Exists", null)));
                return;
            }

            service.save(new BookDTO(bookId, isbn, name, author, description, qty, remQty));
            resp.getWriter().write(gson.toJson(new Response(200, "Success", null)));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(gson.toJson(new Response(500, "Error ".concat(e.getLocalizedMessage()), null)));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
            BookDTO book = new BookDTO(
                    jsonObject.getString("b_id"),
                    jsonObject.getString("isbn"),
                    jsonObject.getString("name"),
                    jsonObject.getString("author"),
                    jsonObject.getString("description"),
                    jsonObject.getInt("qty"),
                    jsonObject.getInt("rem_qty")
            );
            boolean exist = service.isExist(new Book_PK(book.getB_id(), book.getIsbn()));
            System.out.println(exist);
            if (exist) {
                service.update(book);
                resp.getWriter().write(gson.toJson(new Response(200, "Success", null)));
                return;
            }

            resp.getWriter().write(gson.toJson(new Response(400, "Book not found", null)));

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(gson.toJson(new Response(500, "Error ".concat(e.getLocalizedMessage()), null)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        String isbn = req.getParameter("isbn");
        try {
            if (service.isExist(new Book_PK(bookId, isbn))) {
                service.delete(new Book_PK(bookId, isbn));
                resp.getWriter().write(gson.toJson(new Response(200, "Delete Success", null)));
                return;
            }
            resp.getWriter().write(gson.toJson(new Response(400, "Book not found", null)));
        } catch (Exception e) {
            resp.getWriter().write(gson.toJson(new Response(500, "Error", null)));
        }

    }
}
