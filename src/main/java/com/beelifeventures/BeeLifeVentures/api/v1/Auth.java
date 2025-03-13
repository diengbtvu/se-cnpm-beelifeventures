package com.beelifeventures.BeeLifeVentures.api.v1;

import com.beelifeventures.BeeLifeVentures.model.dto.UserAccountDTO;
import com.beelifeventures.BeeLifeVentures.repository.UserAccountRepository;
import com.beelifeventures.BeeLifeVentures.repository.entity.UserAccountEntity;
import com.beelifeventures.BeeLifeVentures.security.JwtUtil;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class Auth {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    @Qualifier("customPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccountDTO userAccountDTO) {
        UserAccountEntity user = new UserAccountEntity();
        modelMapper.map(userAccountDTO, user);


        if (userAccountRepository.findByUserName(user.getUserName()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userAccountRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccountEntity user) {
        Optional<UserAccountEntity> userAccount = userAccountRepository.findByUserName(user.getUserName());
        if (userAccount.isPresent() && passwordEncoder.matches(user.getPassword(), userAccount.get().getPassword())) {
            String token = jwtUtil.generateToken(user.getUserName());
            return ResponseEntity.ok("Bearer: " + token);
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}