package entities;

import com.google.code.morphia.annotations.Entity;
import lombok.Data;
import persistence.MongoBase;

@Data
@Entity
public class Business extends MongoBase {
    private String title;
    private String firstName;
    private String lastName;
    private String companyName;
    private String companyNumber;
    private String vatNumber;
    private String position;
    private String addr1;
    private String add2;
    private String postCode;
    private String town;
    private String country;
    private String contactNumber;
    private int estimatedBudget;
}
