package edu.poly.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.poly.lab4.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.poly.lab4.entity.Shoe;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/getShoes")
public class ShoeController {
    @Autowired
    private ShoeService shoeService;

    @GetMapping
    public List<Shoe> getAllShoes() {
        return shoeService.getAllShoes();
    }
@GetMapping("/{id}")
public Shoe getShoeById(@org.springframework.web.bind.annotation.PathVariable int id) {
    return shoeService.getShoeById(id);
}}
