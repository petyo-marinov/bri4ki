package bri4ka.controller;

import bri4ka.model.dto.user.LoginUserDTO;
import bri4ka.model.dto.user.RegisterRequestUserDTO;
import bri4ka.model.dto.user.ResponseUserDTO;
import bri4ka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends AbstractController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/users")
    public ResponseUserDTO register(@RequestBody RegisterRequestUserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PostMapping("/users")
    public ResponseUserDTO login(@RequestBody LoginUserDTO dto){
        ResponseUserDTO responseUserDTO = userService.login(dto);
        //sava to session
        return responseUserDTO;
    }

    @GetMapping("/users/{id}")
    public ResponseUserDTO getById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<ResponseUserDTO> getAll (){
        return userService.getAllUsers();
    }

}
