package bri4ka.controller;

import bri4ka.model.pojo.CarImage;
import bri4ka.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class ImageController extends AbstractController{

    private final ImageService imageService;

    @PutMapping("/cars/{id}/images")
    public CarImage upload(@PathVariable int id, @RequestPart MultipartFile file){
        return imageService.uploadPicture(id, file);
    }

    @GetMapping(value = "/images/{id}", produces = "image/*")
    public byte[] download(@PathVariable int id){
        System.out.println("Hit0");
        return imageService.getById(id);
    }
}
