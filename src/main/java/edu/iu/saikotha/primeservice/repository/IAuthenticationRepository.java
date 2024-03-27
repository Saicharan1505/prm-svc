package edu.iu.saikotha.primeservice.repository;

import edu.iu.saikotha.primeservice.model.Customer;

import java.io.IOException;

public interface IAuthenticationRepository {

    boolean save(Customer customer) throws IOException;

    Customer findByUsername(String username) throws IOException;
}
