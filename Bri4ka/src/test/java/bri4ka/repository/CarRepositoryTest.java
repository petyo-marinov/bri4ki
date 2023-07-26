package bri4ka.repository;

import bri4ka.model.dto.car.CarRequestDTO;
import bri4ka.model.pojo.Car;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    CarRepository underTest;

    @Test
    @Transactional
    @DisplayName("Check if car exists by ID")
    void itShouldCheckIfCarExistsById() {
        CarRequestDTO carDto = new CarRequestDTO(
                "Ford",
                "Mondeo",
                "3GNNK13ZX3R2989941",
                "yellow",
                2004,
                370000);
        Car car = new Car(carDto);

        underTest.save(car);
        int carId = car.getId();
        Optional<Car> expected = underTest.findById(carId);

        assertTrue(expected.isPresent());
        assertEquals(car, expected.get());
    }

    @Test
    @Transactional
    @DisplayName("Check if car exists by VIN (case-insensitive)")
    void itShouldCheckIfCarExistsByVinIgnoreCase() {
        CarRequestDTO carDto = new CarRequestDTO(
                "Ford",
                "Mondeo",
                "4TEST13ZX3R2989941",
                "yellow",
                2004,
                370000);
        Car car = new Car(carDto);

        underTest.save(car);
        String vinToSearch = "4test13zx3r2989941";
        Optional<Car> expected = underTest.findByVinIgnoreCase(vinToSearch);

        assertTrue(expected.isPresent());
        assertEquals(car, expected.get());
    }
}