package edu.iu.saikotha.primeservice.controller;

import edu.iu.saikotha.primeservice.service.IPrimesService;

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
    public boolean isPrimeNumber(@PathVariable("n") int n){
        boolean result = primesService.isPrime(n);
        mqSender.sendMessage(n,result);
        return result;

    }


}
