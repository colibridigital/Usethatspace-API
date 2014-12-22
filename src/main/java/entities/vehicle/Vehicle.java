package entities.vehicle;

import lombok.Data;

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

    private List<Route> routeList;
    private List<Parking> parkingList;
}
