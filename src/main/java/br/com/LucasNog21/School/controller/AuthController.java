package br.com.LucasNog21.School.controller;

import br.com.LucasNog21.School.dto.security.AccountCredentialsDTO;
import br.com.LucasNog21.School.dto.security.TokenDTO;
import br.com.LucasNog21.School.service.AuthServices;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Endpoint de autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices service;

    @Operation(summary= "Autentica um usuário e retorna um token")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(AccountCredentialsDTO credentials) {
        if (credentialsIsInvalid(credentials)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        var token = service.signIn(credentials);
        if (token == null) ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return ResponseEntity.ok().body(token);
    }

    private static boolean credentialsIsInvalid(AccountCredentialsDTO credentials) {
        return credentials == null ||
                StringUtils.isBlank(credentials.getPassword()) ||
                StringUtils.isBlank(credentials.getUsername());
    }
}
