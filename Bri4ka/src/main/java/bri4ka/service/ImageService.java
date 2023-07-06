package bri4ka.service;

import bri4ka.exceptions.FileHandlingException;
import bri4ka.exceptions.ImageProcessingException;
import bri4ka.exceptions.NotFoundException;
import bri4ka.model.pojo.Car;
import bri4ka.model.pojo.CarImage;
import bri4ka.repository.CarRepository;
import bri4ka.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final CarRepository carRepository;
    @Value("${file.path}")
    private String filePath;

    public ImageService(ImageRepository imageRepository, CarRepository carRepository) {
        this.imageRepository = imageRepository;
        this.carRepository = carRepository;
    }

    public CarImage uploadPicture(int id, MultipartFile file) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with id " + id + " not found."));

        Path imagePath = generateImagePath(id);
        try {
            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, file.getBytes());

            CarImage carImage = createCarImage(imagePath, car);
            return imageRepository.save(carImage);
        } catch (IOException e) {
            throw new FileHandlingException("Error while handling the file.", e);
        }
    }

    private Path generateImagePath(int id) {
        String uniqueFileName = id + "_" + UUID.randomUUID() + ".png";
        return Paths.get(filePath, String.valueOf(id), uniqueFileName);
    }

    private CarImage createCarImage(Path imagePath, Car car) {
        CarImage carImage = new CarImage();
        carImage.setUrl(imagePath.toAbsolutePath().toString());
        carImage.setCar(car);
        return carImage;
    }

    public byte[] getById(int id) {
        System.out.println("hit1");
        CarImage carImage = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image with id " + id + " not found."));
        System.out.println("hit2");
        String url = carImage.getUrl();
        Path imagePath = Paths.get(url);

        try {
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new ImageProcessingException("Error while reading image file.", e);
        }
    }
}
