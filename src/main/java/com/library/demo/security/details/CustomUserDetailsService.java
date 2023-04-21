package com.library.demo.security.details;


import com.library.demo.repository.AdminRepository;
import com.library.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    public final UserRepository userRepository;
    public final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userRepository.findByUsername(username)!=null) return (UserDetails) userRepository.findByUsername(username);
        else if (adminRepository.findByUsername(username)!=null) return adminRepository.findByUsername(username);
        else throw new UsernameNotFoundException("No user found with username:"+ username);
    }
}