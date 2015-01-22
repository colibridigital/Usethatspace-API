package entities;

import lombok.Data;

@Data
public class Hobby {
    private String name;
    private String description;
    private String weeklyFreq;
    private boolean takesCar;
}
