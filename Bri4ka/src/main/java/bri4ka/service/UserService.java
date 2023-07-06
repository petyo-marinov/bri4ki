package bri4ka.service;

import bri4ka.exceptions.InvalidCredentialsException;
import bri4ka.exceptions.NotAcceptableException;
import bri4ka.exceptions.NotFoundException;
import bri4ka.model.dto.user.LoginUserDTO;
import bri4ka.model.dto.user.RegisterRequestUserDTO;
import bri4ka.model.dto.user.ResponseUserDTO;
import bri4ka.model.pojo.Car;
import bri4ka.model.pojo.User;
import bri4ka.repository.CarRepository;
import bri4ka.repository.UserRepository;
import bri4ka.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CarRepository carRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseUserDTO addUser(RegisterRequestUserDTO userDTO){
        validateUserDetails(userDTO);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        user = userRepository.save(user);

        return new ResponseUserDTO(user);
    }

    public ResponseUserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found."));
        return new ResponseUserDTO(user);
    }

    public ResponseUserDTO findByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found."));
        return new ResponseUserDTO(user);
    }

    public List<ResponseUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseUserDTO> listOfUsers = new ArrayList<>();
        for(User u : users){
            listOfUsers.add(new ResponseUserDTO(u));
        }
        return listOfUsers;
    }

    public ResponseUserDTO login(LoginUserDTO dto) {
        String errorMessage = "The username or password is incorrect.";
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException(errorMessage));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException(errorMessage);
        }
        return new ResponseUserDTO(user);
    }

    public ResponseUserDTO buyCar(int userId, int carId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car not found"));
        if(car.getOwner() != null){
            throw new NotAcceptableException("Car already bought");
        }
        car.setOwner(user);
        carRepository.save(car);
        return new ResponseUserDTO(userRepository.findById(userId).get());
    }

    private void validateUserDetails(RegisterRequestUserDTO userDTO){
        Validator.validateAge(userDTO.getAge());
        Validator.validateNames(userDTO.getFirstName(), userDTO.getLastName());
        Validator.validateEmail(userDTO.getEmail());
        Validator.validateUsername(userDTO.getUsername());
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new NotAcceptableException("Passwords do not match.");
        }
        Validator.validatePassword(userDTO.getPassword());
        //database-related validations
        userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(u -> {
                    throw new NotAcceptableException("Account with this email already exists.");
                });
        userRepository.findByUsername(userDTO.getUsername())
                .ifPresent(u -> {
                    throw new NotAcceptableException("Account with this username already exists.");
                });
    }

}
