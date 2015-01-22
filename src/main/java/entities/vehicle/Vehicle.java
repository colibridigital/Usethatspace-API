package entities.vehicle;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vehicle {
    private String make;
    private String model;
    private String colour;
    private String registration;
    private int mileage;
    private boolean roadLegal;
    private int anualMileage;

//    private List<Route> routeList = new ArrayList<>();
//    private List<Parking> parkingList = new ArrayList<>();
//
//    public void addRoute(Route route) {
//        routeList.add(route);
//    }
//
//    public void addParking(Parking parking) {
//        parkingList.add(parking);
//    }
}
