package com.example.obspringejercicios456.controller;

import com.example.obspringejercicios456.model.Laptop;
import com.example.obspringejercicios456.repository.ILaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    @Autowired
    private ILaptopRepository laptopService;
    final private Logger log = LoggerFactory.getLogger(LaptopController.class);

    @GetMapping("api/laptops")
    public ResponseEntity<List<Laptop>> getAll(){
        try{
            List<Laptop> laptopList = laptopService.findAll();
            return ResponseEntity.ok(laptopList);
        }catch(Error err){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        if(laptopService.existsById(id)){
            Optional<Laptop> laptop = laptopService.findById(id);
            return ResponseEntity.ok(laptop.get());
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping("api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        try{
            Laptop laptopSaved = laptopService.save(laptop);
            return ResponseEntity.ok(laptopSaved);
        }catch (Error err) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(!laptopService.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopService.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("api/laptops/{id}")
    public ResponseEntity<Laptop> deleteOne(@PathVariable Long id){
        if(!laptopService.existsById(id)){
            log.error("Trying to delete an inexistent laptop");
            return ResponseEntity.notFound().build();
        }
        try{
            laptopService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (Error err){
            log.error(err.toString());
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping("api/laptops")
    public void deleteAll(){
        try {
            laptopService.deleteAll();
        }catch (Error err){
            log.error(err.toString());
        }
    }
}
