package edu.iu.saikotha.primeservice.model;

import org.springframework.security.config.Customizer;

public class Customer {

    private String username;

    private String password;

    public Customer(String username, String password){
        this.username = username;
        this.password = password;

    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }


}