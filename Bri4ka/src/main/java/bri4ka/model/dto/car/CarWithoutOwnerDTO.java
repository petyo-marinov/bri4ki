package bri4ka.model.dto.car;

import bri4ka.model.pojo.Car;
import bri4ka.model.pojo.CarImage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarWithoutOwnerDTO {

    private int id;
    private String manufacturer;
    private String model;
    private String color;
    private int year;
    private int km;
    private int likes;
    private List<CarImage> images;


    public CarWithoutOwnerDTO(Car car){
        id = car.getId();
        manufacturer = car.getManufacturer();
        model = car.getModel();
        color = car.getColor();
        year = car.getYear();
        km = car.getKm();
        images = car.getImages();
        likes = car.getLikers().size();
    }


}
