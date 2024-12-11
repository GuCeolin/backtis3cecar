package tis3.tis3Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tis3.tis3Back.model.Car;
import tis3.tis3Back.service.CarService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/car")
public class CarController {
    @Autowired
    private CarService service;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        Car obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Car> insert(@RequestBody Car obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Car> update(@PathVariable Long id, @RequestBody Car obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
