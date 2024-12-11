package tis3.tis3Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tis3.tis3Back.model.Mechanic;
import tis3.tis3Back.service.MechanicService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/mechanic")
public class MechanicController {
    @Autowired
    private MechanicService service;

    @GetMapping
    public ResponseEntity<List<Mechanic>> getAllMechanics() {
        List<Mechanic> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Mechanic> findById(@PathVariable Long id) {
        Mechanic mechanic = service.findById(id);
        return ResponseEntity.ok().body(mechanic);
    }

    @GetMapping(value = "/cpf")
    public ResponseEntity<Mechanic> findByCpf(@RequestParam(value = "cpf") String cpf){
        Mechanic mechanic = service.findByCpf(cpf);
        return ResponseEntity.ok().body(mechanic);
    }

    @PostMapping
    public ResponseEntity<Mechanic> insert(@RequestBody Mechanic obj) {
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
    public ResponseEntity<Mechanic> update(@PathVariable Long id, @RequestBody Mechanic obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
