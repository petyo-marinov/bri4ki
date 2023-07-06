package bri4ka.model.dto.car;

import bri4ka.model.pojo.CarImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CarRequestDTO {

    private String manufacturer;
    private String model;
    private String vin;
    private String color;
    private int year;
    private int km;
    //private List<CarImage> images;
}
