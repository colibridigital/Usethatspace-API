package persistence;

import com.google.code.morphia.dao.BasicDAO;
import entities.Business;
import entities.User;
import org.bson.types.ObjectId;

public class BusinessDAO extends BasicDAO<Business, ObjectId> {
	public BusinessDAO() {
		super(Business.class, MongoConnectionManager.instance().getDb());
	}
}
