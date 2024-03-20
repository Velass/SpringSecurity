package fr.diginamic.miniprojet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.miniprojet.dto.UserDTO;
import fr.diginamic.miniprojet.model.User;
import fr.diginamic.miniprojet.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody @Valid User user, @PathVariable("id")Long id){
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable("id")Long id){
       return userService.deleteUser(id);
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody UserDTO userLoginDTO) {
        // Authentification
        User user = userService.authenticateUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        if (user != null) {
            // L'authentification a réussi, vous pouvez retourner des informations utilisateur ou un token JWT ici
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de l'authentification");
        }
    }



}
