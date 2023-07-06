package bri4ka.repository;

import bri4ka.model.pojo.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT c " +
            "FROM Car AS c " +
            "WHERE c.id = :id")
    Optional<Car> findById(int id);

    @Query("SELECT c FROM Car AS c WHERE UPPER(c.vin) = UPPER(:vin)")
    Optional<Car> findByVinIgnoreCase(String vin);
}
