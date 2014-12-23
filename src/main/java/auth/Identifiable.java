package auth;

import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;
import lombok.Data;
import persistence.MongoBase;

@Data
public abstract class Identifiable extends MongoBase{
    @Indexed(value= IndexDirection.ASC, name="userNameIndex", unique=true)
    private String username;
    private Password password;
    private Token token;
}
