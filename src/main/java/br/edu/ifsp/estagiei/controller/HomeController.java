package br.edu.ifsp.estagiei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HomeController {

    @GetMapping("/")
    String home() {
        return "Hello, EstagiEI!";
    }

}