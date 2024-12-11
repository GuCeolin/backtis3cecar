package tis3.tis3Back.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tis3.tis3Back.model.Dto.LoginDTO;
import tis3.tis3Back.model.Login;
import tis3.tis3Back.repository.LoginRepository;


@Data
@Service
public class LoginService {

    @Autowired
    private final LoginRepository loginRepository;


    public Login insert(Login login) {
        return loginRepository.save(login);
    }


    public LoginDTO login(String email, String password) {
        Login login = loginRepository.findByEmail(email);
        if (login != null && login.getPassword().equals(password)) {
            return new LoginDTO(login.isAdmin());
        } else {
            throw new IllegalArgumentException("Credenciais inválidas");
        }
    }


    public Login updatePassword(String email, String newPassword) {
        Login login = loginRepository.findByEmail(email);
        if (login != null) {
            login.setPassword(newPassword);
            return loginRepository.save(login);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }

}
