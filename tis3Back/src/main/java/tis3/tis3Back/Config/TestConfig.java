package tis3.tis3Back.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import tis3.tis3Back.model.Login;
import tis3.tis3Back.repository.LoginRepository;
import tis3.tis3Back.service.LoginService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    LoginRepository loginRepository;

    @Override
    public void run(String... args){
         Login Admin = new Login("Admin@gmail", "1234", true);
         Login mechanic = new Login("mecanico@gmail", "4321", false);
        loginRepository.save(Admin);
        loginRepository.save(mechanic);
    }
}
