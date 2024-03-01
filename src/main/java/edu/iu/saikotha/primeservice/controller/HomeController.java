package edu.iu.saikotha.primeservice.controller;

import edu.iu.saikotha.primeservice.service.IPrimesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

public class HomeController {

    IPrimesService primesService;

    public HomeController(IPrimesService primesService)
    {
        this.primesService = primesService;
    }

    @GetMapping
    public String greetings(){
        return "welcome to the primes servicess!";
    }

    @GetMapping("prime/{n}")
    public boolean isPrime(@PathVariable int n){
        return primesService.isPrime(n);
    }


}
