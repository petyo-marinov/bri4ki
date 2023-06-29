package bri4ka.service;

import bri4ka.exceptions.InvalidCredentialsException;
import bri4ka.exceptions.NotAcceptableException;
import bri4ka.exceptions.NotFoundException;
import bri4ka.model.dto.user.LoginUserDTO;
import bri4ka.model.dto.user.RegisterRequestUserDTO;
import bri4ka.model.dto.user.ResponseUserDTO;
import bri4ka.model.pojo.User;
import bri4ka.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseUserDTO addUser(RegisterRequestUserDTO userDTO){
        userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(u -> {
                    throw new NotAcceptableException("account with this email already exists.");
                });
        userRepository.findByUsername(userDTO.getUsername())
                .ifPresent(u -> {
                    throw new NotAcceptableException("account with this username already exists.");
                });

        userDTO.setPassword(passwordEncoder
                .encode(userDTO.getPassword()));
        User user = new User(userDTO);
        user = userRepository.save(user);

        return new ResponseUserDTO(user);
    }

    public ResponseUserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found."));
        return new ResponseUserDTO(user);
    }

    public List<ResponseUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseUserDTO> returnUsers = new ArrayList<>();
        for(User u : users){
            returnUsers.add(new ResponseUserDTO(u));
        }
        return returnUsers;
    }

    public ResponseUserDTO login(LoginUserDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("The username or password is incorrect."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("The username or password is incorrect.");
        }
        return new ResponseUserDTO(user);
    }

}
