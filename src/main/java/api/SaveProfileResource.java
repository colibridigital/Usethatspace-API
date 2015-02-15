package api;

import auth.AuthenticatableResource;
import auth.Password;
import auth.Token;
import com.google.gson.Gson;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import mail.RegistrationMailer;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import persistence.UserDAO;

import java.io.IOException;

@Slf4j
public class SaveProfileResource extends AuthenticatableResource{
    @Get
    public String represent() {
        return "Save profile";
    }

    @Post
    public Representation acceptItem(Representation entity) throws JSONException, IOException {
        //Get me the JSON
        JSONObject json = new JsonRepresentation(entity).getJsonObject();

        //Log it
        logRequest(json.toString());

        if(!authenticateRequest(json))
            return authenticationError();

        //Get the device specific JSON
        if(!json.has("auth"))
            return getResponseRepresentation(false, "No auth data was supplied");

        if(!json.has("user"))
            return getResponseRepresentation(false, "No data for a user was supplied");

        processJSON(json);

        return getResponseRepresentation(true, "User data updated");
    }

    public void processJSON(JSONObject json) throws JSONException {
        JSONObject authJSON = json.getJSONObject("auth");
        JSONObject userJSON = json.getJSONObject("user");

        String username = authJSON.getString("username");
        UserDAO userDAO = new UserDAO();
        User thisUser = userDAO.findByUsername(username);

        String existingToken = thisUser.getToken().getToken();
        String existingPassword = thisUser.getPassword().getCipherText();
        ObjectId existingId = thisUser.getId();

        User updatedUser = getUpdatedUser(userJSON, existingToken, existingPassword, existingId);
        userDAO.save(updatedUser);

        RegistrationMailer.send(updatedUser.getEmailAddress());
    }

    private User getUpdatedUser(JSONObject userJSON, String token, String password, ObjectId id) {
        Gson gson = new Gson();

        //Remove the ID field as GSON cannot deserialize it
        if(userJSON.has("id")) {
            userJSON.remove("id");
        }

        User updatedUser = gson.fromJson(userJSON.toString(), User.class);

        updatedUser.setToken(new Token(token));
        Password passwordObj = new Password();
        passwordObj.setCiperText(password);

        updatedUser.setPassword(passwordObj);

        updatedUser.setId(id);

        return updatedUser;
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
