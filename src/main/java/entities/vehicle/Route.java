package entities.vehicle;

import lombok.Data;

@Data
public class Route {
    private String startPostCode;
    private String endPostCode;
    private long startTime;
    private long duration;
    private int weeklyFreq;
}
