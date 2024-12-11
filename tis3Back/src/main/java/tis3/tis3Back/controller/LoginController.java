package tis3.tis3Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tis3.tis3Back.model.Dto.LoginDTO;
import tis3.tis3Back.model.Login;
import tis3.tis3Back.service.LoginService;

import java.net.URI;
import java.util.Map;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/create")
    public ResponseEntity<Login> insert(@RequestBody Login obj) {
        Login createdLogin = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(createdLogin.getEmail())
                .toUri();
        return ResponseEntity.created(uri).body(createdLogin);
    }


    @PostMapping("/auth")
    public ResponseEntity<LoginDTO> login(@RequestBody Login obj) {
        LoginDTO authenticatedUser = service.login(obj.getEmail(), obj.getPassword());
        return ResponseEntity.ok(authenticatedUser);
    }


    @PostMapping("/update-password")
    public ResponseEntity<Login> updatePassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");
        Login updatedLogin = service.updatePassword(email, newPassword);
        return ResponseEntity.ok(updatedLogin);
    }

}
