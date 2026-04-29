package com.turkcell.spring_starter.service;

import com.turkcell.spring_starter.exception.InvalidCredentialsException;
import com.turkcell.spring_starter.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.LoginRequest;
import com.turkcell.spring_starter.dto.RegisterRequest;
import com.turkcell.spring_starter.entity.User;
import com.turkcell.spring_starter.repository.UserRepository;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest registerRequest) {

        User userWithSameEmail = userRepository.findByEmail(registerRequest.getEmail())
                .orElse(null);
        if (userWithSameEmail != null) {
            throw new UserAlreadyExistsException("Bu e-posta zaten kayıtlı.");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());

        String encodedPassword = this.passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

    }

    public String login(LoginRequest loginRequest) {
        String errorMessage = "Giriş bilgileri yanlış";

        User user = this.userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException(errorMessage));

        // Kullanıcı var gibi davran
        boolean passwordMatch = this.passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatch)
            throw new InvalidCredentialsException(errorMessage);

        // Bu e-posta ile bir kayıt var.

        return "Giriş başarılı";
    }
}