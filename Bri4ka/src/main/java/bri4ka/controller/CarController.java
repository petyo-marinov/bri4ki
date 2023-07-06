package bri4ka.controller;

import bri4ka.model.dto.car.CarRequestDTO;
import bri4ka.model.dto.car.CarWithoutOwnerDTO;
import bri4ka.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController extends AbstractController {

    private final CarService carService;
    private final SessionManager sessionManager;

    public CarController(CarService carService, SessionManager sessionManager) {
        this.carService = carService;
        this.sessionManager = sessionManager;
    }

    @PutMapping("/cars")
    public CarWithoutOwnerDTO addCar(@RequestBody CarRequestDTO carDTO, HttpSession session){
        int userId = sessionManager.authorizeLogin(session);
        return carService.addCar(carDTO, userId);
    }

    @GetMapping("/cars")
    public List<CarWithoutOwnerDTO> getAll(){
        return carService.getAllCars();
    }


    @PostMapping("/cars/{id}")
    public CarWithoutOwnerDTO like(@PathVariable int id, HttpSession session){
        int userId = sessionManager.authorizeLogin(session);
        return carService.likeCar(id, userId);
    }

    @GetMapping("/cars/{id}")
    public CarWithoutOwnerDTO getById(@PathVariable int id){
        return carService.getCarById(id);
    }

}
