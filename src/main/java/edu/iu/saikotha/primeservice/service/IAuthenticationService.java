package edu.iu.saikotha.primeservice.service;

import edu.iu.saikotha.primeservice.model.Customer;

import java.io.IOException;

public interface IAuthenticationService {

    Customer register(Customer customer) throws IOException;

    boolean login(String username, String password) throws IOException;
}
