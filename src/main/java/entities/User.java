package entities;

import auth.Identity;
import auth.Password;
import auth.Token;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;
import entities.vehicle.Vehicle;
import lombok.Data;
import lombok.SneakyThrows;
import org.json.JSONObject;
import persistence.MongoBase;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User extends MongoBase {
    //
    @Indexed(value= IndexDirection.ASC, name="userNameIndex", unique=true)
    private String username;
    private Password password;
    private Token token;


    private String emailAddress;
    private String title;
    private String firstName;
    private String lastName;
    private String postCode;
    private String ethnicity;
    private String countryOfBirth;
    private String mobile;
    private String landLine;
    private String address;
    private String dob;
    private String gender;
    private String employmentStatus;
    private String occupation;
    private String nationality;
    private boolean termsAgreed;
    private List<Vehicle> cars = new ArrayList<>();
    private boolean hasLicence;
    private boolean hasConvictions;
    private List<Hobby> hobbyList = new ArrayList<>();

    @SneakyThrows
    public boolean validatePassword(String password){
        if(password == null)
            return false;

        return this.getPassword().validate(password);
    }

    @SneakyThrows
    public JSONObject authInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.getUsername());
        jsonObject.put("token", this.getToken().getToken());

        return jsonObject;
    }

    public void addVehicle(Vehicle vehicle) {
        cars.add(vehicle);
    }

    public void addHobby(Hobby hobby) {
        hobbyList.add(hobby);
    }
}

