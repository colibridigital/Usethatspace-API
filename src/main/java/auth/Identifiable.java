package auth;

import persistence.MongoBase;

public abstract class Identifiable extends MongoBase{
    private String username;
    private Password password;
    private String token;
}
