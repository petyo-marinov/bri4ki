package bri4ka.model.dto.user;

import bri4ka.model.dto.car.CarWithoutOwnerDTO;
import bri4ka.model.pojo.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ResponseUserDTO {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String address;
    private List<CarWithoutOwnerDTO> cars;
    private List<CarWithoutOwnerDTO> likedCars;

    public ResponseUserDTO(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        username = user.getUsername();
        email = user.getEmail();
        age = user.getAge();
        address = user.getAddress();
        cars = user.getCars() != null
                ? user.getCars().stream()
                .map(CarWithoutOwnerDTO::new)
                .toList()
                : new ArrayList<>();
        likedCars = user.getLikedCars() != null
                ? user.getLikedCars().stream()
                .map(CarWithoutOwnerDTO::new)
                .toList()
                : new ArrayList<>();
    }
}
