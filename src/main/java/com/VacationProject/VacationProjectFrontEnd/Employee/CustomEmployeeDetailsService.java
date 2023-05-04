package com.VacationProject.VacationProjectFrontEnd.User;

import com.VacationProject.VacationProjectFrontEnd.Persistance.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepository userRepository;

    public CustomUserDetailsService(EmployeeRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("No user found with the given " +
                "username: " + username));
        return new EmployeeDetailsMapper(user);
    }
}
