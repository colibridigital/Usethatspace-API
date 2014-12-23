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


    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String employmentStatus;
    private String occupation;
    private String institution;
    private String eduCourse;
    private String addr1;
    private String add2;
    private String postCode;
    private String town;
    private String country;
    private String nationality;
    private String ethnicity;
    private String countryOfBirth;
    private String emailAddress;
    private String landLine;
    private String mobile;
    private boolean hasLicence;
    private boolean hasConvictions;
    private boolean termsAgreed;
    private boolean marketOptOut;

    private List<Hobby> hobbyList = new ArrayList<>();
    private List<Vehicle> vehicleList = new ArrayList<>();

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
        vehicleList.add(vehicle);
    }

    public void addHobby(Hobby hobby) {
        hobbyList.add(hobby);
    }
}

