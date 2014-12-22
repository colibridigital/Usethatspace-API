package entities;

import auth.Identifiable;
import com.google.code.morphia.annotations.Entity;
import entities.vehicle.Vehicle;
import lombok.Data;
import persistence.MongoBase;

import java.util.List;

@Data
@Entity
public class User extends Identifiable {
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
    private String countryOfBirth;
    private String emailAddress;
    private String landLine;
    private String mobile;
    private boolean hasLicence;
    private boolean hasConvictions;
    private boolean termsAgreed;
    private boolean marketOptOut;

    private List<Hobby> hobbyList;
    private List<Vehicle> vehicleList;
}

