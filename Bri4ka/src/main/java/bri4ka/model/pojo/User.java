package bri4ka.model.pojo;

import bri4ka.model.dto.user.RegisterRequestUserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private int age;
    @Column
    private String address;
    @OneToMany(mappedBy = "owner")
    private List<Car> cars;
    @ManyToMany
    @JoinTable(
            name = "users_like_cars",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "car_id")}
    )
    @JsonManagedReference
    private List<Car> likedCars;

    public User(RegisterRequestUserDTO userDTO){
        firstName = userDTO.getFirstName();
        lastName = userDTO.getLastName();
        username = userDTO.getUsername();
        password = userDTO.getPassword();
        email = userDTO.getEmail();
        age = userDTO.getAge();
        address = userDTO.getAddress();
    }

    public void add(Car car) {
        if (cars == null) {
            cars = new ArrayList<>();
        }
        cars.add(car);

    }
}
