package com.example.obspringejercicios456.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/saludar")
    public String saludo(){
        return "Hola mundo";
    }
}
