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
    private String contactNumber;
    private String contactEmail;
    private String companyName;
    private String companyNumber;
    private String vatNumber;
    private String companyAddress;
    private String companyPostcode;
    private String estimatedBudget;
}
