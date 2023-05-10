package lk.ijse.lms.contoller.servlets;

import com.google.gson.Gson;
import lk.ijse.lms.dto.BookDTO;
import lk.ijse.lms.dto.Book_PK;
import lk.ijse.lms.dto.MemberDTO;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.service.ServiceFactory;
import lk.ijse.lms.service.custom.BookService;
import lk.ijse.lms.service.custom.MemberService;
import lk.ijse.lms.util.Response;
import org.modelmapper.ModelMapper;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/member")
public class MemberController extends HttpServlet {
    MemberService service = (MemberService) ServiceFactory.getInstance().getRepo(RepoFactory.RepoTypes.MEMBER);
    ModelMapper modelMapper = new ModelMapper();
    Gson gson = new Gson();

    JsonObject dataJsonObjectCreator(MemberDTO member) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("m_id", member.getM_id());
        objectBuilder.add("name", member.getName());
        objectBuilder.add("address", member.getAddress());
        objectBuilder.add("contact", member.getContact());
        return objectBuilder.build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        Gson gson = new Gson();
        String option = req.getParameter("OPTION");
        try {
            switch (option) {
                case "SEARCH":
                    if (service.isExist(req.getParameter("m_id"))) {
                        MemberDTO search = service.search(req.getParameter("m_id"));
                        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
                        responseBuilder.add("Code", 200);
                        responseBuilder.add("Message", "Success");
                        responseBuilder.add("Data", dataJsonObjectCreator(search));
                        resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Success", dataJsonObjectCreator(search))));
                    } else {
                        resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Member not found", "")));
                    }
                    break;
                case "GETALL":
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    service.getAll().forEach(memberDTO -> arrayBuilder.add(dataJsonObjectCreator(memberDTO)));
                    JsonArray data = arrayBuilder.build();
                    resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Success", data)));
                    break;
                default:
                    resp.getWriter().write(gson.toJson(Response.getInstance().generateResponse(400, "No option found", "")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(gson.toJson(Response.getInstance().generateResponse(500, (e.getLocalizedMessage()), "")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        Gson gson = new Gson();

        try {
            String memberId = jsonObject.getString("m_id");
            String name = jsonObject.getString("name");
            String address = jsonObject.getString("address");
            String contact = jsonObject.getString("contact");

            if (service.isExist(memberId)) {
                resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Already Exists", "")));
                return;
            }

            service.save(new MemberDTO(memberId, name, address, contact));
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Success", "")));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(500, "Error ".concat(e.getLocalizedMessage()), "")));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
            MemberDTO book = new MemberDTO(
                    jsonObject.getString("m_id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("address"),
                    jsonObject.getString("contact")
            );
            boolean exist = service.isExist(book.getM_id());
            if (exist) {
                service.update(book);
                resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Success", "")));
                return;
            }

            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Book not found", "")));

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(500, "Error ".concat(e.getLocalizedMessage()), "")));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String m_id = req.getParameter("m_id");
        try {
            if (service.isExist(m_id)) {
                service.delete(m_id);
                resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(200, "Delete Success", "")));
                return;
            }
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(400, "Book not found", "")));
        } catch (Exception e) {
            resp.getWriter().write(String.valueOf(Response.getInstance().generateResponse(500, "Error", "")));
        }
    }
}
