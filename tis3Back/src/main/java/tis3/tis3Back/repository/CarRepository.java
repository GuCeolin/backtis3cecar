package tis3.tis3Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tis3.tis3Back.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
