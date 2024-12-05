package io.github.junrdev.booker.sec;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.crypto.password.NoOpPasswordEncoder.*;

@RestController
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        //using no opp
        return getInstance();
    }



    @Bean
    public UserDetailsService userDetailsService(){
        //automatically configures password encoder when using defaults
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withUsername("admin")
                .password("2022")
                .authorities("read")
                .build();

        userDetailsManager. createUser(userDetails);
        return userDetailsManager;
    }

    @GetMapping("/hello")
    private String hello(){
        return "Hello!";
    }


}
