package fr.diginamic.miniprojet.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.miniprojet.config.JwtConfig;
import fr.diginamic.miniprojet.dto.UserDTO;
import fr.diginamic.miniprojet.model.User;
import fr.diginamic.miniprojet.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api/user/login")
public class SessionController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtConfig jwtConfig;

    public SessionController(JwtConfig jwtConfig, UserService userService,
        PasswordEncoder passwordEncoder) {
        this.jwtConfig = jwtConfig;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserDTO userDto) {
        Optional<User> userOptional = this.userService.findByEmail(userDto.getEmail());
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, buildJWTCookie(user)).build();
            }
        }
    
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    

    private String buildJWTCookie(User user) {
        Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String jetonJWT = Jwts.builder()
                .setSubject(user.getEmail())
                .addClaims(Map.of("role", user.getRole()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireIn() * 1000))
                .signWith(
                        jwtConfig.getSecretKey())
                .compact();
        ResponseCookie tokenCookie = ResponseCookie.from(jwtConfig.getCookie(),
                jetonJWT)
                .httpOnly(true)
                .maxAge(jwtConfig.getExpireIn() * 1000)
                .path("/")
                .build();
        return tokenCookie.toString();
    }
}
