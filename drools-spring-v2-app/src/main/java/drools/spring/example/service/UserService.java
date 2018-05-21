package drools.spring.example.service;

import drools.spring.example.controller.exception.AuthorizationException;
import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.User;
import drools.spring.example.model.dto.LoginDto;
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

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new AuthorizationException("No email found"));
        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new AuthorizationException("Bad password");
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

}
