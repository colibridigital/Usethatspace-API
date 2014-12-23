import auth.Password;
import auth.TokenFactory;
import entities.Hobby;
import entities.User;
import entities.vehicle.Parking;
import entities.vehicle.Route;
import entities.vehicle.Vehicle;
import persistence.UserDAO;

public class CreateUser {
    public static void main(String[] args){
        User user = getUser();

        UserDAO dao = new UserDAO();
        dao.save(user);
    }

    private static User getUser() {
        User user = new User();
        user.setUsername("jamescross91");
        user.setPassword(new Password("password"));
        user.setToken(TokenFactory.generate());

        user.setTitle("Mr");
        user.setFirstName("James");
        user.setLastName("Cross");
        user.setGender("Male");
        user.setDob("210491");
        user.setEmploymentStatus("Employed");
        user.setOccupation("Engineer");
        user.setInstitution("Reading");
        user.setEduCourse("Computer Engineering");
        user.setAddr1("Flat 2");
        user.setAdd2("97 Earls Court Road");
        user.setPostCode("W8 6QH");
        user.setTown("London");
        user.setCountry("UK");
        user.setNationality("British");
        user.setEthnicity("White");
        user.setCountryOfBirth("UK");
        user.setEmailAddress("james_cross91@hotmail.com");
        user.setLandLine("01707 662538");
        user.setMobile("07522 700296");
        user.setHasLicence(true);
        user.setHasConvictions(false);
        user.setTermsAgreed(true);
        user.setMarketOptOut(false);

        user.addVehicle(getVehicle1());
        user.addVehicle(getVehicle2());
        user.addHobby(getHobby1());
        return user;
    }

    private static Vehicle getVehicle1() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("BMW");
        vehicle.setModel("3 Series");
        vehicle.setColour("Grey");
        vehicle.setRegistration("GJ08TTO");
        vehicle.setMileage(82000);
        vehicle.setRoadLegal(true);
        vehicle.setMileage(8000);

        Route route1 = new Route();
        route1.setStartPostCode("EN6 1LG");
        route1.setEndPostCode("W8 6QH");
        route1.setStartTime(0L);
        route1.setDuration(1L);
        route1.setWeeklyFreq(1);

        Route route2 = new Route();
        route2.setStartPostCode("W8 6QH\"");
        route2.setEndPostCode("EN6 1LG");
        route2.setStartTime(0L);
        route2.setDuration(1L);
        route2.setWeeklyFreq(1);

        Parking parking1 = new Parking();
        parking1.setPostcode("W8 6QH");
        parking1.setFromTime(0L);
        parking1.setToTime(1L);
        parking1.setWeeklyFreq(5);

        Parking parking2 = new Parking();
        parking2.setPostcode("EN6 1LG");
        parking2.setFromTime(0L);
        parking2.setToTime(1L);
        parking2.setWeeklyFreq(1);

        vehicle.addRoute(route1);
        vehicle.addRoute(route2);
        vehicle.addParking(parking1);
        vehicle.addParking(parking2);
        return vehicle;
    }

    private static Vehicle getVehicle2() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Ford");
        vehicle.setModel("Escort");
        vehicle.setColour("Grey");
        vehicle.setRegistration("123fdf");
        vehicle.setMileage(9999);
        vehicle.setRoadLegal(false);
        vehicle.setMileage(8000);

        Route route1 = new Route();
        route1.setStartPostCode("DJS 1LG");
        route1.setEndPostCode("G7 6QH");
        route1.setStartTime(0L);
        route1.setDuration(1L);
        route1.setWeeklyFreq(1);

        Route route2 = new Route();
        route2.setStartPostCode("G1 6QH\"");
        route2.setEndPostCode("EN6 Blah");
        route2.setStartTime(0L);
        route2.setDuration(1L);
        route2.setWeeklyFreq(1);

        Parking parking1 = new Parking();
        parking1.setPostcode("W8 6QH");
        parking1.setFromTime(0L);
        parking1.setToTime(1L);
        parking1.setWeeklyFreq(5);

        Parking parking2 = new Parking();
        parking2.setPostcode("EN6 1LG");
        parking2.setFromTime(0L);
        parking2.setToTime(1L);
        parking2.setWeeklyFreq(1);

        vehicle.addRoute(route1);
        vehicle.addRoute(route2);
        vehicle.addParking(parking1);
        vehicle.addParking(parking2);
        return vehicle;
    }

    private static Hobby getHobby1() {
        Hobby hobby = new Hobby();
        hobby.setName("Football");
        hobby.setDescription("Kicking a ball");
        hobby.setWeeklyFreq(1);
        hobby.setTakesCar(true);

        return hobby;
    }

    private static Hobby getHobby2() {
        Hobby hobby = new Hobby();
        hobby.setName("Golf");
        hobby.setDescription("Hitting a ball");
        hobby.setWeeklyFreq(2);
        hobby.setTakesCar(true);

        return hobby;
    }
}
