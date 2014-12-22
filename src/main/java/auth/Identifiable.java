package auth;

import lombok.Data;
import persistence.MongoBase;

@Data
public abstract class Identifiable extends MongoBase{
    private String username;
    private Password password;
    private Token token;
}
