package bri4ka.model.dto.user;

import bri4ka.model.pojo.Car;
import bri4ka.model.pojo.User;

import java.util.List;

public class UserWithoutPasswordDTO {

    private int id;
    private String username;
    private String email;
    private int age;
    private String address;
    private List<Car> cars;

    public UserWithoutPasswordDTO(User user){
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        age = user.getAge();
        address = user.getAddress();
        cars = user.getCars();
    }
}
