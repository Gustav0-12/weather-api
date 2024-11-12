package api_clima.controller;

import api_clima.dto.LoginRequestDTO;
import api_clima.dto.LoginResponseDTO;
import api_clima.dto.RegisterRequestDTO;
import api_clima.dto.UserResponseDTO;
import api_clima.entities.User;
import api_clima.repository.UserRepository;
import api_clima.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        User user = userRepository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(data.password(), user.getPassword())) {
            String token = tokenProvider.generateToken(user);

            return ResponseEntity.ok().body(new LoginResponseDTO(token, tokenProvider.expirationTime()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO data) {
        Optional<User> user = userRepository.findByEmail(data.email());

        if (!user.isEmpty()) {
            throw new RuntimeException("Email já registrado");
        }

        User newUser = new User();
        newUser.setUsername(data.username());
        newUser.setEmail(data.email());
        newUser.setPassword(passwordEncoder.encode(data.password()));
        newUser.setUserRole(data.userRole());
        userRepository.save(newUser);
        return ResponseEntity.ok().body(new UserResponseDTO(newUser.getUsername(), newUser.getEmail(), newUser.getUserRole()));
    }
}
