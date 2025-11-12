package br.com.LucasNog21.School.dto.security;

import jdk.jfr.DataAmount;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AccountCredentialsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String fullname;

}