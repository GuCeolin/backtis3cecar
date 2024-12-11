package tis3.tis3Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tis3.tis3Back.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
