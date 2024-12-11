package tis3.tis3Back.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tis3.tis3Back.model.Car;
import tis3.tis3Back.repository.CarRepository;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class CarService {

    @Autowired
    private final CarRepository carRepository;


    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long id) {
        Optional<Car> obj = carRepository.findById(id);
        return obj.orElse(null);
    }

    public Car insert(Car car) {
        return carRepository.save(car);
    }

    public Car update(Long id, Car obj) {
        Car entity = carRepository.getReferenceById(id);
        updateData(entity, obj);
        return carRepository.save(entity);
    }

    private void updateData(Car entity, Car obj) {
        entity.setName(obj.getName());
        entity.setCarYear(obj.getCarYear());
        entity.setDescription(obj.getDescription());
        entity.setColor(obj.getColor());
        entity.setOwnerName(obj.getOwnerName());
        entity.setOwnerNumber(obj.getOwnerNumber());
        entity.setOwnerEmail(obj.getOwnerEmail());
        entity.setPlate(obj.getPlate());

    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
