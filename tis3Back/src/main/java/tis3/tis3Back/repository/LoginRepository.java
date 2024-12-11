package tis3.tis3Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tis3.tis3Back.model.Login;


@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByEmail(String email);
}
