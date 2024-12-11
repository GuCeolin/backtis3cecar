package tis3.tis3Back.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tis3.tis3Back.model.Mechanic;
import tis3.tis3Back.repository.MechanicRepository;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class MechanicService {

    @Autowired
    private final MechanicRepository mechanicRepository;


    public List<Mechanic> findAll() {
        return mechanicRepository.findAll();
    }

    public Mechanic findById(Long id) {
        Optional<Mechanic> mechanic = mechanicRepository.findById(id);
        return mechanic.orElse(null);
    }

    public Mechanic findByCpf(String cpf) {
        Optional<Mechanic> mechanic = mechanicRepository.findByCpf(cpf);
        return mechanic.orElse(null);
    }

    public Mechanic insert(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public Mechanic update(Long id, Mechanic obj) {
        Mechanic entity = mechanicRepository.getReferenceById(id);
        updateData(entity, obj);
        return mechanicRepository.save(entity);
    }

    private void updateData(Mechanic entity, Mechanic obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setCpf(obj.getCpf());
    }

    public void delete(Long id) {
        mechanicRepository.deleteById(id);
    }
}
