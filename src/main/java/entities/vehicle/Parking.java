package entities.vehicle;

import lombok.Data;

@Data
public class Parking {
    private String postcode;
    private long fromTime;
    private long toTime;
    private int weeklyFreq;
}
