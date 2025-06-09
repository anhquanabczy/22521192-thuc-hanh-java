package edu.poly.lab4.service;

import org.springframework.stereotype.Service;
import edu.poly.lab4.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import edu.poly.lab4.entity.Shoe;
import java.util.List;

@Service
public class ShoeService {
    @Autowired
    private ShoeRepository shoeRepository;
    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public Shoe getShoeById(int id) {
        return shoeRepository.findById((long) id).orElse(null);
    }
}
