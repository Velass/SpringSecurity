package fr.diginamic.miniprojet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.diginamic.miniprojet.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import fr.diginamic.miniprojet.model.User;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(@Valid User user) {
        if (user.getId() != 0) {
            throw new EntityNotFoundException("présence d’un ID ");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User updateUser(@Valid User user, Long id) {
        boolean idUser = userRepository.existsById(id);
        if (idUser != true) {
            throw new EntityNotFoundException("cette user n'existe pas");
        }
        return userRepository.save(user);
    }

    public User deleteUser(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID : " + id + " introuvable"));
        if (userToDelete != null) {
            userRepository.deleteById(id);
        }
        return userToDelete;
    }

    public User authenticateUser(String email, String motDePasse) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            String encodedPassword = user.getPassword();
            // Comparer le mot de passe fourni avec le mot de passe stocké après le décodage
            if (passwordEncoder.matches(motDePasse, encodedPassword)) {
                return user;
            }
        }
        return null;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
