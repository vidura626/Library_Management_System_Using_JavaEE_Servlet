package lk.ijse.lms.contoller.servlets;

import com.google.gson.Gson;
import lk.ijse.lms.dto.BookDTO;
import lk.ijse.lms.dto.Book_PK;
import lk.ijse.lms.dto.LoginDTO;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.service.ServiceFactory;
import lk.ijse.lms.service.custom.BookService;
import lk.ijse.lms.service.custom.LoginService;
import lk.ijse.lms.util.Response;
import org.modelmapper.ModelMapper;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    LoginService service = (LoginService) ServiceFactory.getInstance().getRepo(RepoFactory.RepoTypes.LOGIN);
    ModelMapper modelMapper = new ModelMapper();

    JsonObject dataJsonObjectCreator(LoginDTO login) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("l_id", login.getL_id());
        objectBuilder.add("name", login.getName());
        objectBuilder.add("address", login.getAddress());
        objectBuilder.add("username", login.getUsername());
        objectBuilder.add("password", login.getPassword());
        return objectBuilder.build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        String option = req.getParameter("OPTION");
        try {
            switch (option) {
                case "SEARCH":
                    if (service.isExist(req.getParameter("l_id"))) {
                        LoginDTO search = service.search(req.getParameter("l_id"));
                        resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Login found", dataJsonObjectCreator(search))));
                    } else {
                        resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Login not found", "")));
                    }
                    break;
                case "GETALL":
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    service.getAll().forEach(bookDTO -> arrayBuilder.add(dataJsonObjectCreator(bookDTO)));
                    JsonArray data = arrayBuilder.build();
                    resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Success", data)));
                    break;
                default:
                    resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "No Option Found", "")));

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(500, "Error", "")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        try {
            String loginId = jsonObject.getString("l_id");
            String name = jsonObject.getString("name");
            String address = jsonObject.getString("address");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            if (service.isExist(loginId)) {
                resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Already Exists", "")));
                return;
            }

            service.save(new LoginDTO(loginId, name, address, username, password));
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Success", "")));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(500, "Error ".concat(e.getLocalizedMessage()), "")));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        try {
            JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
            LoginDTO login = new LoginDTO(
                    jsonObject.getString("l_id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("address"),
                    jsonObject.getString("username"),
                    jsonObject.getString("password")
            );
            boolean exist = service.isExist(login.getL_id());
            if (exist) {
                service.update(login);
                resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Success", "")));
                return;
            }

            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Login not found", "")));

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(500, "Error ".concat(e.getLocalizedMessage()), "")));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        String loginId = req.getParameter("l_id");
        try {
            if (service.isExist(loginId)) {
                service.delete(loginId);
                resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Delete Success", "")));
                return;
            }
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Login not found", "")));
        } catch (Exception e) {
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(500, "Error", "")));
        }

    }
}
