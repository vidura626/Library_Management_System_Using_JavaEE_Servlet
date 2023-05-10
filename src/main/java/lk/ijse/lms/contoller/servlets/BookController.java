package lk.ijse.lms.contoller.servlets;

import com.google.gson.Gson;
import lk.ijse.lms.dto.BookDTO;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.service.ServiceFactory;
import lk.ijse.lms.service.custom.BookService;
import lk.ijse.lms.util.Response;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/book")
public class BookController extends HttpServlet {
    BookService service = (BookService) ServiceFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOK);

    JsonObject dataJsonObjectCreator(BookDTO book){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("b_id",book.getB_id());
        objectBuilder.add("ISBN",book.getISBN());
        objectBuilder.add("name",book.getName());
        objectBuilder.add("author",book.getAuthor());
        objectBuilder.add("description",book.getDescription());
        objectBuilder.add("qty",book.getQty());
        objectBuilder.add("rem_qty",book.getRem_qty());
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
                    BookDTO search = service.search(req.getParameter("id"));
                    JsonObjectBuilder dataJsonBuilder = Json.createObjectBuilder();
                    resp.getWriter().write(gson.toJson(new Response(400, "Success", dataJsonObjectCreator(search))));
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
        System.out.println(req.getParameter("idd"));
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
