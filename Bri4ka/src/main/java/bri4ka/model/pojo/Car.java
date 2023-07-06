package bri4ka.model.pojo;

import bri4ka.model.dto.car.CarRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String manufacturer;
    private String model;
    private String vin;
    private String color;
    private int year;
    private int km;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "car")
    @JsonManagedReference
    private List<CarImage> images;
    @ManyToMany(mappedBy = "likedCars")
    @JsonBackReference
    private List<User> likers;


    public Car(CarRequestDTO carDTO){
        manufacturer = carDTO.getManufacturer();
        model = carDTO.getModel();
        vin = carDTO.getVin();
        color = carDTO.getColor();
        year = carDTO.getYear();
        km = carDTO.getKm();
        likers = new ArrayList<>();
        //images = carDTO.getImages();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
