package bri4ka.controller;

import bri4ka.exceptions.BadRequestException;
import bri4ka.model.dto.user.EditUserDTO;
import bri4ka.model.dto.user.LoginUserDTO;
import bri4ka.model.dto.user.RegisterRequestUserDTO;
import bri4ka.model.dto.user.ResponseUserDTO;
import bri4ka.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController extends AbstractController{

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public UserController(UserService userService, SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    @PutMapping("/users")
    public ResponseUserDTO register(@RequestBody RegisterRequestUserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PostMapping("/users")
    public ResponseUserDTO login(@RequestBody LoginUserDTO dto, HttpSession session){
        String username = dto.getUsername();
        if(sessionManager.isUserLoggedIn(session)){
            throw new BadRequestException("Already logged in.");
        }
        ResponseUserDTO responseUserDTO = userService.login(dto);
        sessionManager.userLogsIn(session, userService.findByUsername(username).getId());
        return responseUserDTO;
    }

    @PostMapping("/logout")
    public void logout(HttpSession session){
        sessionManager.userLogsOut(session);
    }

    @PutMapping("/users/{user_id}/cars/{car_id}")
    public ResponseUserDTO buyCar(@PathVariable(name = "user_id") int userId,
                                  @PathVariable(name = "car_id") int carId,
                                  HttpSession session){
        sessionManager.authorizeLogin(session, userId, "you cannot purchase a car on behalf of another user.");

        return userService.buyCar(userId, carId);
    }

    @GetMapping("/users/{id}")
    public ResponseUserDTO getById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<ResponseUserDTO> getAll (){
        return userService.getAllUsers();
    }

    @PostMapping("/users/edit")
    public ResponseUserDTO edit(@RequestBody EditUserDTO dto, HttpSession session){
        int id = sessionManager.authorizeLogin(session);
        return userService.editUser(id, dto);
    }

}
