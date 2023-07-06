package bri4ka.repository;

import bri4ka.model.pojo.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<CarImage, Integer> {


}
