import com.google.gson.Gson;
import entities.Business;

public class CreateBusiness {
    public static void main(String[] args){
        Business business= getBusiness();

//        UserDAO dao = new UserDAO();
//        dao.save(user);

        Gson gson = new Gson();
        String json = gson.toJson(business, Business.class);
        System.out.println();
    }

    private static Business getBusiness() {
        Business business = new Business();
        business.setTitle("Mr");
        business.setFirstName("James");
        business.setLastName("Cross");
        business.setCompanyName("Noble");
        business.setCompanyNumber("1234");
        business.setVatNumber("5678");
        business.setPosition("Engineer");
        business.setAddr1("10 Downing Street");
        business.setAdd2("Whitehall");
        business.setPosition("W1 6GH");
        business.setTown("London");
        business.setCountry("UK");
        business.setContactNumber("123456789");
        business.setEstimatedBudget(999);

        return business;
    }
}
