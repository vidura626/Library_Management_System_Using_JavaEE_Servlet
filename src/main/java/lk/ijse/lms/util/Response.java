package lk.ijse.lms.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

@Data
public class Response {
    private static Response response;

    private Response() {
    }

    public static Response getInstance() {
        return response == null ? response = new Response() : response;
    }

    public JsonObject generateResponse(int code,String message,JsonObject data){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Code",code);
        objectBuilder.add("Message",message);
        objectBuilder.add("Data",data);
        return objectBuilder.build();
    }
    public JsonObject generateResponse(int code, String message, JsonArray data){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Code",code);
        objectBuilder.add("Message",message);
        objectBuilder.add("Data",data);
        return objectBuilder.build();
    }

    public JsonObject generateResponse(int code, String message, String data){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Code",code);
        objectBuilder.add("Message",message);
        objectBuilder.add("Data", data);
        return objectBuilder.build();
    }
}
