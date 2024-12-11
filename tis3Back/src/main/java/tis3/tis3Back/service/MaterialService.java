package tis3.tis3Back.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tis3.tis3Back.model.Material;
import tis3.tis3Back.repository.MaterialRepository;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class MaterialService {

    @Autowired
    private final MaterialRepository materialRepository;


    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material findById(Long id) {
        Optional<Material> obj = materialRepository.findById(id);
        return obj.orElse(null);
    }

    public Material insert(Material material) {
        return materialRepository.save(material);
    }

    public Material update(Long id, Material obj) {
        Material entity = materialRepository.getReferenceById(id);
        updateData(entity, obj);
        return materialRepository.save(entity);
    }

        private void updateData(Material entity, Material obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
    }

    public void delete(Long id) {
        materialRepository.deleteById(id);
    }
}
