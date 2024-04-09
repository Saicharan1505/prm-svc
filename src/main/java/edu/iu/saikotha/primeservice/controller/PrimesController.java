package edu.iu.saikotha.primeservice.controller;

import com.nimbusds.jose.proc.SecurityContext;
import edu.iu.saikotha.primeservice.service.IPrimesService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import edu.iu.saikotha.primeservice.rabbitmq.MQSender;


@RestController
@CrossOrigin
@RequestMapping("/primes")

public class PrimesController {

    IPrimesService primesService;

    private final MQSender mqSender;

    public PrimesController(IPrimesService primesService, MQSender mqSender)
    {
        this.primesService = primesService;
        this.mqSender = mqSender;
    }

    @GetMapping("/{n}")
    public boolean isPrimeNumber(@PathVariable int n){
        boolean result = primesService.isPrime(n);
        Object principal = SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = ((Jwt) principal).getSubject();
        System.out.println(username);
        mqSender.sendMessage(username,n,result);
        return result;

    }


}
