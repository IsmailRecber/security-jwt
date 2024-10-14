package com.jwtexample.jwt.security;

import com.jwtexample.jwt.model.User;
import com.jwtexample.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Optional kullanarak kullanıcıyı bulma
        Optional<User> userOptional = userRepository.findByUsername(username);

        // Eğer kullanıcı bulunamazsa exception fırlatılır
        User user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("Kullanıcı bulunamadı: " + username)
        );

        // UserDetails objesini döndürür
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
