package auth;

import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;
import lombok.Data;
import persistence.MongoBase;

@Data
public abstract class Identity extends MongoBase{

}
