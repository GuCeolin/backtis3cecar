package tis3.tis3Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tis3.tis3Back.model.Material;
import tis3.tis3Back.service.MaterialService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/material")
public class MaterialController {
    @Autowired
    private MaterialService service;

    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Material> findById(@PathVariable Long id) {
        Material obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Material> insert(@RequestBody Material obj) {
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
    public ResponseEntity<Material> update(@PathVariable Long id, @RequestBody Material obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
