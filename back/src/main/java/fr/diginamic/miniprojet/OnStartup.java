package fr.diginamic.miniprojet;



import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.diginamic.miniprojet.model.User;
import fr.diginamic.miniprojet.repository.UserRepository;



@Component
public class OnStartup {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public OnStartup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder ;
    }

    // @EventListener(ContextRefreshedEvent.class)
    // public void init() {
    //     // insertion de 2 utilisateurs en base de données
    //     userRepository.save(new User("test1", "test1", "test1@test.fr",passwordEncoder.encode("pass1"),"utilisateur" ));

    // }
}
