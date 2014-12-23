package api;

import auth.AuthenticatableResource;
import auth.Password;
import auth.TokenFactory;
import com.google.gson.Gson;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import persistence.UserDAO;

import java.io.IOException;

@Slf4j
public class LoadProfileResource extends AuthenticatableResource{
    @Get
    public String represent() {
        return "Load profile";
    }

    @Post
    public Representation acceptItem(Representation entity) throws JSONException, IOException {
        //Get me the JSON
        JSONObject json = new JsonRepresentation(entity).getJsonObject();
        JSONObject authJSON;

        //Log it
        logRequest(json.toString());

        if(!authenticateRequest(json))
            return authenticationError();

        //Get the device specific JSON
        if(json.has("auth") )
            authJSON = json.getJSONObject("auth");
        else
            return getResponseRepresentation(false, "No data for a device was supplied");

        String userJsonString = getUserJson(authJSON);

        return new JsonRepresentation(userJsonString);
    }

    public String getUserJson(JSONObject authJSON) throws JSONException {
        String username = authJSON.getString("username");
        UserDAO userDAO = new UserDAO();
        User thisUser = userDAO.findByUsername(username);

        //Remove password field
        thisUser.setPassword(new Password());

        //Jsonify user and reply to the client
        Gson gson = new Gson();

        return gson.toJson(thisUser, User.class);
    }

    private JsonRepresentation getResponseRepresentation(boolean success, String message){
        JSONObject object = new JSONObject();
        try {
            object.put("operation_success", success);
            object.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        log.info("Response to client was " + " sucess: " + success + " message: " + message);

        return new JsonRepresentation(object);
    }

}
