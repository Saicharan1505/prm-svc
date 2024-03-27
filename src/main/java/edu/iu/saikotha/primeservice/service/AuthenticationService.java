package edu.iu.saikotha.primeservice.service;

import edu.iu.saikotha.primeservice.model.Customer;
import edu.iu.saikotha.primeservice.repository.AuthenticationFileRepository;
import edu.iu.saikotha.primeservice.repository.IAuthenticationRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {

    IAuthenticationRepository authenticationRepository;

    public AuthenticationService(IAuthenticationRepository authenticationRepository){
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public boolean login(String username, String password) {
        // Implementation logic here
        return false; // Placeholder return value
    }

    @Override
    public boolean register(Customer customer) throws IOException{
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String passwordEncoded = bc.encode(customer.getPassword());
        customer.setPassword(passwordEncoded);
        return authenticationRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Customer customer = authenticationRepository.findByUsername(username);
            if(customer==null){
                throw new UsernameNotFoundException("");
            }
            return User
                    .withUsername(username)
                    .password(customer.getPassword())
                    .build();

        }
        catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}
