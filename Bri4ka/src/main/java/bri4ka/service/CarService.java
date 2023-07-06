package bri4ka.service;

import bri4ka.exceptions.BadRequestException;
import bri4ka.exceptions.NotAcceptableException;
import bri4ka.exceptions.NotFoundException;
import bri4ka.model.dto.car.CarRequestDTO;
import bri4ka.model.dto.car.CarWithoutOwnerDTO;
import bri4ka.model.dto.user.ResponseUserDTO;
import bri4ka.model.pojo.Car;
import bri4ka.model.pojo.User;
import bri4ka.repository.CarRepository;
import bri4ka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    @Autowired
    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }


    public CarWithoutOwnerDTO addCar(CarRequestDTO carDTO, int userId) {
        carRepository.findByVinIgnoreCase(carDTO.getVin())
                .ifPresent(existingCar -> {
                    throw new NotAcceptableException("Car with this VIN already exists.");
                });
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id " + userId + " not found."));
        Car car = new Car(carDTO);
        car.setOwner(user);
        car = carRepository.save(car);

        return new CarWithoutOwnerDTO(car);
    }

    public CarWithoutOwnerDTO getCarById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with id " + id + " not found."));

        return new CarWithoutOwnerDTO(car);
    }

    public CarWithoutOwnerDTO likeCar(int id, int userId) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with id " + id + " not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found."));

        if(user.getLikedCars().contains(car)){
            throw new BadRequestException("car already like");
        }

        user.getLikedCars().add(car);
        userRepository.save(user);

//        car.getLikers().add(user);
//        carRepository.save(car);

        return new CarWithoutOwnerDTO(car);
    }

    public List<CarWithoutOwnerDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarWithoutOwnerDTO> response = new ArrayList<>();
        for(Car c : cars){
           response.add(new CarWithoutOwnerDTO(c));
        }
        return response;
    }

}
