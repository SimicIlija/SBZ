package drools.spring.example.service;

import drools.spring.example.controller.exception.AuthorizationException;
import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.User;
import drools.spring.example.model.dto.LoginDto;
import drools.spring.example.model.dto.UserDto;
import drools.spring.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new AuthorizationException("Ne postoji korisnik sa takvim emailom."));
        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new AuthorizationException("Pogresna sifra");
        }
        return user;
    }

    public void setCurrentUser(User user) {
        final Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
        final Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getId(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User getCurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            return userRepository.findById((Long) auth.getPrincipal())
                    .orElseThrow(() -> new NotFoundException("User not found!"));
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createNewUser(UserDto user) {
        if (user.getPassword() == null || user.getEmail() == null || user.getUserRole() == null) {
            throw new BadRequestException("Empty fields");
        }
        if (user.getPassword().trim().equals("") || user.getEmail().trim().equals("")) {
            throw new BadRequestException("Empty fields");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setUserRole(user.getUserRole());
        return userRepository.save(user1);
    }

    public User updateUser(Long id, UserDto userDto) {
        if (userDto.getPassword() == null || userDto.getEmail() == null || userDto.getUserRole() == null) {
            throw new BadRequestException("Empty fields");
        }
        if (userDto.getPassword().trim().equals("") || userDto.getEmail().trim().equals("")) {
            throw new BadRequestException("Empty fields");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        User checkUser = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
            throw new BadRequestException("Email already taken");
        }
        user.setUserRole(userDto.getUserRole());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }
}
