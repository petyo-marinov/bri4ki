package bri4ka.model.dto.car;

import bri4ka.model.pojo.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarWithoutOwnerDTO {

    private int id;
    private String manufacturer;
    private String model;
    private String color;
    private int year;
    private int km;

    public CarWithoutOwnerDTO(Car car){
        id = car.getId();
        manufacturer = car.getManufacturer();
        model = car.getModel();
        color = car.getColor();
        year = car.getYear();
        km = car.getKm();
    }
}
